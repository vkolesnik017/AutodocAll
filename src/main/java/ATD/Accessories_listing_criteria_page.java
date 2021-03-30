package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Accessories_listing_criteria_page {

    SelenideElement headlineOfGenericsBlock() {return $x("//div[@data-name='category']/div[1]");}

    public ElementsCollection categoryFilters() {return $$x("//div[@class='filter-disk__image']/following-sibling::*");}

    ElementsCollection listingOfProducts() {return $$x("//ul[@class='list_products ']/li");}

    ElementsCollection titleOfProducts() {return $$x("//div[@class='name']/a");}

    ElementsCollection valueOfCountInDescription() {return $$x("//span[text()='Menge:  ']/following-sibling::span");}

    SelenideElement colorFilterBlockInSideBar() {return $x("//div[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_436']");}

    SelenideElement btnMoreOfColorFilterInSideBar() {return $x("//div[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_436']//i");}

    SelenideElement colorFilterByTitle(String color) {return $x("//label[text()='"+color+"']");}

    ElementsCollection colorCharacteristicOfProduct() {return $$x("//span[@class='lf'][text()='Farbe:  ']//following-sibling::span");}

    SelenideElement selectedMaterialFilterByName(String filter) {return $x("//div[@class='filter-disk__form']//label[contains(text(),'"+filter+"')]");}

    SelenideElement materialFilterByName(String filter) {return $x("//li[@class='js-filter-wrapper-item']//a[text()='"+filter+"']");}

    ElementsCollection materialCharacteristicOfProduct() {return $$x("//span[@class='lf'][text()='Material:  ']/following-sibling::span");}

    }
