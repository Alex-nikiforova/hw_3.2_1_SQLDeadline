package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getValidLogin() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidLogin() {
        Faker faker = new Faker();
        return new AuthInfo("vasya", faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getValidVerificationCode() throws SQLException {
        String codeSQL = "SELECT code FROM auth_codes WHERE id = (SELECT max(id) FROM auth_codes)";
        val runner = new QueryRunner();

        try (
                val connection = DriverManager.getConnection(
                        "jdbc:mysql://192.168.99.100:3306/db", "alex", "E7PgwDjz"
                );
        ) {
            String code = runner.query(connection, codeSQL, new ScalarHandler<>());
            return new VerificationCode(code);
        }
    }

    public static VerificationCode getInvalidVerificationCode() {
        return new VerificationCode("123456");
    }
}
