package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Motoroil_brand_page_Logic extends Motoroil_brand_page {

    @Step("presence INHALT filter in sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic presenceInhaltFilterInSidebar() {
        inhaltFilterInSideBar().shouldBe(visible);
        return this;
    }

    @Step("display OEM-Freigabe Filter In Sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic displayOemFreigabeFilterInSidebar() {
        oemFreigabeFilterInSideBar().shouldBe(visible);
        return this;
    }

    @Step("display of Specification Filter block In Sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic displaySpecificationFilterInSidebar() {
        specificationFilterInSideBar().shouldBe(visible);
        return this;
    }


    @Step("set OEM-Freigabe filter In Sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic setOemFreigabeFilterInSidebar(String oemId) {
        oemFreigabeFilter(oemId).scrollTo().click();
        return this;
    }

    @Step("set Inhalt filter In Sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic setInhaltFilterInSidebar(String inhaltId) {
        inhaltFilter(inhaltId).scrollTo().click();
        return this;
    }

    @Step("appears of Loader . Motoroil_brand_page")
    public Motoroil_brand_page_Logic appearsOfLoader() {
        loaderInTecDocListing().should(appear);
        loaderInTecDocListing().should(disappear);
        return this;
    }

    @Step("check Oem-Freigabe Selector . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkOemFreigabeSelector(String expectedValue) {
        Assert.assertEquals(oemFreigabeSelector().getSelectedText(), expectedValue);
        return this;
    }

    @Step("check Specification Selector . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkSpecificationSelector(String expectedValue) {
        Assert.assertEquals(specificationSelector().getSelectedText(), expectedValue);
        return this;
    }

    @Step("check Selected CheckBox Oem-Freigabe filter . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkSelectedCheckBoxOemFreigabeFilter(String expectedId) {
        checkBoxOemFreigabeFilter(expectedId).shouldHave(attribute("checked", "true"));
        return this;
    }

    @Step("check Selected CheckBox specification filter . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkSelectedCheckBoxSpecificationFilter(String expectedId) {
        checkBoxSpecificationFilter(expectedId).shouldHave(attribute("checked", "true"));
        return this;
    }

    @Step("check Selected CheckBox Inhalt filter . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkSelectedCheckBoxInhaltFilter(String expectedId) {
        checkBoxInhaltFilter(expectedId).shouldHave(attribute("checked", "true"));
        return this;
    }

    @Step("check of Displaying Selected OEM-Freigabe Filter In Products . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkDisplaySelectedOemFreigabeFilterInProducts(String expectedValue) {
        for (int i = 0; i < oemValuesInProducts().size(); i++) {
            List<String> valuesFromProduct = new ArrayList<>(Arrays.asList(oemValuesInProducts().get(i).getText().split(", ")));
            Assert.assertTrue(valuesFromProduct.contains(expectedValue));
            valuesFromProduct.clear();
        }
        return this;
    }

    @Step("check Checkbox Oem-Freigabe Filter . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkCheckboxOemFreigabeFilter(boolean label, String expectedValue) {
        if (label == true) {
            checkBoxOemFreigabeFilter(expectedValue).shouldHave(attribute("checked"));
        } else {
            checkBoxOemFreigabeFilter(expectedValue).shouldNotHave(attribute("checked"));
        }
        return this;
    }

    @Step("check Checkbox of Specification Filter . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkCheckboxSpecificationFilter(boolean label, String expectedValue) {
        if (label == true) {
            checkBoxSpecificationFilter(expectedValue).shouldHave(attribute("checked"));
        } else {
            checkBoxSpecificationFilter(expectedValue).shouldNotHave(attribute("checked"));
        }
        return this;
    }

    @Step("check Checkbox of Inhalt Filter . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkCheckboxInhaltFilter(boolean label, String expectedValue) {
        if (label == true) {
            checkBoxInhaltFilter(expectedValue).shouldHave(attribute("checked"));
        } else {
            checkBoxInhaltFilter(expectedValue).shouldNotHave(attribute("checked"));
        }
        return this;
    }

    @Step("check Size Values Oem-Freigabe Filter In Products . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkSizeValuesOemFreigabeFilterInProducts() {
        for (int i = 0; i < oemValuesInProducts().size(); i++) {
            List<String> valuesFromProduct = new ArrayList<>(Arrays.asList(oemValuesInProducts().get(i).getText().split(", ")));
            Assert.assertTrue(valuesFromProduct.size() > 0);
            valuesFromProduct.clear();
        }
        return this;
    }

    @Step("check Size Values Specification Filter In Products . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkSizeValuesSpecificationFilterInProducts() {
        for (int i = 0; i < specificationValuesInProducts().size(); i++) {
            List<String> valuesFromProduct = new ArrayList<>(Arrays.asList(specificationValuesInProducts().get(i).getText().split(", ")));
            Assert.assertTrue(valuesFromProduct.size() > 0);
            valuesFromProduct.clear();
        }
        return this;
    }

    @Step("check Size Values Inhalt Filter In Products . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkSizeValuesInhaltFilterInProducts() {
        for (int i = 0; i < inhaltValuesInProducts().size(); i++) {
            List<String> valuesFromProduct = new ArrayList<>(Arrays.asList(inhaltValuesInProducts().get(i).getText().split(", ")));
            Assert.assertTrue(valuesFromProduct.size() > 0);
            valuesFromProduct.clear();
        }
        return this;
    }

    @Step("set Specification filter In Sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic setSpecificationFilterInSidebar(String specificationId) {
        specificationFilter(specificationId).scrollTo().click();
        return this;
    }

    @Step("check of Displaying Selected specification Filter In Products . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkDisplaySelectedSpecificationFilterInProducts(String expectedValue) {
        for (int i = 0; i < specificationValuesInProducts().size(); i++) {
            List<String> valuesFromProduct = new ArrayList<>(Arrays.asList(specificationValuesInProducts().get(i).getText().split(", ")));
            Assert.assertTrue(valuesFromProduct.contains(expectedValue));
            valuesFromProduct.clear();
        }
        return this;
    }

    @Step("check of Displaying Selected Inhalt Filter In Products . Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkDisplaySelectedInhaltFilterInProducts(String expectedValue) {
        for (int i = 0; i < inhaltValuesInProducts().size(); i++) {
            List<String> valuesFromProduct = new ArrayList<>(Arrays.asList(inhaltValuesInProducts().get(i).getText().split(", ")));
            Assert.assertTrue(valuesFromProduct.contains(expectedValue.replaceAll("[^0-9]", "")));
            valuesFromProduct.clear();
        }
        return this;
    }

    @Step(":from Motoroil_brand_page")
    public Categories_page_Logic clickFirstBreadCrumb() {
        new Listing_accessories_page_Logic().clickFirstBreadCrumb();
        return page(Categories_page_Logic.class);
    }

    @Step(":from Motoroil_brand_page")
    public Motoroil_page clickSecondBreadCrumb() {
        new Listing_accessories_page_Logic().clickSecondBreadCrumb();
        return page(Motoroil_page.class);
    }

    @Step(":from Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkingPresenceAndNotClickableLastBreadCrumb() {
        new Listing_accessories_page_Logic().checkingPresenceAndNotClickableLastBreadCrumb(new Listing_accessories_page_Logic().thirdBreadCrumb());
        return this;
    }

    @Step(":from Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkingQuantityBreadCrumbs() {
        new Listing_accessories_page_Logic().checkingQuantityBreadCrumbs();
        return this;
    }

    @Step("Check visiable filter brand block. Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkVisibleFilterBrandBlock() {
        brandOilFilterBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks visibility of viscosity oil value in selector. Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkVisibilityOfBrandNameOilValueInSelectorAndListingName(String nameBrand) {
        oilSelector().scrollTo().shouldBe(visible);
        nameOilInSelector().shouldBe(visible).shouldHave(attribute("selected"));
        brandNameInListing().shouldHave(text(nameBrand));
        return this;
    }

    @Step("Check presence a specific product in the gray button in the liter block. Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkPresenceSpecificProductInGrayBtnInLiterBlock(String idProduct) {
        specificProductInGrayBtnInLiterBlock(idProduct).scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Check absence a specific product in the gray button in the liter block. Motoroil_brand_page")
    public Motoroil_brand_page_Logic checkAbsenceSpecificProductInGrayBtnInLiterBlock(String idProduct) {
        specificProductInGrayBtnInLiterBlock(idProduct).shouldNotBe(visible);
        return this;
    }
}
