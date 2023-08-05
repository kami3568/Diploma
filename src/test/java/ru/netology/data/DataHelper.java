package ru.netology.data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DataHelper {

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String cardHolder;
        String cvc;
    }

    private static String getNumber(String status, String locale) {
        if (status.equals("APPROVED")) {
            return "4444 4444 4444 4441";
        }
        if (status.equals("DECLINED")) {
            return "4444 4444 4444 4442";
        }
        if (status.equals("short")) {
            return "4444 4444 4444 444";
        }
        if (status.equals("one")) {
            return "4";
        }
        if (status.equals("random")) {
            return new Faker(new Locale(locale)).finance().creditCard(CreditCardType.MASTERCARD);
        }
        return null;
    }

    private static String getFullName(String status) {
        Faker faker = new Faker();
        if (status.equals("badName")) {
            return faker.aquaTeenHungerForce().character() + " - " + faker.number().numberBetween(1, 999);
        }
        if (status.equals("goodName")) {
            return new Faker().name().firstName().toUpperCase() + " " + new Faker().name().firstName().toUpperCase();
        }
        return null;
    }

    private static String getMonth(String status) {
        Faker faker = new Faker();
        if (status.equals("zero")) {
            return "0";
        }
        if (status.equals("doubleZero")) {
            return "00";
        }
        if (status.equals("badRandom")) {
            return String.valueOf(faker.number().numberBetween(13, 99));
        }
        if (status.equals("current")) {
            return LocalDate.now().format(ofPattern("MM"));
        }
        if (status.equals("past")) {
            return LocalDate.now().minusMonths(1).format(ofPattern("MM"));
        }
        if (status.equals("future")) {
            return LocalDate.now().plusMonths(1).format(ofPattern("MM"));
        }
        return null;
    }

    private static String getYear(String status) {
        if (status.equals("zero")) {
            return "0";
        }
        if (status.equals("doubleZero")) {
            return "00";
        }
        if (status.equals("badRandom")) {
            return LocalDate.now().plusMonths(new Faker().number().numberBetween(12 * 6, 12 * 9)).format(ofPattern("yy"));
        }
        if (status.equals("current")) {
            return LocalDate.now().format(ofPattern("yy"));
        }
        if (status.equals("past")) {
            return LocalDate.now().minusYears(new Faker().number().numberBetween(1, 20)).format(ofPattern("yy"));
        }
        if (status.equals("future")) {
            return LocalDate.now().plusMonths(new Faker().number().numberBetween(1, 12 * 5)).format(ofPattern("yy"));
        }
        return null;
    }

    private static String getCVC(String status) {
        String cvc = new Faker().number().digits(3);
        if (status.equals("tripleZero")) {
            return "000";
        }
        if (status.equals("short")) {
            return String.valueOf(new Faker().number().numberBetween(0, 99));
        }
        if (status.equals("random")) {
            return cvc;
        }
        return null;
    }

    public static CardInfo getCardInfo(String cardStatus, String requiredLocale, String monthStatus, String yearStatus, String holderStatus, String cvcStatus) {
        return new CardInfo(
                getNumber(cardStatus, requiredLocale),
                getMonth(monthStatus),
                getYear(yearStatus),
                getFullName(holderStatus),
                getCVC(cvcStatus)
        );
    }

}

