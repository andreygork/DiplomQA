package ru.netology.PageObject;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyWithCreditCard {
    private SelenideElement heading = $$(".button").findBy(exactText("Купить в кредит"));
    private SelenideElement cardNumber = $("input[type=\"text\"][placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement month = $("input[type=\"text\"][placeholder=\"08\"]");
    private SelenideElement year = $("input[type=\"text\"][placeholder=\"22\"]");
    private SelenideElement holder = $$(".input").findBy(exactText("Владелец")).$(".input__control");
    private SelenideElement cvv = $("input[type=\"text\"][placeholder=\"999\"]");
    private SelenideElement button = $$(".button").find(exactText("Продолжить"));

    private final SelenideElement statusOk = $(".notification_status_ok");
    private final SelenideElement statusError = $(".notification_status_error");
    private final SelenideElement inputInvalid = $(".input__sub");

    public void BuyWithCreditCard() {
        heading.shouldBe(visible);
    }

    public void waitNotificationOk() {
        statusOk.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationError() {
        statusError.shouldBe(visible, Duration.ofSeconds(15));
    }
    public void FillDataHelper(CardInfo card) {
        cardNumber.setValue(card.getNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        holder.setValue(card.getName());
        cvv.setValue(card.getCvv());
        button.click();
    }

    public String getInputInvalid() {
        return inputInvalid.getText();
    }
}