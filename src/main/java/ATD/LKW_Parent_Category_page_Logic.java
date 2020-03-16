package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Parent_Category_page_Logic extends LKW_Parent_Category_page {

    @Step("Check successfully LKW_Categories page loading .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic checkSuccessfullyLKWParentCategoryPageLoading() {
        childCategoryBlock().shouldBe(visible);
        Assert.assertTrue(url().contains("https://lkwteile.autodoc.de/ersatzteile/filter"));
        return this;
    }

    @Step("Check links in bread crumbs block .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(4));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter")).shouldNotHave(attribute("href"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Hersteller auswählen")).shouldNotHave(attribute("href"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("Modell auswählen")).shouldNotHave(attribute("href"));
        closeToolTipPopUpIfVisible();
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_maker_brand_page")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Close tooltip text pop-up in vertical selector .LKW_Category_maker_brand_page")
    public LKW_Parent_Category_page_Logic closeToolTipPopUpIfVisible() {
        if (closeToolTipTextSelector().isDisplayed()) {
            closeToolTipTextSelector().click();
        }
        return this;

    }

}
