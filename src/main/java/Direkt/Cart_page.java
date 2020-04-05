package Direkt;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Cart_page {

    SelenideElement nextButton() {
        return $x("//a[@class='green ga-click']");
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    // locators only for CH
    SelenideElement closeDeliveryLimitPopupForCH() {
        return $x("//div[@class='popup_top']//a");
    }

    SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }
}
