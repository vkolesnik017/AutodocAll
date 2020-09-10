package ATD;

import Common.DataBase;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Parent_Category_maker_page_Logic  extends Moto_Parent_Category_maker_page{

    @Step(" reset of motorcycle selector .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step(" select motorcycle model in selector .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic selectMotoModel(String model) {
        brandOfMotoField().shouldNotHave(exactValue("0"));
        modelFiledInSelector().shouldBe(visible).selectOptionByValue(model);
        return this;
    }

    @Step(" select motorcycle motor in selector .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic selectMotoMotor(String motor) {
        motorFiledInSelector().shouldBe(visible).selectOptionByValue(motor);
        return this;
    }

    @Step(" click on Search button in selector .Moto_Parent_Category_maker_page")
    public Moto_Catalog_page_Logic clickOnSearchButton() {
        searchButton().click();
        return page(Moto_Catalog_page_Logic.class);
    }

    @Step(" presence of exact text in headline at child category block  .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic visibilityOfHeadlineAtChildCategoryBlock(String text) {
        headlineOfChildCategoryBlock().shouldBe(visible).shouldHave(exactText(text));
        return this;
    }

    @Step(" click on child Category  .Moto_Parent_Category_maker_page")
    public Moto_Category_maker_page_Logic clickOnChildCategory(int position) {
        linksOfChildCategoriesList().get(position).shouldBe(visible).click();
        return page(Moto_Category_maker_page_Logic.class);
    }



    @Step(" check bread crumbs block .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic checkBreadCrumbsBlock() throws SQLException {
        breadCrumbsLinks().shouldHaveSize(6);
        checkFirstLinkOfBreadCrumbsLinks();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_categories"));
        back();
        checkSecondLinkOfBreadCrumbsLinks("Filter");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_categories"));
        back();
        checkThirdLinkOfBreadCrumbsLinks("BMW MOTORCYCLES");
        checkFourthLinkOfBreadCrumbsLinks("Teil Wählen");
        checkFifthLinkOfBreadCrumbsLinks("Modell Wählen");
        checkSixthLinkOfBreadCrumbsLinks("Motorrad Wählen");
        return this;
    }

    @Step(" check first link of bread crumbs links .Moto_Parent_Category_maker_page")
    public Moto_Categories_page_Logic checkFirstLinkOfBreadCrumbsLinks() {
        iconOfCatalogBrandInBreadCrumbs().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }

    @Step(" check second link of bread crumbs links .Moto_Parent_Category_maker_page")
    public Moto_Categories_page_Logic checkSecondLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(1).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Categories_page_Logic.class);
    }

    @Step(" check third link of bread crumbs links .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic checkThirdLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(2).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check fourth link of bread crumbs links .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic checkFourthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(3).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check fifth link of bread crumbs links .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic checkFifthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(4).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check sixth link of bread crumbs links .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic checkSixthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(5).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("click on child category in sidebar .Moto_Parent_Category_maker_page")
    public Moto_Category_maker_page_Logic clickOnChildCategoryInSidebar(int position) {
        childCategoriesInSideBar().get(position).shouldBe(visible).click();
        return page(Moto_Category_maker_page_Logic.class);
    }


}
