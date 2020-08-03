package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.url;

public class TyresListing_page_Logic extends TyresListing_page {

    @Step("Adds first product to basket from tyres listing and goes to basket. TyresListing_page")
    public Cart_page_Logic addFirstProductAndGoToCart() {
        new Search_page_Logic().addFirstProductAndGoToCart();
        return page(Cart_page_Logic.class);
    }

    @Step("Adds first product to basket from listing. TyresListing_page")
    public Cart_page_Logic addFirstProductToCart() {
        buyButton().click();
        return page(Cart_page_Logic.class);
    }

    @Step("Gets tyre id. TyresListing_page")
    public String getTyreId() {
        return buyButton().attr("data-id");
    }



    @Step("Check Characteristic On Listing")
    public TyresListing_page_Logic checkCharacteristicOnListing(String characteristicValue, ElementsCollection characteristicLocator) {
        characteristicLocator.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < characteristicLocator.size(); i++) {
            characteristicLocator.get(i).shouldHave(text(characteristicValue));
        }
        return this;
    }

    @Step("Select Season Tyre On Listing")
    public TyresListing_page_Logic selectSeasonTyreOnListing(String season) {
        new Tyres_page_Logic().selectSeasonTyre(season);
        return this;
    }

    @Step("Select Width Tyre On Listing")
    public TyresListing_page_Logic selectWidthTyreOnListing(String width) {
        new Tyres_page_Logic().selectWidth(width);
        return this;
    }

    @Step("Select Height Tyre On Listing")
    public TyresListing_page_Logic selectHeightTyreOnListing(String height) {
        new Tyres_page_Logic().selectHeight(height);
        return this;
    }

    @Step("Select Diameter Tyre On Listing")
    public TyresListing_page_Logic selectDiameterTyreOnListing(String diameter) {
        new Tyres_page_Logic().selectDiameter(diameter);
        return this;
    }

    @Step("Select Type Tyre On Listing")
    public TyresListing_page_Logic selectTypeTyreOnListing(String type) {
        new Tyres_page_Logic().selectType(type);
        return this;
    }

    @Step("Click Submit Tyres Selector On Listing")
    public TyresListing_page_Logic clickSubmitTyresSelectorOnListing() {
        tyresSearchButtonOnListing().click();
        return this;
    }

    @Step("Check Tyres Selector Block Visibility On Listing")
    public TyresListing_page_Logic checkTyresSelectorBlockVisibilityOnListing() {
        tyresSelectorOnListing().shouldBe(visible);
        return this;
    }

    @Step("Check validation Popup With Clear Height Width Diameter On Listing")
    public TyresListing_page_Logic validationPopupWithClearHeightWidthDiameterOnListing() {
        new Tyres_page_Logic().validationPopupWithClearHeightWidthDiameter();
        return this;
    }

    @Step("Close Tyres Validation Popup On Listing")
    public TyresListing_page_Logic closeTyresValidationPopupOnListing() {
        new Tyres_page_Logic().closeTyresValidationPopup();
        return this;
    }

    @Step("Check validation Popup With Clear Height Diameter On Listing")
    public TyresListing_page_Logic validationPopupWithClearHeightDiameterOnListing() {
        new Tyres_page_Logic().validationPopupWithClearHeightDiameter();
        return this;
    }

    @Step("Check validation Popup With Clear Diameter On Listing")
    public TyresListing_page_Logic validationPopupWithClearDiameterOnListing() {
        new Tyres_page_Logic().validationPopupWithClearDiameter();
        return this;
    }

    @Step("Check validation Popup With Clear Height Width Type Diameter On Listing")
    public TyresListing_page_Logic validationPopupWithClearHeightWidthTypeDiameterOnListing() {
        new Tyres_page_Logic().tyresValidationPopup().shouldHave(text("Das Feld Breite ist erforderlich und muss einen Wert enthalten. Das Feld Höhe ist erforderlich und muss einen Wert enthalten. Das Feld Typ ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
        return this;
    }

    @Step("Check validation Popup With Clear Height Typ Diameter On Listing")
    public TyresListing_page_Logic validationPopupWithClearHeightTypDiameterOnListing() {
        new Tyres_page_Logic().validationPopupWithClearHeightTypDiameter();
        return this;
    }

    @Step("Check validation Popup With Clear Typ Diameter On Listing")
    public TyresListing_page_Logic validationPopupWithClearTypDiameterOnListing() {
        new Tyres_page_Logic().validationPopupWithClearTypDiameter();
        return this;
    }

    @Step("Check First Tyre Brand is Apollo")
    public TyresListing_page_Logic checkFirstTyreBrandApollo() {
        firstTyreTitleOnListing().shouldHave(text("Apollo"));
        return this;
    }

    @Step("Check Brand Is Selected In Brand Block")
    public TyresListing_page_Logic checkBrandIsSelectedInBrandBlock(String brandName) {
        firstActiveBrandInBlock().shouldHave(attribute("data-value", brandName));
        return this;
    }

    @Step("Check Speed Index In Selector")
    public TyresListing_page checkSpeedIndexInSelector() {
        speedIndexOnListingValue().shouldHave(text("H:"));
        return this;
    }

    @Step("Get values from selector and check them in all products in top block. TyresListing_page")
    public TyresListing_page_Logic getValueFromSelectorAndCheckTopBlockProducts() {
        String width = widthValueInSelector().text();
        String height = heightValueInSelector().text();
        String diameter = diameterValueInSelector().text();
        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < productsInTopBlock().size(); i++) {
                if (n == 1) {
                    nextButtonInTopBlock().click();
                } else if (n == 2) {
                    previousButtonInTopBlock().click();
                }
                productsInTopBlock().get(i).click();
                productWidthCharacteristic().shouldHave(text(width));
                productHeightCharacteristic().shouldHave(text(height));
                productDiameterCharacteristic().shouldHave(text(diameter));
                back();
            }
        }
        return this;
    }

    @Step("Add to basket from top block for all products. TyresListing_page")
    public TyresListing_page_Logic addToBasketFromTopBlock() {
        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < buyButtonsInTopBlock().size(); i++) {
                if (n == 1) {
                    nextButtonInTopBlock().click();
                } else if (n == 2) {
                    previousButtonInTopBlock().click();
                }
                buyButtonsInTopBlock().get(i).click();
                new Product_page_Logic().checksPresentProductInCartPopup();
                clearBrowserCache();
                refresh();
            }
        }
        return this;
    }

    @Step("Go to product page from top block for all products. TyresListing_page")
    public TyresListing_page_Logic goToProductPageFromTopBlock() {
        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < buyButtonsInTopBlock().size(); i++) {
                if (n == 1) {
                    nextButtonInTopBlock().click();
                } else if (n == 2) {
                    previousButtonInTopBlock().click();
                }
                productsInTopBlock().get(i).click();
                productPage().shouldBe(visible);
                back();
            }
        }
        return this;
    }

    @Step("Check Product Page Of All Products In Top Block. TyresListing_page")
    public TyresListing_page_Logic checkProductPageOfAllProductsInTopBlock() {
        List<String> urlListTopBlock = new ArrayList<>();
        for (int i = 0; i < linksInTopBlock().size(); i++) {
            urlListTopBlock.add(linksInTopBlock().get(i).attr("url"));
        }
        for (int i = 0; i < urlListTopBlock.size(); i++) {
            open(urlListTopBlock.get(i));
            productPage().waitUntil(visible, 30000);
        }
        return this;
    }

    @Step("Check All Products In Top Block And Brand Filter Interaction. TyresListing_page")
    public TyresListing_page_Logic checkProductsInTopBlockAndBrandFilter() {
        List<String> urlListTopBlock = new ArrayList<>();
        for (int i = 0; i < linksInTopBlock().size(); i++) {
            urlListTopBlock.add(linksInTopBlock().get(i).attr("url"));
        }
        String brandName = brandFilterButton().attr("data-value");
        brandFilterButton().click();
        new Listing_page_Logic().waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithProductsNumber(brandName, productTitleOnListing(), 1);
        List<String> urlListTopBlockAfterBrandFilterApplying = new ArrayList<>();
        for (int i = 0; i < linksInTopBlock().size(); i++) {
            urlListTopBlockAfterBrandFilterApplying.add(linksInTopBlock().get(i).attr("url"));
        }
        Assert.assertEquals(urlListTopBlock, urlListTopBlockAfterBrandFilterApplying);

        return this;
    }

    @Step("Check All Products In Top Block And Rating Filter Interaction. TyresListing_page")
    public TyresListing_page_Logic checkProductsInTopBlockAndRatingFilter() {
        List<String> urlListTopBlock = new ArrayList<>();
        for (int i = 0; i < linksInTopBlock().size(); i++) {
            urlListTopBlock.add(linksInTopBlock().get(i).attr("url"));
        }
        new Listing_page_Logic().clickThreeRatingStarsInFilter()
                .waitUntilPreloaderDisappear();
        checkThreeStarsRatingInEveryProductOnListingTyres();
        List<String> urlListTopBlockAfterBrandFilterApplying = new ArrayList<>();
        for (int i = 0; i < linksInTopBlock().size(); i++) {
            urlListTopBlockAfterBrandFilterApplying.add(linksInTopBlock().get(i).attr("url"));
        }
        Assert.assertEquals(urlListTopBlock, urlListTopBlockAfterBrandFilterApplying);

        return this;
    }

    @Step("Method checks that every product on tyres listing has three stars rating. TyresListing_page")
    public TyresListing_page_Logic checkThreeStarsRatingInEveryProductOnListingTyres() {
        for (int i = 1; i < productsRatingOnListing().size(); i++) {
            productsRatingOnListing().get(i).shouldHave(attribute("style", "width: 61.2%;"));
        }
        return this;
    }

    @Step("Method checks that every product on tyres listing has five stars rating. TyresListing_page")
    public TyresListing_page_Logic checkFiveStarsRatingInEveryProductOnListingTyres() {
        for (int i = 1; i < productsRatingOnListing().size(); i++) {
            productsRatingOnListing().get(i).shouldHave(attribute("style", "width: 102%;"));
        }
        return this;
    }

    @Step("Method checks two unique ratings on listing. Listing_page")
    public TyresListing_page_Logic checkTwoUniqueRatingOnTyresListing() {
        Assert.assertTrue(fiveRatingStarsOnTyresListing().size() < ratingInProductBlock().size());
        return this;
    }

    @Step("Check brand relink on tyres listing. TyresListing_page")
    public TyresListing_page_Logic checkBrandRelink() {
        String brandNameInSidebar = brandFilterTyresInSidebar().attr("data-value");
        brandFilterTyresInSidebar().click();
        brandNameInSelector().shouldHave(text(brandNameInSidebar));
        new Listing_page_Logic().checkProductTitleOnListing(brandNameInSidebar, true, titleOfAllProducts());
        checkingContainsUrl(brandNameInSidebar.toLowerCase());
        return this;
    }

    @Step("Check brand relink on tyres listing. TyresListing_page")
    public TyresListing_page_Logic checkDiameterRelink() {
        diameter17InRelinkBlock().click();
        diameterValueInSelector().shouldHave(text("17"));
        checkingContainsUrl("/17-zoll");
        checkCharacteristicOnListing("17", radiusCharacteristic());
        return this;
    }

    @Step("Click all sizes button and check redirect. TyresListing_page")
    public TyresListing_page_Logic clickAllSizesButtonAndCheckRedirect() {
        if (url().contains("reifen/offroad-suv")) {
            allSizesButton().hover().click();
            checkingContainsUrl("reifen/type_list/offroad-suv");
        } else if (url().contains("reifen/llkw")) {
            allSizesButton().hover().click();
            checkingContainsUrl("reifen/type_list/llkw");
        } else if (url().contains("reifen/motorrad")) {
            allSizesButton().hover().click();
            checkingContainsUrl("reifen/type_list/motorrad");
        } else {
            allSizesButton().hover().click();
            checkingContainsUrl("reifen/type_list");
        }
        return this;
    }

    @Step("Click dimension button and check redirect. TyresListing_page")
    public TyresListing_page_Logic clickDimensionButtonAndCheckRedirect(SelenideElement dimensionLinkButton) {
        String routeName = getNameRouteFromJSVarInHTML();
        String baseUrl = url();
        String urlToRedirect = dimensionLinkButton.attr("href");
        String dimension = dimensionLinkButton.text().replaceAll("\\D|\\s", "");
        String width = dimension.substring(0, 3);
        String height = dimension.substring(3, 5);
        String diameter = dimension.substring(5, 7);
        dimensionLinkButton.hover().click();
        if (!baseUrl.contains("motorrad")) {
            waitingWhileLinkBecomeExpected(urlToRedirect);
        } else {
            sleep(10000);
        }
        checkCharacteristicOnListing(width, widthCharacteristic());
        checkCharacteristicOnListing(height, heightCharacteristic());
        checkCharacteristicOnListing(diameter, radiusCharacteristic());
        widthValueInSelector().waitUntil(visible, 5000).shouldHave(text(width));
        heightValueInSelector().shouldHave(text(height));
        diameterValueInSelector().shouldHave(text(diameter));
        if (routeName.equals("tyres_dimension") | routeName.equals("tyres_size")) {
            String baseUrlWithDimension = baseUrl.replaceAll("\\d", "").replaceAll("\\/--r", "").replaceAll("\\/-zoll", "");
            String urlWithDimension = (baseUrlWithDimension + "/" + width + "-" + height + "-r" + diameter);
            waitingWhileLinkBecomeExpected(urlWithDimension);
        } else if (routeName.equals("tyres_season_size") | routeName.equals("tyres_type_list")) {
            checkingContainsUrl(width + "-" + height + "-r" + diameter);
        } else {
            String urlWithDimension = (baseUrl + "/" + width + "-" + height + "-r" + diameter);
            waitingWhileLinkBecomeExpected(urlWithDimension);
        }
        return this;
    }

    @Step("Check brand relink block visibility. TyresListing_page")
    public TyresListing_page_Logic checkBrandRelinkBlockVisibility() {
        brandRelinkBlock().shouldBe(visible);
        return this;
    }

    @Step("Check diameter relink block visibility. TyresListing_page")
    public TyresListing_page_Logic checkDiameterRelinkBlockVisibility() {
        diameterRelinkBlock().shouldBe(visible);
        return this;
    }

    @Step("Check payments block visibility. TyresListing_page")
    public TyresListing_page_Logic checkPaymentsBlockVisibility() {
        paymentsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check dimension relink block visibility. TyresListing_page")
    public TyresListing_page_Logic checkDimensionRelinkBlockVisibility() {
        dimensionRelinkBlock().shouldBe(visible);
        return this;
    }

    @Step("Check delivery block visibility. TyresListing_page")
    public TyresListing_page_Logic checkDeliveryBlockVisibility() {
        deliveryBlock().shouldBe(visible);
        return this;
    }

    @Step("Check advantages block visibility. TyresListing_page")
    public TyresListing_page_Logic checkAdvantagesBlockVisibility() {
        advantagesBlock().shouldHaveSize(5);
        return this;
    }

    @Step("Check radius block visibility on tyres catalog route. TyresListing_page")
    public TyresListing_page_Logic checkRadiusBlockVisibilityOnTyresCatalogRoute() {
        diameterBlockOnCatalogTyresRoute().shouldBe(visible);
        return this;
    }

    @Step("Check sizes block visibility on tyres catalog route. TyresListing_page")
    public TyresListing_page_Logic checkSizesBlockVisibilityOnTyresCatalogRoute() {
        sizesBlockOnCatalogTyresRoute().shouldBe(visible);
        return this;
    }

    @Step("Click diameter button and check redirect. TyresListing_page")
    public TyresListing_page_Logic clickDiameterButtonAndCheckRedirectCatalogRoute() {
        String baseUrl = url().replaceAll("/type_list", "");
        String diameter = diameterLinkCatalogRoute().text().replace("R", "");
        diameterLinkCatalogRoute().hover().click();
        diameterValueInSelector().waitUntil(visible, 5000).shouldHave(text(diameter));
        checkCharacteristicOnListing(diameter, radiusCharacteristic());
        String urlWithDiameter = (baseUrl + "/" + diameter + "-zoll");
        waitingWhileLinkBecomeExpected(urlWithDiameter);
        return this;
    }

    @Step("Check breadcrumbs block visibility. TyresListing_page")
    public TyresListing_page_Logic checkBreadcrumbsBlockVisibility() {
        breadcrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check breadcrumbs first button transition. TyresListing_page")
    public TyresListing_page_Logic checkBreadcrumbsFirstButtonTransiton() {
        breadcrumbsFirstButton().click();
        waitWhileRouteBecomeExpected("tyres");
        back();
        return this;
    }

    @Step("Check breadcrumbs second button transition (example: typeOfTransport = llkw). TyresListing_page")
    public TyresListing_page_Logic checkBreadcrumbsSecondButtonTransiton(String typeOfTransport) {
        breadcrumbsSecondButton().click();
        checkingContainsUrl("/reifen/" + typeOfTransport);
        back();
        return this;
    }

    @Step("Check breadcrumbs third button transition (example: parameterInURL  = llkw/205-65-r15). TyresListing_page")
    public TyresListing_page_Logic checkBreadcrumbsThirdButtonTransiton(String parameterInURL) {
        breadcrumbsThirdButton().click();
        checkingContainsUrl("/reifen/" + parameterInURL);
        back();
        return this;
    }

    @Step("Check breadcrumbs third button. (example: buttonTitle = Ganzjahresreifen or 195 75 R16 or Pirelli). TyresListing_page")
    public TyresListing_page_Logic checkBreadcrumbsLastButton(String buttonTitle) {
        breadcrumbsLastButton().shouldNotHave(attribute("href"));
        breadcrumbsLastButton().shouldHave(text(buttonTitle));
        return this;
    }

    @Step("Check brand filter applying on tyres listing. TyresListing_page")
    public TyresListing_page_Logic checkBrandFilterApplying() {
        String brandName = brandFilterButton().attr("data-value");
        brandFilterButton().click();
        waitUntilPreloaderDisappear();
        checkBrandIsSelectedInBrandBlock(brandName);
        new Listing_page_Logic().checkProductAttributeOnListingWithProductsNumber(brandName, productTitleOnListing(), 1);
        brandNameInSelector().shouldHave(text(brandName));

        String secondBrandName = brandFilterButton().attr("data-value");
        brandFilterButton().click();
        waitUntilPreloaderDisappear();
        secondActiveBrandInBlock().shouldHave(attribute("data-value", secondBrandName));
        new Listing_page_Logic().checkProductAttributeOnListingWithProductsNumber(secondBrandName, productTitleOnListing(), 1);
        brandNameInSelector().shouldHave(text(secondBrandName));
        brandNameInSelector().shouldHave(text(brandName));

        secondActiveBrandInBlock().click();
        waitUntilPreloaderDisappear();
        secondActiveBrandInBlock().shouldNotBe(visible);
        brandNameInSelector().shouldNotHave(text(secondBrandName));
        brandNameInSelector().shouldHave(text(brandName));
        new Listing_page_Logic().checkProductAttributeOnListingWithProductsNumber(brandName, productTitleOnListing(), 1);

        firstActiveBrandInBlock().click();
        waitUntilPreloaderDisappear();
        firstActiveBrandInBlock().shouldNotBe(visible);
        brandNameInSelector().shouldHave(text("Alle"));

        return this;
    }

    @Step("Check brand filter applying on tyres listing moto route. TyresListing_page")
    public TyresListing_page_Logic checkBrandFilterApplyingMotoRoute() {
        String brandName = motoBrandFilterButton().attr("data-value");
        motoBrandFilterButton().click();
        waitUntilPreloaderDisappear();
        checkBrandIsSelectedInBrandBlockMotoRoute(brandName);
        new Listing_page_Logic().checkProductAttributeOnListingWithProductsNumber(brandName, productTitleOnListing(), 1);
        brandNameInSelector().shouldHave(text(brandName));

        String secondBrandName = motoBrandFilterButton().attr("data-value");
        motoBrandFilterButton().click();
        waitUntilPreloaderDisappear();
        checkBrandIsSelectedInBrandBlockMotoRoute(secondBrandName);
        new Listing_page_Logic().checkProductAttributeOnListingWithProductsNumber(secondBrandName, productTitleOnListing(), 1);
        brandNameInSelector().shouldHave(text(secondBrandName));
        brandNameInSelector().shouldHave(text(brandName));

        secondActiveBrandInBlockMoto().click();
        waitUntilPreloaderDisappear();
        secondActiveBrandInBlockMoto().shouldNotBe(visible);
        brandNameInSelector().shouldNotHave(text(brandName));
        brandNameInSelector().shouldHave(text(secondBrandName));
        new Listing_page_Logic().checkProductAttributeOnListingWithProductsNumber(secondBrandName, productTitleOnListing(), 1);

        firstActiveBrandInBlockMoto().click();
        waitUntilPreloaderDisappear();
        firstActiveBrandInBlockMoto().shouldNotBe(visible);
        brandNameInSelector().shouldHave(text("Alle"));

        return this;
    }


    @Step("Check Brand Is Selected In Brand Block Moto Route. TyresListing_page")
    private TyresListing_page_Logic checkBrandIsSelectedInBrandBlockMotoRoute(String brandName) {
        firstActiveBrandInBlockMoto().shouldHave(attribute("data-value", brandName));
        return this;
    }

    @Step("Wait until preloader disappear. TyresListing_page")
    private TyresListing_page_Logic waitUntilPreloaderDisappear() {
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        return this;
    }

    @Step("Check brand filter visibility. TyresListing_page")
    public TyresListing_page_Logic checkBrandFilterVisibility() {
        brandFilterBlockOnTyresListing().shouldBe(visible);
        return this;
    }

    @Step("Check rating filter visibility. TyresListing_page")
    public TyresListing_page_Logic checkRatingFilterVisibility() {
        ratingFilterBlock().shouldBe(visible);
        return this;
    }

    @Step("Check brand listing transition. TyresListing_page")
    public TyresListing_page_Logic checkBrandListingTransition(String brandName) {
        new Listing_page_Logic().checkProductAttributeOnListing(brandName, titleOfAllProducts());
        brandNameInSelector().shouldHave(text(brandName));
        return this;
    }

    @Step("Check season value in selector. TyresListing_page")
    public TyresListing_page_Logic checkSeasonValueInSelector(String expectedValue) {
        seasonValueInSelector().shouldHave(text(expectedValue));
        return this;
    }

    @Step("Check brand listing transition with number of products. TyresListing_page")
    public TyresListing_page_Logic checkBrandListingTransitionWithNumberOfProducts(String brandName, int numberOfProducts) {
        new Listing_page_Logic().checkProductAttributeOnListingWithProductsNumber(brandName, titleOfAllProducts(), numberOfProducts);
        brandNameInSelector().shouldHave(text(brandName));
        return this;
    }

    @Step("Check width value in selector. TyresListing_page")
    public TyresListing_page_Logic checkWidthValueInSelector(String expectedValue) {
        widthValueInSelector().shouldHave(exactText(expectedValue));
        return this;
    }

    @Step("Check height value in selector. TyresListing_page")
    public TyresListing_page_Logic checkHeightValueInSelector(String expectedValue) {
        heightValueInSelector().shouldHave(exactText(expectedValue));
        return this;
    }

    @Step("Check diameter value in selector. TyresListing_page")
    public TyresListing_page_Logic checkDiameterValueInSelector(String expectedValue) {
        diameterValueInSelector().shouldHave(exactText(expectedValue));
        return this;
    }

    @Step("Checks presence product listing block. TyresListing_Page")
    public TyresListing_page_Logic checkPresenceProductListing() {
        productListingOnPage().shouldBe(visible);
        return this;
    }

    @Step("Get product ID in listing and compares it to the ID on the product page. TyresListing_Page")
    public TyresProduct_page_Logic getProductIdAndComparesItToIdOnProductPage() {
        String productIDInListing = buyButton().getAttribute("data-id");
        System.out.println(productIDInListing);
        brandNameInListing().click();
        String productIdInProductPage = new Product_page_Logic().getProductId();
        System.out.println(productIdInProductPage);
        Assert.assertEquals(productIDInListing, productIdInProductPage);
        return page(TyresProduct_page_Logic.class);
    }
}
