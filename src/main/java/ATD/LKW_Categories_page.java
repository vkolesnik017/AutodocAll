package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class LKW_Categories_page {
    SelenideElement tecDocCatalog() {
        return $x("//div[@class='car-parts-categories']");
    }

    SelenideElement headlineInHeader() {
        return $x("//div[@class='car-parts-categories__title']/h2");
    }

    SelenideElement titleOfCatalog() {
        return $x("//div[@class='car-parts-categories__title']");
    }


    SelenideElement verticalTruckSelectorInCloseCondition() {
        return $x("//div[contains(@class,'catalog-title__change-car')]");
    }

    SelenideElement verticalTruckSelectorInOpenCondition() {
        return $x("//div[@class='block-select-car block-select-car--sidebar']");
    }

    SelenideElement markeOfVerticalTruckSelector() {
        return $(byName("maker_id"));
    }

    SelenideElement modelOfVerticalTruckSelector() {
        return $(byName("model_id"));
    }

    SelenideElement motorOfVerticalTruckSelector() {
        return $(byName("car_id"));
    }

    SelenideElement buttonSuchenOfVerticaltruckSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");
    }

    SelenideElement resetBtnInVerticalCarSelectorInOpenCondition() {
        return $x("//a[@class='block-select-car__update hidden js--btn_reset_form']");
    }

    SelenideElement headlineOfTopProductsBlock() {return $x("//div[@class='truck-car-parts-top-product__title']");}

    SelenideElement topBLock() {return $x("//div[@class='truck-car-parts-top-product']");}

    ElementsCollection productsOfTopBlock() {return $$x("//div[@class='product-list__row']/ul/li");}

    SelenideElement topProductsBlock() {return $x("//div[@class='truck-car-parts-top-product']");}

    ElementsCollection additionInfoBlockOfTopProduct() {return $$x("//div[@class='product-list__item__popup']");}
}
