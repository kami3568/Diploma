package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.pages.PaymentForm;
import ru.netology.pages.SalesPage;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("'CVC' field testing")
public class CVCFieldTest {
    PaymentForm paymentForm = new PaymentForm();
    SalesPage salesPage = new SalesPage();

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("Empty field notification")
    @Test
    void emptyCVCNumberField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "future",
                "future",
                "goodName",
                "empty"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

    @DisplayName("Notification if 'Triple Zero'")
    @Test
    void tripleZeroInCVCNumberField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "future",
                "future",
                "goodName",
                "tripleZero"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

    @DisplayName("Notification if 'Incomplete code'")
    @Test
    void shortNumberInCVCNumberField() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "future",
                "future",
                "goodName",
                "short"
        );
        paymentForm.getWrongFormatNotification().shouldBe(Condition.visible);
    }

}