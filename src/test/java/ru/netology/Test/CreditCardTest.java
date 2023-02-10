package ru.netology.Test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.PageObject.BuyWithCreditCard;
import ru.netology.PageObject.StartPage;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQL.*;

public class CreditCardTest {
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
    public void shouldPaymentByCreditCardIsApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getApprovedCard());
        buyWithCreditCard.waitNotificationOk();
        assertEquals("APPROVED", getCreditStatus());
    }

    @SneakyThrows
    @Test
    public void shouldPaymentByCreditCardIsDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getDeclinedCard());
        buyWithCreditCard.waitNotificationError();
        assertEquals("DECLINED", getCreditStatus());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidCreditCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidCard());
        buyWithCreditCard.waitNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getEmptyForm());
        buyWithCreditCard.getInputInvalid();
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldIncompleteField() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getIncompleteForm());
        buyWithCreditCard.getInputInvalid();
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidMonthCreditApprovedtСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidMonthCreditDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidCreditPreviousMonthApprovedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidPreviousMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousMonthCreditDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidPreviousMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidYearCreditApprovedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidYearApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidYearCreditDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidYearDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousYearCreditApprovedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidPreviousYearApprovedCard());
        assertEquals("Истёк срок действия карты", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousYearCreditDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidPreviousYearDeclinedCard());
        assertEquals("Истёк срок действия карты", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldShortNameHolderFieldCreditApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getShortNameOfTheCardHolderApproved());
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldShortNameHolderFieldCreditDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getShortNameOfTheCardHolderDeclined());
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldOneSymbolNameHolderFieldCreditApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getOneSymbolInTheHolderFieldCardApproved());
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldOneSymbolNameHolderFieldCreditDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getOneSymbolInTheHolderFieldCardDeclined());
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }
    @SneakyThrows
    @Test
    public void shouldSymbolsNameHolderFieldCreditApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getSymbolsInTheHolderFieldCardApproved());
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldSymbolsNameHolderFieldCreditDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getSymbolsInTheHolderFieldCardDeclined());
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }
    @SneakyThrows
    @Test
    public void shouldInvalidCVVCreditApprovedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidCVVCodeApprovedCard());
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidCVVCreditDeclinedСard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidCVVCodeDeclinedCard());
        assertEquals("Неверный формат", buyWithCreditCard.getInputInvalid());
    }
}
