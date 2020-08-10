package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

}


