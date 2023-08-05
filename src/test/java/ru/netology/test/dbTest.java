package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.APIHelper;
import ru.netology.data.DataBaseHelper;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Database testing")
public class dbTest {

    @BeforeEach
    void resetAll() {
        new DataBaseHelper().dropAll();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Checking table fields when debit buying by APPROVED-card")
    void shouldFillInFieldsIfDebitBuyingByApprovedCard() {
        APIHelper.debitBuying(
                "APPROVED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        assertEquals(4500000, DataBaseHelper.getPaymentEntityInfo().getAmountFromPaymentEntity());
        assertNotNull(DataBaseHelper.getPaymentEntityInfo().getCreatedDateFromPaymentEntity());
        assertEquals("APPROVED", DataBaseHelper.getPaymentEntityInfo().getStatusFromPaymentEntity());
        assertNotNull(DataBaseHelper.getPaymentEntityInfo().getTransactionIdFromPaymentEntity());
        assertNotNull(DataBaseHelper.getOrderEntityInfo().getCreatedDateFromOrderEntity());
        assertNotNull(DataBaseHelper.getOrderEntityInfo().getPaymentIdFromOrderEntity());
    }

    @Test
    @DisplayName("Checking table fields when debit buying by DECLINED-card")
    void shouldFillInFieldsIfDebitBuyingByDeclinedCard() {
        APIHelper.debitBuying(
                "DECLINED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        assertEquals(4500000, DataBaseHelper.getPaymentEntityInfo().getAmountFromPaymentEntity());
        assertNotNull(DataBaseHelper.getPaymentEntityInfo().getCreatedDateFromPaymentEntity());
        assertEquals("DECLINED", DataBaseHelper.getPaymentEntityInfo().getStatusFromPaymentEntity());
        assertNotNull(DataBaseHelper.getPaymentEntityInfo().getTransactionIdFromPaymentEntity());
        assertNotNull(DataBaseHelper.getOrderEntityInfo().getCreatedDateFromOrderEntity());
        assertNotNull(DataBaseHelper.getOrderEntityInfo().getPaymentIdFromOrderEntity());
    }

    @Test
    @DisplayName("Checking table fields when credit buying by APPROVED-card")
    void shouldFillInFieldsIfCreditBuyingByApprovedCard() {
        APIHelper.creditBuying(
                "APPROVED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        assertNotNull(DataBaseHelper.getCreditRequestEntity().getBankIdFromCreditRequestEntity());
        assertNotNull(DataBaseHelper.getCreditRequestEntity().getCreatedDateFromCreditRequestEntity());
        assertEquals("APPROVED", DataBaseHelper.getCreditRequestEntity().getStatusFromCreditRequestEntity());
        assertNotNull(DataBaseHelper.getOrderEntityInfo().getCreatedDateFromOrderEntity());
        assertNotNull(DataBaseHelper.getOrderEntityInfo().getCreditIdFromOrderEntity());
    }

    @Test
    @DisplayName("Checking table fields when credit buying by DECLINED-card")
    void shouldFillInFieldsIfCreditBuyingByDeclinedCard() {
        APIHelper.creditBuying(
                "DECLINED",
                "en",
                "future",
                "future",
                "goodName",
                "random"
        );
        assertNotNull(DataBaseHelper.getCreditRequestEntity().getBankIdFromCreditRequestEntity());
        assertNotNull(DataBaseHelper.getCreditRequestEntity().getCreatedDateFromCreditRequestEntity());
        assertEquals("DECLINED", DataBaseHelper.getCreditRequestEntity().getStatusFromCreditRequestEntity());
        assertNotNull(DataBaseHelper.getOrderEntityInfo().getCreatedDateFromOrderEntity());
        assertNotNull(DataBaseHelper.getOrderEntityInfo().getCreditIdFromOrderEntity());
    }

}