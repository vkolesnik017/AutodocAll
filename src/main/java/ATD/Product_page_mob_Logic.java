package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class Product_page_mob_Logic extends Product_page_mob {

    @Step("Adding product to basket. Product_page")
    public Product_page_mob_Logic addProductToCart() {
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
}
