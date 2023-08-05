package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.pages.PaymentForm;
import ru.netology.pages.SalesPage;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("'Card Number' field testing")
public class CardNumberFieldTest {
    PaymentForm paymentForm = new PaymentForm();
    SalesPage salesPage = new SalesPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @DisplayName("Empty field notification")
    @Test
    void emptyCardNumberField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "empty",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

    @DisplayName("Notification if 'Incomplete card number'")
    @Test
    void shortNumberInCardNumberField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "short",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

    @DisplayName("Notification if 'One digit in field'")
    @Test
    void oneNumberInCardNumberField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "one",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }
}