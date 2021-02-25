package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class LoginTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");

    }

    @Test
    public void shouldSuccessfulLogin() throws SQLException {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.Login(getValidLogin());
        val dashboardPage = verificationPage.validVerify(getValidVerificationCode());
        dashboardPage.dashboardPage();
    }
}
