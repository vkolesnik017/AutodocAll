package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class Index_chemicals_page_Logic extends Index_chemicals_page {

    @Step("Checking presence title main catalog. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceTitleMainCatalog() {
        Assert.assertFalse(titleMainCatalog().text().isEmpty());
        return this;
    }

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

    @Step("Checking categories from main products catalog. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingCategoriesFromMainProductsCatalog() {
        mainCategoriesCatalogBlock().shouldBe(visible);
        categoriesFromLogicalUnion().shouldHaveSize(58);
        logicalUnionAdditive().scrollIntoView(false).hover();
        categoriesFromActiveLogicalUnion().shouldHaveSize(6);
        int i = 0;
        while (i < 6) {
            logicalUnionAdditive().scrollIntoView(false).hover();
            String nameCategory = categoriesFromActiveLogicalUnion().get(i).getText();
            categoriesFromActiveLogicalUnion().get(i).click();
            String nameListing = new Listing_chemicals_page_Logic().getTitleNameListingPage();
            blockSoft404().shouldNotBe(visible);
            back();
            Assert.assertEquals(nameCategory, nameListing);
            i++;
        }
        return this;
    }

    @Step("Checking presence first separate category in main products catalog. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceSeparateCategoryInProductsCatalog() {
        separateCategoryInMainCatalogCategories().shouldBe(visible);
        return this;
    }

    @Step("Get text first category in logical union. Index_chemicals_page")
    public String getTextFirstCategoryInLogicalUnion() {
        firstGroupLogicalUnion().hover();
        return firstCategoryInLogicalUnion().getText();
    }

    @Step("Click first category in logical union. Index_chemicals_page")
    public Listing_chemicals_page_Logic clickFirstCategoryInLogicalUnion() {
        firstCategoryInLogicalUnion().click();
        return page(Listing_chemicals_page_Logic.class);
    }

    @Step("Get id product from top products block. Index_chemicals_page")
    public String getIdProductFromTopProducts() {
        return btnAddProductToBasketInBlockTopProducts().getAttribute("id");
    }

    @Step("Click on first btn add to basket from block top products. Index_chemicals_page")
    public Index_chemicals_page_Logic clickOnFirstBtnAddProductToBasketInBlockTopProducts() {
        btnAddProductToBasketInBlockTopProducts().click();
        popupBasketAddedProducts().waitUntil(attribute("style","visibility: visible; opacity: 1;"), 10000);
        return this;
    }

    @Step(":from Index_chemicals_page" )
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Checking quantity products in top products block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingQuantityProductsInTopProductsBlock() {
        productsFromTopProductsBlock().shouldHaveSize(12);
        return this;
    }

    @Step("Checking presence categories block in Logical union. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceCategoriesBlockInLogicalUnion() {
        firstGroupLogicalUnion().hover();
        firstCategoryInLogicalUnion().shouldBe(visible);
        return this;
    }

    @Step("Get id all categories from logical union and separate categories then write to list. Index_chemicals_page")
    public ArrayList<String> getIdCategoriesAndSeparateCategoriesThenWriteToList() {
        blockMainProducts().scrollIntoView(false);
        ArrayList<String> categoriesFromLogicalUnion = new ArrayList<>();
        for (SelenideElement element : categoriesFromLogicalUnion()) {
            String idCategory = element.getAttribute("data-ga-action");
            categoriesFromLogicalUnion.add(idCategory);
        }

        ArrayList<String> separateCategoriesId = new ArrayList<>();
        for (SelenideElement element : separateCategories()) {
            String idSeparateCategory = element.getAttribute("data-ga-action");
            separateCategoriesId.add(idSeparateCategory);
        }

        categoriesFromLogicalUnion.addAll(separateCategoriesId);
        Collections.sort(categoriesFromLogicalUnion);
        return categoriesFromLogicalUnion;
    }


    @Step("Get id Logical Unions and write to list. Index_chemicals_page")
    public ArrayList<String> getIdLogicalUnionAndWriteToList() {
        ArrayList<String> logicalUnionsId = new ArrayList<>();
        for (SelenideElement element:logicalUnions()) {
            String idLogicalUnion = element.getAttribute("data-srcset").replaceAll("[\\s\\S]*\\/", "").replaceAll("\\D+", "");
            logicalUnionsId.add(idLogicalUnion);
        }
        Collections.sort(logicalUnionsId);
        return logicalUnionsId;
    }


}
