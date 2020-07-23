package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Index_instruments_page {

    SelenideElement titleMainPage() {
        return $x("//h1[@class='page-title']");
    }

    SelenideElement titleTopCategoriesBlock() {
        return $x("//div[@class='seo-category-blocks']//div[@class='seo-category-blocks__block-title']");
    }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='breadcrumbs']//a[@href][1]");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='breadcrumbs']//a[@class='defcur']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='brand-block']");
    }

    SelenideElement firstBrandInTopBrandsBlock() {
        return $x("//div[@class='brand-block']//a[@class='brand-block__wrap-item'][1]");
    }

    SelenideElement mainCatalogCategoriesBlock() {
        return $x("//div[@class='category-block']");
    }

    SelenideElement titleTopProductsBlock() {
        return $x("//h3[@class='top-product-block__title']");
    }

    SelenideElement firstProductFromTopProductsBlock() {
        return $x("//div[contains(@class,'slick-active')]");
    }

    SelenideElement btnDetailsProductFromTopProductsBlock() {
        return $x("//div[contains(@class,'slick-active')]//span[@class='link']");
    }

    SelenideElement btnAddProductToBasketFromTopProductsBlock() {
        return $x("//div[contains(@class,'slick-active')]//div[@class='listing-grid__item-button']");
    }

    SelenideElement popupBasketAddedProducts() {
        return $x("//div[@class='cart-items-block ']");
    }

    ElementsCollection productsFromTopProductsBlock() {
        return $$x("//div[@class='slick-list draggable']//div[@data-slick-index]");
    }

    SelenideElement firstCategoryFromTopCategoriesBlock() {
        return $x("//div[@class='seo-category-blocks']//li");
    }

    ElementsCollection categoriesTopCategoriesBlock() {
        return $$x("//div[@class='seo-category-blocks']//li");
    }

    SelenideElement firstLogicalUnion() {
        return $x("//div[@class='category-block__item category-block-item-js']");
    }

    SelenideElement categoriesBlockInLogicalUnion() {
        return $x("//div[@class='category-block__item-list category-block__item-list-js']");
    }

    SelenideElement separateCategoryInMainCatalog() {
        return $x("//div[@class='category-block']/a");
    }


}
