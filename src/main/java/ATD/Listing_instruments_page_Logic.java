package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Listing_instruments_page_Logic extends Listing_instruments_page {

    @Step("Get title name Category. Listing_instruments_page")
    public String getTitleNameCategory() {
        return titleNameCategory().getText();
    }

    @Step("Checking presence title page. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingPresenceTitlePage() {
        Assert.assertFalse(titleNameCategory().text().isEmpty());
        return this;
    }

    @Step("Checking presence Seo text block. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingPresenceSeoTextBlock() {
        Assert.assertFalse(seoTextBlock().text().isEmpty());
        return this;
    }

    @Step("Checking work buttons in pagination. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingWorkBtnInPagination () {
        btnSecondPageInPagination().click();
        btnReturnOnFirstPageInPagination().shouldBe(visible);
        btnPreviousPageInPagination().shouldBe(visible);
        btnNextPageInPagination().shouldBe(visible);
        btnLastPageInPagination().shouldBe(visible);
        btnPreviousPageInPagination().click();
        btnReturnOnFirstPageInPagination().shouldNotBe(visible);
        btnNextPageInPagination().click();
        PKW.CommonMethods.checkingContainsUrl("page=2");
        btnLastPageInPagination().click();
        btnLastPageInPagination().shouldNotBe(visible);
        btnReturnOnFirstPageInPagination().click();
        btnReturnOnFirstPageInPagination().shouldNotBe(visible);
        return this;
    }

    @Step("Checking presence pagination in top and lower part page. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingPresencePaginationInTopAndLowerPartPage() {
        topPaginationBlock().shouldBe(visible);
        lowerPaginationBlock().scrollIntoView(false).shouldBe(visible);
        return this;
    }

    @Step("Checking presence products count block. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingPresenceProductsCountBlock() {
        productsCountBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking change display of products on the list view and then grid. Listing_instruments_page")
    public  Listing_instruments_page_Logic checkingChangeDisplayProductsAsListAndGrid() {
        blockChangePositionProductsOnListOrGrid().shouldBe(visible);
        listingProductsDisplayedAsList().shouldBe(visible);
        btnChangePositionProductsAsGrid().click();
        listingProductsDisplayedAsGrid().shouldBe(visible);
        btnChangePositionProductsAsList().click();
        listingProductsDisplayedAsList().shouldBe(visible);
        return this;
    }

    @Step("Click on first bread crumb. Listing_instruments_page")
    public Categories_page_Logic clickOnFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Categories_page_Logic.class);
    }

    @Step("Click on second bread crumb. Listing_instruments_page")
    public Index_instruments_page_Logic clickOnSecondBreadCrumb() {
        secondBreadCrumb().click();
        return page(Index_instruments_page_Logic.class);
    }

    @Step("Checking presence and not clickable third bread crumb. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingPresenceAndNotClickableThirdBreadCrumb() {
        thirdBreadCrumb().shouldBe(visible).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Checking presence bread crumbs block. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingPresenceBreadCrumbsBlock() {
        blockBreadCrumbs().shouldBe(visible);
        return this;
    }


}
