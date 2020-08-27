package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;

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

}
