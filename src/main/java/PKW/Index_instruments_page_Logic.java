package PKW;


import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;


public class Index_instruments_page_Logic extends Index_instruments_page {

    @Step("Checking presence title main page. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTitleMainPage() {
        Assert.assertFalse(titleMainPage().text().isEmpty());
        return this;
    }

    @Step("Checking presence title top categories block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTitleTopCategoriesBlock() {
        Assert.assertFalse(titleTopCategoriesBlock().text().isEmpty());
         return this;
    }

    @Step("Click first bread crumb. Index_instruments_page")
    public Parts_page_Logic clickFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Parts_page_Logic.class);
    }

    @Step("Checking presence and not clickable second bread crumb. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceAndNotClickableSecondCrumb() {
        secondBreadCrumb().shouldBe(visible).shouldNotBe(attribute("href"));
        return this;
    }

    @Step("Checking presence top brands block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTopBrandsBlock() {
        blockTopBrands().shouldBe(visible);
        return this;
    }

    @Step("Get name first brand in top brands block. Index_instruments_page")
    public String getNameFirstBrandInTopBrandsBlock() {
        return firstBrandInTopBrandsBlock().getText();
    }

    @Step("Click on first brand in top brands block. Index_instruments_page")
    public Supplier_page_Logic clickFirstBrandInTopBrandsBlock() {
        firstBrandInTopBrandsBlock().click();
        return page(Supplier_page_Logic.class);
    }

    @Step("Checking presence main catalog categories block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceMainCatalogCategoriesBlock() {
        mainCatalogCategoriesBlock().shouldBe(visible);
        return this;
    }

    @Step("Get id product from top products block. Index_instruments_page")
    public String getIdProductFromTopProducts() {
        return btnAddProductToBasketFromTopProductsBlock().getAttribute("id");
    }

    @Step("Click btn add to basket product from top products block. Index_instruments_page")
    public Index_instruments_page_Logic clickBtnAddToBasketProductFromTopProducts(){
        btnAddProductToBasketFromTopProductsBlock().click();
        popupBasketAddedProducts().waitUntil(attribute("style","visibility: visible; opacity: 1;"), 10000);
        return this;
    }

    @Step(":from Index_instruments_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Checking popup with product characteristic after hover from top products block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPopupProductFromTopProductsBlock() {
        firstProductFromTopProductsBlock().scrollIntoView(false).hover();
        btnDetailsProductFromTopProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking quantity products in top products block. Index_instruments_page")
    public Index_instruments_page_Logic checkingQuantityProductsInTopProductsBlock() {
        productsFromTopProductsBlock().shouldHaveSize(12);
        return this;
    }

    @Step("Get name first category from top categories block. Index_instruments_page")
    public String getNameFirstCategoryFromTopCategoriesBlock() {
        return firstCategoryFromTopCategoriesBlock().getText();
    }

    @Step("Click first category from top categories block. Index_instruments_page")
    public Listing_instruments_Page_Logic clickFirstCategoryFromTopCategoriesBlock() {
        firstCategoryFromTopCategoriesBlock().click();
        return page(Listing_instruments_Page_Logic.class);
    }

    @Step("Checking presence title top products block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTitleTopProductsBlock() {
        Assert.assertFalse(titleTopProductsBlock().text().isEmpty());
        return this;
    }

    @Step("Checking quantity category in top categories block. Index_instruments_page")
    public Index_instruments_page_Logic checkingQuantityCategoriesInTopCategoriesBlock() {
        categoriesTopCategoriesBlock().shouldHaveSize(12);
        return this;
    }

    @Step("Checking presence categories block in logical union from main catalog. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceCategoriesInLogicalUnion() {
        firstLogicalUnion().hover();
        categoriesBlockInLogicalUnion().shouldBe(visible);
        return this;
    }

    @Step("Click separate category from main catalog. Index_instruments_page")
    public Listing_instruments_Page_Logic clickSeparateCategoryFromMainCatalog() {
        separateCategoryInMainCatalog().click();
        return page(Listing_instruments_Page_Logic.class);
    }

    @Step("Get name separate category from main catalog. Index_instruments_page")
    public String getNameSeparateCategoryFromMainCatalog() {
        return separateCategoryInMainCatalog().getText();
    }

}
