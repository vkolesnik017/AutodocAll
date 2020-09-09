package PKW;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static ATD.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.waitWhileRouteContainsExpectedCondition;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Motoroil_viscosity_page_Logic extends Motoroil_viscosity_page {

    @Step("presence of bread crumbs block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of products list block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfProductsList() {
        productsListBlock().shouldBe(visible);
        return this;
    }

    @Step("checking the first link to the presence of the text. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkToPresenceOfTextInFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).shouldNotBe(empty);
        return this;
    }


    @Step("checking count of links in breadcrumbs block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic countOfLinksInBreadCrumbsBlock(int expectedSize) {
        linksOfBreadCrumbsBlock().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check transition by click on links of breadcrumbs. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs("0W-30");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_viscosity_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_viscosity_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkThirdLinkOfBreadCrumbs(String text) {
        linksOfBreadCrumbsBlock().get(2).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }

    @Step("presence of Payment methods block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfPaymentMethodsBlock() {
        paymentMethodsBlock().shouldBe(exist);
        imageOfPaymentMethods().shouldHaveSize(9);
        return this;
    }

    @Step("presence of Advantages block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfAdvantagesBlock() {
        advantagesBlock().shouldBe(visible);
        headLineOfAdvantagesBlock().shouldBe(visible);
        return this;
    }

    @Step("check Count of advantages links. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkCountOfAdvantagesLinks(int expectedSize) {
        advantagesLinks().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check structure of Advantages blocks. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkStructureOfAdvantagesBlock() {
        for (int i = 0; i < advantagesLinks().size(); i++) {
            titleOfAdvantagesLinks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("visibility of hovering text of Advantages links. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic visibilityOfHoveringTextOfAdvantagesLinks() {
        advantagesBlock().scrollIntoView("{block: \"center\"}");
        for (int i = 0; i < advantagesLinks().size(); i++) {
            advantagesLinks().get(i).hover();
            hoveringTextOfAdvantagesLinks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("presence of main headline. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfMainHeadline() {
        mainHeadline().shouldBe(visible).shouldNotBe(empty);
        return this;
    }

    @Step("presence Of characteristic in main Headline. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfCharacteristicInMainHeadline(String characteristicFromUrl) {
        mainHeadline().shouldHave(text(characteristicFromUrl));
        return this;
    }

    @Step("select filter by Tolerance. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic selectFilterByTolerance(String expectedFilter, String expectedCondition) {
        toleranceFilterBlock().shouldBe(exist);
        clickOnToleranceFilter(expectedFilter);
        waitWhileRouteContainsExpectedCondition(expectedCondition);
        presenceAttributeOfCheckBox(expectedFilter).shouldHave(attribute("checked"));
        return this;
    }

    @Step("check selector with selected Tolerance filter. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkSelectorWithSelectedToleranceFilter(String expectedValue) {
        toleranceFieldInSelector().shouldBe(visible).shouldHave(value(expectedValue));
        return this;
    }

    @Step("check listing with selected tolerance filter. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkListingWithSelectedToleranceFilter(String expectedToleranceField) {
        for (int i = 0; i < toleranceCharacteristicsInProductDescription().size(); i++) {
            String[] arrayOfCharacteristic;
            ArrayList<String> listOfCharacteristics = new ArrayList<>();
            arrayOfCharacteristic = toleranceCharacteristicsInProductDescription().get(i).getText().replace(" ", "").split(",");
            Collections.addAll(listOfCharacteristics, arrayOfCharacteristic);
            Assert.assertTrue(listOfCharacteristics.contains(expectedToleranceField.replace(" ", "")));
            listOfCharacteristics.clear();
        }
        return this;
    }

    @Step("click on Tolerance filter. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic clickOnToleranceFilter(String expectedValue) {
        visibleLinksOfToleranceBlock(expectedValue).shouldBe(visible).click();
        return this;
    }

    @Step("get current url. Motoroil_viscosity_page")
    public String getCurrentUrl() {
        String currentUrl = url();
        return currentUrl;
    }

    @Step("check count of selected Tolerance filter. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkCountOfSelectedToleranceFilter() {
        selectedToleranceFilter().shouldHaveSize(1);
        return this;
    }

    @Step("check Viscosity grade field in selector. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkViscosityGradeFieldInSelector(String expectedValue) {
        viscosityGradeInSelector().shouldBe(visible).shouldHave(value(expectedValue));
        return this;
    }

    @Step(" visibility Of selected viscosity in block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic visibilityOfSelectedViscosityInBlock(String expectedViscosityLink) {
        selectedViscosityLink(expectedViscosityLink).shouldBe(visible);
        return this;
    }

    @Step("presence of main listing of products. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfMainListingOfProducts() {
        productsListBlock().shouldBe(visible);
        return this;
    }

    @Step("get id of added product to basket. Motoroil_viscosity_page")
    public String getIdOfAddedProductToBasket(int positionOfAddedProduct) {
        String idOfProduct = btnAddedProductToBasket().get(positionOfAddedProduct).getAttribute("data-ga-entity-product-id");
        return idOfProduct;
    }

    @Step("get Volume of added product. Motoroil_viscosity_page")
    public String getVolumeOfAddedProduct(int positionOfAddedProduct) {
        String volumeOfProduct;
        if (activeValueOfVolumeAtProduct().get(positionOfAddedProduct).has(attribute("listtype", "listing"))) {
            volumeOfProduct = activeValueOfVolumeAtProduct().get(positionOfAddedProduct).getText().replaceAll("[^0-9]", "");
        } else {
            volumeOfProduct = "1";
        }
        return volumeOfProduct;
    }

    @Step("added Product to basket. Motoroil_viscosity_page")
    public Cart_page_Logic addProductToBasket(int positionOfAddedProduct) {
        btnAddedProductToBasket().get(positionOfAddedProduct).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }
}
