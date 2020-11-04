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

    @Step("appears of out of stock product pop-Up. Tyre_item_page")
    public Tyre_item_page_Logic appearsOfOutOfStockProductPopUp() {
        grayButton().shouldBe(visible).click();
        popUpNotifyAboutAvailability().should(appear);
        return this;
    }

    @Step("set value in email field of PopUp .Tyre_item_page")
    public Tyre_item_page_Logic setValueInEmailFieldOfPopUp(String email) {
        emailFieldOfFeedBackPopUp().shouldBe(visible).setValue(email);
        return this;
    }

    @Step("click on 'Get mailing' label of pop-up Notify about availability. Tyre_item_page")
    public Tyre_item_page_Logic clickOnGetMailingLabel() {
        labelOfPopUpNotifyAboutAvailability().shouldBe(visible).click();
        return this;
    }

    @Step("click on Subscription button . Tyre_item_page")
    public Tyre_item_page_Logic clickOnBtnSubscription() {
        btnSendOfFeedBackPopUp().click();
        return this;
    }

    @Step("an error popup about an incomplete email and an unset checkbox check. Tyre_item_page")
    public Tyre_item_page_Logic appearsErrorPopUpAboutIncompleteEmailAndUnsetCheckBox() {
        errorPopUp().shouldBe(visible).shouldHave(text("Dies ist ein Pflichtfeld")).shouldHave(text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        return this;
    }

     @Step("an error popup about an incomplete email. Tyre_item_page")
    public Tyre_item_page_Logic appearsErrorPopUpAboutIncompleteEmail() {
        errorPopUp().shouldBe(visible).shouldHave(text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        return this;
    }

    @Step("close error pop-Up . Tyre_item_page")
    public Tyre_item_page_Logic closeErrorPopUp() {
        btnCloseErrorPopUp().shouldBe(visible).click();
        btnCloseErrorPopUp().shouldNotBe(visible);
        return this;
    }
}
