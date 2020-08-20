package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Car_parts_motoroil_page {

    SelenideElement oilLitericon() {return $("div.oil-title-info-block");}

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}

    SelenideElement mainHeadline() {return $x("//div[@class='listing_title listing-title--oil']/h1");}

    SelenideElement selector() {return $x("//div[@class='mainblock-search__car ']");}

    SelenideElement markeFieldInSelector() {return $(byId("form_maker_id"));}

    SelenideElement modelFieldInSelector() {return $(byId("form_model_id"));}

    SelenideElement motorFieldInSelector() {return $(byId("form_car_id"));}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

    SelenideElement kbaFirstValueInSelector() {return $(byId("kba1"));}

    SelenideElement kbaSecondValueInSelector() {return $(byId("kba2"));}

    SelenideElement nextPagePagination() {return $x("//span[@class='next'][1]/a");}

    ElementsCollection productsOnPage() {return $$x("//div[@class='listing_items']/div");}

    SelenideElement imageOfProductTecDocListingBlock(int position) {
        return $x("(//div[@class='listing-parent-pkw']/img)["+position+"]");
    }
}
