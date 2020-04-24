package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class LKW_makers_page_Logic extends LKW_makers_page {
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
}
