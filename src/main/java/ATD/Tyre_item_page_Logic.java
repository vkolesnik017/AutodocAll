package ATD;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

public class Tyre_item_page_Logic extends Tyre_item_page {

    @Step("presence of horizontal selector. Tyre_item_page")
    public Tyre_item_page_Logic presenceOfHorizontalSelector() {
        horizontalSelector().shouldBe(visible);
        return this;
    }

    @Step("presence of feedBack pop-up by lack of product. Tyre_item_page")
    public Tyre_item_page_Logic presenceOfFeedBackPopUpByLackOfProduct() {
        grayButtonOfProduct().shouldBe(visible).click();
        popUpAboutLackOfProduct().shouldBe(visible);
        return this;
    }

    @Step("compare MPN number of product. Tyre_item_page")
    public Tyre_item_page_Logic compareMpnNumOfProduct(String mpnForCompare) {
        String mpnNumFromProductPage = mpnNumOfProduct().getText().replace("MPN: ", "");
        Assert.assertEquals(mpnForCompare, mpnNumFromProductPage);
        return this;
    }

    @Step("get MPN number of product. Tyre_item_page")
    public String getMpnNumOfProduct() {
        return mpnNumOfProduct().shouldBe(visible).getText().replace("MPN: ", "");
    }

    @Step("compare MPN number of product  in pop-up of basket. Tyre_item_page")
    public Tyre_item_page_Logic compareMpnNumOfProductInBasketPopUp(String mpnForCompare) {
        String mpnNumFromBasketPopUp = getTextFromUnVisibleElement(artNumOfProductInBasketPopUp()).replace("Artikelnummer: ", "");
        Assert.assertEquals(mpnForCompare, mpnNumFromBasketPopUp);
        return this;
    }

    @Step("add product to basket. Tyre_item_page")
    public Tyre_item_page_Logic addProductToBasket() {
        btnAddProductToBasket().shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        return this;
    }

    @Step("go to basket. Tyre_item_page")
    public Cart_page_Logic goToBasket() {
        basket().shouldBe(visible).click();
        return page(Cart_page_Logic.class);
    }

    @Step("go to Tyre size listing by click on bread crumb link. Tyre_item_page")
    public Tyres_dimension_page_Logic goToSizeListingByClickOnBreadCrumbLink(int positionOfLink) {
        linksOfBreadCrumbs().get(positionOfLink).shouldBe(visible).click();
        return page(Tyres_dimension_page_Logic.class);
    }


    @Step("go to Tyre brand-size listing by click on bread crumb link. Tyre_item_page")
    public Tyres_brand_dimension_page_Logic goToBrandSizeListingByClickOnBreadCrumbLink(int positionOfLink) {
        linksOfBreadCrumbs().get(positionOfLink).shouldBe(visible).click();
        return page(Tyres_brand_dimension_page_Logic.class);
    }


    @Step("get full EAN number of product. Tyre_item_page")
    public String getFullEanNumberOfProduct() {
        return eanNumberOfProduct().getText();
    }

    @Step("appears of out of stock product pop-Up. Tyre_item_page")
    public Tyre_item_page_Logic appearsOfOutOfStockProductPopUp() {
        grayButton().shouldBe(visible).click();
        popUpNotifyAboutAvailability().should(appear);
        return this;
    }

    @Step("set value in email field of PopUp. Tyre_item_page")
    public Tyre_item_page_Logic setValueInEmailFieldOfPopUp(String email) {
        emailFieldOfFeedBackPopUp().shouldBe(visible).setValue(email);
        return this;
    }

    @Step("click on 'Get mailing' label of pop-up Notify about availability. Tyre_item_page")
    public Tyre_item_page_Logic clickOnGetMailingLabel() {
        labelOfPopUpNotifyAboutAvailability().shouldBe(visible).click();
        return this;
    }

    @Step("click on Subscription button. Tyre_item_page")
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

    @Step("close error pop-Up. Tyre_item_page")
    public Tyre_item_page_Logic closeErrorPopUp() {
        btnCloseErrorPopUp().shouldBe(visible).click();
        btnCloseErrorPopUp().shouldNotBe(visible);
        return this;
    }


    @Step("presence of TOP products. Tyre_item_page")
    public Tyre_item_page_Logic presenceOfTopProducts() {
        for (int i = 0; i < visibleTopProducts().size(); i++) {
            visibleTopProducts().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Click delivery link and check redirect. TyresProduct_page")
    public Tyre_item_page_Logic clickDeliveryLinkAndCheckRedirect() throws SQLException {
        deliveryLink().click();
        switchTo().window(1);
        waitingWhileLinkBecomeExpected(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        return this;
    }

    @Step("Check product price visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkProductPriceVisibility() {
        productPrice().shouldBe(visible);
        return this;
    }

    @Step("Check product price visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkDeliveryLinkVisibility() {
        deliveryLink().shouldBe(visible);
        return this;
    }

    @Step("Check FAQ block visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkFAQblockVisibility() {
        faqBlock().shouldBe(visible);
        return this;
    }

    @Step("Check rating block visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkRatingBlockVisibility() {
        ratingBlock().shouldBe(visible);
        return this;
    }

    @Step("Check payment methods block visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkPaymentMethodsBlockVisibility() {
        paymentMethodsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check delivery methods block visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkDeliveryMethodsBlockVisibility() {
        deliveryMethodsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check add to basket from topseller block on tyres product page. Tyre_item_page")
    public Tyre_item_page_Logic checkAddToBasketFromTopsellerBlock() {
        topsellerBlock().shouldBe(visible);
        addToBasketTopsellerBlockButton().click();
        return this;
    }

    @Step("Check transition to product page from topseller block. Tyre_item_page")
    public Tyre_item_page_Logic checkTransitionToProductPageFromTopsellerBlock() {
        topsellerBlock().shouldBe(visible);
        String urlInTopBlock = productFronTopsellerBlock().attr("href");
        productFronTopsellerBlock().click();
        waitingWhileLinkBecomeExpected(urlInTopBlock);
        return this;
    }

    @Step("Check reifenlabel block visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkReifenlabelBlockVisibility() {
        reifenlabelBlock().shouldBe(visible);
        return this;
    }

    @Step("Check adding tyres to basket. Tyre_item_page")
    public Cart_page_Logic checkAddingTyresToBasket() {
        basketBlock().hover();
        Float priceOfOneTyre = getPriceFromElement(priceOnProductPage());
        Float priceOfTyresInBasket = getPriceFromElement(priceInBasketPopup());
        Float numberOftyresInFloat = Float.parseFloat(numberOfProductsOnProductPage().attr("value").trim());
        Float resutlPrice = (priceOfOneTyre * numberOftyresInFloat);
        Assert.assertEquals(resutlPrice, priceOfTyresInBasket);
        numberOfProductsInBasketPopup().shouldHave(text(numberOfProductsOnProductPage().getValue()));
        basketBlock().click();
        return page(Cart_page_Logic.class);
    }

    @Step("Check tyres horizontal selector visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkTyresHorizontalSelectorVisibility() {
        tyresHorizontalSelector().shouldBe(visible);
        return this;
    }

    @Step("Check tyres photo block visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkTyresPhotoBlockVisibility() {
        tyresPhotoBlock().shouldBe(visible);
        return this;
    }

    @Step("Check tyres product title visibility. Tyre_item_page")
    public Tyre_item_page_Logic checkTyresProductTitleVisibility() {
        tyresProductTitle().shouldBe(visible);
        return this;
    }

    @Step("Check tyres characteristics block. Tyre_item_page")
    public Tyre_item_page_Logic checkCharacteristicsBlock() {
        Product_page productPage = new Product_page();
        productPage.minimizedCharactericticBlock().shouldBe(visible);
        if (productPage.uncoverCharactericticBtn().isDisplayed()) {
            productPage.uncoverCharactericticBtn().click();
            productPage.maximizedCharacteristicBlock().shouldBe(visible);
            productPage.coverCharacteristicBtn().click();
            productPage.minimizedCharactericticBlock().shouldBe(visible);
        }
        return this;
    }

    @Step("Checks navigating the season link in the characteristics block. Tyre_item_page")
    public TyresListing_page_Logic checkNavigatingSeasonLinkInCharacteristicsBlock() {
        if (new Product_page().uncoverCharactericticBtn().isDisplayed()) {
            new Product_page().uncoverCharactericticBtn().click();
        }
        seasonLincInCharacteristicsBlock().shouldBe(visible);
        String season = seasonLincInCharacteristicsBlock().getText();
        seasonLincInCharacteristicsBlock().click();
        String title = new TyresListing_page_Logic().getTitleInListing();
        Assert.assertTrue(title.contains(season));
        return page(TyresListing_page_Logic.class);
    }

    @Step("Check tyres delivery block. Tyre_item_page")
    public Tyre_item_page_Logic checkDeliveryBlockVisibility() {
        tyresDeliveryBlock().shouldBe(visible);
        return this;
    }

    @Step("Check topseller block. Tyre_item_page")
    public Tyre_item_page_Logic checkTopsellerBlockVisibility() {
        topsellerBlock().shouldBe(visible);
        productInTopsellerBlock().shouldBe(visible);
        return this;
    }

    @Step("Check reviews block. Tyre_item_page")
    public Tyre_item_page_Logic checkReviewsBlockVisibility() {
        reviewsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check related products block. Tyre_item_page")
    public Tyre_item_page_Logic checkRelatedProductsBlockVisibility() {
        relatedProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check price on product page. Tyre_item_page")
    public Tyre_item_page_Logic checkTyreIdOnProductPage(String expectedId) {
        addToBasketButton().shouldHave(attribute("id", expectedId));
        return this;
    }

    @Step("Select width in selector. Tyre_item_page")
    public Tyre_item_page_Logic selectWidthTyreOnListing(String width) {
        new Tyres_page_Logic().selectWidth(width);
        return this;
    }

    @Step("Select height in selector. Tyre_item_page")
    public Tyre_item_page_Logic selectHeightTyreOnListing(String height) {
        new Tyres_page_Logic().selectHeight(height);
        return this;
    }

    @Step("Select diameter in selector. Tyre_item_page")
    public Tyre_item_page_Logic selectDiameterTyreOnListing(String diameter) {
        new Tyres_page_Logic().selectDiameter(diameter);
        return this;
    }

    @Step("Click Submit Tyres Selector On Listing. Tyre_item_page")
    public TyresListing_page_Logic clickSubmitTyresSelectorOnListing() {
        new TyresListing_page_Logic().clickSubmitTyresSelectorOnListing();
        return page(TyresListing_page_Logic.class);
    }

    @Step("Select summer season in selector if it is not selected. Tyre_item_page")
    public Tyre_item_page_Logic selectSummerSeason() {
        if (!summerSeasonCheckbox().isSelected()) {
            summerSeasonCheckbox().click();
        }
        return this;
    }

    @Step("Checking presence text about period product return. Tyre_item_page")
    public Tyre_item_page_Logic checkingPresenceTextAboutPeriodProductReturn() {
        daysReturnPeriodProduct().shouldHave(text("14"));
        return this;
    }

    @Step("Checking presence block with return period. Tyre_item_page")
    public Tyre_item_page_Logic checkingForBlockWithReturnPeriod() {
        returnPeriodProductBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking color change on hover text from block with return period. Tyre_item_page")
    public Tyre_item_page_Logic checkingColorChangeOnHoverTextFromBlockReturnPeriod() {
        daysReturnPeriodProduct().shouldHave(cssValue("color", "rgba(128, 128, 128, 1)"));
        iconDaysReturnPeriodProduct().shouldHave(cssValue("color", "rgba(128, 128, 128, 1)"));
        daysReturnPeriodProduct().hover();
        daysReturnPeriodProduct().shouldHave(cssValue("color", "rgba(51, 100, 219, 1)"));
        iconDaysReturnPeriodProduct().shouldHave(cssValue("color", "rgba(51, 100, 219, 1)"));
        return this;
    }

    @Step("displaying active add product to basket button. Tyre_item_page")
    public Tyre_item_page_Logic displayActiveBtnAddToBasket() {
        addButtonToBasket().shouldBe(visible);
        return this;
    }

}
