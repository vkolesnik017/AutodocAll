package ATD;


import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

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

        @Step("Check top block presence. Tyres_page")
        public Tyres_page_Logic checkTopBlock() {
            topBlock().shouldBe(visible);
            productsInTopBlock().shouldBe(sizeLessThanOrEqual(12));
            return this;
        }

        @Step("Click Michelin brand link in brand catalog. Tyres_page")
        public TyresListing_page_Logic clickMichelinLink() {
            michelinBrandInBrandCatalog().click();
            return page(TyresListing_page_Logic.class);
        }

        @Step("Click brand in top block and check redirect. Tyres_page")
        public String getBrandNameAndClickButtonInTopBlock() {
            String brandName = brandButtonInTopBlock().attr("alt").split(" ")[0];
            brandButtonInTopBlock().click();
            return brandName;
        }

        @Step("Click season button and check transition. Tyres_page")
        public TyresListing_page_Logic clickSeasonButtonAndCheckTransition() {
            winterButtonInSeasonBlock().hover().click();
            return page(TyresListing_page_Logic.class);
        }

        @Step("Click tyre in top block and check redirect. Tyres_page")
        public String clickTyreInTopBlockAndGetTyreId() {
            topBlock().shouldBe(visible);
            String tyreId = productInTopBlock().attr("data-ga-action");
            productInTopBlock().click();
            return tyreId;
        }

        @Step("Check all brands list presence. Tyres_page")
        public Tyres_page_Logic checkAllBrandsListPresence() {
            allBrandsList().shouldBe(visible);
            return this;
        }

        @Step("Check all brands list title presence. Tyres_page")
        public Tyres_page_Logic checkAllBrandsListTitlePresence() {
            allBrandsListTitle().shouldBe(visible);
            if (url().contains("/offroad-suv")) {
                allBrandsListTitle().shouldHave(text("Alle 4x4 Reifen Hersteller"));
            } else if (url().contains("/llkw")) {
                allBrandsListTitle().shouldHave(text("Alle Transporterreifen Hersteller"));
            } else if (url().contains("/motorrad")) {
                allBrandsListTitle().shouldHave(text("Alle Reifen für Motorrad Hersteller"));
            } else {
                allBrandsListTitle().shouldHave(text("Alle PKW Reifen Hersteller"));
            }
            return this;
        }

    @Step("Check tyres size selector presence. Tyres_page")
    public Tyres_page_Logic checkTyresSizeSelectorPresence() {
        tyresSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step("Check tyres season block presence. Tyres_page")
    public Tyres_page_Logic checkTyresSeasonBlockPresence() {
        seasonBlock().shouldBe(visible);
        if (url().contains("/motorrad")) {
            seasonsInSeasonBlock().shouldHaveSize(2);
        } else {
            seasonsInSeasonBlock().shouldHaveSize(3);
        }
        return this;
    }

    @Step("Check tyres diameter relink block presence. Tyres_page")
    public Tyres_page_Logic checkTyresDiameterRelinkBlockPresence() {
            diameterRelinkBlock().shouldBe(visible);
            linksInDiameterblock().shouldHave(sizeGreaterThan(5));
            return this;
    }

    @Step("Check tyres dimension relink block presence. Tyres_page")
    public Tyres_page_Logic checkTyresDimensionRelinkBlockPresence() {
            dimensionRelinkBlock().shouldBe(visible);
            linksInDimensionRelinkBlock().shouldHave(sizeGreaterThan(5));
            return this;
    }

    @Step("Check popular brands block visibility. Tyres_page")
    public Tyres_page_Logic checkPopularBrandsBlockVisibility() {
            brandTopBlock().shouldBe(visible);
            brandTopBlockTitle().shouldHave(exactText("Hochwertige Reifenmarken zur Auswahl"));
            return this;
    }

    @Step("Check popular brand block first position. Tyres_page")
    public Tyres_page_Logic checkPopularBrandsSliderFirstPosition() {
        brandsInSlider().shouldHaveSize(14);
        List<String> brandsInFirstSliderPosition = new ArrayList<>();
        List<String> brandsInSecondSliderPosition = new ArrayList<>();
            for (int i = 0; i < brandsInSlider().size(); i++) {
                if (i < 7) {
                    brandsInSlider().get(i).shouldBe(visible);
                    brandsInFirstSliderPosition.add(brandsInSlider().get(i).attr("alt"));
                } else {
                    brandsInSlider().get(i).shouldNotBe(visible);
                    brandsInSecondSliderPosition.add(brandsInSlider().get(i).attr("alt"));
                }
            }
        Assert.assertNotEquals(brandsInFirstSliderPosition, brandsInSecondSliderPosition);
        return this;
    }

    @Step("Check popular brand block second position. Tyres_page")
    public Tyres_page_Logic checkPopularBrandsSliderSecondPosition() {
        brandsInSlider().shouldHaveSize(14);
        List<String> brandsInFirstSliderPosition = new ArrayList<>();
        List<String> brandsInSecondSliderPosition = new ArrayList<>();
        for (int i = 0; i < brandsInSlider().size(); i++) {
            if (i < 7) {
                brandsInSlider().get(i).shouldNotBe(visible);
                brandsInFirstSliderPosition.add(brandsInSlider().get(i).attr("alt"));
            } else {
                brandsInSlider().get(i).shouldBe(visible);
                brandsInSecondSliderPosition.add(brandsInSlider().get(i).attr("alt"));
            }
        }
        Assert.assertNotEquals(brandsInFirstSliderPosition, brandsInSecondSliderPosition);
        return this;
    }

    @Step("Click second page in brand slider. Tyres_page")
    public Tyres_page_Logic clickSecondPageInBrandSlider() {
            secondButtonInTopBrandSlider().click();
            return this;
    }

    @Step("Сhecks the number of identical brands and size tyres in top block. Tyres_page")
    public Tyres_page_Logic checkingNumberIdenticalBrandAndSizeInTopBlock(int expectedSize) {
        ArrayList<String> brandAndSizeTyres = new ArrayList<>();
            for (int i = 0; i < productsInTopBlock().size(); i++) {
                String nameBrand = brandProductFromTopTyresBlock().get(i).getAttribute("alt");
                String size = sizeProductFromTopTyresBlock().get(i).getText();
                brandAndSizeTyres.add(nameBrand);
                brandAndSizeTyres.add(size);
            }
        Collections.sort(brandAndSizeTyres);

            for (int i = 0; i < brandAndSizeTyres.size(); i++) {
                String name = brandAndSizeTyres.get(i);
                int quantity = Collections.frequency(brandAndSizeTyres,name);

                if (quantity > expectedSize) {
                    Assert.fail("Finds more than three brands or size! " + name + " =" + quantity);
                }
                System.out.println(name + " (quantity =" + quantity +")");
            }
            return this;
    }


}
