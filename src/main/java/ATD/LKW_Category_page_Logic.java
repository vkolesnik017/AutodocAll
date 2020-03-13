package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_page_Logic extends LKW_Category_page {
    @Step("check successfully child category page loading .LKW_Category_page ")
    public LKW_Category_page_Logic checkSuccessfullyChildCategoryPageLoading() {
        imageOfChildCategory().shouldBe(visible);
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/olfilter-200157");
        return this;
    }

    @Step("Check links in bread crumbs block .LKW_Category_page")
    public LKW_Category_page_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(6));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Lenkgetriebehydraulikfilter")).shouldNotHave(attribute("href"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("Hersteller auswählen")).shouldNotHave(attribute("href"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("Modell auswählen")).shouldNotHave(attribute("href"));
        sixthLinkOfBreadCrumbsBlock().shouldHave(exactText("Ersatzteile kaufen")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check  link click in bread crumbs block .LKW_Category_page")
    public LKW_Category_page_Logic checkLinkClickInBreadCrumbsBlock() {
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        secondLinkClick().checkSuccessfullyLKWParentCategoryPageLoading();

        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_page")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_page")
    public LKW_Parent_Category_page_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

}
