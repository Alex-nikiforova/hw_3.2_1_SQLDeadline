package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class LoginTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSuccessfulLogin() {
        val loginPage = new LoginPage();
        loginPage.login(getValidLogin());
        val verificationPage = loginPage.goToVerificationPage();
        verificationPage.verify(getValidVerificationCode());
        val dashboardPage = verificationPage.goToDashboardPage();
        dashboardPage.dashboardPage();
    }

    @Test
    public void shouldGetErrorIfInvalidLogin() {
        val loginPage = new LoginPage();
        loginPage.login(getInvalidLogin());
        loginPage.getErrorIfInvalidUser();
    }

    @Test
    public void shouldGetErrorIfAttemptsExceeded() {
        val loginPage = new LoginPage();

        loginPage.login(getInvalidLogin());
        loginPage.clearLoginFields();
        loginPage.login(getInvalidLogin());
        loginPage.clearLoginFields();
        loginPage.login(getInvalidLogin());

        loginPage.getErrorIfAttemptsExceeded();
    }

    @Test
    public void shouldGetErrorIfInvalidVerificationCode() {
        val loginPage = new LoginPage();
        loginPage.login(getValidLogin());
        val verificationPage = loginPage.goToVerificationPage();
        verificationPage.verify(getInvalidVerificationCode());
        verificationPage.getErrorIfInvalidVerify();
    }
}
