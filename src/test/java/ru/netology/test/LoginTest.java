package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSuccessfulLogin() throws SQLException {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin();
        val dashboardPage = verificationPage.validVerify();
        dashboardPage.dashboardPage();
    }

    @Test
    public void shouldGetErrorIfInvalidLogin() {
        val loginPage = new LoginPage();
        loginPage.invalidLogin();
        loginPage.getErrorIfInvalidUser();
    }

    @Test
    public void shouldGetErrorIfAttemptsExceeded() {
        val loginPage = new LoginPage();
        int i;
        for (i = 0; i < 3; i++) {
            loginPage.invalidLogin();
            loginPage.clearLoginFields();
        }
        loginPage.getErrorIfAttemptsExceeded();
    }

    @Test
    public void shouldGetErrorIfInvalidVerificationCode() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin();
        verificationPage.invalidVerify();
        verificationPage.getErrorIfInvalidVerify();
    }
}
