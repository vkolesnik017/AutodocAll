package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_makers_page_Logic extends LKW_makers_page {

    @Step("Check successfully LKW_makers page loading .LKW_makers_page")
    public LKW_makers_page_Logic checkSuccessfullyLKWMakersPageLoading() {
        brandsListBlock().shouldBe(visible);
        Assert.assertTrue(url().contains("https://lkwteile.autodoc.de/lastkraftwagen"));
        return this;
    }

    @Step("visibility of headline of selector and icon of truck  .LKW_makers_page")
    public LKW_makers_page_Logic visibilityOfHeadLineSelectorAndIconOfTruck(String selectTruck) {
        iconOfTruckInHeadlineOfSelector().shouldBe(visible);
        titleOfTruckInHeadlineOfSelector().shouldHave(exactText(selectTruck));
        return this;
    }

    @Step("Select truck in vertical selector .LKW_makers_page")
    public LKW_maker_car_list_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelector().click();
        buttonSuchenOfVerticaltruckSelector().should(disappear);
        return page(LKW_maker_car_list_Logic.class);
    }


    @Step("visibility of brands list  .LKW_makers_page")
    public LKW_makers_page_Logic visibilityOfBrandsList() {
        brandsListBlock().shouldBe(visible);
        return this;
    }


    @Step("check of elements in brands list  .LKW_makers_page")
    public LKW_makers_page_Logic checkOfElementsInBrandsList() {
        for (int i = 0; i < brandsInListBlock().size(); i++) {
            imageOfBrandsList().get(i).shouldBe(visible);
            titleOfBrandsList().get(i).shouldBe(visible);
            modelCountsOfBrandsList().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check transition at icon of truck brands in TOP brands block .LKW_makers_page")
    public LKW_Categories_maker_page_Logic checkTransitionAtIconOfTruckBrand() {
        brandOfTruckInTopBlock("MERCEDES-BENZ").click();
        return page(LKW_Categories_maker_page_Logic.class);
    }


    @Step("visibility of right count of brands and models in headline  .LKW_makers_page")
    public LKW_makers_page_Logic visibilityOfCountOfBrandsAndModels() {
        brandsListOfTruck().shouldHaveSize(28);
        List<Integer> mainCountOfModels = new ArrayList<>();
        for (int i = 0; i < countOfModels().size(); i++) {
            mainCountOfModels.add(Integer.parseInt(countOfModels().get(i).getText().replaceAll("[^0-9]", "")));
        }
        int totalAmountOfModels = getMainAmount(mainCountOfModels);
        Assert.assertEquals(totalAmountOfModels, 243);
        return this;
    }

    @Step("get main summ of models .LKW_makers_page")
    public int getMainAmount(List<Integer> countOfModels) {
        int amount = 0;
        for (int e : countOfModels) {
            amount += e;
        }
        return amount;
    }
}
