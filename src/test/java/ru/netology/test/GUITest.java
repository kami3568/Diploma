package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.pages.PaymentForm;
import ru.netology.pages.SalesPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("GUI testing")
public class GUITest {
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

    @DisplayName("Page Head title checking")
    @Test
    void headTitleTest() {
        salesPage.getHeadTitle().shouldHave(attribute("text", "Путешествие дня"));
    }

    @DisplayName("Page Debit title checking")
    @Test
    void debitHeadingTitleTest() {
        salesPage.getDebitBuyingButton().click();
        salesPage.getBody().shouldHave(text("Оплата по карте"));
    }

    @DisplayName("Page Credit title checking")
    @Test
    void creditHeadTitleTest() {
        salesPage.getCreditBuyingButton().click();
        salesPage.getBody().shouldHave(text("Кредит по данным карты"));
    }

    @DisplayName("Sending Data Visualization")
    @Test
    void sendingVisualizationTest() {
        salesPage.buyByDebit().buyWithCardInfo(
                "APPROVED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        paymentForm.getLoadingSpinOnContinueButton().shouldBe(Condition.visible);
    }
}