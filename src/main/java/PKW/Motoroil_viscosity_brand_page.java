package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class Motoroil_viscosity_brand_page {

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}

    SelenideElement mainHeadline() {return $x("//div[@class='listing_title listing-title--oil']/h1");}

    ElementsCollection relinkingBlocks() {return $$x("//div[@class='listing_microdata']");}

    SelenideElement titleOfRelinkingBLocks(int positionOfBlock) {return $x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//div[@class='lm_title']");}

    SelenideElement contentPartOfRelinkingBLocks(int positionOfBlock) {return $x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//div[@class='lm_content']");}

    ElementsCollection linksOfRelinkingBlocks(int positionOfBlock) {return $$x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//li/a");}

    SelenideElement toleranceFilterBlock() {
        return $x("//div[contains(text(),'Herstellerfreigabe')]/..");
    }

    SelenideElement visibleLinksOfToleranceBlock(String expectedFilter) {return $x("//div[contains(text(),'Herstellerfreigabe')]/../div/div//label[contains(text(),'"+expectedFilter+"')]");}

    SelenideElement presenceAttributeOfCheckBox(String expectedFilter) {return $x("//div[contains(text(),'Herstellerfreigabe')]/../div/div//label[contains(text(),'"+expectedFilter+"')]/preceding-sibling::input");}

    SelenideElement toleranceFieldInSelector() {return $(byName("releases_id"));}

    ElementsCollection toleranceCharacteristicsInProductDescription() {return $$x("//span[contains(text(),'Herstellerfreigabe:')]/following-sibling::span");}

    ElementsCollection selectedToleranceFilter() {return $$x("//div[contains(text(),'Herstellerfreigabe')]/..//input[@class='checkbox' and @checked]/../label");}
}
