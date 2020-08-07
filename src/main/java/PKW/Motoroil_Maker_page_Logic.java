package PKW;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Motoroil_Maker_page_Logic extends Motoroil_Maker_page {

    @Step("presence of bread crumbs block. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of products list block. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic presenceOfProductsListBlock() {
        productsListBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().shouldHaveSize(3);
        return this;
    }

    @Step("check transition by click on links of breadcrumbs. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs("VW");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_Maker_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_Maker_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic checkThirdLinkOfBreadCrumbs(String text) {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }
}
