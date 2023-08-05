package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.pages.PaymentForm;
import ru.netology.pages.SalesPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Functional testing")
public class FunctionalTest {
    SalesPage salesPage = new SalesPage();
    PaymentForm paymentForm = new PaymentForm();

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

    @DisplayName("Debit buying by APPROVED-card")
    @Test
    void debitBuyingByApprovedCard() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        salesPage.getSuccessNotification().shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @DisplayName("Debit buying by DECLINED-card")
    @Test
    void debitBuyingByDeclinedCard() {
        salesPage.buyByDebit().buyWithCardInfo(
                "DECLINED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        salesPage.getErrorNotification().shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @DisplayName("Credit buying by APPROVED-card")
    @Test
    void creditBuyingByApprovedCard() {
        salesPage.buyByCredit().buyWithCardInfo(
                "APPROVED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        salesPage.getSuccessNotification().shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @DisplayName("Credit buying by DECLINED-card")
    @Test
    void creditBuyingByDeclinedCard() {
        salesPage.buyByCredit().buyWithCardInfo(
                "DECLINED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        salesPage.getErrorNotification().shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @DisplayName("Debit buying by Random-card")
    @Test
    void debitBuyingByAnotherCard() {
        salesPage.buyByDebit().buyWithCardInfo(
                "random",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        salesPage.getErrorNotification().shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @DisplayName("Credit buying by Random-card")
    @Test
    void creditBuyingByAnotherCard() {
        salesPage.buyByCredit().buyWithCardInfo(
                "random",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        salesPage.getErrorNotification().shouldBe(Condition.visible, Duration.ofMillis(15000));
    }

    @DisplayName("Empty form sending")
    @Test
    void continueWithEmptyFields() {
        salesPage.buyByDebit().moveWhichContinueButton();
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

}