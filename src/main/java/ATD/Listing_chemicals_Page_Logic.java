package ATD;


import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Listing_chemicals_Page_Logic extends Listing_chemicals_Page {

    @Step("Get name title Category. Listing_chemicals_Page")
    public String getNameTitleCategory() {
        return titleNameCategory().getText();
    }

    @Step("Checking presence name title. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceNameTitle() {
        Assert.assertFalse(titleNameCategory().text().isEmpty());
        return this;
    }

    @Step("Checking presence brands block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceBrandsBlock() {
        blockBrands().shouldBe(visible);
        return this;
    }

    @Step("Checking presence bread crumbs block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceBreadCrumbsBlock() {
        blockBreadCrumbs().shouldBe(visible);
        return this;
    }

    @Step("Click on first bread crumb. Listing_chemicals_Page")
    public Categories_page_Logic clickOnFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Categories_page_Logic.class);
    }

    @Step("Click on second bread crumb. Listing_chemicals_Page")
    public Index_chemicals_page_Logic clickOnSecondBreadCrumb() {
        secondBreadCrumb().click();
        return page(Index_chemicals_page_Logic.class);
    }

    @Step("Checking presence and not clickable third bread crumb. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceAndNotClickableThirdBreadCrumb() {
        thirdBreadCrumb().shouldBe(visible).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Checking change display of products on the list view and then grid. Listing_chemicals_Page")
    public  Listing_chemicals_Page_Logic checkingChangeDisplayProductsAsListAndGrid() {
        blockChangePositionProductsOnListOrGrid().shouldBe(visible);
        listingProductsDisplayedAsList().shouldBe(visible);
        btnChangePositionProductsAsGrid().click();
        listingProductsDisplayedAsGrid().shouldBe(visible);
        btnChangePositionProductsAsList().click();
        listingProductsDisplayedAsList().shouldBe(visible);
        return this;
    }

    @Step("Checking presence quantity products block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceQuantityProductBlock() {
        blockProductQuantity().shouldBe(visible);
        return this;
    }

    @Step("Checking presence pagination in top and lower part page. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresencePaginationInTopAndLowerPartPage() {
        topPaginationBlock().shouldBe(visible);
        lowerPaginationBlock().scrollIntoView(false).shouldBe(visible);
        return this;
    }

    @Step("Checking work btn prev, next, first, last in pagination. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingWorkButtonInPagination() {
        btnPageThirdInPagination().click();
        checkingContainsUrl("page=3");
        btnForReturnOnFirstPageInPagination().shouldBe(visible);
        btnPreviousInPagination().shouldBe(visible);
        btnNextInPagination().shouldBe(visible);
        btnLastInPagination().shouldBe(visible);
        btnPreviousInPagination().click();
        checkingContainsUrl("page=2");
        btnNextInPagination().click();
        checkingContainsUrl("page=3");
        btnLastInPagination().click();
        btnLastInPagination().shouldNotBe(visible);
        btnForReturnOnFirstPageInPagination().click();
        btnForReturnOnFirstPageInPagination().shouldNotBe(visible);
        return this;
    }

    @Step("Click on second brand in block brands. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickOnSecondBrand() {
        secondVisibleBrand().click();
        return this;
    }

    @Step("Click on seven brands in block brands. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickOnSevenBrand() {
        sevenVisibleBrand().click();
        return this;
    }

    @Step("Get value first visible brand in block brands. Listing_chemicals_Page")
    public String getValueFirstVisibleBrandInBlockBrands() {
        return firstVisibleBrandToGetData().getAttribute("value");
    }

    @Step("Get value second visible brand in block brands. Listing_chemicals_Page")
    public String getValueSecondVisibleBrandInBlockBrands() {
        return secondVisibleBrandToGetData().getAttribute("value");
    }

    @Step("Get value seven visible brand in block brands. Listing_chemicals_Page")
    public String getValueSevenVisibleBrandInBlockBrands() {
        return sevenVisibleBrandToGetData().getAttribute("value");
    }

    @Step("Get name second brand from brands block. Listing_chemicals_Page")
    public String getNameFromSecondBrand() {
        return secondVisibleBrandImg().getAttribute("alt");
    }

    @Step("Get name seven brand from brands block. Listing_chemicals_Page")
    public String getNameFromSevenBrand() {
        return sevenVisibleBrandImg().getAttribute("alt");
    }



    @Step("Checking sorting products by brands and display selected brands at top of list . Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingSortingProductsByBrandsAndDisplayBrandsAtTopList() {
        Listing_page_Logic listingPageLogic = new Listing_page_Logic();
        String valueSecondActiveBrand = getValueSecondVisibleBrandInBlockBrands();
        String valueSevenActiveBrand = getValueSevenVisibleBrandInBlockBrands();
        String secondBrandName = getNameFromSecondBrand();
        String sevenBrandName = getNameFromSevenBrand();
        clickOnSecondBrand();
        listingPageLogic.waitUntilPreloaderDisappear();
        String firstSelectedBrand = getValueFirstVisibleBrandInBlockBrands();
        Assert.assertEquals(valueSecondActiveBrand, firstSelectedBrand);
        listingPageLogic.checkProductTitleOnListing(secondBrandName, true, titleNameProductsFromListing());
        clickOnSevenBrand();
        listingPageLogic.waitUntilPreloaderDisappear();
        ArrayList<String> twoFirstBrandsFromBegin = new ArrayList<>();
        String firstCheckedBrand = getValueFirstVisibleBrandInBlockBrands();
        String secondCheckedBrand = getValueSecondVisibleBrandInBlockBrands();
        twoFirstBrandsFromBegin.add(firstCheckedBrand);
        twoFirstBrandsFromBegin.add(secondCheckedBrand);
        if (!twoFirstBrandsFromBegin.contains(valueSecondActiveBrand) && twoFirstBrandsFromBegin.contains(valueSevenActiveBrand)) {
            Assert.fail( " Two first brands does not match selected brands");
        }
        listingPageLogic.checkProductTitleOnListingWithTwoExpectedTexts(secondBrandName, sevenBrandName, true, titleNameProductsFromListing());
        return this;
    }





}
