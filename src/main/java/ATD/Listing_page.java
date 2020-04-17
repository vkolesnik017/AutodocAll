package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Listing_page {

    //Side filters locators
    public SelenideElement minPriceValue() {
        return $(By.xpath("//*[@name='minprice']"));
    }

    public SelenideElement maxPriceValue() {
        return $(By.xpath("//*[@name='maxprice']"));
    }

    public SelenideElement minPriceMapping() {
        return $(By.xpath("//*[@class='min-price']"));
    }

    public SelenideElement maxPriceMapping() {
        return $(By.xpath("//*[@class='max-price']"));
    }

    public SelenideElement priceFilterSubmitButton() {
        return $(By.xpath("//*[@class='btn-submit range-price-js ']"));
    }

    public SelenideElement resetPriceFilerButton() {
        return $(By.xpath("//*[@class='btn-reset range-price-reset-js']"));
    }

    public SelenideElement priceFilterBlock() {
        return $(By.cssSelector(".js-price-range-filter"));
    }

    public SelenideElement gelochtAttribute() {
        return $(By.xpath("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_232']/div/ul/li[1]"));
    }

    public SelenideElement produktreiheFilterCheckbox() {
        return $x("//*[@id='mCSB_2_container']/li[2]");
    }

    public SelenideElement produktreiheFilterCheckboxLKW() {
        return $(".js-filter-criteria_30056 li:nth-child(2) > label");
    }

    public SelenideElement produktreiheFilterAttribute() {
        return $(By.xpath("//*[@id='mCSB_2_container']/li[2]/label"));
    }

    public SelenideElement langeFilterCheckbox() {
        return $x("//*[@id='mCSB_2_container']/li[4]");
    }

    public SelenideElement langeFilterCheckboxLKW() {
        return $x("//*[@id='mCSB_2_container']/li[2]/label");
    }

    public SelenideElement langeFilterCheckboxLKW700() {
        return $x("//*[@id='mCSB_2_container']/li[3]/label");
    }

    public SelenideElement langeFilterCheckbox450() {
        return $x("//*[@id='mCSB_2_container']/li[9]/label");
    }

    public SelenideElement langeFilterAttribute450() {
        return $x("//*[@id='mCSB_2_container']/li[9]");
    }

    public SelenideElement langeFilterCheckbox280() {
        return $x("//*[@id='mCSB_2_container']/li[19]/label");
    }

    public SelenideElement langeFilterAttribute280() {
        return $x("//*[@id='mCSB_2_container']/li[19]");
    }


    public SelenideElement langeFilterAttribute() {
        return $x("//*[@id='mCSB_2_container']/li[4]/label");
    }

    public SelenideElement wischblattausfuhrungFilterCheckbox() {
        return $x("//*[@id='mCSB_4_container']/li[1]");
    }

    public SelenideElement wischblattausfuhrungFilterAttribute() {
        return $x("//*[@id='mCSB_4_container']/li[1]/label");
    }

    public SelenideElement langeFilterCheckbox2() {
        return $x("//*[@id='mCSB_4_container']/li[2]");
    }

    public SelenideElement langeFilterAttribute2() {
        return $x("//*[@id='mCSB_4_container']/li[2]/label");
    }

    public SelenideElement langeFilterCheckbox3() {
        return $x("//*[@id='mCSB_2_container']/li[2]");
    }

    public SelenideElement langeFilterAttribute3() {
        return $x("//*[@id='mCSB_2_container']/li[2]/label");
    }

    public SelenideElement activeSideFilter() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']//li[1]/label");
    }

    public SelenideElement activeSideFilter2() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']//li[2]/label");
    }

    public SelenideElement activeSideFilter3() {
        return $x("//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//li[1]/label");
    }

    public SelenideElement secondValueInActiveSideFilter() {
        return $x("//*[@class='checkbox js-filter-click ga-click ga-click-criteria-filter active']/../../li[2]");
    }

    public SelenideElement activeSideFilter4FirstPosition() {
        return $x("(//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y'])[2]//li[1]/label");
    }

    public SelenideElement activeSideFilterLkwCheckbox() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_593']//li[1]");
    }
    public SelenideElement activeSideFilterLkw() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_339']//ul//li[1]/label"); }


    public SelenideElement activeSideFilterAttributeLkw2() {
        return $x("//*[contains(@class,'filter-criteria_339')]//li[2]");
    }

    public SelenideElement hoheThirdSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_209')]//li[3]");
    }

    public SelenideElement hoheFirstSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_209')]//li[1]");
    }

    public SelenideElement hoheSecondSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_209')]//li[2]");
    }

    public SelenideElement oberflacheSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_599')]//li");
    }

    public SelenideElement bremsscheibenartSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_232')]//li");
    }

    public SelenideElement durchmesserSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_200')]//li[4]");
    }

    public SelenideElement durchmesserSideFilterButtonFirstValue() {
        return $x("//*[contains(@class,'filter-criteria_200')]//li[1]");
    }

    public SelenideElement durchmesserSideFilterButtonSecondValue() {
        return $x("//*[contains(@class,'filter-criteria_200')]//li[2]");
    }

    public SelenideElement furprnummerSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_1197')]//li");
    }

    public SelenideElement lochanzahlSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_500')]//li[1]");
    }

    public SelenideElement carBrandFilterOem() {
        return $(".model_list_oem > li > label > input");
    }

    SelenideElement langeFilterBlockInSidebar() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']"); }

    SelenideElement bySideFilterBlockInSidebar() { return $(".installation-side--sidebar-fixed"); }

    SelenideElement brandFilterBlockInSidebar() { return $(".branded-filter-sidebar"); }

    SelenideElement furPrnummerBlockInSidebar() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_1197']"); }

    SelenideElement verschleisswarnkontaktBlockInSidebar() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_593']"); }

    SelenideElement thirdProductOnListing() { return $(".list_products > li:nth-child(3)"); }

    SelenideElement durchmesserBlockInSidebar() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_200']"); }

    SelenideElement bremsscheibenartBlockInSidebar() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_232']"); }

    SelenideElement blackOverlay() { return $(".overlay.black"); }

    //Rating filters locators
    public ElementsCollection ratingInProductBlock() {
        return $$(".review-vote");
    }

    ElementsCollection activeRatingStarsInEveryProduct(int productPosition) {
        return $$x("(//div[@class='description'])[ " + productPosition + "]//div[@class='review-vote']//li[not(contains(@class,'empty'))]");
    }

    SelenideElement activeRatingStarsInEveryProductPercent(int productPosition) {
        return $x("(//div[@class='description'])[ " + productPosition + "]//div[@class='review-vote']/span/span");
    }

    public SelenideElement ratingFiveStarsFilterCheckbox() {
        return $x("//*[@class='sort-rating__form-list']/li[1]/label");
    }

    public SelenideElement ratingThreeStarsFilterCheckbox() {
        return $x("//*[@class='sort-rating__form-list']/li[3]/label");
    }

    public SelenideElement ratingFilterBlock() {
        return $(".sort-rating__form");
    }

    //For Oem listing
    public SelenideElement firstBrandButtonOemListing() {
        return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[1]"));
    }

    public SelenideElement secondBrandButtonOemListing() {
        return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[2]"));
    }

    public SelenideElement firstBrandNameOemListing() {
        return $(By.xpath("//*[@id='selected-instalation__slider']/ul/li[1]//img"));
    }

    public SelenideElement sideFilterOenAttribute() {
        return $x("//*[@class='model_list_oem']/li[2]/label/a");
    }

    public SelenideElement sideFilterOenCheckbox() {
        return $x("//*[@class='model_list_oem']/li[2]/label");
    }

    public SelenideElement sideFilterOenAttribute2() {
        return $x("//*[@class='model_list_oem']/li[1]/label/a");
    }

    public SelenideElement oemNumberBlock() {
        return $(".oem-number");
    }

    public SelenideElement oemDescriptionBlock() {
        return $(".oem-number__desc");
    }

    public SelenideElement oemAnalogBlock() {
        return $(".oem-number__analog");
    }

    public ElementsCollection carBrandApplicabilityAttribute() {
        return $$x("//*[@class='list_products ']/li//li[1]/a[@class='pkw-oem__link ga-click']");
    }

    //Brand filters locators

    public SelenideElement firstBrandNameInFiler() {
        return $(By.xpath("//*[@class='slick-track']/li[8]/label/img"));
    }

    public SelenideElement secondBrandNameInFilter() {
        return $(By.xpath("//*[@class='slick-track']/li[9]/label/img"));
    }

    public SelenideElement thirdBrandNameInFilter() {
        return $(By.xpath("//*[@class='slick-track']/li[10]/label/img"));
    }

    public SelenideElement fourthBrandNameInFilter() {
        return $(By.xpath("//*[@class='slick-track']/li[11]/label/img"));
    }

    public SelenideElement fifthBrandNameInFilter() {
        return $(By.xpath("//*[@class='slick-track']/li[12]/label/img"));
    }

    public SelenideElement sixthBrandNameInFilter() {
        return $(By.xpath("//*[@class='slick-track']/li[13]/label/img"));
    }

    public SelenideElement firstBrandInFilterButton() {
        return $(By.xpath("//*[@class='slick-track']/li[8]/label"));
    }

    public SelenideElement secondBrandInFilterButton() {
        return $(By.xpath("//*[@class='slick-track']/li[9]/label"));
    }

    SelenideElement brandFilterBlock() {
        return $("#selected-instalation__slider");
    }

    //By side filters locators

    SelenideElement blockOfBySideFilters() { // By side
        return $(".installation-side__content");
    }

    public SelenideElement filterBySideBack() {
        return $(By.xpath("//*[@class='installation-side__filter back-side']"));
    }

    public SelenideElement filterBySideLKW() {
        return $(By.xpath("//*[@class='installation-side__filter front-side']"));
    }

    //By generic filters locators

    public SelenideElement firstGeneric() {
        return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[1]/div[2]"));
    }

    public SelenideElement secondGeneric() {
        return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[2]/div[2]"));
    }

    public SelenideElement fourthGeneric() {
        return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[4]/div[2]"));
    }

    // locators for tecdoc listing
    public SelenideElement tecDocBlockOfLinkingCategories() {
        return $(".teile_catalog");
    }

    SelenideElement titleOfAdditionalListingForTecDoc() {
        return $(".title_list");
    }

    // this collection finds only products that are in additional listing, suit only for tecdoc listing
    ElementsCollection imagesProductsInAdditionalListingForTecDoc() {
        return $$x("//*[@class='title_list']/../following-sibling::li//*[@class='image']/span[1]");
    }

    //Locators for tile mode listings

    public ElementsCollection productTitleInTileMode() {
        return $$(By.cssSelector(".rec_prod_title"));
    }

    public ElementsCollection priceOfAllProductsOnPageInTile() {
        return $$(By.xpath("//*[@class='rpp_price']"));
    }

    public SelenideElement showListingInTileModeButton() {
        return $(By.xpath("//*[@class='sortby js-change-view-block']/span[3]"));
    }

    public SelenideElement showListingInListModeButton() {
        return $(By.xpath("//*[@class='sortby js-change-view-block']/span[2]"));
    }

    ElementsCollection articleProductsInTileMode() {
        return $$(".rec_prod_article");
    }

    //Locators for all list mode listings

    public ElementsCollection recoveryCharacteristics() {
        return $$x("//span[@class='rc' and contains(text(),'Wiederaufbereitet')]");
    }

    public SelenideElement titleOfListing() {
        return $(".title_count_search");
    }

    public SelenideElement pfandBlock() {
        return $(".product-eco-block");
    }

    SelenideElement pfandPagelink() {
        return $x("//p[@class='top']//a[@target='_blank']");
    }

    SelenideElement productsWithPfandBlock() {
        return $x("//div[@class='product-eco-block']/../..//div[@class='name']");
    }

    public SelenideElement listProducts() {
        return $(".list_products ");
    }

    SelenideElement paginationFirstBlock() {
        return $x("(//*[@class='pagination'])[1]");
    }

    SelenideElement paginationSecondBlock() {
        return $x("(//*[@class='pagination'])[2]");
    }

    public SelenideElement titleOnListing() {
        return $(".title_count_search");
    }

    public ElementsCollection priceOfAllProductsOnPageInList() {
        return $$(By.xpath("//p[@class='actual_price']"));
    }

    public SelenideElement secondListingPage() {
        return $(By.xpath("//*[@class='pagination']/span[3]/a"));
    }

    public SelenideElement thirdListingPage() { return $x("//*[@class='pagination']/span[4]/a"); }

    ElementsCollection sideFilterAttribute() {
        return $$(By.xpath("//*[@class='subname']"));
    }

    public SelenideElement preloader() {
        return $(By.cssSelector(".preloader_wrapper"));
    }

    public ElementsCollection productTitleInListMode() {
        return $$(By.cssSelector(".name"));
    }

    ElementsCollection productArticlesInListing() {
        return $$x("//div[@class='description']//span[@class='article_number' and contains(text(),'Artikelnummer')]");
    }

    public ElementsCollection produktreiheProductAttributeTecdocRoute() {
        return $$x("//*[@class='important' and contains(span, 'Produktreihe')]/span[2]");
    }

    public ElementsCollection produktreiheProductAttributeTecdocRouteLKW() {
        return $$x("//span[contains(text(),'Produktreihe:')]/../span[2]");
    }

    public ElementsCollection produktreiheProductAttributeGenericRoute() {
        return $$(By.xpath("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span, 'Produktreihe')]/span[2]"));
    }

    public ElementsCollection produktreiheProductAttributeGenericRouteLKW() {
        return $$(By.xpath("//*[@class='w_search no_margin']/preceding-sibling::li//span[contains(text(),'Produktreihe:')]/../span[2]"));
    }

    SelenideElement productsForOtherCars() {
        return $x("//*[@class='w_search no_margin']");
    }

    public ElementsCollection langeProductAttributeTecdocRoute() {
        return $$x("//*[@class='important' and contains(span,'Länge [mm]')]/span[2]");
    }

    public ElementsCollection langeProductAttributeTecdocRouteLKW() {
        return $$x("//span[contains(text(),'Länge [mm]:')]/../span[2]");
    }

    public ElementsCollection langeProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span,'Länge [mm]')]/span[2]");
    }

    public ElementsCollection langeProductAttributeGenericRouteLKW() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//span[contains(text(),'Länge [mm]:')]/../span[2]");
    }

    public ElementsCollection verschleiswarnkontaktProductAttributeTecdocRouteLKW() {
        return $$x("//span[contains(text(),'Verschleißwarnkontakt:')]/../span[2]");
    }

    public ElementsCollection verschleiswarnkontaktProductAttributeGenericRouteLKW() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//span[contains(text(),'Verschleißwarnkontakt:')]/../span[2]");
    }

    public ElementsCollection wischblattausfuhrungProductAttributeTecdocRoute() {
        return $$x("//*[@class='important' and contains(span,'Wischblattausführung')]/span[2]");
    }

    public ElementsCollection wischblattausfuhrungProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[@class='important' and contains(span,'Wischblattausführung')]/span[2]");
    }

    public ElementsCollection hoheProductAttributeTecdocRoute() {
        return $$x("//*[contains(text(),'Höhe [mm]:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection hoheProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Höhe [mm]:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection hoheProductAttributeTecdocRouteLKW() {
        return $$x("//*[contains(text(),'Höhe 1 [mm]:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection hoheProductAttributeGenericRouteLKW() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Höhe 1 [mm]:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection einbauseiteProductAttributeTecdocRoute() {
        return $$x("//*[contains(text(),'Einbauseite:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection einbauseiteProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Einbauseite:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection oberflacheProductAttributeTecdocRoute() {
        return $$x("//*[contains(text(),'Oberfläche:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection oberflacheProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Oberfläche:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection bremsscheibenartProductAttributeTecdocRoute() {
        return $$x("//*[contains(text(),'Bremsscheibenart:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection bremsscheibenartProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Bremsscheibenart:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection durchmesserProductAttributeTecdocRoute() {
        return $$x("//*[contains(text(),'Durchmesser [mm]:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection durchmesserProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Durchmesser [mm]:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection furprnummerProductAttributeTecdocRoute() {
        return $$x("//*[contains(text(),'für PR-Nummer:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection furprnummerProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'für PR-Nummer:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection lochanzahlProductAttributeTecdocRoute() {
        return $$x("//*[contains(text(),'Lochanzahl:')]/ancestor :: li[1]/span[2]");
    }

    public ElementsCollection lochanzahlProductAttributeGenericRoute() {
        return $$x("//*[@class='w_search no_margin']/preceding-sibling::li//*[contains(text(),'Lochanzahl:')]/ancestor :: li[1]/span[2]");
    }

    public SelenideElement nextPageButton() {
        return $(".pagination > .next");
    }

    public SelenideElement grayButton() {
        return $x("//*[contains(@class,'not_active')]/a");
    }

    ElementsCollection addToBasketButtons() {
        return $$x("//*[@class='add_info']/div[2]");
    }

    ElementsCollection redButtons() {
        return $$x("//*[@class='button ']");
    }

    public ElementsCollection vorderachseAttributeInTileMode() {
        return $$x("//*[@class='product_desc_table_container']//*[contains(text(),'Vorderachse')]");
    }

    public ElementsCollection vorderachseAttributeInListMode() {
        return $$x("//div[@class='about']//*[contains(text(),'Vorderachse')]");
    }

    public ElementsCollection productsOnListingInTileMode() {
        return $$(".rec_products_block");
    }

    public ElementsCollection productsOnListingInListMode() {
        return $$(".description");
    }

    public SelenideElement resetAllFiltersButton() {
        return $(".reset-buttons__all");
    }

    public SelenideElement labelOfActiveFilter() {
        return $x("//*[@class='reset-buttons__list']/li[1]");
    }

    public SelenideElement closeLabelOfActiveFilter() {
        return $x("//*[@class='reset-buttons__list']/li[1]/div[2]");
    }

    SelenideElement buyButton() { return $x("//div[@class='button ']/a"); }

    SelenideElement nextButtonInBySideSlider() { return $x("//*[@class='js-filter-criteria-top']//span[@class='next slick-arrow']"); }

    SelenideElement nextButtonInBySideSliderDisabled() { return $x("//*[@class='js-filter-criteria-top']//span[@class='next slick-arrow slick-disabled']"); }

    SelenideElement previousButtonInBySideSlider() { return $x("//*[@class='installation-side__filter__nav']/span[@class='prev slick-arrow']"); }

    SelenideElement previousButtonInBySideSliderDisabled() { return $x("//*[@class='js-filter-criteria-top']//span[@class='prev slick-arrow slick-disabled']"); }

    SelenideElement firstPageInBySideSlider() { return $x("//*[@class='slick-track']//div[@data-slick-index='0']"); }

    SelenideElement secondPageInBySideSlider() { return $x("//*[@class='slick-track']//div[@data-slick-index='1']"); }

    SelenideElement sideJSfilterForm() { return $(".js-filter-form-sidebar"); }

    SelenideElement hoheBlock() { return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_209']"); }

    SelenideElement moreCharacteristicButtonInFilter() { return $(".filter-disk__more"); }

    SelenideElement bySideFilterInSidebarFront() { return $(".installation-side--sidebar-fixed > div > .front-side"); }

    SelenideElement brandFilterButtonInSidebarName() { return $(".branded-filter-sidebar__list img"); }

    SelenideElement brandFilterButtonInSidebarButton() { return $(".branded-filter-sidebar__list label"); }
}

