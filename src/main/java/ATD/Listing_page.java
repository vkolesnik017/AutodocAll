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
        return $x("//*[@id='mCSB_1_container']/li[4]");
    }

    public SelenideElement langeFilterCheckboxLKW() {
        return $x("//div[contains(@class,'js-filter-criteria_203')]//label");
    }

    public SelenideElement langeFilterCheckboxLKW700() {
        return $x("//div[contains(@class,'js-filter-criteria_203')]//li[3]/label");
    }

    public SelenideElement langeFilterCheckbox450() {
        return $x("//*[@id='mCSB_1_container']/li[9]/label");
    }

    public SelenideElement langeFilterAttribute450() {
        return $x("//*[@id='mCSB_1_container']/li[9]");
    }

    public SelenideElement langeFilterCheckbox280() {
        return $x("//*[@id='mCSB_2_container']/li[19]/label");
    }

    public SelenideElement langeFilterAttribute280() {
        return $x("//*[@id='mCSB_2_container']/li[19]");
    }


    public SelenideElement langeFilterAttribute() {
        return $x("//*[@id='mCSB_1_container']/li[4]/label");
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
        return $x("//*[@id='mCSB_1_container']/li[2]");
    }

    public SelenideElement langeFilterAttribute3() {
        return $x("//*[@id='mCSB_1_container']/li[2]/label");
    }

    public SelenideElement activeGenericsFilter() {
        return $x("//label[@class='filter-generics-tecdoc__item active']");
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

    public SelenideElement activeSideFilterLkw() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_339']//ul//li[1]/label");
    }


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

    public SelenideElement secondBremsscheibenartSideFilterButton() {
        return $x("//*[contains(@class,'filter-criteria_232')]//li[2]");
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

    public SelenideElement durchmesserSideFilterButtonThirdValue() {
        return $x("//*[contains(@class,'filter-criteria_200')]//li[3]");
    }

    public SelenideElement durchmesserSideFilterButtonFourthValue() {
        return $x("//*[contains(@class,'filter-criteria_200')]//li[4]");
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

    SelenideElement langeFilterBlockInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']");
    }

    SelenideElement langeSecondButtonInFixedSidebarFilter() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']//li[2]/label");
    }

    SelenideElement langeFirstButtonInFixedSidebarFilter() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_203']//li[1]/label");
    }

    SelenideElement bySideFilterBlockInSidebar() {
        return $(".installation-side--sidebar-fixed");
    }

    SelenideElement brandFilterBlockInSidebar() {
        return $(".branded-filter-sidebar");
    }

    SelenideElement furPrnummerBlockInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_1197']");
    }

    SelenideElement furPrnummerFirstButtonInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_1197']//label");
    }

    SelenideElement verschleisswarnkontaktBlockInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_593']");
    }

    public SelenideElement verschleisswarnkontaktFirstButtonInSidebar() {
        return $x("//div[contains(@class,'criteria_593')]//li[1]//label");
    }

    public SelenideElement verschleisswarnkontaktSecondButtonInSidebar() {
        return $x("//div[contains(@class,'criteria_593')]//li[2]//label");
    }

    SelenideElement ninthProductOnListing() {
        return $(".list_products > li:nth-child(15)");
    }

    SelenideElement durchmesserBlockInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_200']");
    }

    SelenideElement durchmesserFirstButtonInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_200']//li[1]/label");
    }

    SelenideElement durchmesserSecondButtonInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_200']//li[2]/label");
    }

    SelenideElement bremsscheibenartBlockInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_232']");
    }

    SelenideElement bremsscheibenartFirstButtonInSidebar() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_232']//label");
    }

    SelenideElement blackOverlay() {
        return $(".overlay.black");
    }

    //Rating filters locators
    public ElementsCollection ratingInProductBlock() {
        return $$(".review-vote__price-block__rating__bg");
    }

    ElementsCollection activeRatingStarsInEveryProduct(int productPosition) {
        return $$x("(//div[@class='description'])[ " + productPosition + "]//span[@class='review-vote__price-block__rating__bg']//li[not(contains(@class,'empty'))]");
    }

    ElementsCollection threeRatingStarsInProduct() {
        return $$x("//div[@class='description']//span[@class='review-vote__price-block__rating__bg']//span[@style='width:60%']");
    }

    SelenideElement activeRatingStarsInEveryProductPercent(int productPosition) {
        return $x("(//div[@class='description'])[" + productPosition + "]//span[@class='review-vote__price-block__rating__bg']/span");
    }

    public SelenideElement ratingFiveStarsFilterCheckbox() {
        return $x("//*[@class='sort-rating__form-list']/li[1]/label");
    }

    public SelenideElement ratingFourStarsFilterCheckbox() {
        return $x("//*[@class='sort-rating__form-list']/li/label[@for='rating_4']");
    }

    public SelenideElement ratingThreeStarsFilterCheckbox() {
        return $x("//*[@class='sort-rating__form-list']/li/label[@for='rating_3']");
    }

    public SelenideElement ratingTwoStarsFilterCheckbox() {
        return $x("//*[@class='sort-rating__form-list']/li/label[@for='rating_2']");
    }

    public SelenideElement ratingFilterBlock() {
        return $(".sort-rating__form");
    }

    ElementsCollection fiveRatingStarsInProduct() {
        return $$x("//div[@class='description']//span[@class='review-vote__price-block__rating__bg']//span[@style='width:100%']");
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

    ElementsCollection idOfBrandFromHeader() {
        return $$x("//div[@data-filter-id='brand']");
    }

    ElementsCollection imageBrandOfProduct() {
        return $$x("//div[@class='image']/span[1]/img");
    }

    public SelenideElement resetBlock() {
        return $x("//div[@class='reset-buttons']/ul");
    }

    public SelenideElement firstResetBrandBtn() {
        return $x("//div[@class='reset-buttons']//li[1]");
    }

    public SelenideElement secondResetBtn() {
        return $x("//ul[@class='reset-buttons__list']/li[2]");
    }

    //Brand filters locators

    public SelenideElement firstBrandNameInFiler() {
        return $(By.xpath("//*[@class='slick-track']/li[8]/label/img"));
    }

    public SelenideElement firstBrandNameGetData() {
        return $x("//*[@class='slick-track']/li[8]/input");
    }

    public SelenideElement firstBrandNameInFilterLKWmodelRoute() {
        return $x("//*[@class='slick-track']/li[@class='slick-slide slick-current slick-active']//img");
    }

    public SelenideElement secondBrandNameGetData() {
        return $x("//*[@class='slick-track']/li[9]/input");
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

    public SelenideElement firstBrandInFilterButtonLKWmodelRoute() {
        return $x("//*[@class='slick-track']/li[@class='slick-slide slick-current slick-active']/label");
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

    SelenideElement blockOfBySideFiltersInSidebar() {
        return $(".js-filter-criteria_100");
    }

    public SelenideElement filterBySideBack() {
        return $(By.xpath("//*[@class='installation-side__filter js-filter-wrapper-item back-side installation-side-lights-back']"));
    }

    public SelenideElement filterBySideLKW() {
        return $(By.xpath("//div[contains(@class,'installation-side-lights-front')]"));
    }

    //By generic filters locators

    public SelenideElement allCategoryGeneric() {
        return $x("//label[@for='category-all']");
    }

    public SelenideElement firstGenericAboveListing() {
        return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[1]/div[2]"));
    }

    public SelenideElement firstGenericInSidebar() {
        return $x("//div[contains(@class,'js-filter-50001')]//li[2]");
    }

    public SelenideElement secondGenericAboveListing() {
        return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[2]/div[2]"));
    }

    public SelenideElement secondGenericInSidebar() {
        return $x("//div[contains(@class,'js-filter-50001')]//li[3]");
    }

    SelenideElement thirdGenericAboveListing() {
        return $(By.xpath("//div[contains(@class,'filter-generics-tecdoc__list js-filter-generics-tecdoc')]//label[3]/div[2]"));
    }

    SelenideElement thirdGenericInSidebar() {
        return $x("//div[contains(@class,'js-filter-50001')]//li[4]");
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

    public SelenideElement pfandBlockByIndex(int numIndex) {
        return $x("//ul[contains(@class,'list_products')]/li[" + numIndex + "]//div[@class='product-eco-block']");
    }

    SelenideElement pfandPagelink() {
        return $x("//p[@class='top']//a[@target='_blank']");
    }

    SelenideElement productsWithPfandBlock() {
        return $x("//div[@class='product-eco-block']/../..//div[@class='name']");
    }

    ElementsCollection vatPostscript() {
        return $$x("//div[@class='price-block__inkl']/span");
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

    public SelenideElement thirdListingPage() {
        return $x("//*[@class='pagination']/span[4]/a");
    }

    ElementsCollection sideFilterAttribute() {
        return $$(By.xpath("//*[@class='subname']"));
    }

    public SelenideElement preloader() {
        return $(By.cssSelector(".preloader_wrapper"));
    }

    public ElementsCollection productTitleInListMode() {
        return $$(By.cssSelector(".name"));
    }

    public ElementsCollection productArticlesInListing() {
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

    SelenideElement buyButton() {
        return $x("//div[@class='button ']/a");
    }

    SelenideElement nextButtonInBySideSlider() {
        return $x("//*[@class='js-filter-criteria-top']//span[@class='next slick-arrow']");
    }

    SelenideElement nextButtonInBySideSliderDisabled() {
        return $x("//*[@class='js-filter-criteria-top']//span[@class='next slick-arrow slick-disabled']");
    }

    SelenideElement previousButtonInBySideSlider() {
        return $x("//*[@class='installation-side__filter__nav']/span[@class='prev slick-arrow']");
    }

    SelenideElement previousButtonInBySideSliderDisabled() {
        return $x("//*[@class='js-filter-criteria-top']//span[@class='prev slick-arrow slick-disabled']");
    }

    SelenideElement firstPageInBySideSlider() {
        return $x("//*[@class='slick-track']//div[@data-slick-index='0']");
    }

    SelenideElement secondPageInBySideSlider() {
        return $x("//*[@class='slick-track']//div[@data-slick-index='1']");
    }

    SelenideElement sideJSfilterForm() {
        return $(".js-filter-form-sidebar");
    }

    SelenideElement hoheBlock() {
        return $x("//*[@class='filter-disk sidebar_block_radio js-criteria-filter  js-filter-wrapper js-filter-criteria_209']");
    }

    SelenideElement moreCharacteristicButtonInFilter() {
        return $(".filter-disk__more");
    }

    SelenideElement bySideFilterInSidebarFront() {
        return $(".installation-side--sidebar-fixed > div > .front-side");
    }

    SelenideElement bySideFilterInSidebarFrontSearchRoute() {
        return $(".installation-side--sidebar-fixed .front-side");
    }

    SelenideElement bySideFilterInSidebarBack() {
        return $(".installation-side--sidebar-fixed > div > .back-side");
    }

    SelenideElement brandFilterButtonInSidebarName() {
        return $(".branded-filter-sidebar__list img");
    }

    SelenideElement secondBrandFilterButtonInSidebarName() {
        return $(".branded-filter-sidebar__list li:nth-child(2) img");
    }

    SelenideElement thirdBrandFilterButtonInSidebarName() {
        return $(".branded-filter-sidebar__list li:nth-child(3) img");
    }

    SelenideElement brandFilterButtonInSidebarButton() {
        return $(".branded-filter-sidebar__list label");
    }

    SelenideElement secondBrandFilterButtonInSidebarButton() {
        return $(".branded-filter-sidebar__list li:nth-child(2) label");
    }

    SelenideElement thirdBrandFilterButtonInSidebarButton() {
        return $(".branded-filter-sidebar__list li:nth-child(3) label");
    }

    SelenideElement firstBrandFilterButtonInSidebarButton() {
        return $(".branded-filter-sidebar__list li:nth-child(1) label");
    }

    SelenideElement brandFilterSecondButtonInSidebarName() {
        return $x("//*[@class='branded-filter-sidebar']//li[2]//img");
    }

    SelenideElement brandFilterSecondButtonInSidebarButton() {
        return $x("//*[@class='branded-filter-sidebar']//li[2]//label");
    }

    ElementsCollection characteristicsOfProduct(String productArticle) {
        return $$x("//*[text()='" + productArticle + "']/../..//*[@class='about']//li");
    }

    SelenideElement characteristicOfProduct(String productArticle, String nameCharacteristic) {
        return $x("//*[text()='" + productArticle + "']/../..//*[@class='about']//span[contains(text(),'" + nameCharacteristic + "')]/..//span[2]");
    }

    SelenideElement titleDesiredProduct(String productArticle) {return $x("//*[text()='" + productArticle + "']/../..//a[@class='ga-click']"); }


    SelenideElement infoButton() {
        return $("#selected_car_info");
    }

    SelenideElement carInfoPopup() {
        return $(".selected_car_info_popap");
    }

    SelenideElement closeCarInfoPopupButton() {
        return $(".selected_car_info_popap .close");
    }

    SelenideElement popupSelector() {
        return $x("//div[@class='new_popup popup_content']");
    }

    SelenideElement closePopupSelectorBtn() {
        return $x("//div[@class='new_popup popup_content']/a");
    }

    SelenideElement titleInfoPopup() {
        return $x("//*[@class='selected_car_info_popap']/strong");
    }

    ElementsCollection characteristicsFromPopup() {
        return $$x("//*[@class='selected_car_info_popap']//li");
    }

    SelenideElement firstProductTitleOnListing() {
        return $x("(//div[@class='name']/a)[1]");
    }

    SelenideElement firstCharacteristicInFirstProduct() {
        return $x("//li[1][@class='important']");
    }

    SelenideElement secondCharacteristicInFirstProduct() {
        return $x("//li[2][@class='important']");
    }

    SelenideElement thirdCharacteristicInFirstProduct() {
        return $x("//li[3][@class='important']");
    }

    SelenideElement fourthCharacteristicInFirstProduct() {
        return $x("//li[4][@class='important']");
    }

    SelenideElement fifthCharacteristicInFirstProduct() {
        return $x("//li[5][@class='important']");
    }

    ElementsCollection mainCharacteristicsOnListing() {
        return $$x("(//div[@class='autopart_kit_table'])[1]/ancestor::div[@class='about']//li");
    }

    ElementsCollection additionalCharacteristicsOnListing() {
        return $$x("(//div[@class='autopart_kit_table'])[1]/div");
    }

    SelenideElement firstProductAdditionalCharacteristicsOnListing() {
        return $x("(//div[@class='autopart_kit_table'])[1]/ancestor::div[@class='description']/div/a");
    }

    SelenideElement brandButtonInFilterWithGivenName(String brandName) {
        return $x("//*[@id='selected-instalation__slider']/ul//img[contains (@alt,'" + brandName + "')]/..");
    }

    SelenideElement brandButtonInFilterWithGivenNameLKWRoute(String brandName) {
        return $x("//*[@id='selected-instalation__slider']/ul//li[@class='slick-slide']//img[contains (@alt,'" + brandName + "')]/..");
    }

    SelenideElement nextButtonInBrandFilter() {
        return $x("//a[@class='next slick-arrow']");
    }

    SelenideElement productArticleOnListing() {
        return $x("//div[@class='description']//span[@class='article_number' and contains(text(),'Artikelnummer')]");
    }

    SelenideElement productName(String brandName) {
        return $x("//*[@class='name']/a[contains (text(),'" + brandName + "')]");
    }

    SelenideElement productArticle(String brandName) {
        return $x("//*[@class='name']/a[contains (text(),'" + brandName + "')]/../span[@class='article_number']");
    }

    SelenideElement productOEMnumber(String brandName) {
        return $x("//*[@class='name']/a[contains (text(),'" + brandName + "')]/span[@class='highlight']");
    }

    SelenideElement productAddToBasketButton(String brandName) {
        return $x("//*[@data-brand-name='" + brandName + "']//a[contains(@class,'add_')]");
    }

    SelenideElement brandsOfBrandBlock(String idOfBrand) {
        return $x("//input[@id='cb-brand-" + idOfBrand + "']/..");
    }

    ElementsCollection titleOfProductInListing() {
        return $$x("//div[@class='name']/*[self::a or self::span][1]");
    }

    SelenideElement characteristicZustand() {
        return $x("//li[@class='default_ul_li_class']/span[2]");
    }

    SelenideElement characteristicZustandByIndex(int numIndex) {
        return $x("//ul[contains(@class,'list_products')]/li[" + numIndex + "]//li[@class='default_ul_li_class']/span[2]");
    }

    ElementsCollection artNumOfProductGridType() {
        return $$x("//div[@class='rec_prod_article']");
    }

    SelenideElement allCharacteristicsOfProductInPopUp(int positionOfProduct) {
        return $x("(//div[@class='product_desc_table_container'])[" + positionOfProduct + "]");
    }

    ElementsCollection cardProducts() {
        return $$x("//ul[contains(@class,'list_products')]/li");
    }

    SelenideElement ersatzAnzeigenButton() {
        return $x("//*[@class='show_alternative__btn ga-click button additional_btn'][@data-article-id='7799855']");
    }

    SelenideElement alternativeBlock() {
        return $x("//*[@class='top-small-products-items__item']");
    }

    ElementsCollection pictogramsAlternativeBlock() {
        return $$x("//*[@class='dangerous-listing__icons']//*[@class='dangerous-listing__icon dangerous-listing__icon-attention']");
    }

    SelenideElement popUpDangerousListing() {
        return $x("//*[@class='popup-dangerous']");
    }

    SelenideElement iconInPopUpDangerousListing() {
        return $x("//*[@class='popup-dangerous__icon']");
    }

    SelenideElement popUpDangerousTitle() {
        return $x("//*[@class='popup-dangerous__title']");
    }

    SelenideElement popUpDangerousText() {
        return $x("//*[@class='popup-dangerous']//p");
    }

    SelenideElement closePopUpButton() {
        return $x("//*[@class='popup-dangerous__close js-popup-dangerous__close']");
    }

    ElementsCollection mehrButtonListing() {
        return $$x("//*[@class='top-small-products-items__item']//*[@class='dangerous-listing js-dangerous-listing__show-more']");
    }

    SelenideElement detailsPopUp() {
        return $x("//*[@class='rec_prod_info_popup']");
    }

    SelenideElement mehrButtonListingOne() {
        return $x("//*[@class='top-small-products-items__item']//*[@class='dangerous-listing js-dangerous-listing__show-more']");
    }

    SelenideElement scrollProgressBar() {
        return $x("//div[@class='scroll-progress-bar']");
    }

    SelenideElement timeBlockFromFooter() {
        return $x("//div[@class='work-time']");
    }

    SelenideElement logoAutodoc() {
        return $x("//a[@class='header__logo-main']");
    }

    SelenideElement brandFilterById(String id) {return $x("//*[self::li[@class='slick-slide slick-active'] or self::li[@class='slick-slide slick-current slick-active']]//label[@for='cb-brand-"+id+"']");}

    SelenideElement forwardOfBrandBlock() {return $x("//a[@class='next slick-arrow']");}

    ElementsCollection countFilter() {return $$x("//div[contains(text(),'Menge')]/following-sibling::div//label");}

    ElementsCollection valueOfInstallationSideInDescription() {return $$x("//span[text()='Einbauseite:  ']/following-sibling::span");}

    ElementsCollection valueOfCountInDescription() {return $$x("//span[text()='Menge:  ']/following-sibling::span");}

    SelenideElement productInfoPriceForSet() { return $x("//p[@class='product-info-price' and text()='(Preis pro Satz)']");}

    SelenideElement productInfoPriceForPiece() { return $x("//p[@class='product-info-price' and text()='(Artikelpreis)']");}

}