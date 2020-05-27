package ATD;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.page;

public class Tyres_page_Logic extends Tyres_page {

        @Step("Select Season Tyre")
        public Tyres_page_Logic selectSeasonTyre(String season) {
            seasonDropdown().selectOption(season);
            Wait().until(webDriver -> seasonDropdown().getSelectedText().equals(season));
            return this;
        }

        @Step("Select Width")
        public Tyres_page_Logic selectWidth(String width) {
            widthDropdown().selectOption(width);
            Wait().until(webDriver -> widthDropdown().getSelectedText().equals(width));
            return this;
        }

        @Step("Select Height")
        public Tyres_page_Logic selectHeight(String height) {
            heightDropdown().selectOption(height);
            Wait().until(webDriver -> heightDropdown().getSelectedText().equals(height));
            return this;
        }


        @Step("Select Type")
        public Tyres_page_Logic selectType(String type) {
            typeDropdown().selectOption(type);
            Wait().until(webDriver -> typeDropdown().getSelectedText().equals(type));
            return this;
        }

        @Step("Select Diameter")
        public Tyres_page_Logic selectDiameter(String diameter) {
            diameterDropdown().selectOption(diameter);
            Wait().until(webDriver -> diameterDropdown().getSelectedText().equals(diameter));
            return this;
        }

        @Step("Click Submit Tyres Selector")
        public TyresListing_page_Logic clickSubmitTyresSelector() {
            submitTyresSelectorButton().click();
            return page(TyresListing_page_Logic.class);
        }

        @Step("Click Submit Tyres Selector To Check Popup")
        public Tyres_page_Logic clickSubmitTyresSelectorToCheckPopup() {
            submitTyresSelectorButton().click();
            return this;
        }

        @Step("Check Tyres Selector Visibility")
        public Tyres_page_Logic checkTyresSelectorVisibility() {
            tyresSelectorBlock().shouldBe(visible);
            return this;
        }

        @Step("Check Tyres Selector Visibility PKW")
        public Tyres_page_Logic checkTyresSelectorVisibilityPKW() {
            tyresSelectorBlockPKW().shouldBe(visible);
            return this;
        }

        @Step("Check Tyres Selector Visibility SUV")
        public Tyres_page_Logic checkTyresSelectorVisibilitySUV() {
            tyresSelectorBlockSUV().shouldBe(visible);
            return this;
        }

        @Step("Check Tyres Selector Visibility LLKW")
        public Tyres_page_Logic checkTyresSelectorVisibilityLLKW() {
            tyresSelectorBlockLLKW().shouldBe(visible);
            return this;
        }

        @Step("Check Tyres Selector Visibility MOTO")
        public Tyres_page_Logic checkTyresSelectorVisibilityMOTO() {
            tyresSelectorBlockMOTO().shouldBe(visible);
            return this;
        }

        @Step("Select Tab SUV")
        public Tyres_page_Logic selectTabSUV() {
            tabSUVtype().click();
            return this;
        }

        @Step("Select Tab LLKW")
        public Tyres_page_Logic selectTabLLKW() {
            tabLLKWtype().click();
            return this;
        }

        @Step("Select Tab MOTO")
        public Tyres_page_Logic selectTabMOTO() {
            tabMOTOtype().click();
            return this;
        }

        @Step("Validation Popup With Clear Height Width Diameter")
        public Tyres_page_Logic validationPopupWithClearHeightWidthDiameter() {
            tyresValidationPopup().shouldHave(text("Das Feld Breite ist erforderlich und muss einen Wert enthalten. Das Feld Höhe ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
            return this;
        }

        @Step("Validation Popup With Clear Height Diameter")
        public Tyres_page_Logic validationPopupWithClearHeightDiameter() {
            tyresValidationPopup().shouldHave(text("Das Feld Höhe ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
            return this;
        }

        @Step("Validation Popup With Clear Diameter")
        public Tyres_page_Logic validationPopupWithClearDiameter() {
            tyresValidationPopup().shouldHave(text("Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
            return this;
        }

        @Step("Validation Popup With Clear Height Typ Diameter")
        public Tyres_page_Logic validationPopupWithClearHeightTypDiameter() {
            tyresValidationPopup().shouldHave(text("Das Feld Höhe ist erforderlich und muss einen Wert enthalten. Das Feld Typ ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
            return this;
        }

        @Step("Validation Popup With Clear Typ Diameter")
        public Tyres_page_Logic validationPopupWithClearTypDiameter() {
            tyresValidationPopup().shouldHave(text("Das Feld Typ ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
            return this;
        }

        @Step("Close Tyres Validation Popup")
        public Tyres_page_Logic closeTyresValidationPopup() {
            closeTyresValidationPopupButton().click();
            return this;
        }

        @Step("Click Brand Dropdown")
        public Tyres_page_Logic clickBrandDropdown() {
            brandDropdown().click();
            return this;
        }

        @Step("Click Apollo Brand")
        public Tyres_page_Logic clickApolloBrand() {
            brandApolloInDropdown().click();
            return this;
        }

        @Step("Click Speed Index Dropdown")
        public Tyres_page_Logic clickSpeedIndexDropdown() {
            speedIndexDropdown().click();
            return this;
        }

        @Step("Click Speed Index H")
        public Tyres_page_Logic clickSpeedIndexH() {
            speedIndexHinDropdown().click();
            return this;
        }

        @Step("Click all tyres brands button. Tyres_page")
        public Tyres_page_Logic clickAllTyresBrandsButton() {
            allBrandsButton().click();
            return this;
        }

        @Step("Click all tyres sizes button. Tyres_page")
        public Tyres_page_Logic clickAllTyresSizesButton() {
            allSizesButton().click();
            return this;
        }

        @Step("Click mobile App link. Tyres_page")
        public Tyres_page_Logic clickMobileAppLink() {
            mobileAppLink().click();
            return this;
        }

        @Step("Click video link. Tyres_page")
        public Tyres_page_Logic clickVideoLink() {
            videoLink().click();
            return this;
    }

}
