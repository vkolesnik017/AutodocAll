package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Tyre_item_page_Logic extends Tyre_item_page {

    @Step("presence of horizontal selector .Tyre_item_page")
    public Tyre_item_page_Logic presenceOfHorizontalSelector() {
        horizontalSelector().shouldBe(visible);
        return this;
    }

    @Step("presence of feedBack pop-up by lack of product .Tyre_item_page")
    public Tyre_item_page_Logic presenceOfFeedBackPopUpByLackOfProduct() {
        grayButtonOfProduct().shouldBe(visible).click();
        popUpAboutLackOfProduct().shouldBe(visible);
        return this;
    }

    @Step("compare MPN number of product .Tyre_item_page")
    public Tyre_item_page_Logic compareMpnNumOfProduct(String mpnForCompare) {
        String mpnNumFromProductPage = mpnNumOfProduct().getText().replace("MPN: ", "");
        Assert.assertEquals(mpnForCompare, mpnNumFromProductPage);
        return this;
    }

    @Step("get MPN number of product.Tyre_item_page")
    public String getMpnNumOfProduct() {
        return mpnNumOfProduct().shouldBe(visible).getText().replace("MPN: ", "");
    }

    @Step("compare MPN number of product  in pop-up of basket.Tyre_item_page")
    public Tyre_item_page_Logic compareMpnNumOfProductInBasketPopUp(String mpnForCompare) {
        String mpnNumFromBasketPopUp = getTextFromUnVisibleElement(artNumOfProductInBasketPopUp()).replace("Artikelnummer: ", "");
        Assert.assertEquals(mpnForCompare, mpnNumFromBasketPopUp);
        return this;
    }

    @Step("get MPN number of product.Tyre_item_page")
    public Tyre_item_page_Logic addProductToBasket() {
        btnAddProductToBasket().shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        return this;
    }

    @Step("go to basket .Tyre_item_page")
    public Cart_page_Logic goToBasket() {
        basket().shouldBe(visible).click();
        return page(Cart_page_Logic.class);
    }

    @Step("go to Tyre size listing by click on bread crumb link .Tyre_item_page")
    public Tyres_dimension_page_Logic goToSizeListingByClickOnBreadCrumbLink(int positionOfLink) {
        linksOfBreadCrumbs().get(positionOfLink).shouldBe(visible).click();
        return page(Tyres_dimension_page_Logic.class);
    }


    @Step("go to Tyre brand-size listing by click on bread crumb link .Tyre_item_page")
    public Tyres_brand_dimension_page_Logic goToBrandSizeListingByClickOnBreadCrumbLink(int positionOfLink) {
        linksOfBreadCrumbs().get(positionOfLink).shouldBe(visible).click();
        return page(Tyres_brand_dimension_page_Logic.class);
    }


    @Step("get full EAN number of product.Tyre_item_page")
    public String getFullEanNumberOfProduct() {
        return eanNumberOfProduct().getText();
    }
}
