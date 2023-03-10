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
        assertEquals("????????????! ???????? ?????????????? ?? ???????????????????? ????????????????.", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getEmptyForm());
        buyWithCreditCard.getInputInvalid();
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldIncompleteField() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getIncompleteForm());
        buyWithCreditCard.getInputInvalid();
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidMonthCreditApprovedt??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidMonthCreditDeclined??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidCreditPreviousMonthApproved??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidPreviousMonthApprovedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousMonthCreditDeclined??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidPreviousMonthDeclinedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidYearCreditApproved??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidYearApprovedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidYearCreditDeclined??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidYearDeclinedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousYearCreditApproved??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidPreviousYearApprovedCard());
        assertEquals("?????????? ???????? ???????????????? ??????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidPreviousYearCreditDeclined??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidPreviousYearDeclinedCard());
        assertEquals("?????????? ???????? ???????????????? ??????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldShortNameHolderFieldCreditApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getShortNameOfTheCardHolderApproved());
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldShortNameHolderFieldCreditDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getShortNameOfTheCardHolderDeclined());
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldOneSymbolNameHolderFieldCreditApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getOneSymbolInTheHolderFieldCardApproved());
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldOneSymbolNameHolderFieldCreditDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getOneSymbolInTheHolderFieldCardDeclined());
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }
    @SneakyThrows
    @Test
    public void shouldSymbolsNameHolderFieldCreditApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getSymbolsInTheHolderFieldCardApproved());
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldSymbolsNameHolderFieldCreditDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getSymbolsInTheHolderFieldCardDeclined());
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }
    @SneakyThrows
    @Test
    public void shouldInvalidCVVCreditApproved??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidCVVCodeApprovedCard());
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }

    @SneakyThrows
    @Test
    public void shouldInvalidCVVCreditDeclined??ard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCreditCard();
        val buyWithCreditCard = new BuyWithCreditCard();
        buyWithCreditCard.FillDataHelper(DataHelper.getInvalidCVVCodeDeclinedCard());
        assertEquals("???????????????? ????????????", buyWithCreditCard.getInputInvalid());
    }
}
