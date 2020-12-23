package PKW;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class Motoroil_Maker_page {

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}

    SelenideElement productsListBlock() {return $x("//div[@class='listing_items']");}

    ElementsCollection relinkingBlocks() {return $$x("//div[@class='listing_microdata']");}

    SelenideElement titleOfRelinkingBLocks(int positionOfBlock) {return $x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//div[@class='lm_title']");}

    SelenideElement contentPartOfRelinkingBLocks(int positionOfBlock) {return $x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//div[@class='lm_content']");}

    ElementsCollection linksOfRelinkingBlocks(int positionOfBlock) {return $$x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//p/a");}

    SelenideElement selector() {return $x("//div[@class='mainblock-search__car ']");}

    SelenideElement errorToolTipOfMarkeFieldInSelector() {return $(byId("selector-error-tooltip"));}

    SelenideElement errorToolTipOfModelFieldInSelector() {return $(byId("selector-error-tooltip-model"));}

    SelenideElement errorToolTipOfMotorFieldInSelector() {return $(byId("selector-error-tooltip-car"));}

    SelenideElement btnSearchOfSelector() {return $x("//div[@id='selector-wrapper']//a[contains(@class,'submit')]");}

    SelenideElement markeFieldInSelector() {return $(byId("form_maker_id"));}

    SelenideElement modelFieldInSelector() {return $(byId("form_model_id"));}

    SelenideElement motorFieldInSelector() {return $(byId("form_car_id"));}

    ElementsCollection btnAddedProductToBasket() {return $$x("//div[@class='basket_btn button active_red_button ']/a");}

    ElementsCollection activeValueOfVolumeAtProduct() { return $$x("//div[contains(@class,'right_side')]/div[1]");    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='show_cart ga-click']");
    }

    ElementsCollection visibleEngineOilViscosity() {return $$x("//div[@class='listing-viscosity-filters']/div[2]/a").filter(visible);}

}
