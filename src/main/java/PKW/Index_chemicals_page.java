package PKW;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Index_chemicals_page {

    SelenideElement firstGroupLogicalUnion() {
        return $x("//div[@class='category-block__item category-block-item-js'][1]");
    }

    SelenideElement firstCategoryInLogicalUnion() {
        return $x("//div[@class='category-block__item category-block-item-js active']//div[@class='category-block__item-list category-block__item-list-js']/ul[1]/li[1]");
    }

    SelenideElement titleMainCatalog() {
        return $x("//h1[@class='page-title']");
    }

    SelenideElement blockSeoTextWithDescription() {
        return $x("//div[@class='text_description']");
    }

    SelenideElement separateCategoryInMainCatalogCategories() {
        return $x("//div[@class='category-block']//a[@class='category-block__item ga-click'][1]");
    }

    SelenideElement lastSeparateCategoryInMainCatalogCategories() {
        return $x("//div[@class='category-block']//a[@class='category-block__item ga-click'][11]");
    }

    SelenideElement blockBreadCrumb() {
        return $x("//div[@class='crabs']");
    }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][1]");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][2]/a");
    }

    SelenideElement seoBlockOilCategories() {
        return $x("//div[@class='seo-category-blocks__block']");
    }

    SelenideElement firstCategoryInOilCategoriesBlock() {
        return $x("//div[@class='seo-category-blocks__block']//ul/li[1]");
    }

    SelenideElement secondCategoryInOilCategoriesBlock() {
        return $x("//div[@class='seo-category-blocks__block']//ul/li[2]");
    }

    SelenideElement blockBrands() {
        return $x("//div[@class='brand-block']");
    }

    SelenideElement firstBrandInBlockBrands() {
        return $x("//div[@class='brand-block']//a[@class='brand-block__wrap-item'][1]");
    }

    SelenideElement seoBlockTopCategories() {
        return $x("//div[@class='seo-category-blocks__block seo-category-blocks__block-js']");
    }

    SelenideElement firstCategoryInTopCategoriesBlock() {
        return $x("//div[@class='seo-category-blocks__block seo-category-blocks__block-js']//li[1]");
    }

    SelenideElement elevenCategoryInTopCategoriesBlock() {
        return $x("//div[@class='seo-category-blocks__block seo-category-blocks__block-js']//li[11]");
    }

    SelenideElement btnMoreInTopCategoriesBlock() {
        return $x("//div[@class='seo-category-blocks__block seo-category-blocks__block-js']//a[@class='more_link']");
    }

    SelenideElement btnLessInTopCategoriesBlock() {
       return  $x("//div[@class='seo-category-blocks__block seo-category-blocks__block-js']//a[@class='less_link']");
    }

    SelenideElement firstProductInTopProductsBlock() {
        return $x("//div[@id='js-product-slider']//div[contains(@class,'slick-active')][1]");
    }

    SelenideElement btnDetailsFirstProductInTopProductsBlock() {
        return $x("//div[@id='js-product-slider']//div[contains(@class,'slick-active')][1]//span[@class='link']");
    }

    SelenideElement mainCategoriesCatalogBlock() {
        return $x("//div[@class='category-block']");
    }

    SelenideElement btnAddProductToBasketInBlockTopProducts() {
        return $x("//div[@id='js-product-slider']//div[@class='listing-grid__item-button']");
    }

    SelenideElement popupBasketAddedProducts() {
        return $x("//div[@class='cart-items-block ']");
    }

    ElementsCollection productsFromTopProductsBlock() {
        return $$x("//div[@class='slick-list draggable']//div[@data-slick-index]");
    }




}
