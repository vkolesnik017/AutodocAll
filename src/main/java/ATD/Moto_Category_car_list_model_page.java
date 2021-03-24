package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
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
        return $$x("//div[@class='small-product-button price_box']//a");
    }
    ElementsCollection visibleBtnAddToBasketAtAnAnalogProduct() {
        return $$x("//div[@class='small-product-button price_box']//a").filter(visible);
    }
    SelenideElement btnShowReplacement(String artOfProduct) {
        return $x("//span[contains(text(),'" + artOfProduct + "')]/ancestor::li[@class='ovVisLi  item_not_available']//div[contains(@class,'show_alternative__btn')]");
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

    ElementsCollection childCategoriesInSideBar() {return $$x("//div[@class='block categories blue topSubCats']//li/a");}

    ElementsCollection analogProducts() {return $$x("//div[@class='top-small-products-items__item']").filter(visible);}

    ElementsCollection detailsBlockOfAnalogProduct() {return $$x("//div[@class='rec_prod_info_popup']").filter(visible);}

    SelenideElement productList() {return $x("//ul[@class='list_products ']");}

    ElementsCollection btnAddToBasket() {return $$x("//*[self::div[@class='button '] or self::div[@class='button not_active']]");}

    ElementsCollection attributeOfBtnAddedToBasket() { return $$x("//div[@class='count']/following-sibling::div[1]");}

    SelenideElement forwardNextPaginator() {return $x("//span[@class='next'][1]/a");}

    ElementsCollection priceOfProduct() {return $$x("//p[contains(@class,'actual_price')]");}
}
