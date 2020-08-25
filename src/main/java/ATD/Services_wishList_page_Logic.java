package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Services_wishList_page_Logic extends Services_wishList_page {

    @Step("presence of product list.   Services_wishList_page")
    public Services_wishList_page_Logic presenceOfProductList() {
        productList().shouldBe(visible);
        return this;
    }

    @Step("get MPN number of product. Services_wishList_page")
    public String getMpnNumberOfProduct(int positionOfProduct) {
        String mpnNumber = mpnNumberOfProduct().get(positionOfProduct).getText().replace("MPN: ", "");
        return mpnNumber;
    }
}
