package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Data
public class SalesPage {
    private final SelenideElement body = $("body");
    private final SelenideElement headTitle = $("title");
    private final SelenideElement debitBuyingButton = $(byText("Купить"));
    private final SelenideElement creditBuyingButton = $(byText("Купить в кредит"));
    private final SelenideElement successNotification = $(".notification_status_ok ");
    private final SelenideElement errorNotification = $(".notification_status_error");

    public PaymentForm buyByDebit() {
        debitBuyingButton.click();
        return new PaymentForm();
    }

    public PaymentForm buyByCredit() {
        creditBuyingButton.click();
        return new PaymentForm();
    }
}

