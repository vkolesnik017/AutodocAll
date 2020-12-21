package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Tyres_maker_page {
    SelenideElement paymentMethodsBlock() {return $x("//div[@class='tyres-payment']");}

    ElementsCollection iconOfPaymentMethods() {return $$x("//div[@class='tyres-payment']//img");}

    SelenideElement advantagesBlock() {return $x("//div[@class='advantages']");}

    ElementsCollection titlesOfAdvantagesBlock() {return $$x("//div[@class='advantages__title']");}

    ElementsCollection numberingOfAdvantageBlocks() {return $$x("//div[@class='advantages__count']");}

    ElementsCollection infoTextOfAdvantageBlocks() {return $$x("//div[@class='advantages__text']");}

    ElementsCollection iconOfAdvantageBlocks() {return $$x("//div[@class='advantages__icon']/img");}

    SelenideElement carBrandsBlock() {return $x("//div[@class='other-marks']");}

    ElementsCollection linksInCarBrandsBlock() {return $$x("//div[@class='other-marks']/a");}

    ElementsCollection logoInCarBrandsBlock() {return $$x("//div[@class='other-marks']/a/img");}

    ElementsCollection visibleTitlesOfTopTyres() {return $$x("//div[@class='top-products-lkw__slider-item-wrapper']/../a").filter(visible);}

    ElementsCollection typeOfSeasonsInTopBlock() {return $$x("//div[@class='tyre-season-tabs__row tabs-js']/div");}

    ElementsCollection tyreSeasons() {return $$x("//div[@class='tyre-seasons']//span");}

    public ElementsCollection brandsInSlider() {
        return $$x("//a[contains(@class,'top-brands-slider')]//img");
    }

    SelenideElement seasonBlock() {
        return $x("//div[@class='tyre-seasons']");
    }

    ElementsCollection seasonsInSeasonBlock() {
        return $$x("//div[@class='tyre-seasons__item']//img");
    }

}
