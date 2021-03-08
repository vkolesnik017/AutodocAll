package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class Tyres_feature_page {

    ElementsCollection btnMoreOfLinkingBlock() {return $$x("//div[@class='most-popular']//span[@class='link']");}

    SelenideElement linkingBlock() {return $x("//div[@class='most-popular']");}

    ElementsCollection btnPaginatorOfLinkingBlock() {return $$x("//div[@class='most-popular']//button");}

    ElementsCollection titlesOfLinkingBlocks() {return $$x("//div[@class='most-popular']//a/div[2]");}

    ElementsCollection visibleTitleOfLinkingBlocks() {return $$x("//div[@class='most-popular']//a/div[2]").filter(visible);}

    SelenideElement headLineOfLinkingBlock() {return $x("//div[@class='most-popular__heading']");}

    ElementsCollection visibleBtnMoreOfLinkingBlock() {return $$x("//div[@class='most-popular']//span[@class='link']").filter(visible);}

    SelenideElement mainHeadline() {return $x("//h1[@class='title-reifen']");}

    ElementsCollection seoHeadlines() {return $$x("//div[@class='reifen-text-rows']/strong");}

    ElementsCollection seoTexts() {return $$x("//div[@class='reifen-text-rows']//*[self::p or self::ul/li/b]");}

    SelenideElement tyresSizeSelector() {return $(".reifen-selector");}

    SelenideElement widthDropdown() { return $("select#form_Width"); }

    SelenideElement heightDropdown() { return $("select#form_CrossSections"); }

    SelenideElement diameterDropdown() { return $("#form_Size"); }

    SelenideElement winterSeason() {return $(byId("winter-radio"));}

    SelenideElement summerSeason() {return $("#summer-radio");}

    public SelenideElement summerSeasonRadioBtn() {
        return $x("//div[@class='radio-block']//input[@id='summer-radio']/../span");
    }

    SelenideElement allSizesButtonInSizeBlock() {
        return $x("//div[@class='tyre-size-block-button']/a");
    }

    SelenideElement brandButtonInTopBlock() {
        return $x("//div[@class='top-brands-slider']//a//img");
    }

    SelenideElement diameterRelinkBlock() {
        return $x("//ul[@class='list-zoll']");
    }

    ElementsCollection linksInDiameterblock() {
        return $$x("//ul[@class='list-zoll']//li/a");
    }

    SelenideElement dimensionRelinkBlock() {
        return $x("//ul[@class='list-size']");
    }

    ElementsCollection linksInDimensionRelinkBlock() {
        return $$x("//ul[@class='list-size']//li/a");
    }

    SelenideElement submitBtnInSelector() {
        return $x("//a[@class='button size-block__select-button']");
    }

    SelenideElement topBlock() {
        return $x("//div[@class='top-products-lkw']");
    }
    public SelenideElement allSeasonTabInTopBlock() {
        return $x("//div[contains(@class,'all-season tab-link current')]");
    }

    public SelenideElement sunSeasonTabInTopBlock() {
        return $x("//div[contains(@class,'season-sun tab-link')]");
    }

    public SelenideElement winterSeasonTabInTopBlock() {
        return $x("//div[contains(@class,'season-winter tab-link')]");
    }

    public SelenideElement topItemInAllSeason() {
        return $x("//div[@id='tab-1']//a[contains(@class,'item-title ga-click')]");
    }

    public ElementsCollection topItemInAllSeasonCollection() {
        return $$x("//div[@id='tab-1']//a[contains(@class,'item-title ga-click')]");
    }

    public SelenideElement topItemInSunSeason() {
        return $x("//div[@id='tab-2']//a[contains(@class,'item-title ga-click')]");
    }

    public ElementsCollection topItemInSunSeasonCollection() {
        return $$x("//div[@id='tab-2']//a[contains(@class,'item-title ga-click')]");
    }

    public SelenideElement topItemInWinterSeason() {
        return $x("//div[@id='tab-3']//a[contains(@class,'item-title ga-click')]");
    }

    public ElementsCollection topItemInWinterSeasonCollection() {
        return $$x("//div[@id='tab-3']//a[contains(@class,'item-title ga-click')]");
    }

    SelenideElement brandTopBlock() {
        return $x("//div[@class='top-brands-slider']");
    }

    SelenideElement brandTopBlockTitle() {
        return $x("//div[@class='top-brands-slider__title']");
    }

    ElementsCollection brandsInSlider() {
        return $$x("//a[contains(@class,'top-brands-slider')]//img");
    }

    SelenideElement secondButtonInTopBrandSlider() {
        return $x("//div[@class='top-brands-slider']//button[@class='slick-next slick-arrow']");
    }

    SelenideElement secondButtonInTopItemSlider(int numberTab) {
        return $x("//div[@id='tab-" + numberTab + "']//button[@class='slick-next slick-arrow']");
    }

    public SelenideElement slickSlideInTopItemBlockForSunSeason() {
        return $x("//div[@id='tab-2']//button[@aria-label='2 of 2']/..");
    }

    public SelenideElement slickSlideInTopItemBlockForWinterSeason() {
        return $x("//div[@id='tab-3']//button[@aria-label='2 of 2']/..");
    }
}

