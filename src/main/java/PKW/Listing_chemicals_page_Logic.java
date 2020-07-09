package PKW;


import io.qameta.allure.Step;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;


public class Listing_chemicals_page_Logic extends Listing_chemicals_page {

    @Step("Get title name listing page. Listing_chemicals_page")
    public String getTitleNameListingPage() {
       return titleNameListingPage().getText();
    }

    @Step("Click first bread crumb. Listing_chemicals_page")
    public Parts_page_Logic clickFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Parts_page_Logic.class);
    }
    @Step("Click second bread crumb. Listing_chemicals_page")
    public Index_chemicals_page_Logic clickSecondBreadCrumb() {
        secondBreadCrumb().click();
        return page(Index_chemicals_page_Logic.class);
    }

    @Step("Checking presence and not clickable third bread crumb. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresenceAndNotClickableThirdBreadCrumb() {
        thirdBreadCrumb().shouldBe(visible).shouldNotBe(attribute("href"));
        return this;
    }

    @Step("Checking presence bread crumbs block. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresenceBreadCrumbsBlock() {
        blockBreadCrumbs().shouldBe(visible);
        return this;
    }

    @Step("Checking work buttons in pagination. Listing_chemicals_page")
    public  Listing_chemicals_page_Logic checkingWorkBtnInPagination () {
        btnSecondPageInPagination().scrollIntoView(false).click();
        btnForReturnOnFirstPageInPagination().scrollIntoView(false).shouldBe(visible);
        btnPreviousInPagination().shouldBe(visible);
        btnNextInPagination().shouldBe(visible);
        btnLastInPagination().shouldBe(visible);
        btnPreviousInPagination().click();
        blockPagination().scrollIntoView(false);
        btnForReturnOnFirstPageInPagination().shouldNotBe(visible);
        btnNextInPagination().click();
        checkingContainsUrl("page=2");
        btnLastInPagination().scrollIntoView(false).click();
        blockPagination().scrollIntoView(false);
        btnLastInPagination().shouldNotBe(visible);
        btnForReturnOnFirstPageInPagination().click();
        blockPagination().scrollIntoView(false);
        btnForReturnOnFirstPageInPagination().shouldNotBe(visible);
        return this;
    }

    @Step("Checking presence pagination block and page selection. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresencePaginationBlockAndPageSelection() {
        blockPagination().scrollIntoView(false).shouldBe(visible);
        btnSecondPageInPagination().click();
        checkingContainsUrl("page=2");
        btnFourPageInPagination().scrollIntoView(false).click();
        checkingContainsUrl("page=4");
        return this;
    }

    @Step("Get name first brand in brands block. Listing_chemicals_page")
    public String getNameFirstBrandInBrandsBlock() {
        return firstBrandInBrandsBlockImg().getAttribute("Alt");
    }

    @Step("Get name third brand in brands block. Listing_chemicals_page")
    public String getNameThirdBrandInBrandsBlock() {
        return thirdBrandInBrandsBlockImg().getAttribute("Alt");
    }



    @Step("Checking presence brands block and sorting of goods by selected brand after selecting another brand, first brand is reset. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresenceBrandsBlockAndSortingProductsBySelectedBrands() {
        Listing_page_Logic listing_page_logic = new Listing_page_Logic();
        String firstBrandName = getNameFirstBrandInBrandsBlock();
        String thirdBrandName = getNameThirdBrandInBrandsBlock();
        brandsBlock().shouldBe(visible);
        firstBrandInBrandsBlock().click();
        listing_page_logic.waitUntilPreloaderDisappear();
        listing_page_logic.checkProductTitleOnListing(firstBrandName, true, titleNameProductsFromListing());
        thirdBrandInBrandsBlock().click();
        listing_page_logic.waitUntilPreloaderDisappear();
        listing_page_logic.checkProductTitleOnListing(thirdBrandName, true, titleNameProductsFromListing());
        return this;
    }

    @Step("Checking work button more and less in brands block. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingWorkBtnMoreAndLessInBrandsBlock() {
        btnMoreInBrandsBlock().click();
        btnLessInBrandsBlock().shouldBe(visible).click();
        btnLessInBrandsBlock().shouldNotBe(visible);
        return this;
    }


}
