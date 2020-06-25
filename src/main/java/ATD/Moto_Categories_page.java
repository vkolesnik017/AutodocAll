package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Categories_page {

    SelenideElement brandOfMotoField() {return $(byId("form_maker_id")); }

    SelenideElement modelFiledInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorFiledInSelector() {return $(byId("form_car_id")); }

    SelenideElement searchButton() {return $x("//a[contains(@class,'search_button')]");}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

    SelenideElement selectorInCloseCondition() {return $x("//div[@class='catalog-title__change-car ']");}

    SelenideElement mainFormOfSelector() {return $(byId("selector-wrapper"));}

    SelenideElement mainHeadline() {return $x("//div[@class='car-parts-categories__title']/h2");}

    SelenideElement brandsBlock() {return $x("//div[@class='moto-manufactures']");}

    ElementsCollection imageOfMotoBrands() {return $$x("//ul[@class='moto-manufactures__list']/li//img");}

    ElementsCollection titleOfMotoBrands() {return $$x("//ul[@class='moto-manufactures__list']/li//div[2]");}

    ElementsCollection linksOfBrands() {return $$x("//ul[@class='moto-manufactures__list']/li//a");}

    SelenideElement brandsTitle() {return $x("//strong[@class='moto-manufactures__title']");}

    SelenideElement tecDocCatalog() {return $x("//div[@class='car-parts-categories']");}

    ElementsCollection parentsCategoriesOfTecDocCatalog() {return $$x("//div[@class='car-parts-categories__item-link']");}

    ElementsCollection  childCategoriesFirstLevelBlock() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']");}

    ElementsCollection childCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__link-list']//a");}

    ElementsCollection childCategoriesFirstLevell() {return $$x("//div[contains(@class,'car-parts-categories-modal__level1')]/ul[@class='car-parts-categories-modal__link-list']//a").filter(visible);}

    ElementsCollection intermediateChildCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__cat-list']/li");}

    ElementsCollection intermediateChildCategoriesFirstLevell() {return $$x("//div[contains(@class,'car-parts-categories-modal__level1')]/ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    SelenideElement childCategoriesSecondLevelBlock() {return $x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']");}

    ElementsCollection childCategoriesSecondLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]//div[contains(@class,'car-parts-categories-modal__level2')]//li").filter(visible);}

    ElementsCollection childCategoriesSecondLevell() {return $$x("//div[contains(@class,'car-parts-categories-modal__level1')]//div[contains(@class,'car-parts-categories-modal__level2')]//li").filter(visible);}

    ElementsCollection imageOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-link']//img");}

    ElementsCollection imageOfChildCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__link-list']//img");}

    ElementsCollection imageOfChildCategoriesSecondLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]//div[contains(@class,'car-parts-categories-modal__level2')]//li//img").filter(visible);}

    ElementsCollection childCategoriesSecondLevelBlockCheck(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]//div[@class='car-parts-categories-modal__cat-name']/following-sibling::div[contains(@class,'car-parts-categories-modal__level2')]");}

    SelenideElement childCategoriesSecondLevelBlockCheckk() {return $x("//div[contains(@class,'car-parts-categories-modal__level1')]//div[@class='car-parts-categories-modal__cat-name']/following-sibling::div[contains(@class,'car-parts-categories-modal__level2')]");}

    SelenideElement closedSelector() {return $x("//div[contains(@class,'catalog-title__change-car')]");}


    ElementsCollection imedCategory() {return $$x("//ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    ElementsCollection chCategory() {return $$x("//ul[@class='car-parts-categories-modal__link-list']/li").filter(visible);}

    SelenideElement secondLevelBlock() {return $x("//li[@class='active']/div[2]");}

     SelenideElement topProductsBlock() {return $x("//div[@class='moto-top-product']");}

    SelenideElement headlineOfTopProductsBlock() {return $x("//div[@class='moto-top-product__title']");}

    ElementsCollection btnAddToBasketTopProduct() {
        return $$x("//div[@class='price_box product-list__item__button']");
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }
}
