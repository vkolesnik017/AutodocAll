package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_maker_brand_page_Logic extends LKW_Category_maker_brand_page {

    @Step("Check links in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Category_maker_brand_page_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(7));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Ölfilter"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("MANN-FILTER"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("VOLVO"));
        sixthLinkOfBreadCrumbsBlock().shouldHave(exactText("LKW-Modell auswählen")).shouldNotHave(attribute("href"));
        seventhLinkOfBreadCrumbsBlock().shouldHave(exactText("LKW-Ersatzteile kaufen")).shouldNotHave(attribute("href"));
        return this;
    }


    @Step("Check  link click in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Category_maker_brand_page_Logic checkLinkClickInBreadCrumbsBlock() {
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        secondLinkClick().checkSuccessfullyLKWParentCategoryPageLoading("https://lkwteile.autodoc.de/ersatzteile/filter");
        back();
        thirdLinkClick().checkSuccessfullyChildCategoryPageLoading();
        back();
        fourthLinkClick().checkSuccessfullyCategoryBrandPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mf-mann-filter");
        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Parent_Category_page_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("Click on third link in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Category_page_Logic thirdLinkClick() {
        thirdLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("Click on fourth link in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Category_brand_page_Logic fourthLinkClick() {
        fourthLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_brand_page_Logic.class);
    }

    @Step("visibility of Image of brand in headline .LKW_Category_maker_brand_page ")
    public LKW_Category_maker_brand_page_Logic visibilityOfImageBrandInHeadLine() {
        imageOfTruckInHeadLine().shouldBe(visible);
        return this;
    }

    @Step("visibility of headline in Top brands block .LKW_Category_maker_brand_page ")
    public LKW_Category_maker_brand_page_Logic visibilityOfHeadlineInTopBrandsBlock() {
        titleOfTopBrandsBlock().shouldBe(visible);
        return this;
    }


    @Step("visibility  Top brands block .LKW_Category_maker_brand_page ")
    public LKW_Category_maker_brand_page_Logic visibilityOfTopBrandsBlock() {
        topBrandsBlock().shouldBe(visible);
        brandsOfTopBrandsBlock().shouldHave(size(6));
        return this;
    }

    @Step("check opportunity to open Top brands block .LKW_Category_maker_brand_page ")
    public LKW_Category_maker_brand_page_Logic openOfTopBrandsBlock() {
        linkMoreOfTopBrandsBlock().shouldBe(visible).click();
        linkCloseOfTopBrandsBlock().shouldBe(visible);
        brandsOfTopBrandsBlock().shouldHave(sizeGreaterThan(6));
        return this;
    }


    @Step("check opportunity to close Top brands block .LKW_Category_maker_brand_page ")
    public LKW_Category_maker_brand_page_Logic closeOfTopBrandsBlock() {
        linkCloseOfTopBrandsBlock().click();
        brandsOfTopBrandsBlock().shouldHaveSize(6);
        return this;
    }


    @Step("check successfully Category maker brand page loading .LKW_Category_maker_brand_page ")
    public LKW_Category_maker_brand_page_Logic checkSuccessfullyCategoryMakerBrandPageLoading(String currentUrl) {
        childCategoryBlockInSideBar().shouldBe(visible);
        Assert.assertEquals(url(), currentUrl);
        return this;
    }


    @Step("check successfully Category maker brand page loading .LKW_Category_maker_brand_page ")
    public LKW_Category_maker_brand_page_Logic availabilityOfCarBrandInSelectorFromUrl() {
         markeInVerticalTruckSelector().shouldNotHave(exactValue("0"));
         String selectedCarBrandFromSelector = markeInVerticalTruckSelector().getText().toLowerCase();
         Assert.assertTrue(url().contains(selectedCarBrandFromSelector));
        return this;
    }

    @Step("select brand of car in vertical truck selector .LKW_Category_maker_brand_page")
    public LKW_Category_maker_brand_page_Logic selectBrandOfCarInVerticalSelector(String valueOfBrand) {
        markeInVerticalTruckSelector().selectOptionByValue(valueOfBrand);
        markeInVerticalTruckSelector().shouldHave(value(valueOfBrand));
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_Category_maker_brand_page")
    public LKW_Category_brand_page_Logic resetOfVerticalSelector() {
        resetBtnInVerticalCarSelector().click();
        resetBtnInVerticalCarSelector().should(disappear);
        return page(LKW_Category_brand_page_Logic.class);
    }

    @Step("availability of headline in TOP model block .LKW_Category_maker_brand_page")
    public LKW_Category_maker_brand_page_Logic availabilityOfHeadlineOfTopModelBlock(String partOfHeadline) {
        headlineOfTopModelBlock().shouldBe(visible).shouldHave(text(partOfHeadline));
        return this;
    }
}
