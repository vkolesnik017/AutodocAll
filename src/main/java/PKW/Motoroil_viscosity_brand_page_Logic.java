package PKW;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;


import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.waitWhileRouteContainsExpectedCondition;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Motoroil_viscosity_brand_page_Logic extends Motoroil_viscosity_brand_page {

    @Step("presence of bread crumbs block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("checking count of links in breadcrumbs block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic countOfLinksInBreadCrumbsBlock(int expectedSize) {
        linksOfBreadCrumbsBlock().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check transition by click on links of breadcrumbs. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs().presenceOfProductsList();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil_viscosity"));
        back();
        presenceOfBreadCrumbsBlock().checkFourthLinkOfBreadCrumbs("MOBIL");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_viscosity_brand_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_viscosity_brand_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_page_Logic checkThirdLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).click();
        return page(Motoroil_viscosity_page_Logic.class);
    }

    @Step("check Fourth link of breadCrumbs. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkFourthLinkOfBreadCrumbs(String text) {
        linksOfBreadCrumbsBlock().get(3).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }

    @Step("presence Of characteristic in main Headline. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic presenceOfCharacteristicInMainHeadline(String characteristicFromUrl) {
        mainHeadline().shouldHave(text(characteristicFromUrl));
        return this;
    }

    @Step("presence of Relinking blocks. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic presenceOfRelinkingBlocks(int expectedSize) {
        relinkingBlocks().get(0).shouldBe(visible);
        relinkingBlocks().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check main elements of Relinking blocks. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkElementsOfRelinkingBlocks() {
        for (int i = 0; i < relinkingBlocks().size(); i++) {
            titleOfRelinkingBLocks(i + 1).shouldBe(visible);
            contentPartOfRelinkingBLocks(i + 1).shouldBe(visible);
        }
        return this;
    }

    @Step("get attribute from link in Relinking block. Motoroil_viscosity_brand_page")
    public String getAttributeFromLink(ElementsCollection list, int position) {
        String urlFromLink = list.get(position).getAttribute("href");
        String urlPart = urlFromLink.replace(urlFromLink.substring(urlFromLink.lastIndexOf("/")), "");
        String cutUrlPart = urlPart.replace(urlPart.substring(urlPart.lastIndexOf("/")), "");
        String expectedPart = urlFromLink.replace(cutUrlPart + "/", "");
        return expectedPart;
    }

    @Step("check transition by click in Relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkTransitionByClickInRelinkingBlock() throws SQLException {
        String firstBlock = getAttributeFromLink(linksOfRelinkingBlocks(1), 0);
        String secondBlock = getAttributeFromLink(linksOfRelinkingBlocks(2), 0);
        String thirdBlock = getAttributeFromLink(linksOfRelinkingBlocks(3), 0);
        String fourthBlock = getAttributeFromLink(linksOfRelinkingBlocks(4), 0);

        clickOnValueFromFirstRelinkingBlock(0);
        checkingContainsUrl(firstBlock);
        back();
        clickOnValueFromSecondRelinkingBlock(0);
        checkingContainsUrl(secondBlock);
        back();
        clickOnValueFromThirdRelinkingBlock(0);
        checkingContainsUrl(thirdBlock);
        back();
        clickOnValueFromFourthRelinkingBlock(0);
        checkingContainsUrl(fourthBlock);
        return this;
    }

    @Step("click on value from First relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic clickOnValueFromFirstRelinkingBlock(int position) {
        linksOfRelinkingBlocks(1).get(position).shouldBe(visible).click();
        return page(Motoroil_viscosity_brand_page_Logic.class);
    }

    @Step("click on value from Second relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic clickOnValueFromSecondRelinkingBlock(int position) {
        linksOfRelinkingBlocks(2).get(position).shouldBe(visible).click();
        return page(Motoroil_viscosity_brand_page_Logic.class);
    }

    @Step("click on value from Third relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_Release_page_Logic clickOnValueFromThirdRelinkingBlock(int position) {
        linksOfRelinkingBlocks(3).get(position).shouldBe(visible).click();
        return page(Motoroil_Release_page_Logic.class);
    }

    @Step("click on value from Fourth relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_specification_page_Logic clickOnValueFromFourthRelinkingBlock(int position) {
        linksOfRelinkingBlocks(4).get(position).shouldBe(visible).click();
        return page(Motoroil_specification_page_Logic.class);
    }

    @Step("select filter by Tolerance. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic selectFilterByTolerance(String expectedFilter, String expectedCondition) {
        toleranceFilterBlock().shouldBe(exist);
        visibleLinksOfToleranceBlock(expectedFilter).click();
        waitWhileRouteContainsExpectedCondition(expectedCondition);
        presenceAttributeOfCheckBox(expectedFilter).shouldHave(attribute("checked"));
        return this;
    }

    @Step("check selector with selected Tolerance filter. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkSelectorWithSelectedToleranceFilter(String expectedValue) {
        toleranceFieldInSelector().shouldBe(visible).shouldHave(value(expectedValue));
        return this;
    }

    @Step("check listing with selected tolerance filter. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkListingWithSelectedToleranceFilter(String expectedToleranceField) {
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

    @Step("get current url. Motoroil_viscosity_brand_page")
    public String getCurrentUrl() {
        String currentUrl = url();
        return currentUrl;
    }

    @Step("click on Tolerance filter. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic clickOnToleranceFilter(String expectedValue) {
        visibleLinksOfToleranceBlock(expectedValue).shouldBe(visible).click();
        return this;
    }

    @Step("check count of selected Tolerance filter. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkCountOfSelectedToleranceFilter() {
        selectedToleranceFilter().shouldHaveSize(1);
        return this;
    }

}
