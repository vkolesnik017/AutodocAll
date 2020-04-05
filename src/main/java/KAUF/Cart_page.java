package KAUF;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Cart_page {

    SelenideElement nextButton() {
        return $x("//a[@class='green_bt']");
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    // locators only for CH
    SelenideElement closeDeliveryLimitPopupForCH() {
        return $x("//a[@class='close middle']");
    }

    SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }

}
