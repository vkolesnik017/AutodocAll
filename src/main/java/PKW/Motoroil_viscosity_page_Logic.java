package PKW;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;

public class Motoroil_viscosity_page_Logic extends Motoroil_viscosity_page {

    @Step("presence of bread crumbs block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("checking the first link to the presence of the text. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkToPresenceOfTextInFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).shouldNotBe(empty);
        return this;
    }


    @Step("checking count of links in breadcrumbs block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic countOfLinksInBreadCrumbsBlock(int expectedSize) {
        linksOfBreadCrumbsBlock().shouldHaveSize(expectedSize);
        return this;
    }
}
