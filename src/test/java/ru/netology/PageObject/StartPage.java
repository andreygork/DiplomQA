package ru.netology.PageObject;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private SelenideElement heading = $$("h2").findBy(exactText("Путешествие дня"));
    private SelenideElement debitButton = $$(".button").findBy(exactText("Купить"));
    private SelenideElement creditButton = $$(".button").findBy(exactText("Купить в кредит"));

    public StartPage() {
        heading.shouldBe(visible);
    }

    public BuyWithDebitCard openBuyWithDebitCard() {
        debitButton.click();
        return new BuyWithDebitCard();
    }

    public BuyWithCreditCard openBuyWithCreditCard() {
        creditButton.click();
        return new BuyWithCreditCard();
    }
}
