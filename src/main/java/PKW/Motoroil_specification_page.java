package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class Motoroil_specification_page {

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}

    ElementsCollection relinkingBlocks() {return $$x("//div[@class='listing_microdata']");}

    SelenideElement titleOfRelinkingBLocks(int positionOfBlock) {return $x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//div[@class='lm_title']");}

    SelenideElement contentPartOfRelinkingBLocks(int positionOfBlock) {return $x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//div[@class='lm_content']");}

    ElementsCollection linksOfRelinkingBlocks(int positionOfBlock) {return $$x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//*[self::p or self::li]/a");}

    SelenideElement mainHeadline() {return $x("//div[@class='listing_title listing-title--oil']/h1");}

    SelenideElement markeFieldInSelector() {return $(byId("form_maker_id"));}

    SelenideElement modelFieldInSelector() {return $(byId("form_model_id"));}

    SelenideElement motorFieldInSelector() {return $(byId("form_car_id"));}

    SelenideElement selector() {return $x("//div[@class='mainblock-search__car ']");}

    SelenideElement btnSearchOfSelector() {return $x("//div[@id='selector-wrapper']//a[contains(@class,'submit')]");}

    ElementsCollection specificationCharacteristicsInProductDescription() {return $$x("//span[contains(text(),'Spezifikation:')]/following-sibling::span");}

    SelenideElement listingBlock() {return $x("//div[@class='listing_items']");}

    SelenideElement specificationFieldInSelector() {return $(byName("specifications_id"));}

    SelenideElement selectedSpecificationInSideBar(String titleOfSpecification) {return $x("//label[contains(text(),'"+titleOfSpecification+"')]/../input");}
}
