package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

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

    SelenideElement grayButton() {return $(byId("product_page_product"));}

    SelenideElement popUpNotifyAboutAvailability() {return $x("//div[@class='popup-available']");}

    SelenideElement labelOfPopUpNotifyAboutAvailability() {return $x("//label[@class='popup-available__label']");}

    SelenideElement emailFieldOfFeedBackPopUp() { return $(byId("form_AvailabilityReminder[email]")); }

    SelenideElement btnSendOfFeedBackPopUp() { return $x("//input[@class='popup-available__button']");  }

    SelenideElement errorPopUp() {return $x("//div[@class='txt ']");}

    SelenideElement btnCloseErrorPopUp() {return $x("//div[@class='popup_inner']/div/a");}
}
