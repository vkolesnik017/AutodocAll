package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

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

    SelenideElement firstActiveBrandInBlock() { return $x("//*[@class='js-filter-item ga-click-criteria-filter active slick-slide slick-current slick-active']"); }

    SelenideElement firstActiveBrandInBlockMoto() { return $x("//*[@class='js-filter-item ga-click-criteria-filter active']"); }

    SelenideElement speedIndexOnListingValue() { return $x("(//*[@class='multiple-select__title'])[2]/span/b"); }

    SelenideElement widthValueInSelector() { return $x("//*[@class='main-selector Width']//*[@selected]"); }

    SelenideElement heightValueInSelector() { return $x("//*[@class='main-selector CrossSections']//*[@selected]"); }

    SelenideElement diameterValueInSelector() { return $x("//*[@class='main-selector Size']//*[@selected]"); }

    ElementsCollection productsInTopBlock() { return $$(".top-products-lkw__slider-item.slick-active > span > a"); }

    SelenideElement nextButtonInTopBlock() { return $(".slick-next.slick-arrow"); }

    SelenideElement previousButtonInTopBlock() { return $(".slick-prev.slick-arrow"); }

    SelenideElement productWidthCharacteristic() { return $x("//*[contains(text(),'Breite:')]/ancestor :: li[1]/span[2]"); }

    SelenideElement productHeightCharacteristic() { return $x("//*[contains(text(),'Höhe:')]/ancestor :: li[1]/span[2]"); }

    SelenideElement productDiameterCharacteristic() { return $x("//*[contains(text(),'Zoll:')]/ancestor :: li[1]/span[2]"); }

    SelenideElement buyButtonInTopBlock() { return $x("(//*[@data-ga-action='Add_to_basket'])[5]"); }

    ElementsCollection buyButtonsInTopBlock() { return $$(".top-products-lkw__slider-item.slick-active > div > .still_add_to_basket"); }

    SelenideElement productPage() { return $(".product-page"); }

    ElementsCollection pagesInSlider() { return $$x("//li[@role='presentation']"); }

    ElementsCollection linksInTopBlock() { return $$(".top-products-link-wrap"); }

    SelenideElement brandFilterButton() { return $x("//*[@class='js-filter-item ga-click-criteria-filter slick-slide slick-active']"); }

    SelenideElement motoBrandFilterButton() { return $x("//*[@class='js-filter-item ga-click-criteria-filter']"); }

    ElementsCollection productTitleOnListing() { return $$(".name > a");}

    ElementsCollection productsRatingOnListing() { return $$x("//div[@class='review-vote']/span/span"); }

    SelenideElement brandInputInSelector() { return $(".multiple-select__dropdown-search > input"); }

    SelenideElement brandFilterTyresInSidebar() { return $x("//*[@class='tires_marks criteria-filter-block-js']//a"); }

    SelenideElement brandNameInSelector() { return $x("//*[@class='main-selector Brand']//span"); }

    ElementsCollection titleOfAllProducts() { return $$(".name > a"); }

    SelenideElement diameter17InRelinkBlock() { return $x("//*[contains (text(),'17 Zoll')]"); }

    SelenideElement allSizesButton() { return $(".all_sizes"); }

    public SelenideElement dimensionLinkListingRoute() { return $x("(//*[contains (text(),'Die beliebtesten Reifengrößen')]/..//a)[3]"); }

    SelenideElement brandRelinkBlock() { return $(".tires_marks"); }

    SelenideElement diameterRelinkBlock() { return $x("//*[contains (text(),'Reifen nach Zoll')]/.."); }

    SelenideElement paymentsBlock() { return $(".pays_block"); }

    SelenideElement dimensionRelinkBlock() { return $x("//*[contains (text(),'Die beliebtesten Reifengrößen')]/.."); }

    SelenideElement deliveryBlock() { return $(".pays_block"); }

    ElementsCollection advantagesBlock() { return $$("div .features li"); }

    SelenideElement diameterBlockOnCatalogTyresRoute() { return $(".table_sizes tr"); }

    SelenideElement sizesBlockOnCatalogTyresRoute() { return $(".table_sizes"); }

    public SelenideElement dimensionLinkCatalogRoute() { return $x("//*[@class='table_sizes']//tr[2]//a"); }

    SelenideElement diameterLinkCatalogRoute() { return $x("//*[@class='table_sizes']//th/a"); }

    SelenideElement breadcrumbsBlock() { return $x("//*[@class='steps breadcrumbs']"); }

    SelenideElement breadcrumbsFirstButton() { return $x("(//a[@itemprop = 'item'])[1]"); }

    SelenideElement breadcrumbsSecondButton() { return $x("(//a[@itemprop = 'item'])[2]"); }

    SelenideElement breadcrumbsThirdButton() { return $x("(//a[@itemprop = 'item'])[3]"); }

    SelenideElement breadcrumbsLastButton() { return $(".last_active_element"); }

    SelenideElement brandFilterBlockOnTyresListing() { return $("#selected-instalation__slider"); }

}
