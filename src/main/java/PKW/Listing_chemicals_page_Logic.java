package PKW;


import io.qameta.allure.Step;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;


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

    @Step("Checking presence products listing block. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresenceProductsListingBlock() {
        blockProductsListing().shouldBe(visible);
        return this;
    }

    @Step("Checking the number of products in listing. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingNumberOfProductsInListing() {
        productsListing().shouldHaveSize(20);
        return this;
    }

    @Step("Checking work quantity counter on decrease and increase products. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingWorkQuantityCounterOnDecreaseAndIncrease() {
        new CommonMethods().checkingCounterIncrease(3, counterValueInQuantityCounter(), btnPlusInQuantityCounter());
        new CommonMethods().checkingCounterDecrease(2, counterValueInQuantityCounter(), btnMinusInQuantityCounter() );
        counterValueInQuantityCounter().shouldHave(attribute("value", "2"));
        return this;
    }

    @Step("Click first product in listing. Listing_chemicals_page")
    public Product_page_Logic clickFirstProductInListing() {
        titleNameFirstProductInListing().click();
        return page(Product_page_Logic.class);
    }

    @Step("Get name first product in listing. Listing_chemicals_page")
    public String getNameFirstProductInListing() {
        return titleNameFirstProductInListing().getText();
    }

    @Step("Get id product listing. Listing_chemicals_page")
    public String getIdProductListing() {
        return idProductInBtnAddBasket().getAttribute("id");
    }

    @Step("Click btn add to basket in first product listing. Listing_chemicals_page")
    public Listing_chemicals_page_Logic clickBtnAddToBasketInFirstProductListing() {
        redBtnAddToBasket().click();
        sleep(2000);
        return this;
    }

    @Step("Get value quantity counter first product listing. Listing_chemicals_page")
    public String getValueQuantityCounterFirstProductListing() {
        return counterValueInQuantityCounter().getValue();
    }

    @Step("from. Listing_chemicals_page")
    public Listing_chemicals_page_Logic increasesNumberProductsInQuantityCounter() {
        new CommonMethods().checkingCounterIncrease(2, counterValueInQuantityCounter(), btnPlusInQuantityCounter());
        return this;
    }

    @Step(":from Listing_chemicals_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Checking presence features block. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresenceFeaturesBlock() {
        blockFeatures().scrollIntoView(false).shouldBe(visible);
        return this;
    }

    @Step("Checking presence description feature after hover. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingDescriptionFeatureAfterHoverInFeaturesBlock() {
        firstFeatures().hover();
        popupFirstFeatures().shouldBe(visible);
        return this;
    }

    @Step("Click first generic in generics block. Listing_chemicals_page")
    public Listing_chemicals_page_Logic clickFirstGeneric() {
        firstGenericInGenericsBlock().click();
        return this;
    }

    @Step("Click second generic in generics block. Listing_chemicals_page")
    public Listing_chemicals_page_Logic clickSecondGeneric() {
        secondGenericInGenericsBlock().click();
        return this;
    }

    @Step("Get name first generic in generics block. Listing_chemicals_page")
    public String getNameFirstGeneric() {
        return firstGenericInGenericsBlock().getText();
    }

    @Step("Get name second generic in generics block. Listing_chemicals_page")
    public String getNameSecondGeneric() {
        return secondGenericInGenericsBlock().getText();
    }

    @Step("Checking sorting products by two generics.Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingSortingProductsByTwoGenerics() {
        Listing_page_Logic listing_page_logic = new Listing_page_Logic();
        String firstGeneric = getNameFirstGeneric();
        String secondGeneric = getNameSecondGeneric();
        clickFirstGeneric();
        listing_page_logic.waitUntilPreloaderDisappear();
        clickSecondGeneric();
        listing_page_logic.waitUntilPreloaderDisappear();
        listing_page_logic.checkProductTitleOnListingWithTwoExpectedTexts(firstGeneric, secondGeneric, true, titleNameProductsFromListing());
        btnSecondPageInPagination().scrollIntoView(false);
        btnSecondPageInPagination().click();
        checkingContainsUrl("page=2");
        listing_page_logic.checkProductTitleOnListingWithTwoExpectedTexts(firstGeneric, secondGeneric, true, titleNameProductsFromListing());
        return this;
    }

    @Step("Checking presence generics block. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresenceGenericsBlock() {
        genericsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking sorting products by one generic. Listing_chemicals_page")
    public Listing_chemicals_page checkingSortingProductsByOneGeneric() {
        Listing_page_Logic listing_page_logic = new Listing_page_Logic();
        String firstGeneric = getNameFirstGeneric();
        clickFirstGeneric();
        listing_page_logic.waitUntilPreloaderDisappear();
        listing_page_logic.checkProductTitleOnListing(firstGeneric, true, titleNameProductsFromListing());
        return this;
    }

    @Step("Checking presence payment method block. Listing_chemicals_page")
    public Listing_chemicals_page checkingPresencePaymentMethodBlock() {
        blockPaymentMethod().shouldBe(visible);
        return this;
    }


}
