package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$x;

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
}
