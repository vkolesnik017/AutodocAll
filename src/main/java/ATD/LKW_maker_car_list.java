package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;


class LKW_maker_car_list {

    SelenideElement tecDocCatalog() {return $x("//div[@class='car-parts-categories__list']");}

    SelenideElement inputSearchInHeader() { return $(byId("search"));}

    SelenideElement dropMenuOfSearchFieldInHeader()  {return $x("//div[@class='autocomplete-suggestions']");}
    ElementsCollection productsInDropMenuOfSearchField() {return $$x("//div[@class='autocomplete-suggestion']");}
}
