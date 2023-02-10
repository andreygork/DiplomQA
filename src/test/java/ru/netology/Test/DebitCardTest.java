package ru.netology.Test;


import org.junit.jupiter.api.*;
import ru.netology.PageObject.BuyWithDebitCard;
import ru.netology.PageObject.StartPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import ru.netology.data.DataHelper;
import ru.netology.data.SQL;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import lombok.val;


import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQL.clearData;

public class DebitCardTest {

    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @SneakyThrows
    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
        clearData();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @SneakyThrows
    @Test
    public void shouldPaymentByDebitCardIsApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getApprovedCard());
        buyWithDebitCard.waitNotificationOk();
        assertEquals("APPROVED", SQL.getDebitStatus());
    }

    @SneakyThrows
    @Test
    public void shouldPaymentByDebitCardIsDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getDeclinedCard());
        buyWithDebitCard.waitNotificationError();
        assertEquals("DECLINED", SQL.getDebitStatus());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidDebitCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidCard());
        buyWithDebitCard.waitNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getEmptyForm());
        buyWithDebitCard.getInputInvalid();
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldIncompleteField() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getIncompleteForm());
        buyWithDebitCard.getInputInvalid();
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidMonthDebitApprovedtСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidMonthDebitDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidDebitPreviousMonthApprovedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidPreviousMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousMonthDebitDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidPreviousMonthDeclinedCard());
            assertEquals("Неверно указан срок действия карты", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidYearDebitApprovedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidYearApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidYearDebitDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidYearDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousYearDebitApprovedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidPreviousYearApprovedCard());
        assertEquals("Истёк срок действия карты", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousYearDebitDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidPreviousYearDeclinedCard());
        assertEquals("Истёк срок действия карты", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldShortNameHolderFieldDebitApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getShortNameOfTheCardHolderApproved());
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldShortNameHolderFieldDebitDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getShortNameOfTheCardHolderDeclined());
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldOneSymbolNameHolderFieldDebitApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getOneSymbolInTheHolderFieldCardApproved());
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldOneSymbolNameHolderFieldDebitDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getOneSymbolInTheHolderFieldCardDeclined());
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }
    @SneakyThrows
    @Test
    public void shouldSymbolsNameHolderFieldDebitApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getSymbolsInTheHolderFieldCardApproved());
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldSymbolsNameHolderFieldDebitDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getSymbolsInTheHolderFieldCardDeclined());
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }
    @SneakyThrows
    @Test
    public void shouldInvalidCVVDebitApprovedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidCVVCodeApprovedCard());
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidCVVDebitDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithDebitCard();
        val buyWithDebitCard = new BuyWithDebitCard();
        buyWithDebitCard.FillDataHelper(DataHelper.getInvalidCVVCodeDeclinedCard());
        assertEquals("Неверный формат", buyWithDebitCard.getInputInvalid());
    }
}