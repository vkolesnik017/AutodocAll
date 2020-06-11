package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_makers_page {
    SelenideElement brandOfMotoField() {
        return $(byId("form_maker_id"));
    }

    SelenideElement modelFiledInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorFiledInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement searchButton() {return $x("//a[contains(@class,'search_button')]");}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

    SelenideElement mainHeadline() {return $x("//div[@class='top_title no_image']/h2");}

    SelenideElement headlineOfBrandsBlock() {return $x("//div[@class='top_title no_image']/h2");}

    ElementsCollection linksOfBrands() {return $$x("//div[@class='marke-list marke-list--grid']/ul/li");}
}
