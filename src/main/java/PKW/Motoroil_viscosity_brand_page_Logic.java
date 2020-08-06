package PKW;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Motoroil_viscosity_brand_page_Logic extends Motoroil_viscosity_brand_page{

    @Step("presence of bread crumbs block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
        }

    @Step("checking count of links in breadcrumbs block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic countOfLinksInBreadCrumbsBlock(int expectedSize) {
        linksOfBreadCrumbsBlock().shouldHaveSize(expectedSize);
        return this;
    }
}
