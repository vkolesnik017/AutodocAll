package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
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

    SelenideElement brandsBlock() {return $x("//div[@class='marke-list marke-list--grid']");}

    ElementsCollection imageOfMotoBrands() {return $$x("//div[@class='marke-list marke-list--grid']//li//img");}

    ElementsCollection titleOfMotoBrands() {return $$x("//div[@class='marke-list marke-list--grid']//li//span[1]");}

    ElementsCollection countOfMotoBrands() {return $$x("//div[@class='marke-list marke-list--grid']//li//span[2]");}
}
