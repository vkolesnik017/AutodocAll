package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Tyre_item_page {

    SelenideElement horizontalSelector() {return $x("//div[@class='atd-carselector__content atd-carselector__tyres']");}

    SelenideElement grayButtonOfProduct() { return $x("//div[@class='product-button button not_active out-of-stock']/a");   }

    SelenideElement popUpAboutLackOfProduct() {return $x("//div[@class='popup-available']");}

    SelenideElement mpnNumOfProduct() {return $x("//div[@class='product-block__description__subtitle']/span[2]");}

    SelenideElement artNumOfProductInBasketPopUp() {return $x("//div[@class='row-text']/span");}

    SelenideElement btnAddProductToBasket() {return $x("//div[@class='product-button button ']/a");}

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='steps breadcrumbs']");}

    ElementsCollection linksOfBreadCrumbs() {return $$x("//div[@class='steps breadcrumbs']//ul/li//a");}

    SelenideElement eanNumberOfProduct() {return $x("//div[@class='product-block__description__subtitle']/span[1]");}

}
