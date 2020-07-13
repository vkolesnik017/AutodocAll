package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        modelInVerticalCarSelector().selectOptionByValue("714");
        btnSearchInVerticalCarSelector().click();
        tooltipForFieldInVerticalCarSelector().shouldBe(visible);
        return this;
    }


    @Step("visibility of truck in selector from url  .LKW_maker_car_list")
    public LKW_maker_car_list_Logic availabilityOfTruckInSelectorFromUrl() {
        markeInVerticalCarSelector().shouldNotHave(exactValue("0"));
        modelInVerticalCarSelector().shouldNotHave(exactValue("0"));
        String brandOfCarFromSelector = markeInVerticalCarSelector().getText().toLowerCase();
        String modelOfCarFromSelector = modelInVerticalCarSelector().getText().substring(0, 2);
        Assert.assertTrue(url().contains(brandOfCarFromSelector) && url().contains(modelOfCarFromSelector));
        return this;
    }


    @Step("go to main page  .LKW_maker_car_list")
    public LKW_main_page_Logic goToMainPage() {
        logoInHeader().click();
        return page(LKW_main_page_Logic.class);
    }

    @Step("visibility of headline of selector and icon of truck  .LKW_maker_car_list")
    public LKW_maker_car_list_Logic visibilityOfHeadLineSelectorAndIconOfTruck(String selectTruck) {
        iconOfTruckInHeadlineOfSelector().shouldBe(visible);
        titleOfTruckInHeadlineOfSelector().shouldHave(exactText(selectTruck));
        return this;
    }

    @Step("select brand of car in vertical truck selector .LKW_maker_car_list")
    public LKW_maker_car_list_Logic selectBrandOfCarInVerticalSelector(String valueOfBrand) {
        markeInVerticalCarSelector().shouldBe(visible).selectOptionByValue(valueOfBrand);
        markeInVerticalCarSelector().shouldHave(value(valueOfBrand));
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_maker_car_list")
    public LKW_Categories_page_Logic resetOfVerticalSelector() {
        resetBtnInVerticalCarSelector().click();
        return page(LKW_Categories_page_Logic.class);
    }


    @Step("availability of image of brand in main headline .LKW_maker_car_list")
    public LKW_maker_car_list_Logic availabilityOfImageOfBrand() {
          imageOfBrandInMainHeadline().shouldBe(visible);
        return this;
    }


    @Step("visibility of brand and model at main headline .LKW_maker_car_list")
    public LKW_maker_car_list_Logic visibilityOfBrandAndModelAtMainHeadline() {
           mainHeadline().shouldBe(visible).shouldHave(text("DAF 45"));
        return this;
    }

    @Step("checking list of added vehicle in header after selected a new vehicle .LKW_maker_car_list")
    public LKW_maker_car_list_Logic checkListOfAddedVehicleInHeaderAfterSelectedNewAuto() {
        mainHeadline().shouldBe(visible);
        garageIconInHeader().shouldBe(visible).click();
        urlsOfAddedVehicleInPopUpOfGarageFromSelector().get(0).shouldBe(visible).shouldHave(attribute("href", url()));
        radioBtnOfAddedVehicleInPopUpOfGarageFromSelector().get(0).shouldHave(attribute("checked", "true"));
        return this;
    }

    @Step("select motorcycle block in header .LKW_maker_car_list")
    public Moto_main_page_Logic selectMotoBlock() {
        mainCategoriesInHeader().get(1).shouldBe(visible).click();
        return page(Moto_main_page_Logic.class);
    }

    @Step("added current url to list .LKW_maker_car_list")
    public LKW_maker_car_list_Logic addedCurrentUrlToList(List<String> list) {
        verticalTruckSelectorInCloseStateSecond().shouldBe(visible);
        list.add(url());
        return this;
    }

    @Step("check selected vehicle in PopUp of garage icon .LKW_maker_car_list")
    public LKW_maker_car_list_Logic checkSelectedVehicleInPopUpOfGarageIcon(List<String> list) {
        List<String> listOfVehicleFromPopUp = new ArrayList<>();
        garageIconInHeader().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        for (int i = 0; i < urlsOfAddedVehicleInPopUpOfGarageInHeader().size(); i++) {
            listOfVehicleFromPopUp.add(urlsOfAddedVehicleInPopUpOfGarageInHeader().get(i).getAttribute("href"));
        }
        Assert.assertTrue(urlsOfAddedVehicleInPopUpOfGarageInHeader().get(0).getAttribute("href").equals(list.get(list.size()-1)));
        urlsOfAddedVehicleInPopUpOfGarageInHeader().shouldHaveSize(list.size());
        Assert.assertEquals(getSortedList(listOfVehicleFromPopUp), getSortedList(list));
        return this;
    }

    @Step("get sorted list .LKW_maker_car_list")
    public List<String> getSortedList(List<String> list) {
        List<String> expectedSortedList = new ArrayList<>(list);
        Collections.sort(expectedSortedList);
        return expectedSortedList;
    }


    @Step("added current url to list .LKW_maker_car_list")
    public LKW_maker_car_list_Logic  selectTruckInSelector(String marke, String model, String motor) {
        verticalTruckSelectorInCloseStateSecond().shouldBe(visible).click();
        verticalTruckSelectorInOpenState().shouldBe(visible);
        markeInVerticalCarSelector().selectOptionByValue(marke);
        modelInVerticalCarSelector().selectOptionByValue(model);
        motorInVerticalCarSelector().selectOptionByValue(motor);
        btnSearchInVerticalCarSelector().click();
        return this;
    }

    @Step("close popUp of my garage block .LKW_maker_car_list")
    public LKW_maker_car_list_Logic closePopUpOfMyGarageBlock() {
        garageIconInHeaderActive().shouldBe(visible).click();
        return this;
    }

    @Step("open selector from My garage block .LKW_maker_car_list")
    public LKW_maker_car_list_Logic openSelectorFromMyGarageBlock() {
        garageIconInHeader().shouldBe(visible).click();
        btnAddedVehicleOfMyGaragePopUp().shouldBe(visible).click();
        selectorFromMyGarageBlock().shouldBe(visible);
        return this;
    }

    @Step("select motorcycle block in selector() .LKW_maker_car_list")
    public LKW_maker_car_list_Logic selectMotoBlockInSelector() {
        motoTab().click();
        return this;
    }

    @Step("select vehicle in selector .LKW_maker_car_list")
    public Moto_Catalog_page_Logic selectMotoInSelectorFromMyGarage(String brand, String model, String motor) {
        markeOfVehicleInSelector().shouldBe(visible).selectOptionByValue(brand);
        modelOfVehicleInSelector().shouldBe(visible).selectOptionByValue(model);
        motorOfVehicleInSelector().shouldBe(visible).selectOptionByValue(motor);
        btnSearchVehicleInSelector().click();
        return page(Moto_Catalog_page_Logic.class);
    }
}
