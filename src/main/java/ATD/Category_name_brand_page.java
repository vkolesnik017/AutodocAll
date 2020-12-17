package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Category_name_brand_page {

    SelenideElement topProductsBlock() {return $x("//div[@class='product-list product-list--4items js-product-list-slider--4 js-product-list-animation js-car-popup slick-initialized slick-slider']");}

    ElementsCollection visibleTitleOfTopProducts() {return $$x("//div[@class='product-list__item__title']");}

    ElementsCollection popUpOfTopProducts() {return $$x("//div[@class='product-list__item__popup']");}

    SelenideElement activeBtnForwardOfTopProductsBlock() {return $x("//button[@class='slick-next slick-arrow']");}

    SelenideElement titleOfTopProductsBlock() {return $x("//div[@class='title_list']/b");}

    ElementsCollection visibleCharacteristicInPopUpOfTopProducts(int position) {return $$x("(//div[@class='product-list__item__popup'])["+position+"]//ul/li/span[1]");}

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement headerGarageIcon(){ return $x("//div[@class='header-garage js-header-garage']"); }

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    ElementsCollection visibleImageOfTopProduct() {return $$x("//a[@class='ga-click']/img").filter(visible);}

    ElementsCollection artNumOfTopProduct() {return $$x("//div[@class='product-list__item__nummer']");}

    ElementsCollection allCharacteristicInPopUpOfTopProducts(int position) {return $$x("(//div[@class='product-list__item__popup'])["+position+"]//ul/li");}

    SelenideElement articleNumberInPopUpOfTopProducts(int position) {
        return $x("(//div[@class='product-list__item__popup'])["+position+"]//span[contains(text(),'Artikelnummer')]/../span[2]");
    }

    SelenideElement characteristicZustandInPopUpOfTopProducts(int position) {
        return $x("(//div[@class='product-list__item__popup'])["+position+"]//span[contains(text(),'Zustand')]/../span[2]");
    }

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[@class='popup-dangerous__title']");}

    ElementsCollection fullValuesOfCharacteristicTopProduct(int position) {return $$x("(//div[@class='item-table-box__container'])["+position+"]//li");}

    SelenideElement dangerousPopUp() {return $x("//div[@class='popup-dangerous']");}

    SelenideElement mainImageCategories() {
        return $x("//div[@class='autoteile-top-content__image']/img");
    }

    ElementsCollection btnAddProductToWishlist() {return $$x("//div[@class='add-to-wishlist add-article']");}

    ElementsCollection priceTitle() {return $$x("//div[@class='product-list__item__info']/*[1]");}
}
