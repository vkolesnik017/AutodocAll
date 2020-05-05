package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Categories_page_Logic extends LKW_Categories_page {

    @Step("Check successfully LKW_Categories page loading .LKW_Categories_page")
    public LKW_Categories_page_Logic checkSuccessfullyLKWCategoriesPageLoading() {
        verticalTruckSelectorInCloseCondition().shouldBe(visible);
        headlineInHeader().shouldBe(visible).shouldHave(exactText("LKW Ersatzteilkatalog"));
        Assert.assertTrue(url().contains("https://lkwteile.autodoc.de/ersatzteile"));
        return this;
    }

    @Step("visibility of headline .LKW_Categories_page")
    public LKW_Categories_page_Logic visibilityOfHeadLine() {
        headlineInHeader().shouldBe(visible);
        titleOfCatalog().shouldBe(visible);
        return this;
    }

    @Step("Select truck in vertical selector .LKW_Categories_page")
    public LKW_maker_car_list_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        verticalTruckSelectorInCloseCondition().click();
        verticalTruckSelectorInOpenCondition().shouldBe(visible);
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelector().click();
        verticalTruckSelectorInOpenCondition().should(disappear);
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("select brand of car in vertical truck selector .LKW_Categories_page")
    public LKW_Categories_page_Logic selectBrandOfCarInVerticalSelector(String valueOfBrand) {
        verticalTruckSelectorInCloseCondition().shouldBe(visible).click();
        markeOfVerticalTruckSelector().shouldBe(visible).selectOptionByValue(valueOfBrand);
        markeOfVerticalTruckSelector().shouldHave(value(valueOfBrand));
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_Categories_page")
    public LKW_Categories_page_Logic resetOfCarBrandFieldInVerticalSelector() {
        resetBtnInVerticalCarSelectorInOpenCondition().shouldBe(visible).click();
        return this;
    }

    @Step("visibility of headline of TOP products block .LKW_Categories_page")
    public LKW_Categories_page_Logic visibilityOfHeadlineOfTopProductsBlock() {
        headlineOfTopProductsBlock().shouldBe(visible);
        return this;
    }


    @Step("availability of top block and top products .LKW_Categories_page")
    public LKW_Categories_page_Logic availabilityOfTopProductsBlock() {
        topBLock().shouldBe(visible);
        productsOfTopBlock().shouldHaveSize(6);
        return this;
    }
}
