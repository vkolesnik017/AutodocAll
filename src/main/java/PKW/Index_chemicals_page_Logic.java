package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

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




}
