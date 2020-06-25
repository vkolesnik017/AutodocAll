package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Cart_page {

    SelenideElement nextButton() {
        return $(byCssSelector(".next-step"));
    }

    SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    // locators only for CH
    SelenideElement closeDeliveryLimitPopupForCH() {
        return $x("//a[@class='close_popup close continue_shopping']");
    }

    SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }

    SelenideElement emptyCart() {
        return $x("//div[@class='cart-page-listing__empty-cart']");
    }

    SelenideElement nameProduct() {
        return $x("//td[@class='info']/h3");
    }
}
