package PKW;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Car_parts_motoroil_page_Logic extends Car_parts_motoroil_page {

    @Step("presence of Oil liter icon in main headline. Car_parts_motoroil_page")
    public Car_parts_motoroil_page_Logic presenceOfOilLiterIconInMainHeadline() {
        oilLitericon().shouldBe(visible);
        return this;
    }

    @Step("presence of bread crumbs block.  Car_parts_motoroil_page")
    public Car_parts_motoroil_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("checking count of links in breadcrumbs block.  Car_parts_motoroil_page")
    public Car_parts_motoroil_page_Logic countOfLinksInBreadCrumbsBlock(int expectedSize) {
        linksOfBreadCrumbsBlock().shouldHaveSize(expectedSize);
        return this;
    }
}
