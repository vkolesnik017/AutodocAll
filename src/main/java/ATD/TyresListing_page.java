package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

class TyresListing_page {

    SelenideElement buyButton() { return $x("//div[@class='button ']/a[@id='search_page_product']"); }

    public ElementsCollection widthCharacteristic() { return $$x("//*[contains(text(),'Breite:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection heightCharacteristic() { return $$x("//*[contains(text(),'Höhe:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection radiusCharacteristic() { return $$x("//*[contains(text(),'Zoll:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection seasonCharacteristic() { return $$x("//*[contains(text(),'Reifenart:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection typeCharacteristic() { return $$x("//*[contains(text(),'Typ:')]/ancestor :: li[1]/span[2]"); }

    public ElementsCollection speedIndexCharacteristic() { return $$x("//*[contains(text(),'Geschwindigkeitsindex:')]/ancestor :: li[1]/span[2]"); }

    SelenideElement tyresSelectorOnListing() { return $("#tyres_search_form"); }

    SelenideElement tyresSearchButtonOnListing() { return $(".search_button"); }

    SelenideElement firstTyreTitleOnListing() { return $x("(//*[@class='name'])[1]"); }

    SelenideElement firstActiveBrandInBlock() { return $x("//*[@class='js-filter-item ga-click-criteria-filter active slick-slide slick-current slick-active']/img"); }

    SelenideElement speedIndexOnListingValue() { return $x("(//*[@class='multiple-select__title'])[2]/span/b"); }
}
