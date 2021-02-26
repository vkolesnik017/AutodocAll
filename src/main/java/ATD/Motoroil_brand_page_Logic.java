package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;

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
}
