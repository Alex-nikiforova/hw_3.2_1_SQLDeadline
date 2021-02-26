package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataHelper.getInvalidVerificationCode;
import static ru.netology.data.DataHelper.getValidVerificationCode;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify() throws SQLException {
        codeField.setValue(getValidVerificationCode().getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public void invalidVerify() {
        codeField.setValue(getInvalidVerificationCode().getCode());
        verifyButton.click();
    }

    public void getErrorIfInvalidVerify() {
        $(byText("Ошибка")).shouldBe(visible);
        $(byText("Неверно указан код! Попробуйте ещё раз.")).shouldBe(visible);
    }
}