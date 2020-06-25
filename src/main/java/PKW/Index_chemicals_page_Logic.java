package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Index_chemicals_page_Logic extends Index_chemicals_page {


    @Step("Checking presence block with SEO text description. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceBlockWithSeoText() {
        Assert.assertFalse( blockSeoTextWithDescription().text().isEmpty());
        return this;
    }

    @Step("Get name text separate category in main categories catalog. Index_chemicals_page")
    public String getNameSeparateCategoryInMainCatalog() {
        return separateCategoryInMainCatalogCategories().getText();
    }

    @Step("Click on first separate category in main categories catalog. Index_chemicals_page")
    public Listing_chemicals_page_Logic clickOnFirstSeparateCategoryMainCatalog() {
        separateCategoryInMainCatalogCategories().click();
        return page(Listing_chemicals_page_Logic.class);
    }

    @Step("Checking presence block Bread Crumbs. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceBlockBreadCrumbs() {
        blockBreadCrumb().shouldBe(visible);
        return this;
    }

    @Step("Click on first bread crumb. Index_chemicals_page")
    public Parts_page_Logic clickOnFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Parts_page_Logic.class);
    }

    @Step("Checking presence and not clickable second bread crumb. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceAndNotClickableSecondCrumb() {
        secondBreadCrumb().shouldBe(visible).shouldNotBe(attribute("href"));
        return this;
    }



}
