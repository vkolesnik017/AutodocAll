package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_car_list_page_Logic extends LKW_Category_car_list_page {
    @Step("Check successfully LKW_Category car list page loading .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkSuccessfullyLKWCategoryCarListPageLoading(String currentUrl) {
        listingOfProducts().shouldBe(visible);
        Assert.assertTrue(url().contains(currentUrl));
        return this;
    }


    @Step("Check links in bread crumbs block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkLinksInBreadCrumbsBlockTecDocListing() {
        breadCrumbsLinks().shouldHave(size(5));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Ölfilter"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("MERCEDES-BENZ"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("ACTROS")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check link click in bread crumbs block  TecDoc Listing.LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkLinkClickInBreadCrumbsBlock() {
        firstLinkClick().checkSuccessfullyMakerCarListPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/mercedes-benz/actros");
        back();
        secondLinkClick().checkSuccessfullyCategoryMakerPageLoading("https://lkwteile.autodoc.de/ersatzteile/filter");
        back();
        thirdLinkClick().checkSuccessfullyLKWCategoryCarListPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mercedes-benz/actros?car_id=1000784");
        back();
        fourthLinkClick().checkSuccessfullyCategoryMakerPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mercedes-benz");
        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_car_list_page")
    public LKW_maker_car_list_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_car_list_page")
    public LKW_Category_maker_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_maker_Logic.class);
    }

    @Step("Click on third link in bread crumbs block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic thirdLinkClick() {
        thirdLinkOfBreadCrumbsBlock().click();
        return this;
    }

    @Step("Click on fourth link in bread crumbs block .LKW_Category_car_list_page")
    public LKW_Category_maker_Logic fourthLinkClick() {
        fourthLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_maker_Logic.class);
    }

    @Step("Check links in bread crumbs block TecDoc catalog .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkLinksInBreadCrumbsBlockTecDocCatalog() {
        breadCrumbsLinks().shouldHave(size(4));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        firstLinkOfBreadCrumbsBlockTitleTecDoC().shouldHave(exactText("MERCEDES-BENZ"));
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("ACTROS"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("1831 AK"));
        return this;
    }

    @Step("Check link click in bread crumbs block TecDoc Catalog .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkLinkClickTecDocCatalog() {
        firstLinkClickTecDocCatalog().checkUrlWithSelectingCar("https://lkwteile.autodoc.de/lastkraftwagen/mercedes-benz");
        back();
        secondLinkClickTecDocCatalog().checkSuccessfullyMakerCarListPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/mercedes-benz/actros");
        return this;
    }

    @Step("Click on first link in bread crumbs block TecDoc catalog .LKW_Category_car_list_page")
    public LKW_Categories_maker_page_Logic firstLinkClickTecDocCatalog() {
        firstLinkOfBreadCrumbsBlockTitleTecDoC().click();
        return page(LKW_Categories_maker_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block TecDoc catalog .LKW_Category_car_list_page")
    public LKW_maker_car_list_Logic secondLinkClickTecDocCatalog() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_maker_car_list_Logic.class);
    }


}
