package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Categories_maker_page {

    SelenideElement markeOfMotoInSelector() {
        return $(byId("form_maker_id"));
    }

    SelenideElement modelFiledInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorFiledInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement searchButton() {
        return $x("//a[contains(@class,'search_button')]");
    }

    SelenideElement btnResetOfSelector() {
        return $(byId("reset_selector_form"));
    }

    ElementsCollection linksOfBreadCrumbs() {
        return $$x("//div[@class='steps breadcrumbs']//li");
    }

    SelenideElement iconOfFirstLinksAtBreadCrumbs() {
        return $x("//div[@class='steps breadcrumbs']//li//img");
    }

    SelenideElement titleOfFirstLinksAtBreadCrumbs() {
        return $x("//li[@itemprop='itemListElement']//a");
    }

    SelenideElement topParentChildBlock() {
        return $x("//ul[@class='moto-parts-catalog__list']");
    }

    ElementsCollection topParentBlocks() {
        return $$x("//ul[@class='moto-parts-catalog__list']//li");
    }

    ElementsCollection imageOfTopParentBlock() {
        return $$x("//span[@class='moto-parts-catalog__item-image']");
    }

    ElementsCollection titleOfTopParentBlock() {
        return $$x("//span[@class='moto-parts-catalog__item-title']");
    }

    ElementsCollection topChildLinks(int position) {
        return $$x("(//div[@class='moto-parts-catalog__cat-list'])[" + position + "]//a/span");
    }

    SelenideElement mainHeadline() {
        return $x("//div[@class='title_count_search']/h2");
    }

    SelenideElement iconOfBrandInHeadline() {
        return $x("//div[@class='title_count_search']/img");
    }

    SelenideElement headlineOfModelsBlock() {
        return $x("//div[@class='moto-cars-models__title']/h3");
    }

    ElementsCollection linksOfModels() {
        return $$x("//ul[@class='moto-cars-models__list']/li");
    }

    SelenideElement breadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']");
    }

    SelenideElement linkMoreOfModelBlock() {
        return $x("//div[@class='moto-cars-models']//div[@class='m_text show']");
    }

    SelenideElement modelBlock() {
        return $x("//div[@class='moto-cars-models']");
    }

    ElementsCollection linksOfMotoModels() {
        return $$x("//ul[@class='moto-cars-models__list']/li");
    }

    ElementsCollection imageOfMotoModels() {
        return $$x("//ul[@class='moto-cars-models__list']/li//img").filter(visible);
    }

    ElementsCollection titleOfMotoModels() {
        return $$x("//ul[@class='moto-cars-models__list']/li//span[2]").filter(visible);
    }

    ElementsCollection yearOfMotoModels() {
        return $$x("//ul[@class='moto-cars-models__list']/li//span[3]").filter(visible);
    }

    SelenideElement titleOfModelsBlock() {
        return $x("//div[@class='moto-cars-models__title']");
    }

    SelenideElement headlineOfTopParentCategories() {
        return $x("//div[@class='moto-parts-catalog']/strong");
    }

    ElementsCollection topProducts() {
        return $$x("//div[contains(@class,'product-list__row')]/ul/li");
    }

    SelenideElement mainImageBlock() {
        return $x("//div[@class='moto-top-content__model']");
    }

    SelenideElement mainImageOfBrand() {
        return $x("//div[@class='moto-top-content__model']/img");
    }


    public SelenideElement loginBtnInHeader() {
        return $(byCssSelector(".sigin_btn>a"));
    }

    SelenideElement mailFieldLogin() {
        return $(By.id("login_top_email"));
    }

    SelenideElement passFieldLogin() {
        return $(By.xpath("//input[@type='password']"));
    }

    SelenideElement submitBtnLogin() {
        return $(By.xpath("//a[@class='enter submit']"));
    }

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    SelenideElement garageIconInHeader() {return $x("//div[@class='header-garage js-header-garage']");}

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement seoText() {return $(".moto-cars-features");}
}
