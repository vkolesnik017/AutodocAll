package PKW;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Motoroil_viscosity_page_Logic extends Motoroil_viscosity_page {

    @Step("presence of bread crumbs block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of products list block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfProductsList() {
        productsListBlock().shouldBe(visible);
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

    @Step("check transition by click on links of breadcrumbs. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs("0W-30");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_viscosity_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_viscosity_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkThirdLinkOfBreadCrumbs(String text) {
        linksOfBreadCrumbsBlock().get(2).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }

    @Step("presence of Payment methods block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfPaymentMethodsBlock() {
        paymentMethodsBlock().shouldBe(exist);
        imageOfPaymentMethods().shouldHaveSize(9);
        return this;
    }

    @Step("presence of Advantages block. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic presenceOfAdvantagesBlock() {
        advantagesBlock().shouldBe(visible);
        headLineOfAdvantagesBlock().shouldBe(visible);
        return this;
    }

    @Step("check Count of advantages links. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkCountOfAdvantagesLinks(int expectedSize) {
        advantagesLinks().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check structure of Advantages blocks. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic checkStructureOfAdvantagesBlock() {
        for (int i = 0; i < advantagesLinks().size(); i++) {
            titleOfAdvantagesLinks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("visibility of hovering text of Advantages links. Motoroil_viscosity_page")
    public Motoroil_viscosity_page_Logic visibilityOfHoveringTextOfAdvantagesLinks() {
        advantagesBlock().scrollIntoView("{block: \"center\"}");
        for (int i = 0; i < advantagesLinks().size(); i++) {
            advantagesLinks().get(i).hover();
            hoveringTextOfAdvantagesLinks().get(i).shouldBe(visible);
        }
        return this;
    }

}
