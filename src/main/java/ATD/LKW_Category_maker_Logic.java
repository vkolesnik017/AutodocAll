package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_maker_Logic extends LKW_Category_maker {

    @Step("check successfully child category page loading. LKW_Category_maker ")
    public LKW_Category_maker_Logic checkSuccessfullyCategoryMakerPageLoading(String currentUrl) {
        childCategoryBlockSideBar().shouldBe(visible);
        Assert.assertEquals(url(), currentUrl);
        return this;
    }

    @Step("Check the presence of  Breadcrumbs block .LKW_Category_maker")
    public LKW_Category_maker_Logic checkOfPresenceBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check links in bread crumbs block .LKW_Category_maker")
    public LKW_Category_maker_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(6));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("ÖLFILTER"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("DAF")).shouldNotHave(attribute("href"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("Baureihe auswählen")).shouldNotHave(attribute("href"));
        sixthLinkOfBreadCrumbsBlock().shouldHave(exactText("Typ wählen")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check  link click in bread crumbs block .LKW_Category_maker")
    public LKW_Category_maker_Logic checkLinkClickInBreadCrumbsBlock() {
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        secondLinkClick().checkSuccessfullyLKWParentCategoryPageLoading("https://lkwteile.autodoc.de/ersatzteile/filter");
        back();
        thirdLinkClick().checkSuccessfullyChildCategoryPageLoading();
        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_maker")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_maker")
    public LKW_Parent_Category_page_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("Click on third link in bread crumbs block .LKW_Category_maker")
    public LKW_Category_page_Logic thirdLinkClick() {
        thirdLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("visibility of headline   .LKW_Category_maker")
    public LKW_Category_maker_Logic visibilityOfHeadLine() {
        headlineInHeader().shouldBe(visible);
        return this;
    }


}
