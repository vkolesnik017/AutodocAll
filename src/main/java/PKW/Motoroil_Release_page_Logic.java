package PKW;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
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
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil"));
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

    @Step("get attribute from link in Relinking block. Motoroil_Release_page")
    public String getAttributeFromLink(ElementsCollection list, int position) {
        String urlFromLink = list.get(position).getAttribute("href");
        String urlPart = urlFromLink.replace(urlFromLink.substring(urlFromLink.lastIndexOf("/")), "");
        String cutUrlPart = urlPart.replace(urlPart.substring(urlPart.lastIndexOf("/")), "");
        String expectedPart = urlFromLink.replace(cutUrlPart + "/", "");
        return expectedPart;
    }

    @Step("check transition by click in Relinking block. Motoroil_Release_page")
    public Motoroil_Release_page_Logic checkTransitionByClickInRelinkingBlock() throws SQLException {
        String firstBlock = getAttributeFromLink(linksOfRelinkingBlocks(1), 0);
        String secondBlock = getAttributeFromLink(linksOfRelinkingBlocks(2), 0);
        String thirdBlock = getAttributeFromLink(linksOfRelinkingBlocks(3), 0);
        String fourthBlock = getAttributeFromLink(linksOfRelinkingBlocks(4), 0);

        String currentMainHeadline = getCurrentHeadline();
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

    @Step("select Marke in selector. Motoroil_Release_page")
    public Motoroil_Release_page_Logic selectMarkeInSelector(String idOfMarke) {
        markeFieldInSelector().selectOptionByValue(idOfMarke);
        return this;
    }

    @Step("visibility of Reset button in selector. Motoroil_Release_page")
    public Motoroil_Release_page_Logic visibilityOfResetButtonOfSelector() {
        btnResetOfSelector().shouldBe(visible);
        return this;
    }

    @Step("reset of selector. Motoroil_Release_page")
    public Motoroil_page_Logic resetOfSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("select vehicle in selector. Motoroil_Release_page")
    public Car_parts_motoroil_page_Logic selectVehicleInSelector(String marke, String model, String motor) {
        selectMarkeInSelector(marke);
        modelFieldInSelector().selectOptionByValue(model);
        motorFieldInSelector().selectOptionByValue(motor);
        btnSearchOfSelector().click();
        return page(Car_parts_motoroil_page_Logic.class);
    }
}
