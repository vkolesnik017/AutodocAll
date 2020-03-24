package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LKW_Category_car_list_page {
    SelenideElement listingOfProducts() {
        return $x("//ul[@class='list_products ']");
    }

    ElementsCollection breadCrumbsLinks() {
        return $$x("//div[@class='steps breadcrumbs']//li");
    }

    SelenideElement firstLinkOfBreadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']//img");
    }

    SelenideElement secondLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_2 active parts_step_2']//a");
    }

    SelenideElement thirdLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_3 active parts_step_3']//a");
    }

    SelenideElement fourthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_4 active parts_step_4']//a");
    }

    SelenideElement fifthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_5 active parts_step_5']//a");
    }

    SelenideElement firstLinkOfBreadCrumbsBlockTitleTecDoC() {
        return $x("//div[@class='steps breadcrumbs']//li[1]/span/a");
    }

    SelenideElement listOfProductInTecDocListingBlock() {
        return $x("//ul[@class='list_products ']");
    }

    ElementsCollection productsOnPage() {
        return $$x("//div[@class='image']//span[2]/img");
    }

    SelenideElement imageOfProductTecDocListingBlock(int position) {
        return $x("(//div[@class='image']//span[2]/img)[" + position + "]");
    }

    SelenideElement imageBrandOfProductTecDocListingBlock(int position) {
        return $x("(//div[@class='image']/span[1]/img)[" + position + "]");
    }

    ElementsCollection titleOfProductInTecDocListingBlock() {
        return $$x("//div[@class='name']/*[self::a or self::span][1]");
    }


    SelenideElement nextPagePagination() {
        return $x("//span[@class='next']");
    }

    SelenideElement lastPagePaginator() {return $x("//span[@class='last']/a");}


    SelenideElement btnOfFirstProductInTecDocListing() {
        return $x("(//div[@class='button '])[1]");
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement articleNumberOfProduct(String titleOfArticle) {
        return $x("//span[contains(text(),'" + titleOfArticle + "')]");
    }

    SelenideElement dynamicCharacteristicInTecDocListingBlock(String titleOfArticle) {
        return $x("//span[contains(text(),'" + titleOfArticle + "')]/ancestor::div[@class='description']//ul[@class='criteria']//li[1]/span[2]");
    }

    SelenideElement totalAmountOfProductsInTecDocListing(){ return $x("//div[@class='product_count']/span");}

    SelenideElement filterByGenericBlock() {
        return $x("//div[@class='filter-generics-tecdoc__content']");
    }

    ElementsCollection genericsInFilterGenericBlock() {
        return $$x("//label[@class='filter-generics-tecdoc__item ']");
    }

    SelenideElement loaderInTecDocListing() {
        return $x("//div[@class='preloader_wrapper']");
    }

    SelenideElement allFiltersGeneric() {
        return $x("//label[@class='filter-generics-tecdoc__all show_all  ']");
    }

    SelenideElement brandBlock() {
        return $x("//div[@id='selected-instalation__slider']");
    }

    ElementsCollection brandsOfBrandBlock() {
        return $$x("//div[@id='selected-instalation__slider']//li");
    }

    SelenideElement installationSideBlock() {
        return $x("//div[@class='installation-side__content']");
    }

    ElementsCollection sidesOfInstallation() {
        return $$x("//div[@class='installation-side__content']/div");
    }
}
