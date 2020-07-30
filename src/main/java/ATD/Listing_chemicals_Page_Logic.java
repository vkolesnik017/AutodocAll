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

    @Step("Click first active brand. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickFirstVisibleBrand() {
        firstVisibleBrand().click();
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

    @Step("Get value first generic in generic block. Listing_chemicals_Page")
    public String getValueFirstGenericInGenericBlock() {
        return firstGenericInGenericsBlockToGetData().getAttribute("value");
    }

    @Step("Get value second visible brand in block brands. Listing_chemicals_Page")
    public String getValueSecondVisibleBrandInBlockBrands() {
        return secondVisibleBrandToGetData().getAttribute("value");
    }

    @Step("Get value seven visible brand in block brands. Listing_chemicals_Page")
    public String getValueSevenVisibleBrandInBlockBrands() {
        return sevenVisibleBrandToGetData().getAttribute("value");
    }

    @Step("Get id first criteria from Konsistenz block . Listing_chemicals_Page")
    public String getValueFirstCriteriaFromKonsistenzBlock() {
        return firstCriteriaFromKonsistenzBlockToGetData().getAttribute("id");
    }

    @Step("Click first criteria from Konsistenz block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickFirstCriteriaFromKonsistenzBlock() {
        firstCriteriaFromKonsistenzBlock().click();
        return this;
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

    @Step("Checking presence title categories block in sidebar. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceTitleCategoriesBlockInSidebar() {
        titleCategoriesBlockInSidebar().shouldHave(text(""));
        return this;
    }

    @Step(":from Listing_chemicals_Page")
    public ArrayList<String> getNameCategoriesFromSidebarAndTitleNamePage() {
        return new Listing_accessories_page_Logic().getNameAllCategoriesInSidebarAndTitleNamePage();
    }

    @Step("Click on first category in sidebar. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickFirstCategoryInSidebar() {
        firstCategoryInSidebar().click();
        return this;
    }

    @Step("Get name first category in sidebar. Listing_chemicals_Page")
    public  String getNameFirstCategoryInSidebar() {
        return firstCategoryInSidebar().scrollIntoView(false).getText();
    }

    @Step("Checking presence block with seo text. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceBlockWithSeoText() {
        blockSeoText().shouldBe(visible);
        return this;
    }

    @Step("Checking reset selected brand in brands block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingResetSelectedBrandInBrandsBlock() {
        firstVisibleBrandToGetData().shouldHave(attribute("data-checked","false"));
        clickFirstVisibleBrand();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        firstVisibleBrandToGetData().shouldHave(attribute("data-checked","true"));
        clickFirstVisibleBrand();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        firstVisibleBrandToGetData().shouldHave(attribute("data-checked","false"));
        return this;
    }

    @Step("Click btn reset All in generics block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickBtnResetAllInGenericsBlock() {
        btnResetAllInGenericsBlock().click();
        return this;
    }

    @Step("Click first generic in generics block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickFirstGenericInGenericsBlock() {
        firstGenericInGenericsBlock().click();
        return this;
    }

    @Step("Click second generic in generics block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickSecondGenericInGenericsBlock() {
        secondGenericInGenericsBlock().click();
        return this;
    }

    @Step("Click btn reset all filter from selected filter block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickBtnResetAllFilterFromFilterBlock() {
        btnResetAllFilterInResetBlock().click();
        return this;
    }

    @Step("Click btn reset filter from selected filter block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic clickBtnResetFilterFromFilterBlock() {
        firstBtnResetFilterInResetBlock().click();
        return this;
    }


    @Step("Get name from first generic in generics block. Listing_chemicals_Page")
    public String  getNameFirstGenericInGenericBlock() {
        return firstGenericInGenericsBlock().getText();
    }

    @Step("Get name from second generic in generics block. Listing_chemicals_Page")
    public String  getNameSecondGenericInGenericBlock() {
        return secondGenericInGenericsBlock().getText();
    }

    @Step("Checking sorting products by generic. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingSortingProductsByGeneric() {
        Listing_page_Logic listingPageLogic = new Listing_page_Logic();
        String firstGeneric = getNameFirstGenericInGenericBlock();
        String secondGeneric = getNameSecondGenericInGenericBlock();
        clickFirstGenericInGenericsBlock();
        listingPageLogic.waitUntilPreloaderDisappear();
        listingPageLogic.checkProductTitleOnListing(firstGeneric, true, titleNameProductsFromListing());
        clickSecondGenericInGenericsBlock();
        listingPageLogic.waitUntilPreloaderDisappear();
        listingPageLogic.checkProductTitleOnListing(secondGeneric, true, titleNameProductsFromListing());
        firstGenericInGenericsBlockToGetData().shouldHave(attribute("data-checked", "true"));
        secondGenericInGenericsBlockToGetData().shouldHave(attribute("data-checked", "false"));
        return this;

    }

    @Step("Checking presence generic block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceGenericBlock() {
        blockGeneric().shouldBe(visible);
        return this;
    }

    @Step("Checking work btn Previous and Next in generic block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingWorkBtnPrevAndNextInGenericBlock() {
        if (allGenericsInGenericBlock().size() > 6) {
            btnNextInGenericBlock().click();
            firstGenericByIndexInGenericsBlock().waitUntil(attribute("aria-hidden", "true"), 20000);
            firstGenericByIndexInGenericsBlock().shouldNotBe(visible);
            btnPreviousInGenericBlock().click();
            firstGenericByIndexInGenericsBlock().waitUntil(attribute("aria-hidden", "false"), 20000);
            firstGenericByIndexInGenericsBlock().shouldBe(visible);
        }
        return this;
    }

    @Step("Checking the generic reset by pressing again then by pressing the reset button. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingResetSelectedGeneric() {
        Listing_page_Logic listingPageLogic = new Listing_page_Logic();
        firstGenericInGenericsBlockToGetData().shouldHave(attribute("data-checked", "true"));
        String firstGeneric = getNameFirstGenericInGenericBlock();
        listingPageLogic.checkProductTitleOnListing(firstGeneric, true, titleNameProductsFromListing());
        clickFirstGenericInGenericsBlock();
        listingPageLogic.waitUntilPreloaderDisappear();
        firstGenericInGenericsBlockToGetData().shouldHave(attribute("data-checked", "false"));
        clickFirstGenericInGenericsBlock();
        listingPageLogic.waitUntilPreloaderDisappear();
        listingPageLogic.checkProductTitleOnListing(firstGeneric, true, titleNameProductsFromListing());
        clickBtnResetAllInGenericsBlock();
        listingPageLogic.waitUntilPreloaderDisappear();
        firstGenericInGenericsBlockToGetData().shouldHave(attribute("data-checked", "false"));
        return this;
    }

    @Step("Checking presence main products block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceMainProductsBlock(){
        mainProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking quantity main products. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingQuantityMainProducts() {
        mainProductsList().shouldHaveSize(20);
        return this;
    }

    @Step("Checking work btn reset all filter from filter block if selected generic and brand. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingWorkBtnResetAllFilterFromFilterBlock() {
        String valueGeneric = getValueFirstGenericInGenericBlock();
        clickFirstGenericInGenericsBlock();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        String valueBrand = getValueFirstVisibleBrandInBlockBrands();
        clickFirstVisibleBrand();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        ArrayList<String> twoFirstSelectedFilters = new ArrayList<>();
        String firstBtnFilter = getIdFirstBtnResetFilterInResetBlock();
        String secondBtnFilter = getIdSecondBtnResetFilterInResetBlock();
        twoFirstSelectedFilters.add(firstBtnFilter);
        twoFirstSelectedFilters.add(secondBtnFilter);
        if (!twoFirstSelectedFilters.contains(valueGeneric) || !twoFirstSelectedFilters.contains(valueBrand)) {
            Assert.fail( "Filters do not match the selected criteria");
        }
        clickBtnResetAllFilterFromFilterBlock();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        firstGenericInGenericsBlockToGetData().shouldHave(attribute("data-checked", "false"));
        firstVisibleBrandToGetData().shouldHave(attribute("data-checked", "false"));
        return this;

    }

    @Step("Get id first btn reset filter in reset block. Listing_chemicals_Page")
    public String  getIdFirstBtnResetFilterInResetBlock() {
        return firstBtnResetFilterInResetBlock().getAttribute("data-crit-id");
    }

    @Step("Get id second btn reset filter in reset block. Listing_chemicals_Page")
    public String  getIdSecondBtnResetFilterInResetBlock() {
        return secondBtnResetFilterInResetBlock().getAttribute("data-crit-id");
    }

    @Step("Get id first btn reset filter for criteria from reset block . Listing_chemicals_Page")
    public String  getIdFirstBtnResetFilterForCriteriaInResetBlock() {
        return firstBtnResetFilterInResetBlock().getAttribute("data-param-id");
    }

    @Step("Checking what generic from generic block not selected. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingWhatGenericNotSelected() {
        firstGenericInGenericsBlockToGetData().shouldHave(attribute("data-checked", "false"));
        return this;
    }


    @Step("Checking what brand from brands block not selected. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingWhatBrandNotSelected() {
        firstVisibleBrandToGetData().shouldHave(attribute("data-checked", "false"));
        return this;
    }

    @Step("Checking what first criteria from Konsistenz block not selected. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingWhatFirstCriteriaFromKonsistenzBlockNotSelected() {
        firstCriteriaFromKonsistenzBlockToGetData().shouldHave(attribute("data-checked", "false"));
        return this;
    }

}
