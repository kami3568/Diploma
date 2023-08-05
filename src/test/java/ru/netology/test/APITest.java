package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.APIHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("API testing")
public class APITest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("Debit buying by APPROVED-card")
    @Test
    void debitBuyingByApprovedCard() {
        val response = APIHelper.debitBuying(
                        "APPROVED",
                        "en",
                        "future",
                        "future",
                        "goodName",
                        "random"
                )
                .then()
                .statusCode(200)
                .extract().asString();
        assertTrue(response.contains("APPROVED"));
    }

    @DisplayName("Debit buying by DECLINED-card")
    @Test
    void debitBuyingByDeclinedCard() {
        val response = APIHelper.debitBuying(
                        "DECLINED",
                        "en",
                        "future",
                        "future",
                        "goodName",
                        "random"
                )
                .then()
                .statusCode(200)
                .extract().asString();
        assertTrue(response.contains("DECLINED"));
    }

    @DisplayName("Credit buying by APPROVED-card")
    @Test
    void creditBuyingByApprovedCard() {
        val response = APIHelper.creditBuying(
                        "APPROVED",
                        "en",
                        "future",
                        "future",
                        "goodName",
                        "random"
                )
                .then()
                .statusCode(200)
                .extract().asString();
        assertTrue(response.contains("APPROVED"));
    }

    @DisplayName("Credit buying by DECLINED-card")
    @Test
    void creditBuyingByDeclinedCard() {
        val response = APIHelper.creditBuying(
                        "DECLINED",
                        "en",
                        "future",
                        "future",
                        "goodName",
                        "random"
                )
                .then()
                .statusCode(200)
                .extract().asString();
        assertTrue(response.contains("DECLINED"));
    }

    @DisplayName("Debit buying by Random-card")
    @Test
    void debitBuyingByAnotherCard() {
        val response = APIHelper.debitBuying(
                        "random",
                        "en",
                        "future",
                        "future",
                        "goodName",
                        "random"
                )
                .then()
                .statusCode(500)
                .extract().asString();
        assertTrue(response.contains("Bad Request"));
    }

    @DisplayName("Credit buying by Random-card")
    @Test
    void creditBuyingByAnotherCard() {
        val response = APIHelper.creditBuying(
                        "random",
                        "en",
                        "future",
                        "future",
                        "goodName",
                        "random"
                )
                .then()
                .statusCode(500)
                .extract().asString();
        assertTrue(response.contains("Bad Request"));
    }

}