package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage Login(DataHelper.AuthInfo login) {
        loginField.setValue(login.getLogin());
        passwordField.setValue(login.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void getErrorIfInvalidUser() {
        $(byText("Ошибка! Неверно указан логин или пароль")).shouldBe(visible);
    }

    public void clearLoginFields() {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    public void getErrorIfAttemptsExceeded() {
        $(byText("Ошибка! Превышено количество попыток ввода кода.")).shouldBe(visible);
    }
}
