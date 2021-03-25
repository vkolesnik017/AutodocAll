package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Category_oen_Page {
    SelenideElement listProductBlock() {return $x("//ul[@class='list_products ']");}

    ElementsCollection titleOfProductInList() {return $$x("//div[@class='name']/*[self::a or self::span][1]");}

    ElementsCollection activeBtnAddToBasket() {return $$x("//div[@class='button ']");}

    ElementsCollection btnAddToBasketWithBrand(String brand) {return $$x("//div[@class='button '][@data-brand-name='"+brand+"']");}

    ElementsCollection  priceOfProduct() {return $$x("//p[@class='actual_price']");}

    SelenideElement  brandsFilterBlock() {return $x("//div[@data-name='brand']");}

    SelenideElement forwardLinkAtBrandsFilter() {return $x("//a[contains(@class,'next')]");}

    SelenideElement loaderInTecDocListing() {
        return $x("//div[@class='preloader_wrapper']");
    }

    SelenideElement brandsLinkInSideBar(String idOfBrand) {return $x("//div[@id='selected-instalation__slider']//ul//li//label[@for='cb-brand-"+idOfBrand+"']");}

    ElementsCollection imageOfBrandInProductBlock() {return $$x("//div[@class='image']/span[1]/img");}

    ElementsCollection descriptionBlockOfProduct() {return $$x("//div[@class='description']");}

    ElementsCollection characteristicListOfProduct(int positionOfProduct) {return $$x("(//div[@class='description'])["+positionOfProduct+"]//div[@class='about']//ul/li/span[1]");}

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement headerGarageIcon(){ return $x("//div[@class='header-garage js-header-garage']"); }

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement listOfProductTableView() {return $x("//div[@class='sub_catalog_grid']");}

    ElementsCollection labelTitleDangerousProducts() {return $$x("//div[@class='rec_products_block js-products-item ']//span[@class='dangerous-listing__show-more']");}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[@class='dangerous-listing__title hazard-danger-title']");}

    SelenideElement titleOfProductWithArtNum(String artNum) {return $x("//span[contains(text(),'Artikelnummer: "+artNum+"')]/preceding-sibling::a");}

    ElementsCollection visibleFilterBrands() {return $$x("//*[self::li[contains(@class,'slick-active')] or self::li]//label").filter(visible);}

}


