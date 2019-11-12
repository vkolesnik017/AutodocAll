package ATD;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class Cart_page {

    SelenideElement nextButton() {
        return $(byCssSelector(".next-step"));
    }

    public CartAccount_page nextButtonClick() {
        nextButton().click();
        return page(CartAccount_page.class);
    }

    public SelenideElement addProductBtn() {
        return $(byCssSelector(".plus"));
    }

    // locators only for CH
    public SelenideElement closeDeliveryLimitPopupForCH() {
        return $(byCssSelector(".delivery-limit-popup>a"));
    }

    public SelenideElement nextBtnIsNotActiveForCH() {
        return $(byCssSelector(".noclicked"));
    }

    // locators of prices with Currencies
    public SelenideElement totalOrderPriceInHead() {
        return $(byCssSelector(".cart-page-head__price>b"));
    }

    public SelenideElement productPrice() {
        return $(byCssSelector(".price"));
    }

    public SelenideElement totalProductPrice() {
        return $(byCssSelector(".total-price"));
    }

    public SelenideElement priceOfAllProducts() {
        return $(byXpath("//*[@class='order-summary ']/div[2]/span[2]"));
    }

    public SelenideElement totalOrderPrice() {
        return $(byXpath("//*[@class='order-summary__row order-summary__row--total']/span[2]"));
    }

    public SelenideElement priceWithoutDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[1]"));
    }

    public SelenideElement priceWithDiscount() {
        return $(byXpath("//*[@id='promo-footer']//p/b[2]"));
    }

    public SelenideElement discount() {
        return $(byXpath("//*[@id='promo-footer']//span/b"));
    }

    public Cart_page makePriceForMiniumOrderForCH() {
        if (closeDeliveryLimitPopupForCH().isDisplayed()) {
            closeDeliveryLimitPopupForCH().click();
            while (nextBtnIsNotActiveForCH().isDisplayed()) {
                addProductBtn().click();
                sleep(100);
            }
        }
        return this;
    }


}
