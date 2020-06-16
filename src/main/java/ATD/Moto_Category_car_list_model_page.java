package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Category_car_list_model_page {

    SelenideElement brandOfMotoField() {return $(byId("form_maker_id"));}

    SelenideElement modelFiledInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorFiledInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement searchButton() {return $x("//a[contains(@class,'search_button')]");}

    ElementsCollection breadCrumbsLinks() {return $$x("//div[@class='steps breadcrumbs']/ul/li");}

    SelenideElement iconOfCatalogBrandInBreadCrumbs() {return $x("//li[@class='step_1 active parts_step_1']//img");}

    ElementsCollection btnAddToBasketAtAnAnalogProduct() {
        return $$x("//div[@class='top-small-products__items']//a[contains(@class,'still_add_to_basket')]");
    }

    SelenideElement btnShowReplacement(String artOfProduct) {
        return $x("//span[contains(text(),'" + artOfProduct + "')]/ancestor::li[@class='ovVisLi item_not_available']//div[contains(@class,'show_alternative__btn')]");
    }

    SelenideElement analogBlockOfProduct() {
        return $x("//div[@class='top-small-products__items']");
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }
}
