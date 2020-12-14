package ATD;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Category_maker_page_Logic extends Moto_Category_maker_page {

    @Step(" select motorcycle model in selector .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic selectMotoModel(String model) {
        brandOfMotoField().shouldNotHave(exactValue("0"));
        modelFiledInSelector().shouldBe(visible).selectOptionByValue(model);
        return this;
    }

    @Step(" select motorcycle motor in selector .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic selectMotoMotor(String motor) {
        motorFiledInSelector().shouldBe(visible).selectOptionByValue(motor);
        return this;
    }

    @Step(" click on Search button in selector .Moto_Category_maker_page")
    public Moto_Category_car_list_page_Logic clickOnSearchButton() {
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }


    @Step(" check bread crumbs block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic checkBreadCrumbsBlock() throws SQLException {
        breadCrumbsLinks().shouldHaveSize(6);
        checkFirstLinkOfBreadCrumbsLinks();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_categories"));
        back();
        checkSecondLinkOfBreadCrumbsLinks("Filter");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_parent_category"));
        back();
        checkThirdLinkOfBreadCrumbsLinks("Ölfilter");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category"));
        back();
        checkFourthLinkOfBreadCrumbsLinks("BMW MOTORCYCLES");
        checkFifthLinkOfBreadCrumbsLinks("Modell Wählen");
        checkSixthLinkOfBreadCrumbsLinks("Motorrad Wählen");
        return this;
    }

    @Step(" check first link of bread crumbs links .Moto_Category_maker_page")
    public Moto_Categories_page_Logic checkFirstLinkOfBreadCrumbsLinks() {
        iconOfCatalogBrandInBreadCrumbs().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }

    @Step(" check second link of bread crumbs links .Moto_Category_maker_page")
    public Moto_Parent_Category_page_Logic checkSecondLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(1).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step(" check third link of bread crumbs links .Moto_Category_maker_page")
    public Moto_Category_page_Logic checkThirdLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(2).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step(" check fourth link of bread crumbs links .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic checkFourthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(3).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check fifth link of bread crumbs links .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic checkFifthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(4).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check sixth link of bread crumbs links .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic checkSixthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(5).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("presence of main headline block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic presenceOfMainHeadlineBlock() {
        mainHeadline().shouldBe(visible);
        return this;
    }

    @Step(" presence of brand icon in Headline .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic presenceOfBrandIconInHeadline() {
        iconOfBrandInHeadline().shouldBe(visible);
        return this;
    }

    @Step(" click on motorcycle model .Moto_Category_maker_page")
    public Moto_Category_car_list_model_page_Logic clickOnMotoModel(int position) {
        modelsLinks().get(position).shouldBe(visible).click();
        return page(Moto_Category_car_list_model_page_Logic.class);
    }

    @Step(" presence of models block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic presenceOfModelsBlock() {
        modelsBlock().shouldBe(visible);
        modelsLinks().shouldHaveSize(6);
        return this;
    }


    @Step("open of models block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic openOfModelsBlock() {
        linkMoreOfModelBlock().shouldBe(visible).click();
        modelsLinks().shouldHaveSize(21);
        return this;
    }

    @Step("minimized of model block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic minimizedOfModelBlock() {
        linkLessOfModelBlock().shouldBe(visible).click();
        modelsLinks().shouldHaveSize(6);
        return this;
    }

    @Step("presence of Headline at brands block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic presenceOfHeadlineAtBrandsBlock() {
        headlineOfBrandsBlock().shouldBe(visible);
        return this;
    }

    @Step("check brands links in close condition of brands block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic checkBrandsLinksInCloseCondition() {
        brandsLinks().shouldHaveSize(6);
        for (int i = 0; i < brandsLinks().size(); i++) {
            brandsLinks().get(i).shouldNotHave(attribute("href"));
        }
        return this;
    }

    @Step("opening brands block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic openBrandsBlock() {
        brandsBlock().shouldBe(visible);
        linkMoreOfBrandsBlock().click();
        Assert.assertTrue(brandsLinks().size() > 6);
        return this;
    }

    @Step("minimized of brands block .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic minimizedBrandsBlock() {
        brandsBlock().shouldBe(visible);
        linkMoreOfBrandsBlock().click();
        brandsLinks().shouldHaveSize(6);
        return this;
    }

    @Step("get motorcycle brand from Url .Moto_Category_maker_page")
    public String getMotoBrandFromUrl() {
        String brandFromUrl = url().replaceAll("^.+\\/", "").toUpperCase();
        return brandFromUrl;
    }

    @Step("check applicability brand motorcycle and Product .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic checkApplicabilityMotoAndProduct(String motoBrand) {

        for (int i = 0; i < imageOfTopProducts().size(); i++) {
            clickOnTopProductImage(i).checkCompatibilityProductAndMoto(motoBrand);
            back();
        }
        return this;
    }

    @Step("check applicability brand motorcycle and Product .Moto_Category_maker_page")
    public Moto_Product_page_Logic clickOnTopProductImage(int position) {
        imageOfTopProducts().get(position).shouldBe(visible).scrollIntoView("{block: \"center\"}").click();
        return page(Moto_Product_page_Logic.class);
    }


    @Step("click on child category in sidebar .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic clickOnChildCategoryInSidebar(int position) {
        childCategoriesInSideBar().get(position).shouldBe(visible).click();
        return page(Moto_Category_maker_page_Logic.class);
    }

    @Step("get title from main headline .Moto_Category_maker_page")
    public String getTitleFromMainHeadline() {
        String title = mainHeadline().getText();
        return title;
    }

    @Step("get title from main headline .Moto_Category_maker_page")
    public Moto_Category_maker_page_Logic checkOfChangeAtMainHeadline(String title) {
        mainHeadline().shouldBe(visible).shouldNotHave(exactText(title));
        return this;
    }
}
