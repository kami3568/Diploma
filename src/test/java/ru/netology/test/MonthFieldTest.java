package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.pages.PaymentForm;
import ru.netology.pages.SalesPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("'Month' field testing")
public class MonthFieldTest {
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

    @DisplayName("Current date in month and year fields")
    @Test
    void currentDateInMonthAndYearFields() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "current",
                "current",
                "goodName",
                "random"
        );
        salesPage.getSuccessNotification().shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @DisplayName("Future month and current year")
    @Test
    void futureDateInMonthFieldAndCurrentDateInYearField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "future",
                "current",
                "goodName",
                "random"
        );
        salesPage.getSuccessNotification().shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @DisplayName("Past month and current year")
    @Test
    void pastDateInMonthFieldAndCurrentDateInYearField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "past",
                "current",
                "goodName",
                "random"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

    @DisplayName("Empty field notification")
    @Test
    void emptyMonthField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "empty",
                "current",
                "goodName",
                "random"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

    @DisplayName("Notification if 'Zero'")
    @Test
    void zeroDigitInMonthField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "zero",
                "current",
                "goodName",
                "random"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

    @DisplayName("Notification if 'Double Zero'")
    @Test
    void doubleZeroDigitInMonthField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "doubleZero",
                "current",
                "goodName",
                "random"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

    @DisplayName("Notification if 'Incorrect month number: 13-99' ")
    @Test
    void badRandomDigitInMonthField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "badRandom",
                "current",
                "goodName",
                "random"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

}