package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Search_page {

    SelenideElement titleOnSearchPage() {
        return $(".title_count_search");
    }

    SelenideElement blockOfHelpSearchProducts() {
        return $(".filter-not-found__title strong");
    }

    SelenideElement blockOfLinkingCategory() {
        return $(".sidebar-category");
    }

    SelenideElement buyButton() {
        return $(By.xpath("//div[@class='button ']/a[@id='search_page_product']"));
    }

    SelenideElement detalisBtn() {
        return $x("//div[@class='about']/button");
    }

    SelenideElement dividingLineForProductsOtherCategories() {
        return $x("//*[@class='w_search no_margin']");
    }

    SelenideElement closeBtnPopupOfChooseCar() {
        return $(".back");
    }

    // locator for counter
    SelenideElement counterValue() {
        return $(By.xpath("//input[@id='form_']"));
    }

    SelenideElement counterPlus() {
        return $(By.xpath("//a[@class='ga-click plus add']"));
    }

    SelenideElement counterMinus() {
        return $(By.xpath("//a[@class='ga-click minus remove']"));
    }


    // locators in popup of gray button for subscription for product which is not stock
    SelenideElement emailFieldInPopUpOfGrayBtn() {
        return $(byId("form_AvailabilityReminder[email]"));
    }

    SelenideElement sendButtonInPopUpOfGrayBtn() {
        return $(byCssSelector(".popup-available__button"));
    }

    SelenideElement checkboxInPopUpOfGrayBtn() {
        return $(byCssSelector(".popup-available__label"));
    }

    SelenideElement closeSuccessPopUpOfGrayBtn() {
        return $(byXpath("//div[@class='popup_top']//a[@class='close']"));
    }

    // locator for search button by ID product in listing
    public SelenideElement buttonProductById(String id) {
        return $(byId(id));
    }

    // locator for search image by ID product in listing
    public SelenideElement imageProductById(String id) {
        return $(byXpath("//*[@id='" + id + "']/../../..//img[contains(@class,'image')]"));
    }

    SelenideElement cartPopupWithProduct() {
        return $(byCssSelector(".cart-items-block__title"));
    }

    SelenideElement secondListingPage() {
        return $(By.xpath("//*[@class='pagination']/span[3]/a"));
    }

    // Form soft 404
    SelenideElement mainFormOfSoft404() {return $(".search_box_result");}

    SelenideElement mailFieldSoftForm() {
        return $(By.id("form_email"));
    }

    SelenideElement submitBtnSoftForm() {
        return $(By.cssSelector(".notification-form__row > button"));
    }

    SelenideElement subscribeCheckboxSoftForm() {
        return $(By.id("subscribe_on"));
    }

    SelenideElement errPopupSoftForm() {
        return $(By.id("popup_update"));
    }

    SelenideElement successPopupSoftForm() {
        return $(By.xpath("//div[@class='new_popup popup_email_not']"));
    }

    SelenideElement closeErrPopupSoftForm() {
        return $(By.xpath("//div[@class='popup_content']//a[@class='close']"));
    }

    SelenideElement closeSuccessPopupSoftForm() {
        return $(By.xpath("//div[@class='button loc']"));
    }

    SelenideElement datenschutzerklarungLinkSoftForm() {
        return $(By.cssSelector("#privacy_policy1>a"));
    }

    SelenideElement brandsFilterBlock() { return $x("//div[@data-name='brand']"); }

    SelenideElement brandsFilterBlockInSideBar() { return $x("//div[@class='sidebar']//div[@data-name='brand']");  }

    SelenideElement forwardLinkAtBrandsFilter() {
        return $x("//a[contains(@class,'next')]");
    }

  SelenideElement brandsLinkInSideBar(String idOfBrand) {
      return $x("//*[self::li[@class='slick-slide slick-current slick-active'] or self::li[@class='slick-slide slick-active']]//label[@for='cb-brand-" + idOfBrand + "']");
  }
    SelenideElement brandFromSideBar(String idOfBrand) { return $x("//label[@for='cb-brand-" + idOfBrand + "']");  }

    SelenideElement loaderInTecDocListing() {
        return $x("//div[@class='preloader_wrapper']");
    }

    ElementsCollection imageOfBrandInProductBlock() {
        return $$x("//div[@class='image']/span[1]/img");
    }

    ElementsCollection descriptionBlockOfProduct() {
        return $$x("//div[@class='description']");
    }

    ElementsCollection characteristicListOfProduct(int positionOfProduct) {
        return $$x("(//div[@class='description'])[" + positionOfProduct + "]//div[@class='about']//ul/li/span[1]");
    }

    SelenideElement mainListingBlock() {
        return $x("//ul[@class='list_products']");
    }

    ElementsCollection titleOfProductsInListing() {
        return $$x("//div[@class='name']/a");
    }

    SelenideElement forwardLinkOfPaginator() {
        return $x("//span[@class='next'][1]/a");
    }

    ElementsCollection activeBtnAddProductToBasket() {
        return $$x("//div[@class='button ']");
    }

    SelenideElement closeAnotherPartsOfCarPopUp() {
        return $x("//div[@class='popup-other-cat__close']");
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement countOfVehicleInIconOfGarageInHeader() {
        return $x("//span[@class='header-garage__count header-garage__count--added']");
    }

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {
        return $x("//div[@class='wrapper-radio']/label[@for='" + idOfVehicle + "']");
    }

    SelenideElement headerGarageIcon() {
        return $x("//div[@class='header-garage js-header-garage']");
    }

    SelenideElement popUpOfGarageInHeader() {
        return $x("//div[@class='header-garage__logged-header']");
    }

    ElementsCollection btnAddedProductToWishList() {
        return $$x("//div[@class='price_box']/span");
    }

    ElementsCollection btnAddedActiveProductToWishList() {return $$x("//div[@class='button ']/ancestor::div[@class='price_box']/span");}

    ElementsCollection addedProductToWishList() {
        return $$x("//span[@class='add-to-wishlist title_btn add-to-wishlist--added remove-article']");
    }

    ElementsCollection addedActiveProductToWishList() {
        return $$x("//div[@class='button ']/ancestor::div[@class='price_box']/span[@class='add-to-wishlist title_btn add-to-wishlist--added remove-article']");
    }

    SelenideElement iconOfWishList() {
        return $x("//span[@class='header__wishes link']");
    }

    SelenideElement productListBlock() {
        return $x("//div[@class='listing_items']");
    }

    ElementsCollection allCharacteristicsOfProducts() {
        return $$x("//div[@class='box criteria_toogle_active']//ul/li");
    }

    ElementsCollection visibleTitleOfProducts() {
        return $$x("//*[self::a[@class='ga-click prod_link'] or self::span[@class='ga-click prod_link link']]");
    }

    ElementsCollection visibleArtNumOfProduct() {
        return $$x("//span[@class='article_number'][1]");
    }

    ElementsCollection visibleCharacteristicsOfProducts(int position) {
        return $$x("(//div[@class='about'])[" + position + "]//ul/li");
    }

    ElementsCollection artNumOfProduct() {
        return $$x("//span[@class='article_number'][1]");
    }

    ElementsCollection artNumOfActiveProduct() {return $$x("//div[@class='button ']/ancestor::div[@class='price_box']/..//span[@class='article_number']");}

    SelenideElement popUpSelector() {
        return $x("//div[@class='new_popup popup_content']");
    }

    SelenideElement closePopUpSelector() {
        return $x("//a[@class='back']");
    }

    SelenideElement tooltipOfMarkeFieldInPopUpSelector() {
        return $x("//div[@class='tooltiptext-close js-tooltiptext-close']");
    }

       ElementsCollection visibleBrands() {
        return $$x("//ul[@class='branded-slider slick-initialized slick-slider']//li//label").filter(visible);
    }

    ElementsCollection visibleBrandsInSideBar() {
        return $$x("//div[@id='mCSB_1_container']//li//label");
    }

    ElementsCollection addedProductToWishListLabel() {return $$x("//span[@class='add-to-wishlist title_btn add-to-wishlist--added remove-article']");}

    SelenideElement currentCountOfProductInWishList() {return $x("//span[@class='header__wishes-count header__wishes-count--added']");}

    ElementsCollection artNumOfAddedProductToWishList() {return $$x("//span[@class='add-to-wishlist title_btn add-to-wishlist--added remove-article']/ancestor::li//span[@class='article_number'][1]");}

    ElementsCollection titleOfMainProducts() {return $$x("//li[contains(@class,'item_in_stock') and not(contains(@class,'recommended'))]//div[@class='name']/*[self::a or self::span][1]");}

    ElementsCollection labelAddToWishListNotActiveProduct() {return $$x("//div[@class='button not_active']/ancestor::div[@class='price_box']//span[@class='add-to-wishlist title_btn add-article']");}

    ElementsCollection artNumOfNotActiveProduct() {return $$x("//div[@class='button not_active']/ancestor::div[@class='all_desc_item']//span[@class='article_number']");}

    SelenideElement forwardOfPaginator() { return $x("//span[@class='next']");}

    ElementsCollection priceBlock() {return $$x("//div[@class='price_box']");}

    ElementsCollection amountQuantityOfProduct() {return $$x("//div[@class='count']/input");}

    ElementsCollection priceOfProduct() {return $$x("//p[contains(@class,'actual_price')]");}

    ElementsCollection attributeOfBtnAddedToBasket() { return $$x("//div[@class='count']/following-sibling::div");}

    ElementsCollection attributeOfActiveBtnAddedToBasket() { return $$x("//div[@class='count']/following-sibling::div[@class='button ']");}

    ElementsCollection attributeOfNotActiveBtnAddedToBasket() { return $$x("//div[@class='count']/following-sibling::div[@class='button not_active']");}

    ElementsCollection productsList() {return $$x("//ul[@class='list_products']/li");}

    ElementsCollection visibleParentCategories() {return $$x("//ul[@class='filetree cat_tree treeview']//li").filter(visible);}

    ElementsCollection visibleParentInSidebar() {return $$x("//ul[@class='filetree cat_tree treeview']//li[contains(@class,'expandable')]//span");}

    public ElementsCollection categoriesFromSideBar() {
        return $$x("//ul[@class='filetree cat_tree treeview']//div[@class='child-category']//li/a");
    }

    SelenideElement visibleCategoryFromSideBar() {
        return $x("//ul[@class='filetree cat_tree treeview']//div[@class='child-category']//ul[@style='display: block;']//li[1]");
    }

    ElementsCollection paginatorLinks() {return $$x("//div[@class='pagination']/span");}

    ElementsCollection visibleGenerics() {return $$x("//div[@class='filter-generics-tecdoc__item-image']").filter(visible);}

    SelenideElement visibleExactBrand(String id) {return $x("//li[@class='slick-slide slick-active']//label[@for='cb-brand-"+id+"']");}

    SelenideElement btnRestartDefaultValues() {return $x("//div[contains(text(),'Zurücksetzen')]");}

    ElementsCollection attributeOfGeneric() {return $$x("//div[@class='filter-generics-tecdoc__item-image']/img");}

    ElementsCollection genericFromBtnAddToBasket() {return $$x("//div[@class='add_info']/div[2]");}

    SelenideElement generic(String number) {return $x("//div[@class='filter-generics-tecdoc__content']//label[@for='category"+number+"']");}

    ElementsCollection visiblePaginatorLinks() {return $$x("//div[@class='pagination']/span").filter(visible);}

    SelenideElement genericBlock() {return $x("//div[@class='filter-generics-tecdoc js-filter-generic js-filter- js-filter-wrapper criteria-filter-block-js']");}

    ElementsCollection visibleTitleOfGenerics() {return $$x("//div[@class='filter-generics-tecdoc__item-title']").filter(visible);}

    SelenideElement rightPaginatorOfGenericBlock() {return $x("//span[@class='next slick-arrow']");}

    SelenideElement leftPaginatorOfGenericBlock() {return $x("//span[@class='prev slick-arrow']");}

    SelenideElement titleOfProductWithArtNum(String artNum) {return $x("//span[contains(text(),'Artikelnummer')]/span/span[contains(text(),'"+artNum+"')]/ancestor::span/../a");}

    ElementsCollection importantOptionsOfProduct(int positionOfProduct) {return $$x("(//ul[@class='criteria'])["+positionOfProduct+"]/li/span[1]");}

    ElementsCollection countInputsOne() {return $$x(" //input[@value='1']/ancestor::div[@class='add_info']/preceding-sibling::div[@class='product-quantity']/span[2]/span");}

    ElementsCollection countInputsTwo() {return $$x(" //input[@value='2']/ancestor::div[@class='add_info']/preceding-sibling::div[@class='product-quantity']/span[2]/span");}

    ElementsCollection titleOfProducts() {return $$x("//div[@class='name']/*[self::a or self::span][1]");}

    ElementsCollection pricePerMeterLabel() {return $$x("//p[text()='Preis pro Meter']");}

    SelenideElement additionalResultLabel() {return $x("//div[@class='w_search no_margin']");}

    ElementsCollection mainProducts() {return $$x("//ul[@class='list_products']/li[not(contains(@class,'recommended'))]");}

    ElementsCollection recommendedProducts()  {return $$x("//ul[@class='list_products']/li[contains(@class,'recommended')]");}
}

