package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;
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

    @Step(":from Listing_accessories_page")
    public Listing_accessories_page_Logic increasesNumberProductsInQuantityCounter() {
        new CommonMethods().checkingCounterIncrease(2, counterValueInQuantityCounter(), btnPlusInQuantityCounter());
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
    public Listing_accessories_page_Logic checkingNotClickableThirdBreadCrumb() {
        thirdBreadCrumb().shouldNotBe(attribute("href"));
        return this;
    }

}
