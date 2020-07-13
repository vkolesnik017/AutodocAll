package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Maker_car_list_page_Logic extends Maker_car_list_page {

    @Step("Verify name route equals maker_car_list. Maker_car_list_page")
    public Maker_car_list_page_Logic verifyNameRouteEqualsMakerCarList() {
        waitWhileRouteBecomeExpected("maker_car_list");
        return this;
    }

    @Step("Input text in search bar by catalog. Maker_car_list_page")
    public Maker_car_list_page_Logic inputTextInSearchBarByCatalog(String text) {
        new Categories_page_Logic().inputTextInSearchBarByCatalog(text);
        return this;
    }

    @Step("Click tooltip in search by catalog by exact text. Maker_car_list_page")
    public Category_car_list_page_Logic clickTooltipInSearchByCatalogByExactText(String exactTooltipText) {
        new Categories_page_Logic().clickTooltipInSearchByCatalogByExactText(exactTooltipText);
        return page(Category_car_list_page_Logic.class);
    }

    @Step("Click oil filter category link. Maker_car_list_page")
    public Category_car_list_page_Logic clickOilFilterCategoryLink() {
        new Categories_page_Logic().clickOilFilterCategoryLink();
        return page(Category_car_list_page_Logic.class);
    }

    @Step(" select car in selector . Maker_car_list_page")
    public Maker_car_list_page_Logic selectCarInSelector(String marke, String model, String motor) {
        selectorInCloseCondition().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        markeInSelector().selectOptionByValue(marke);
        modelInSelector().selectOptionByValue(model);
        motorInSelector().selectOptionByValue(motor);
        btnSearchOfSelector().click();
        mainFormOfSelector().shouldNotBe(visible);
        return this;
    }

    @Step("added current url to list . Maker_car_list_page")
    public Maker_car_list_page_Logic addedCurrentUrlToList(List<String> list) {
        selectorInCloseCondition().shouldBe(visible);
        list.add(url());
        return this;
    }

    @Step("check selected vehicle in PopUp of garage icon . Maker_car_list_page")
    public Maker_car_list_page_Logic checkSelectedVehicleInPopUpOfGarageIcon(List<String> list) {

        List<String> listOfVehicleFromPopUp = new ArrayList<>();
        garageIconInHeader().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        for (int i = 0; i < urlsOfAddedVehicleInPopUpOfGarageInHeader().size(); i++) {
            listOfVehicleFromPopUp.add(urlsOfAddedVehicleInPopUpOfGarageInHeader().get(i).getAttribute("href"));
        }
        Assert.assertTrue(urlsOfAddedVehicleInPopUpOfGarageInHeader().get(0).getAttribute("href").equals(list.get(list.size() - 1)));
        urlsOfAddedVehicleInPopUpOfGarageInHeader().shouldHaveSize(list.size());
        Assert.assertEquals(getSortedList(listOfVehicleFromPopUp), getSortedList(list));
        return this;
    }

    @Step("get sorted list . Maker_car_list_page")
    public List<String> getSortedList(List<String> list) {
        List<String> expectedSortedList = new ArrayList<>(list);
        Collections.sort(expectedSortedList);
        return expectedSortedList;
    }

    @Step("clear list of vehicle in pop-up of garage icon . Maker_car_list_page")
    public Maker_car_list_page_Logic clearListOfVehicleInPopUpOfGarageIcon() {
        btnClearVehicleListInPopUpOfGarageIcon().shouldBe(visible).click();
        urlsOfAddedVehicleInPopUpOfGarageInHeader().shouldHaveSize(0);
        return this;
    }

    @Step("checking list of added vehicle in header after selected a new vehicle . Maker_car_list_page")
    public Maker_car_list_page_Logic checkListOfAddedVehicleInHeaderAfterSelectedNewAuto() {
        headingOfSearchByCatalog().shouldBe(visible);
        garageIconInHeader().shouldBe(visible).click();
        urlsOfAddedVehicleInPopUpOfGarageFromSelector().get(0).shouldBe(visible).shouldHave(attribute("href", url()));
        radioBtnOfAddedVehicleInPopUpOfGarageFromSelector().get(0).shouldHave(attribute("checked", "true"));
        return this;
    }


    @Step("select truck block in header . Maker_car_list_page")
    public LKW_main_page_Logic selectTruckBlock() {
        mainCategoriesInHeader().get(0).shouldBe(visible).click();
        return page(LKW_main_page_Logic.class);
    }

    @Step("close popUp of my garage block . Maker_car_list_page")
    public Maker_car_list_page_Logic closePopUpOfMyGarageBlock() {
        garageIconInHeaderActive().shouldBe(visible).click();
        return this;
    }

    @Step("open selector from My garage block . Maker_car_list_page")
    public Maker_car_list_page_Logic openSelectorFromMyGarageBlock() {
        garageIconInHeader().shouldBe(visible).click();
        btnAddedVehicleOfMyGaragePopUp().shouldBe(visible).click();
        selectorFromMyGarageBlock().shouldBe(visible);
        return this;
    }

    @Step("select Truck in selector  from My garage block. Maker_car_list_page")
    public LKW_maker_car_list_Logic selectTruckInSelectorFromMyGarage(String brand, String model, String motor) {
        trucksTab().click();
        markeOfTruckInSelector().shouldBe(visible).selectOptionByValue(brand);
        modelOfTruckInSelector().shouldBe(visible).selectOptionByValue(model);
        motorOfTruckInSelector().shouldBe(visible).selectOptionByValue(motor);
        btnSearchOfSelectorFromMyGarage().click();
        return page(LKW_maker_car_list_Logic.class);
    }
}
