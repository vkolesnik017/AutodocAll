package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Listing_instruments_Page_Logic extends Listing_instruments_Page {


    @Step("Get name title category. Listing_instruments_Page")
    public String getNameTitleCategory() {
        return titleCategory().getText();
    }

    @Step("Checking presence title page. Listing_instruments_Page")
    public Listing_instruments_Page_Logic checkingPresenceTitleCategory() {
        Assert.assertFalse(titleCategory().text().isEmpty());
        return this;
    }

    @Step("Get name first brand in brands block. Listing_instruments_Page")
    public String getNameFirstBrandInBrandsBlock() {
        return firstBrandInBrandsBlockImg().getAttribute("alt");
    }

    @Step("Get name third brand in brands block. Listing_instruments_Page")
    public String getNameThirdBrandInBrandsBlock() {
        return thirdBrandInBrandsBlockImg().getAttribute("alt");
    }

    @Step("Checking presence brands block and sorting of goods by selected brand after selecting another brand, first brand is reset. Listing_instruments_Page")
    public Listing_instruments_Page_Logic checkingPresenceBrandsBlockAndSortingProductsBySelectedBrands() {
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

    @Step("Checking work button more and less in brands block. Listing_instruments_Page")
    public Listing_instruments_Page_Logic checkingWorkBtnMoreAndLessInBrandsBlock() {
        btnMoreInBrandsBlock().click();
        btnLessInBrandsBlock().shouldBe(visible).click();
        btnLessInBrandsBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Get name product in listing. Listing_instruments_Page")
    public String getNameProductInListing() {
        return titleNameProductInListing().getText();
    }

    @Step("Click first product in listing. Listing_instruments_Page")
    public Product_page_Logic clickFirstProductInListing() {
        titleNameProductInListing().click();
        return page(Product_page_Logic.class);
    }

    @Step("Get id product listing. Listing_instruments_Page")
    public String getIdProductListing() {
        return idProductInBtnAddBasket().getAttribute("id");
    }

    @Step(":from Listing_instruments_Page")
    public Listing_instruments_Page_Logic increasesNumberProductsInQuantityCounter() {
        new CommonMethods().checkingCounterIncrease(2, counterValueInQuantityCounter(), btnPlusInQuantityCounter());
        return this;
    }

    @Step("Get value quantity counter from first product listing. Listing_instruments_Page")
    public String getValueQuantityCounterFirstProductListing() {
        return counterValueInQuantityCounter().getValue();
    }

    @Step("Click button add to basket first product. Listing_instruments_Page")
    public Listing_instruments_Page_Logic clickBtnAddToBasketFirstProduct() {
        redBtnAddToBasket().click();
        popupBasketAddedProducts().waitUntil(attribute("style","visibility: visible; opacity: 1;"), 10000);
        return this;
    }

    @Step(":from Listing_instruments_Page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

}
