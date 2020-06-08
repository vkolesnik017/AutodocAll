package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Categories_maker_page {

    SelenideElement markeOfMotoInSelector() {return $(byId("form_maker_id"));}

     SelenideElement modelFiledInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorFiledInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement searchButton() {return $x("//a[contains(@class,'search_button')]");}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

    ElementsCollection linksOfBreadCrumbs() {return $$x("//div[@class='steps breadcrumbs']//li");}

    SelenideElement iconOfFirstLinksAtBreadCrumbs() {return linksOfBreadCrumbs().get(0).$x("//img");}

    SelenideElement titleOfFirstLinksAtBreadCrumbs() {return $x("//li[@itemprop='itemListElement']//a");}
}
