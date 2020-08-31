package ATD;

import io.qameta.allure.Step;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Product_page_mob_Logic extends Product_page_mob {

    @Step("Adding product to basket. Product_page")
    public Product_page_mob_Logic addProductToCart() {
        closePopupUnbelievableDeals();
        buyButton().click();
        sleep(2000);
        return this;
    }

    @Step(":from Product_page")
    public Cart_page_mob_Logic cartClick() {
        new Main_page_mob_Logic().cartClick();
        return page(Cart_page_mob_Logic.class);
    }


    @Step("Get product ID. Product_page_Mob")
    public String getProductId() {
        String productId = buyButton().getAttribute("id");
        return productId;
    }

    @Step("Close popup Unbelievable Deals. Product_page_Mob")
    public Product_page_mob_Logic closePopupUnbelievableDeals() {
        try {
            popupUnbelievableDeals().waitUntil(visible, 5000).click(1, 1);
            Wait().until(WebDriver -> url().contains("apps.apple.com"));
            back();
        } catch (NoSuchElementException e) {
            System.out.println("Footer popup doesn't appear");
        }
        return this;
    }
}
