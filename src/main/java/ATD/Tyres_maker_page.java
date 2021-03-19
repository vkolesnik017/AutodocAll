package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Tyres_maker_page {

//Locator for tyres-payment block

    SelenideElement paymentMethodsBlock() {return $x("//div[@class='tyres-payment']");}

    ElementsCollection iconOfPaymentMethods() {return $$x("//div[@class='tyres-payment']//img");}


//Locator for advantages block

    SelenideElement advantagesBlock() {return $x("//div[@class='advantages']");}

    ElementsCollection titlesOfAdvantagesBlock() {return $$x("//div[@class='advantages__title']");}

    ElementsCollection numberingOfAdvantageBlocks() {return $$x("//div[@class='advantages__count']");}

    ElementsCollection infoTextOfAdvantageBlocks() {return $$x("//div[@class='advantages__text']");}

    ElementsCollection iconOfAdvantageBlocks() {return $$x("//div[@class='advantages__icon']/img");}


//Locators for other-marks block

    SelenideElement carBrandsBlock() {return $x("//div[@class='other-marks']");}

    ElementsCollection linksInCarBrandsBlock() {return $$x("//div[@class='other-marks']/a");}

    ElementsCollection logoInCarBrandsBlock() {return $$x("//div[@class='other-marks']/a/img");}


//Locators for tyre-season-tabs block

    ElementsCollection visibleTitlesOfTopTyres() {return $$x("//div[@class='top-products-lkw__slider-item-wrapper']/../a").filter(visible);}

    ElementsCollection typeOfSeasonsInTopBlock() {return $$x("//div[@class='tyre-season-tabs__row tabs-js']/div");}

    ElementsCollection visibleTopTyres() {return $$x("//div[contains(@class,'slick-active')]").filter(visible);}


//Locators for tytyre-seasons block

    ElementsCollection tyreSeasons() {return $$x("//div[@class='tyre-seasons']//span");}

    SelenideElement seasonBlock() {
        return $x("//div[@class='tyre-seasons']");
    }

    ElementsCollection seasonsInSeasonBlock() {
        return $$x("//div[@class='tyre-seasons__item']//img");
    }


// Locators for top-brands-slider block

    public ElementsCollection brandsInSlider() {
        return $$x("//a[contains(@class,'top-brands-slider')]//img");
    }


//Locators for reifen_select reifen_select_block_pkw block

    ElementsCollection vehicleLinksInSelector() {return $$x("//ul[@class='select_links']//li").filter(visible);}


//Locators for car-group block

    SelenideElement carGroupBlock() { return $x("//div[@class='car-group']"); }

    SelenideElement titleCarGroupBlock() { return $x("//div[@class='car-group__title']/strong"); }

    SelenideElement linkModel() { return $x("//div[@class='car-group__wrap']//ul/li/a"); }

    ElementsCollection linkModels() { return $$x("//div[@class='car-group__wrap']//ul/li/a"); }

    ElementsCollection linkModelsBlocks() { return $$x("//div[@class='car-group__wrap']//ul"); }





//Locators for


}
