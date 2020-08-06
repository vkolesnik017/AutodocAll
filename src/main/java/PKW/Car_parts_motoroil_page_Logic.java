package PKW;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

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


    @Step("check transition by click on links of breadcrumbs. Car_parts_motoroil_page")
    public Car_parts_motoroil_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs().presenceOfProductsListBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil_maker"));
        back();
        presenceOfBreadCrumbsBlock().checkFourthLinkOfBreadCrumbs().presenceOfBreadCrumbsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil_maker_group2"));
        back();
        presenceOfBreadCrumbsBlock().checkFifthLinkOfBreadCrumbs("Motorenöl");
        return this;
    }

    @Step("check First link of breadCrumbs. Car_parts_motoroil_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Car_parts_motoroil_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Car_parts_motoroil_page")
    public Motoroil_Maker_page_Logic checkThirdLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).click();
        return page(Motoroil_Maker_page_Logic.class);
    }

    @Step("check Fourth link of breadCrumbs. Car_parts_motoroil_page")
    public Motoroil_Maker_Group_page_Logic checkFourthLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(3).click();
        return page(Motoroil_Maker_Group_page_Logic.class);
    }

    @Step("check Fifth link of breadCrumbs. Car_parts_motoroil_page")
    public Car_parts_motoroil_page_Logic checkFifthLinkOfBreadCrumbs(String text) {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(4).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }
}
