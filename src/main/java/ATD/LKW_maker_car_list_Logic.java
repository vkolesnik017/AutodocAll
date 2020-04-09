package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_maker_car_list_Logic extends LKW_maker_car_list {

    @Step("check successfully child category page loading .LKW_maker_car_list ")
    public LKW_maker_car_list_Logic checkSuccessfullyMakerCarListPageLoading(String currentUrl) {
        tecDocCatalog().shouldBe(visible);
        Assert.assertEquals(url(), currentUrl);
        return this;
    }

    @Step("input article of product in search_field in header. LKW_maker_car_list ")
    public LKW_maker_car_list_Logic inputArticleOfProductInSearchField() {
        inputSearchInHeader().setValue("4.90930");
        return this;
    }

    @Step("select product in search_field drop menu. LKW_maker_car_list ")
    public LKW_Product_page_Logic selectProductInSearchDropMenu() {
        dropMenuOfSearchFieldInHeader().shouldBe(visible);
        productsInDropMenuOfSearchField().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("visibility of Image of brand in headline .LKW_maker_car_list ")
    public LKW_maker_car_list_Logic visibilityOfImageBrandInHeadLine() {
        imageOfTruckInHeadLine().shouldBe(visible);
        return this;
    }

    @Step("visibility of Image of brand in headline .LKW_maker_car_list ")
    public LKW_maker_car_list_Logic visibilityOfMakrModelMotorInHeadLine() {
        titleOfTruckInHeadLine().shouldHave(exactText("Ersatzteile für MERCEDES-BENZ ACTROS 1835, 1835 L"));
        return this;
    }

    @Step("visibility of info link in headline .LKW_maker_car_list ")
    public LKW_maker_car_list_Logic visibilityOfInfoLink() {
        infoLinkInHeadline().shouldBe(visible);
        return this;
    }

    @Step("appearance of info popup with data about selected car .LKW_maker_car_list ")
    public LKW_maker_car_list_Logic appearanceOfInfoPopUpWithSelectedCar() {
        infoLinkInHeadline().click();
        infoPopUp().should(appear);
        titleOfInfoPopUp().shouldHave(exactText("Info: MERCEDES-BENZ ACTROS 1835, 1835 L (260 kW / 354 PS):"));
        return this;
    }

    @Step("availability of vertical truck selector СatalogByModel .LKW_maker_car_list")
    public LKW_maker_car_list_Logic availabilityOfVerticalTruckSelectorСatalogByModel() {
        verticalTruckSelectorInCloseState().shouldBe(visible);
        return this;
    }

    @Step("availability of vertical truck selector  TecDoc catalog .LKW_maker_car_list")
    public LKW_maker_car_list_Logic availabilityOfVerticalTruckSelectorTecDocCatalog() {
        verticalTruckSelectorInCloseStateSecond().shouldBe(visible);
        return this;
    }

    @Step("availability of vertical truck selector СatalogByModel .LKW_maker_car_list")
    public LKW_maker_car_list_Logic visibilityOfSelectorInOpenConditionСatalogByModel() {
        verticalTruckSelectorInCloseState().click();
        verticalTruckSelectorInOpenState().shouldBe(visible);
        return this;
    }

    @Step("availability of vertical truck selector TecDoc catalog .LKW_maker_car_list")
    public LKW_maker_car_list_Logic visibilityOfSelectorInOpenConditionTecDocCatalog() {
        verticalTruckSelectorInCloseStateSecond().click();
        verticalTruckSelectorInOpenState().shouldBe(visible);
        return this;
    }

    @Step("availability of vertical truck selector TecDoc catalog .LKW_maker_car_list")
    public LKW_maker_car_list_Logic visibilityOfTooltipInVerticalSelector() {
        verticalTruckSelectorInOpenState().shouldBe(visible);
        tooltipOfVerticalCarSelector().shouldBe(visible);
        return this;
    }


    @Step("open vertical selector if it close .LKW_maker_car_list")
    public LKW_maker_car_list_Logic openVerticalSelector() {
        if (verticalTruckSelectorInCloseState().isDisplayed()) {
            verticalTruckSelectorInCloseState().click();
        }
        verticalTruckSelectorInOpenState().shouldBe(visible);
        return this;
    }

    @Step("reset of vertical selector   .LKW_maker_car_list")
    public LKW_maker_car_list_Logic setupDefaultValueForVerticalSelector() {
        if (!markeInVerticalCarSelector().has(value("0"))) {
            markeInVerticalCarSelector().selectOptionByValue("0");
        }
        return this;
    }

    @Step("visibility of tooltip for marke_field in selector  .LKW_maker_car_list")
    public LKW_maker_car_list_Logic visibilityOfTooltipForMarkeFieldInCloseSelector() {
        btnSearchInVerticalCarSelector().click();
        tooltipForFieldInVerticalCarSelector().shouldBe(visible);
        return this;
    }
    @Step("visibility of tooltip for model_field in selector  .LKW_maker_car_list")
    public LKW_maker_car_list_Logic visibilityOfTooltipForModelFieldInCloseSelector() {
        markeInVerticalCarSelector().selectOptionByValue("24");
        btnSearchInVerticalCarSelector().click();
        tooltipForFieldInVerticalCarSelector().shouldBe(visible);
        return this;
    }


    @Step("visibility of tooltip for motor_field in selector  .LKW_maker_car_list")
    public LKW_maker_car_list_Logic visibilityOfTooltipForMotorFieldInCloseSelector() {
        motorInVerticalCarSelector().selectOptionByValue("714");
        btnSearchInVerticalCarSelector().click();
        tooltipForFieldInVerticalCarSelector().shouldBe(visible);
        return this;
    }

}
