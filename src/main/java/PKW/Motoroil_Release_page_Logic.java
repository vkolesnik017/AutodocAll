package PKW;

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

public class Motoroil_Release_page_Logic extends Motoroil_Release_page {


    @Step("presence of bread crumbs block. Motoroil_Release_page")
    public Motoroil_Release_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("check transition by click on links of breadcrumbs. Motoroil_Release_page")
    public Motoroil_Release_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs("BMW LONGLIFE-01");
        return this;
    }

    @Step("check First link of breadCrumbs.Motoroil_Release_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_Release_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_Release_page")
    public Motoroil_Release_page_Logic checkThirdLinkOfBreadCrumbs(String text) {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }

    @Step("presence Of characteristic in main Headline. Motoroil_Release_page")
    public Motoroil_Release_page_Logic presenceOfCharacteristicInMainHeadline(String characteristicFromUrl) {
        mainHeadline().shouldHave(text(characteristicFromUrl));
        return this;
    }


    @Step("check transition by click in Relinking block. Motoroil_Release_page")
    public Motoroil_Release_page_Logic checkTransitionByClickInRelinkingBlock() throws SQLException {
        DataBase db = new DataBase();
        String currentMainHeadline = getCurrentHeadline();
        clickOnValueFromFirstRelinkingBlock(0).waitForChangingOfMainHeadline(currentMainHeadline);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_release3"));
        back();
        clickOnValueFromSecondRelinkingBlock(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_viscosity2"));
        back();
        clickOnValueFromThirdRelinkingBlock(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_brand2"));
        back();
        clickOnValueFromFourthRelinkingBlock(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_specification3"));
        return this;
    }

    @Step("click on value from First relinking block. Motoroil_Release_page")
    public Motoroil_Release_page_Logic clickOnValueFromFirstRelinkingBlock(int position) {
        linksOfRelinkingBlocks(1).get(position).shouldBe(visible).scrollIntoView("{block: \"center\"}").hover().click();
        return page(Motoroil_Release_page_Logic.class);
    }

    @Step("click on value from Second relinking block. Motoroil_Release_page")
    public Motoroil_viscosity_page_Logic clickOnValueFromSecondRelinkingBlock(int position) {
        linksOfRelinkingBlocks(2).get(position).shouldBe(visible).click();
        return page(Motoroil_viscosity_page_Logic.class);
    }

    @Step("click on value from Third relinking block. Motoroil_Release_page")
    public Motoroil_Brand_page_Logic clickOnValueFromThirdRelinkingBlock(int position) {
        linksOfRelinkingBlocks(3).get(position).shouldBe(visible).click();
        return page(Motoroil_Brand_page_Logic.class);
    }

    @Step("click on value from Fourth relinking block. Motoroil_Release_page")
    public Motoroil_specification_page_Logic clickOnValueFromFourthRelinkingBlock(int position) {
        linksOfRelinkingBlocks(4).get(position).shouldBe(visible).click();
        return page(Motoroil_specification_page_Logic.class);
    }

    @Step("waiting for changing of Main headline. Motoroil_Release_page")
    public Motoroil_Release_page_Logic waitForChangingOfMainHeadline(String headline) {
        mainHeadline().shouldNotHave(exactText(headline));
        return this;
    }

    @Step("get current url. Motoroil_Release_page")
    public String getCurrentHeadline() {
        mainHeadline().shouldBe(visible);
        String currentHeadLine = mainHeadline().getText();
        return currentHeadLine;
    }

    @Step("select filter by Tolerance. Motoroil_Release_page")
    public Motoroil_Release_page_Logic selectFilterByTolerance(String expectedFilter) {
        toleranceFilterBlock().shouldBe(exist);
        visibleLinksOfToleranceBlock(expectedFilter).click();
        waitWhileRouteContainsExpectedCondition("motoroel/allison-c4");
        presenceAttributeOfCheckBox(expectedFilter).shouldHave(attribute("checked"));
        return this;
    }

    @Step("check selector with selected Tolerance filter. Motoroil_Release_page")
    public Motoroil_Release_page_Logic checkSelectorWithSelectedToleranceFilter(String expectedValue) {
        toleranceFieldInSelector().shouldBe(visible).shouldHave(value(expectedValue));
        return this;
    }

    @Step("check listing with selected tolerance filter. Motoroil_Release_page")
    public Motoroil_Release_page_Logic checkListingWithSelectedToleranceFilter(String expectedToleranceField) {
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

    @Step("click on selected Tolerance filter. Motoroil_Release_page")
    public Motoroil_Search_page_Logic clickOnSelectedToleranceFilter(String expectedFilter) {
        toleranceFilterBlock().shouldBe(exist);
        visibleLinksOfToleranceBlock(expectedFilter).click();
        return page(Motoroil_Search_page_Logic.class);
    }

    @Step("presence of selector. Motoroil_Release_page")
    public Motoroil_Release_page_Logic presenceOfSelector() {
        selector().shouldBe(visible);
        return this;
    }


    @Step("visibility errorTooltip for Marke field of selector. Motoroil_Release_page")
    public Motoroil_Release_page_Logic visibilityErrorToolTipForMarkeFieldOfSelector() {
        selector().shouldBe(visible);
        btnSearchOfSelector().click();
        errorToolTipOfMarkeFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step("visibility errorTooltip for Model field of selector. Motoroil_Release_page")
    public Motoroil_Release_page_Logic visibilityErrorToolTipForModelFieldOfSelector(String idOfMarke) {
        selector().shouldBe(visible);
        markeFieldInSelector().selectOptionByValue(idOfMarke);
        btnSearchOfSelector().click();
        errorToolTipOfModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step("visibility errorTooltip for Motor field of selector. Motoroil_Release_page")
    public Motoroil_Release_page_Logic visibilityErrorToolTipForMotorFieldOfSelector(String idOfModel) {
        selector().shouldBe(visible);
        modelFieldInSelector().selectOptionByValue(idOfModel);
        btnSearchOfSelector().click();
        errorToolTipOfMotorFieldInSelector().shouldBe(visible);
        return this;
    }
}
