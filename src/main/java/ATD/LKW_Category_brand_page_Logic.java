package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

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


    @Step("Check verification link click in bread crumbs block .LKW_Category_brand_page")
    public LKW_Category_brand_page_Logic checkVerificationLinkClick() {
        checkVerificationFirstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        checkVerificationSecondLinkClick().checkSuccessfullyLKWParentCategoryPageLoading();
        back();
        checkVerificationThirdLinkClick().checkSuccessfullyChildCategoryPageLoading();
        return this;
    }

    @Step("Check verification first link click in bread crumbs block .LKW_Category_brand_page")
    public LKW_Categories_page_Logic checkVerificationFirstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();

        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Check verification second link click in bread crumbs block .LKW_Category_brand_page")
    public LKW_Parent_Category_page_Logic checkVerificationSecondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("Check verification third link click in bread crumbs block .LKW_Category_brand_page")
    public LKW_Category_page_Logic checkVerificationThirdLinkClick() {
        thirdLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_page_Logic.class);
    }


    @Step("check successfully Category Brand page loading .LKW_Category_brand_page ")
    public LKW_Category_brand_page_Logic checkSuccessfullyCategoryBrandPageLoading() {
        childCategoryBlockInSideBar().shouldBe(visible);
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mf-mann-filter");
        return this;
    }
}
