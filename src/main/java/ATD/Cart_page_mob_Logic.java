package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;

public class Cart_page_mob_Logic extends Cart_page_mob {

    @Step("Check of id {idOfProduct} added product to basket. Cart_page_Mob")
    public Cart_page_mob_Logic checkOfIdAddedProductInBasket(String idOfProduct) {
        idOfAddedProduct().shouldHave(attribute("data-article-id", idOfProduct));
        return this;
    }
}
