package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.VerificationCode;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(VerificationCode code) {
        codeField.setValue(code.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public void getErrorIfInvalidVerify(VerificationCode verificationCode) {
        $(byText("Ошибка! Неверно указан код! Попробуйте ещё раз.")).shouldBe(visible);
    }
}