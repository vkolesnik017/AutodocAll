package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class Motoroil_viscosity_page {

    SelenideElement breadCrumbsBlock() {
        return $x("//div[@class='crabs']");
    }

    ElementsCollection linksOfBreadCrumbsBlock() {
        return $$x("//div[@class='crabs']/div/a");
    }

    SelenideElement productsListBlock() {
        return $x("//div[@class='listing_items']");
    }

    SelenideElement paymentMethodsBlock() {
        return $x("//div[@class='sidebar']//div[contains(text(),'Zahlungsmethode')]/..");
    }

    ElementsCollection imageOfPaymentMethods() {
        return $$x("//div[@class='sidebar']//div[contains(text(),'Zahlungsmethode')]/..//ul/li/img");
    }

    SelenideElement advantagesBlock() {
        return $x("//div[@class='sb_content features_wrap']");
    }

    SelenideElement headLineOfAdvantagesBlock() {
        return $x("//div[@class='sb_content features_wrap']/../div[1]");
    }

    ElementsCollection advantagesLinks() {
        return $$x("//div[@class='sb_content features_wrap']//ul/li");
    }

    ElementsCollection titleOfAdvantagesLinks() {
        return $$x("//div[@class='sb_content features_wrap']//ul/li//strong");
    }

    ElementsCollection hoveringTextOfAdvantagesLinks() {
        return $$x("//span[@class='pophover-text']");
    }

    SelenideElement mainHeadline() {
        return $x("//div[@class='listing_title listing-title--oil']/h1");
    }

    SelenideElement toleranceFilterBlock() {
        return $x("//div[contains(text(),'Herstellerfreigabe')]/..");
    }

    SelenideElement visibleLinksOfToleranceBlock(String expectedFilter) {
        return $x("//div[contains(text(),'Herstellerfreigabe')]/../div/div//label[contains(text(),'" + expectedFilter + "')]");
    }

    SelenideElement presenceAttributeOfCheckBox(String expectedFilter) {
        return $x("//div[contains(text(),'Herstellerfreigabe')]/../div/div//label[contains(text(),'" + expectedFilter + "')]/preceding-sibling::input");
    }

    SelenideElement toleranceFieldInSelector() {
        return $(byName("releases_id"));
    }

    ElementsCollection toleranceCharacteristicsInProductDescription() {
        return $$x("//span[contains(text(),'Herstellerfreigabe:')]/following-sibling::span");
    }

    ElementsCollection selectedToleranceFilter() {
        return $$x("//div[contains(text(),'Herstellerfreigabe')]/..//input[@class='checkbox' and @checked]/../label");
    }

    SelenideElement viscosityGradeInSelector() {
        return $(byName("viscosity_id"));
    }

    SelenideElement selectedViscosityLink(String viscosityLink) {
        return $x("//div[contains(text(),'" + viscosityLink + "')]/ancestor::span[contains(@class,'active')]");
    }

    ElementsCollection btnAddedProductToBasket() {
        return $$x("//div[@class='basket_btn button active_red_button ']/a");
    }

    ElementsCollection activeValueOfVolumeAtProduct() {
        return $$x("//div[contains(@class,'right_side')]/div[1]");
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='show_cart ga-click']");
    }

}
