package ru.netology.data;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    private static String getApprovedCardNumber() {
        return new String("4444 4444 4444 4441");
    }

    ;

    private static String getDeclinedCardNumber() {
        return new String("4444 4444 4444 4442");
    }

    private static String getMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        return String.format("%02d", month);
    }

    private static String getPreviousMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.minusMonths(1).getMonthValue();
        return String.format("%02d", month);
    }

    private static String getYear() {
        DateFormat df = new SimpleDateFormat("yy");
        return df.format(Calendar.getInstance().getTime());
    }

    private static String getPreviousYear() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.minusYears(1).getYear();
        return String.format("%02d", year);
    }

    private static String getName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    private static String getCVVCode() {
        Random random = new Random();
        int cvv = random.nextInt((1000 - 1));
        return String.format("%03d", cvv);
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), getCVVCode());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), getName(), getCVVCode());
    }

    public static CardInfo getInvalidCard() {
        return new CardInfo("2345 6543 3456 9876", getMonth(), getYear(), getName(), getCVVCode());
    }

    public static CardInfo getEmptyForm() {
        return new CardInfo();
    }

    public static CardInfo getIncompleteForm() {
        return new CardInfo("2222 2211_13", "9", "7", "A", "0");
    }

    public static CardInfo getInvalidMonthApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), "33", getYear(), getName(), getCVVCode());
    }

    public static CardInfo getInvalidMonthDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), "33", getYear(), getName(), getCVVCode());
    }

    public static CardInfo getInvalidPreviousMonthApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getPreviousMonth(), getYear(), getName(), getCVVCode());
    }

    public static CardInfo getInvalidPreviousMonthDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getPreviousMonth(), getYear(), getName(), getCVVCode());
    }
    public static CardInfo getInvalidYearApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), "29", getName(), getCVVCode());
    }

    public static CardInfo getInvalidYearDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), "29", getName(), getCVVCode());
    }

    public static CardInfo getInvalidPreviousYearApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getPreviousYear(), getName(), getCVVCode());
    }

    public static CardInfo getInvalidPreviousYearDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getPreviousYear(), getName(), getCVVCode());
    }
    public static CardInfo getShortNameOfTheCardHolderApproved() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "An", getCVVCode());
    }

    public static CardInfo getShortNameOfTheCardHolderDeclined() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), "An", getCVVCode());
    }

    public static CardInfo getOneSymbolInTheHolderFieldCardApproved() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "A", getCVVCode());
    }

    public static CardInfo getOneSymbolInTheHolderFieldCardDeclined() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), "A", getCVVCode());
    }

    public static CardInfo getSymbolsInTheHolderFieldCardApproved() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "5646/^", getCVVCode());
    }

    public static CardInfo getSymbolsInTheHolderFieldCardDeclined() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), "5646/^", getCVVCode());
    }
    public static CardInfo getInvalidCVVCodeApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), "01");
    }

    public static CardInfo getInvalidCVVCodeDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), getName(), "01");
    }

}
