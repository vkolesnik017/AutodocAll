package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

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

    @Step("Checking presence block with oil categories. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOilCategoriesBlock() {
        seoBlockOilCategories().shouldBe(visible);
        return this;
    }

    @Step("Click on first category in oil category block. Index_chemicals_page")
    public Motoroil_page_Logic clickFirstCategoryInOilCategoriesBlock() {
        firstCategoryInOilCategoriesBlock().click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("Get name second category in oil categories block. Index_chemicals_page")
    public String getNameSecondCategoryInOilCategoriesBlock() {
        return secondCategoryInOilCategoriesBlock().getText();
    }

    @Step("Click on second category in oil category block. Index_chemicals_page")
    public Parts_group_page_Logic  clickSecondCategoryInOilCategoriesBlock() {
        secondCategoryInOilCategoriesBlock().click();
        return page(Parts_group_page_Logic.class);
    }

    @Step("Checking presence top brands block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceTopBrandsBlock() {
        blockBrands().shouldBe(visible);
        return this;
    }

    @Step("Get name first brand in top brands block. Index_chemicals_page ")
    public String getNameFirstBrandInTopBrandsBlock() {
        return firstBrandInBlockBrands().getText();
    }

    @Step("Click first brand in block top brands. Index_chemicals_page")
    public Supplier_page_Logic clickFirstBrandInTopBrandsBlock() {
        firstBrandInBlockBrands().click();
        return page(Supplier_page_Logic.class);
    }

    @Step("Checking presence seo top categories block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceSeoTopCategoriesBlock() {
        lastSeparateCategoryInMainCatalogCategories().scrollTo();
        seoBlockTopCategories().shouldBe(visible);
        return this;
    }

    @Step("Get name first category in seo top categories block. Index_chemicals_page")
    public String getNameFirstCategoryInTopCategoriesBlock() {
        return firstCategoryInTopCategoriesBlock().getText();
    }

    @Step("Click first category in seo top category block. Index_chemicals_page")
    public Listing_chemicals_page_Logic clickFirstCategoryInTopCategoriesBlock() {
        firstCategoryInTopCategoriesBlock().click();
        return page(Listing_chemicals_page_Logic.class);
    }

    @Step("Checking work btn More and Less in seo top category block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkWorkBtnMoreAndLessInTopCategoryBlock() {
        elevenCategoryInTopCategoriesBlock().shouldNotBe(visible);
        btnMoreInTopCategoriesBlock().click();
        sleep(2000);
        elevenCategoryInTopCategoriesBlock().shouldBe(visible);
        btnLessInTopCategoriesBlock().click();
        elevenCategoryInTopCategoriesBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Checking opening product characteristics after hover on mini card in top products block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingHoverFirstProductInTopProductsBlock() {
        firstProductInTopProductsBlock().scrollTo().hover();
        btnDetailsFirstProductInTopProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking presence main categories catalog block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceMainCategoriesCatalogBlock() {
        mainCategoriesCatalogBlock().shouldBe(visible);
        return this;
    }



}
