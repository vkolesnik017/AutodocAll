package PRF;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Cart_page {

    SelenideElement nextButton() {
        return $x("//a[@class='btn push-right step_1']");
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    // locators only for CH
    SelenideElement closeDeliveryLimitPopupForCH() {
        return $x("//span[@class='btn_pp begin']");
    }

    SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }
}
