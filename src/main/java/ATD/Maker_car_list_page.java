package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Maker_car_list_page {

    public SelenideElement headingOfSearchByCatalog() {
        return $(".page_search_title");
    }

    SelenideElement blockWithSelectedVehicle() {
        return $x("//div[@class='catalog-title__block']");
    }

    SelenideElement selectorInCloseCondition() {
        return $(".catalog-title__change-car");
    }

    SelenideElement mainFormOfSelector() {
        return $(byId("selector-wrapper"));
    }

    SelenideElement markeInSelector() {
        return $(byId("form_maker_id"));
    }

    SelenideElement modelInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement btnSearchOfSelector() {
        return $(".search_button");
    }

    SelenideElement garageIconInHeader() {
        return $x("//div[@class='header-garage js-header-garage']");
    }

    SelenideElement popUpOfGarageInHeader() {
        return $x("//div[contains(@class,'header-garage__notlogged')]");
    }

    ElementsCollection urlsOfAddedVehicleInPopUpOfGarageInHeader() {
        return $$x("//div[contains(@class,'header-garage__notlogged')]//a[contains(@class,'history-cars-rows__link')]");
    }

    SelenideElement btnClearVehicleListInPopUpOfGarageIcon() {
        return $x("//div[@class='header-garage__logged-clear js-clear-recent-car']/span");
    }

    ElementsCollection urlsOfAddedVehicleInPopUpOfGarageFromSelector() {
        return $$x("//div[@class='header-garage__logged-check']//a");
    }

    ElementsCollection radioBtnOfAddedVehicleInPopUpOfGarageFromSelector() {
        return $$x("//input[@name='radio-car']");
    }

    ElementsCollection mainCategoriesInHeader() {
        return $$x("//ul[@class='header__nav-list']/li/a");
    }

    ElementsCollection urlsOfAddedVehicleInPopUpOfGarageWithOutAuthorization() {
        return $$x("//div[@class='history-cars-rows']//a[2]");
    }

    SelenideElement garageIconInHeaderActive() {
        return $x("//div[@class='header-garage js-header-garage active']");
    }

    SelenideElement btnAddedVehicleOfMyGaragePopUp() {
        return $x("//div[@class='header-garage__history-button js-selector-add-car']/button");
    }

    SelenideElement selectorFromMyGarageBlock() {
        return $(byId("selector-my-garage"));
    }


    SelenideElement trucksTab() {
        return $x("//li[@class='popup-selector__tabs-item select-lkw ']");
    }

    SelenideElement markeOfTruckInSelector() {
        return $x("//div[@class='block-select-car tooltip-block tooltip-block--left']/div[2]/select");
    }

    SelenideElement modelOfTruckInSelector() {
        return $x("//div[@class='block-select-car tooltip-block tooltip-block--left']/div[3]/select");
    }

    SelenideElement motorOfTruckInSelector() {
        return $x("//div[@class='block-select-car tooltip-block tooltip-block--left']/div[4]/select");
    }

    SelenideElement btnSearchOfSelectorFromMyGarage() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit-garage']");
    }

    SelenideElement tecDocCatalog() {
        return $x("//div[@class='list_ersats_n list-ersatz-n--catalog']");
    }

    ElementsCollection titleOfParentCategories() {
        return $$x("//span[@class='name']");
    }

    SelenideElement iconOfWishList() {
        return $x("//span[@class='header__wishes link']");
    }

    SelenideElement searchBar() {
        return $(byId("search"));
    }

    ElementsCollection productsList() {
        return $$x("//ul[@class='list_products']/li");
    }

    SelenideElement btnAddToBasketFromTopProductsBlock() {
        return $x("//a[@class='ga-click button add_basket']");
    }

    SelenideElement popupBasketAddedProducts() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement topProductsBlock() {
        return $x("//*[self::div[@class='top-small-products top-small-products--shortened'] or self::div[@class='top-small-products']]");
    }

    ElementsCollection allTitlesOfTopProducts() {
        return $$x("//div[@class='top-small-products top-small-products--shortened']//div[@class='small-prod-title']/span");
    }

    ElementsCollection allBtnAddToBasketOfTopProducts() {
        return $$x("//div[@class='top-small-products top-small-products--shortened']//div[@class='small-product-button price_box ']/a");
    }

    ElementsCollection genericsOfTopProducts() {
        return $$x("//div[@class='top-small-products top-small-products--shortened']//div[@class='small-product-button price_box']");
    }

    SelenideElement topCategoriesBlock() {
        return $x("//div[@class='list blocks']");
    }

    SelenideElement btnNextInTopCategoriesBlock() {
        return $x("//div[@class='list blocks']//button[@aria-label='Next']");
    }

    SelenideElement btnPreviousInTopCategoriesBlock() {
        return $x("//div[@class='list blocks']//button[@aria-label='Previous']");
    }

    public ElementsCollection overCategoriesInTopCategoriesBlock() {
        return $$x("//div[@class='list blocks']//div[@class='title']");
    }

    public ElementsCollection categoriesInTopCategoriesBlock() {
        return $$x("//div[@class='slick-list draggable']//span[@class='link ga-click']");
    }

    ElementsCollection imagesOfTopProducts() {
        return $$x("//div[@class='small-prod-image']/a");
    }

    ElementsCollection genericOfTopProducts() {
        return $$x("//div[@class='small-prod-title']/span");
    }

    ElementsCollection allChildCategories() {
        return $$x("//ul[@class='expand list_ersats_row']/li/div[2]//li/*[self::a or self::span]/span");
    }

    ElementsCollection childFromFront(String parent) {
        return $$x("//span[@class='name'][text()='" + parent + "' or text()='" + parent + " ']/ancestor::li/div[2]//li/*[self::a or self::span]/span");
    }

}
