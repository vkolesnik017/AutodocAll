package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

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


    @Step("Check verification link click in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Category_maker_brand_page_Logic checkVerificationLinkClick() {
        checkVerificationFirstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        checkVerificationSecondLinkClick().checkSuccessfullyLKWParentCategoryPageLoading();
        back();
        checkVerificationThirdLinkClick().checkSuccessfullyChildCategoryPageLoading();
        back();
        checkVerificationFourthLinkClick().checkSuccessfullyCategoryBrandPageLoading();
        return this;
    }

    @Step("Check verification first link click in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Categories_page_Logic checkVerificationFirstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Check verification second link click in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Parent_Category_page_Logic checkVerificationSecondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("Check verification third link click in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Category_page_Logic checkVerificationThirdLinkClick() {
        thirdLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("Check verification fourth link click in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Category_brand_page_Logic checkVerificationFourthLinkClick() {
        fourthLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_brand_page_Logic.class);
    }
}
