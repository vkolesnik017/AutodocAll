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

    @Step("presence of Relinking blocks. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic presenceOfRelinkingBlocks(int expectedSize) {
        relinkingBlocks().get(0).shouldBe(visible);
        relinkingBlocks().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check main elements of Relinking blocks. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkElementsOfRelinkingBlocks() {
        for (int i = 0; i < relinkingBlocks().size(); i++) {
            titleOfRelinkingBLocks(i + 1).shouldBe(visible);
            contentPartOfRelinkingBLocks(i + 1).shouldBe(visible);
        }
        return this;
    }

    @Step("check transition by click in Relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic checkTransitionByClickInRelinkingBlock() throws SQLException {
        DataBase db = new DataBase();
        clickOnValueFromFirstRelinkingBlock(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_viscosity_brand2"));
        back();
        clickOnValueFromSecondRelinkingBlock(2);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_viscosity_brand3"));
        back();
        clickOnValueFromThirdRelinkingBlock(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_release2"));
        back();
        clickOnValueFromFourthRelinkingBlock(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_specification"));
        return this;
    }

    @Step("click on value from First relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic clickOnValueFromFirstRelinkingBlock(int position) {
        linksOfRelinkingBlocks(1).get(position).shouldBe(visible).click();
        return page(Motoroil_viscosity_brand_page_Logic.class);
    }

    @Step("click on value from Second relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_viscosity_brand_page_Logic clickOnValueFromSecondRelinkingBlock(int position) {
        linksOfRelinkingBlocks(2).get(position).shouldBe(visible).click();
        return page(Motoroil_viscosity_brand_page_Logic.class);
    }

    @Step("click on value from Third relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_Release_page_Logic clickOnValueFromThirdRelinkingBlock(int position) {
        linksOfRelinkingBlocks(3).get(position).shouldBe(visible).click();
        return page(Motoroil_Release_page_Logic.class);
    }

    @Step("click on value from Fourth relinking block. Motoroil_viscosity_brand_page")
    public Motoroil_specification_page_Logic clickOnValueFromFourthRelinkingBlock(int position) {
        linksOfRelinkingBlocks(4).get(position).shouldBe(visible).click();
        return page(Motoroil_specification_page_Logic.class);
    }

}
