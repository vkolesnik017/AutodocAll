package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class Motoroil_Release_page {

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}

    SelenideElement mainHeadline() {return $x("//div[@class='listing_title listing-title--oil']/h1");}

    ElementsCollection linksOfRelinkingBlocks(int positionOfBlock) {return $$x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//p/a");}

    SelenideElement toleranceFilterBlock() {
        return $x("//div[contains(text(),'Herstellerfreigabe')]/..");
    }

    SelenideElement visibleLinksOfToleranceBlock(String expectedFilter) {return $x("//div[contains(text(),'Herstellerfreigabe')]/../div/div//label[contains(text(),'"+expectedFilter+"')]");}

    SelenideElement presenceAttributeOfCheckBox(String expectedFilter) {return $x("//div[contains(text(),'Herstellerfreigabe')]/../div/div//label[contains(text(),'"+expectedFilter+"')]/preceding-sibling::input");}

    SelenideElement toleranceFieldInSelector() {return $(byName("releases_id"));}

    ElementsCollection toleranceCharacteristicsInProductDescription() {return $$x("//span[contains(text(),'Herstellerfreigabe:')]/following-sibling::span");}

    SelenideElement selector() {return $x("//div[@class='mainblock-search__car ']");}

    SelenideElement errorToolTipOfMarkeFieldInSelector() {return $(byId("selector-error-tooltip"));}

    SelenideElement errorToolTipOfModelFieldInSelector() {return $(byId("selector-error-tooltip-model"));}

    SelenideElement errorToolTipOfMotorFieldInSelector() {return $(byId("selector-error-tooltip-car"));}

    SelenideElement btnSearchOfSelector() {return $x("//div[@id='selector-wrapper']//a[contains(@class,'submit')]");}

    SelenideElement markeFieldInSelector() {return $(byId("form_maker_id"));}

    SelenideElement modelFieldInSelector() {return $(byId("form_model_id"));}

    SelenideElement motorFieldInSelector() {return $(byId("form_car_id"));}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}
    }
