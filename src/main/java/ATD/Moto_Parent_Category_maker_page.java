package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Parent_Category_maker_page {

    SelenideElement  btnResetOfSelector() {return $(byId("reset_selector_form"));}

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

    SelenideElement headlineOfChildCategoryBlock() {return $x("//div[@class='title']");}

    ElementsCollection linksOfChildCategoriesList() {return $$x("//ul[@class='simple_links']/li");}

    ElementsCollection breadCrumbsLinks() {return $$x("//div[@class='steps breadcrumbs']/ul/li//span/*[self::a or self::span]");}

    SelenideElement iconOfCatalogBrandInBreadCrumbs() {return $x("//li[@class='step_1 active parts_step_1']//img");}
}
