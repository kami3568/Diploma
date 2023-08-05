package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Data
public class PaymentForm {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvcField = $("[placeholder='999'");
    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement loadingSpinOnContinueButton = $(".spin");
    private final SelenideElement wrongFormatNotification = $(".input__sub");

    public void moveWhichContinueButton() {
        continueButton.click();
    }

    public void buyWithCardInfo(String cardStatus, String requiredLocale, String monthStatus, String yearStatus, String holderStatus, String cvcStatus) {
        cardNumberField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getNumber());
        monthField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getMonth());
        yearField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getYear());
        holderField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getCardHolder());
        cvcField.setValue(DataHelper.getCardInfo(cardStatus, requiredLocale, monthStatus, yearStatus, holderStatus, cvcStatus)
                .getCvc());
        moveWhichContinueButton();
    }

}