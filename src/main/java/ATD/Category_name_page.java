package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Category_name_page {

    protected SelenideElement imageOfChildCategory() {
        return $x("//div[@class='autoteile-top-content__image']");
    }

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement headerGarageIcon(){ return $x("//div[@class='header-garage js-header-garage']"); }

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement topProductsBlock() {return $x("//div[contains(@class,'js-product-list-animation')]");}

    ElementsCollection btnAddDangerousProductToWishList() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[contains(@class,'product-list__item')]/div[@class='add-to-wishlist add-article']");}

    SelenideElement forwardOfTopBrandsBlock() {return $x("//button[@class='slick-next slick-arrow']");}

    ElementsCollection visibleArtNumOfTopProduct() {return $$x("//div[@class='product-list__item__nummer']").filter(visible);}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[@class='dangerous-listing__title hidden']");}

    ElementsCollection dangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[contains(@class,'product-list__item')]");}

    ElementsCollection attributeOfWarningIcon(int positionOfProduct) {return $$x("(//span[@class='dangerous-listing__show-more'])["+positionOfProduct+"]/ancestor::div[contains(@class,'product-list__item')]//div[@class='dangerous-listing__icon']");}

    ElementsCollection labelTitleDangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']");}

    SelenideElement blackBackground() {return $x("//div[@class='overlay black hidden']");}

    SelenideElement warningPopUp() {return $x("//div[@class='popup-dangerous']");}

    SelenideElement titleOfDangerousPopUp() {return $x("//div[@class='popup-dangerous__title']");}

    SelenideElement infoTextOfDangerousPopUp() {return $x("//div[@class='popup-dangerous']//p");}
}
