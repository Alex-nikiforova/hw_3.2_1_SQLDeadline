package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataHelper.getInvalidLogin;
import static ru.netology.data.DataHelper.getValidLogin;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin() {
        loginField.setValue(getValidLogin().getLogin());
        passwordField.setValue(getValidLogin().getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin() {
        loginField.setValue(getInvalidLogin().getLogin());
        passwordField.setValue(getInvalidLogin().getPassword());
        loginButton.click();
    }

    public void getErrorIfInvalidUser() {
        $(byText("Ошибка")).shouldBe(visible);
        $(byText("Неверно указан логин или пароль")).shouldBe(visible);
    }

    public void clearLoginFields() {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    public void getErrorIfAttemptsExceeded() {
        $(byText("Ошибка")).shouldBe(visible);
        $(byText("Превышено количество попыток ввода кода.")).shouldBe(visible);
    }
}
