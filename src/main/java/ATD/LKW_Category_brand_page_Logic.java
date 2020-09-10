package ATD;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_brand_page_Logic extends LKW_Category_brand_page {

    @Step("Check links in bread crumbs block .LKW_Category_brand_page")
    public LKW_Category_brand_page_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(7));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Ölfilter"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("MANN-FILTER")).shouldNotHave(attribute("href"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("Hersteller")).shouldNotHave(attribute("href"));
        sixthLinkOfBreadCrumbsBlock().shouldHave(exactText("Modell")).shouldNotHave(attribute("href"));
        seventhLinkOfBreadCrumbsBlock().shouldHave(exactText("Ersatzteile bestellen")).shouldNotHave(attribute("href"));
        return this;
    }


    @Step("Check  link click in bread crumbs block .LKW_Category_brand_page")
    public LKW_Category_brand_page_Logic checkLinkClickInBreadCrumbsBlock() throws SQLException {
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        secondLinkClick();  checkingContainsUrl(new DataBase().getRouteByRouteName("DE","lkw_parent_category"));
        back();
        thirdLinkClick();  checkingContainsUrl(new DataBase().getRouteByRouteName("DE","lkw_category2"));
        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_brand_page")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_brand_page")
    public LKW_Parent_Category_page_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("Click on third link in bread crumbs block .LKW_Category_brand_page")
    public LKW_Category_page_Logic thirdLinkClick() {
        thirdLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_page_Logic.class);
    }


    @Step("check successfully Category Brand page loading .LKW_Category_brand_page ")
    public LKW_Category_brand_page_Logic checkSuccessfullyCategoryBrandPageLoading(String currentUrl) {
        childCategoryBlockInSideBar().shouldBe(visible);
        Assert.assertEquals(url(), currentUrl);
        return this;
    }


    @Step("visibility of Image of brand in headline .LKW_Category_brand_page ")
    public LKW_Category_brand_page_Logic visibilityOfImageBrandInHeadLine() {
        imageOfBrandInHeadLine().shouldBe(visible);

        return this;
    }

    @Step("click on brand .LKW_Category_brand_page")
    public LKW_Category_maker_brand_page_Logic clickOnBrand(String titleOfBrand) {
        titleOfBrand(titleOfBrand).click();
        return page(LKW_Category_maker_brand_page_Logic.class);
    }

    @Step("Select truck in vertical selector .LKW_Category_brand_page")
    public LKW_Category_car_list_page_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelector().click();
        return page(LKW_Category_car_list_page_Logic.class);
    }

    @Step("check transition at icon of truck brands in TOP brands block .LKW_Category_brand_page")
    public LKW_Category_maker_brand_page_Logic checkTransitionAtIconOfTruckBrand() {
        brandOfTruckInTopBlock().shouldBe(visible).click();
        return page(LKW_Category_maker_brand_page_Logic.class);
    }

    @Step("click at link More for opening brands block .LKW_Category_brand_page")
    public LKW_Category_brand_page_Logic openOfBrandsBlock() {
        linkMoreOfTopBrandBlock().click();
        return this;
    }

    @Step("visibility of brands block in open condition .LKW_Category_brand_page")
    public LKW_Category_brand_page_Logic brandBlockInOpenCondition() {
        brandsOfTruckInBlock().shouldHave(sizeGreaterThanOrEqual(24));
        return this;
    }

    @Step("availability of popular model list in first row of TOP brands block .LKW_Category_brand_page")
    public LKW_Category_brand_page_Logic availabilityOfPopularModelList() {
        for (int i = 1; i <= firstRowOfBrands().size(); i++) {
            linksOfPopularModelList(i).shouldHave(sizeGreaterThan(1));
            for (int j = 0; j < linksOfPopularModelList(i).size(); j++) {
                linksOfPopularModelList(i).get(j).shouldHave(attribute("href"));
            }
        }
        return this;
    }

    @Step("select popular model from TOP brands block .LKW_Category_brand_page")
    public LKW_Category_model_brand_page_Logic selectPopularModel(int positionOfBrand, int positionOfModel) {
        linksOfPopularModelList(positionOfBrand).get(positionOfModel).click();
        return page(LKW_Category_model_brand_page_Logic.class);
    }


    @Step("corresponding of product to selected brand .LKW_Category_brand_page")
    public LKW_Category_brand_page_Logic productCorrespondTOSelectedBrand(String selectedBrand) {
        for (int i = 0; i < titleOfTopProducts().size(); i++) {
            titleOfTopProducts().get(i).shouldHave(text(selectedBrand));
        }
        return this;
    }


    @Step("visibility of headline .LKW_Category_brand_page")
    public LKW_Category_brand_page_Logic visibilityOfMainHeadline() {
         mainHeadline().shouldBe(visible).shouldHave(exactText("LKW Ölfilter von MANN-FILTER"));
        return this;
    }
}
