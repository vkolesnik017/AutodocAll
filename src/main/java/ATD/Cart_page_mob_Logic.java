package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;

public class Cart_page_mob_Logic extends Cart_page_mob {

    @Step("Check of id {idOfProduct} added product to basket. Cart_page_Mob")
    public Cart_page_mob_Logic checkOfIdAddedProductInBasket(String idOfProduct) {
        idOfAddedProduct().shouldHave(attribute("data-article-id", idOfProduct));
        return this;
    }

    @Step("Delete item from cart. Cart_page_Mob")
    public Cart_page_mob_Logic deleteItemFromCart() {
        deleteProductBtn().click();
        deleteConfirmationPopUp().shouldBe(visible);
        deleteConfirmationBtn().click();
        return this;
    }

    @Step("Cheks that the basket is empty. Cart_page_Mob")
    public Cart_page_mob_Logic checkEmptyCart() {
        emptyCart().shouldBe(visible);
        return this;
    }
}
