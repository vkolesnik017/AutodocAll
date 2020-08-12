package PKW;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Motoroil_Search_page_Logic extends Motoroil_Search_page{

    @Step("presence of selector. Motoroil_Search_page")
    public Motoroil_Search_page_Logic presenceOfSelector() {
        selector().shouldBe(visible);
        return this;
    }


    @Step("select Marke in selector. Motoroil_Search_page")
    public Motoroil_Search_page_Logic selectMarkeInSelector(String idOfMarke) {
        markeFieldInSelector().selectOptionByValue(idOfMarke);
        return this;
    }

    @Step("visibility of Reset button in selector. Motoroil_Search_page")
    public Motoroil_Search_page_Logic visibilityOfResetButtonOfSelector() {
        btnResetOfSelector().shouldBe(visible);
        return this;
    }

    @Step("reset of selector. Motoroil_Search_page")
    public Motoroil_Search_page_Logic resetOfSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }
}
