package PKW;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Motoroil_viscosity_brand_page_Logic extends Motoroil_viscosity_brand_page {

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

    @Step("check transition by click on links of breadcrumbs. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs().presenceOfProductsList();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil_viscosity"));
        back();
        presenceOfBreadCrumbsBlock().checkFourthLinkOfBreadCrumbs("MOBIL");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_viscosity_brand_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_viscosity_brand_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_page_Logic checkThirdLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).click();
        return page(Motoroil_viscosity_page_Logic.class);
    }

    @Step("check Fourth link of breadCrumbs. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkFourthLinkOfBreadCrumbs(String text) {
        linksOfBreadCrumbsBlock().get(3).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }

    @Step("presence Of characteristic in main Headline. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic presenceOfCharacteristicInMainHeadline(String characteristicFromUrl) {
        mainHeadline().shouldHave(text(characteristicFromUrl));
        return this;
    }
}
