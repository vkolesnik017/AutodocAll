package PKW;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Index_accessories_page {


    SelenideElement titleNamePage() {
        return $x("//h1[@class='page-title']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='brand-block']");
    }

    SelenideElement firstBrandInBlockTopBrands() {
        return $x("//div[@class='brand-block']//a[@class='brand-block__wrap-item'][1]");
    }

    SelenideElement blockTopProducts() {
        return $x("//section[contains(@class,'top-product-block')]");
    }

    SelenideElement firstProductInBlockTopProducts() {
        return $x("//div[@class='slick-list draggable']//div[@data-slick-index='0']");
    }

    SelenideElement nameFirstProductInBlockTopProducts() {
        return $x("//div[@data-slick-index='0']//div[@class='listing-grid__item-title']");
    }

    SelenideElement btnPreviousInBlockTopProducts() {
        return $x("//section[contains(@class,'top-product-block')]//button[contains(@class,'slick-prev')]");
    }

    SelenideElement btnNextInBlockTopProducts() {
        return $x("//section[contains(@class,'top-product-block')]//button[contains(@class,'slick-next')]");
    }

    SelenideElement btnDetailsInPopupBlockTopProducts() {
        return $x("//div[contains(@class,'slick-active')][1]//span[@class='link']");
    }

    SelenideElement firstBtnAddProductToBasketInBlockTopProducts() {
        return $x("//div[@data-slick-index='0']//div[@class='listing-grid__item-button']");
    }

    SelenideElement blockBreadCrumbs() {
        return $x("//div[@class='breadcrumbs']/div[@class='crabs']");
    }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='breadcrumbs']/div[@class='crabs']/div[1]");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='breadcrumbs']/div[@class='crabs']/div[2]//a[@class='defcur']");
    }

    ElementsCollection blocksCategoriesInMainCatalog() {
        return $$x("//div[@class='category-block']//div[@class='category-block__item']");
    }

    SelenideElement firstCategoryInBlockCategoriesMainCatalog() {
        return $x("//div[@class='category-block__item'][1]//ul[1]/li[1]");
    }

    SelenideElement blockWithSeoTextDescription() {
        return $x("//div[@class='text_description']");
    }

    SelenideElement blockTopCategories() {
        return $x("//div[@class='seo-category-blocks']");
    }



}
