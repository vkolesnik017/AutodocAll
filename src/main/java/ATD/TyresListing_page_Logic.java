package ATD;


import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

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
    public TyresListing_page_Logic selectSeasonTyreOnListing( String season) {
        new Tyres_page_Logic().selectSeasonTyre(season);
        return this;
    }

    @Step("Select Width Tyre On Listing")
    public TyresListing_page_Logic selectWidthTyreOnListing( String width) {
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
    public TyresListing_page_Logic checkBrandIsSelectedInBrandBlock() {
        firstActiveBrandInBlock().shouldHave(attribute("alt", "Apollo"));
        return this;
    }

    @Step("Check Speed Index In Selector")
    public TyresListing_page checkSpeedIndexInSelector() {
        speedIndexOnListingValue().shouldHave(text("H:"));
        return this;
    }

    @Step("Get value from selector. TyresListing_page")
    public TyresListing_page_Logic getValueFromSelectorAndCheckTopBlockProducts() {
        String width = widthValueInSelector().text();
        String height = heightValueInSelector().text();
        String diameter = diameterValueInSelector().text();
        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < productsInTopBlock().size(); i++) {
                if (n == 1) {
                    nextButtonInTopBlock().click();
                } else if (n==2) {
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

    @Step("Add to basket from top block. TyresListing_page")
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
        }return this;
    }

    @Step("Go to product page from top block. TyresListing_page")
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
        }return this;
    }

}
