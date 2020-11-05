package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Listing_accessories_page_Logic extends Listing_accessories_page {

    @Step("Get name main title on Listing Page. Listing_accessories_page")
    public String getNameMainTitleOnListingPage(){
        return mainTitleListingPage().getText();
    }

    @Step("Checking presence main title on Listing Page. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceMainTitle() {
        Assert.assertFalse(mainTitleListingPage().text().isEmpty());
        return this;
    }

    @Step("Checking work quantity counter on decrease and increase products. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingWorkQuantityCounterOnDecreaseAndIncrease() {
        new CommonMethods().checkingCounterIncrease(3, counterValueInQuantityCounter(), btnPlusInQuantityCounter());
        new CommonMethods().checkingCounterDecrease(2, counterValueInQuantityCounter(), btnMinusInQuantityCounter() );
        counterValueInQuantityCounter().shouldHave(attribute("value", "2"));
        return this;
    }

    @Step("Get name product in listing. Listing_accessories_page")
    public String getNameProductInListing() {
        return titleNameProductInListing().getText();
    }

    @Step("Click first product in listing. Listing_accessories_page")
    public Product_page_Logic clickFirstProductInListing() {
        titleNameProductInListing().click();
        return page(Product_page_Logic.class);
    }

    @Step("Checking presence products listing block. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceProductsListingBlock() {
        blockProductsListing().shouldBe(visible);
        return this;
    }

    @Step("Checking the number of products in listing. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingNumberOfProductsInListing() {
        productsListing().shouldHaveSize(20);
        return this;
    }

    @Step("Get id product listing. Listing_accessories_page")
    public String getIdProductListing() {
        return idProductInBtnAddBasket().getAttribute("id");
    }

    @Step("Get value quantity counter from first product listing. Listing_accessories_page")
    public String getValueQuantityCounterFirstProductListing() {
        return counterValueInQuantityCounter().getValue();
    }

    @Step("Click button add to basket first product. Listing_accessories_page")
    public Listing_accessories_page_Logic clickBtnAddToBasketFirstProduct() {
        redBtnAddToBasket().click();
        popupBasketAddedProducts().waitUntil(attribute("style","visibility: visible; opacity: 1;"), 10000);
        return this;
    }

    @Step(":from Listing_accessories_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Checking presence bread crumbs. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceBreadCrumbs() {
        Assert.assertFalse(blockBreadCrumbs().text().isEmpty());
        return this;
    }

    @Step("Click first bread crumb. Listing_accessories_page")
    public Parts_page_Logic clickFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Parts_page_Logic.class);
    }

    @Step("Click second bread crumb. Listing_accessories_page")
    public Index_accessories_page_Logic clickSecondBreadCrumb() {
        secondBreadCrumb().click();
        return page(Index_accessories_page_Logic.class);
    }

    @Step("Checking not clickable third bread crumb. Listing_accessories_page")
    public Index_accessories_group_page_Logic clickThirdBreadCrumb() {
        thirdBreadCrumb().click();
        return page(Index_accessories_group_page_Logic.class);
    }

    @Step("Checking not clickable third bread crumb. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingNotClickableFourthBreadCrumb() {
        fourthBreadCrumb().shouldNotBe(attribute("href"));
        return this;
    }

    @Step("Get name first brand in brands block. Listing_accessories_page")
    public String getNameFirstBrandInBrandsBlock() {
        return firstBrandInBrandsBlockImg().getAttribute("Alt");
    }

    @Step("Get name third brand in brands block. Listing_accessories_page")
    public String getNameThirdBrandInBrandsBlock() {
        return thirdBrandInBrandsBlockImg().getAttribute("Alt");
    }

    @Step("Checking presence brands block and sorting of goods by selected brand after selecting another brand, first brand is reset. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceBrandsBlockAndSortingProductsBySelectedBrands() {
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

    @Step("Checking work button more and less in brands block. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingWorkBtnMoreAndLessInBrandsBlock() {
        btnMoreInBrandsBlock().click();
        btnLessInBrandsBlock().shouldBe(visible).click();
        btnLessInBrandsBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Checking work buttons in pagination. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingWorkBtnInPagination() {
        btnSecondPageInPagination().scrollIntoView(false).waitUntil(visible,3000).click();
        checkingContainsUrl("page=2");
        btnForReturnOnFirstPageInPagination().scrollIntoView(false).shouldBe(visible);
        btnPreviousInPagination().shouldBe(visible);
        btnNextInPagination().shouldBe(visible);
        btnLastInPagination().shouldBe(visible);
        btnPreviousInPagination().waitUntil(visible, 5000).click();
        blockPagination().scrollIntoView(false);
        btnForReturnOnFirstPageInPagination().shouldNotBe(visible);
        btnNextInPagination().waitUntil(visible, 5000).click();
        checkingContainsUrl("page=2");
        btnLastInPagination().scrollIntoView(false).waitUntil(visible, 5000).click();
        blockPagination().scrollIntoView(false);
        btnLastInPagination().shouldNotBe(visible);
        btnForReturnOnFirstPageInPagination().waitUntil(visible, 5000).click();
        blockPagination().scrollIntoView(false);
        btnForReturnOnFirstPageInPagination().shouldNotBe(visible);
        return this;
    }

}
