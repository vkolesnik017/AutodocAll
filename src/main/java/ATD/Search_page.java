package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Search_page {

    SelenideElement titleOnSearchPage() {
        return $(".title_count_search");
    }

    SelenideElement blockOfHelpSearchProducts() {
        return $(".filter-not-found__title");
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
        return $(By.xpath("//input[@class='amount qty_2']"));
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
    SelenideElement buttonProductById(String id) {
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

    SelenideElement  brandsFilterBlock() {return $x("//div[@data-name='brand']");}

    SelenideElement forwardLinkAtBrandsFilter() {return $x("//a[contains(@class,'next')]");}

    SelenideElement brandsLinkInSideBar(String idOfBrand) {return $x("//div[@id='selected-instalation__slider']//ul//li//label[@for='cb-brand-"+idOfBrand+"']");}

    SelenideElement loaderInTecDocListing() {
        return $x("//div[@class='preloader_wrapper']");
    }

    ElementsCollection imageOfBrandInProductBlock() {return $$x("//div[@class='image']/span[1]/img");}

    ElementsCollection descriptionBlockOfProduct() {return $$x("//div[@class='description']");}

    ElementsCollection characteristicListOfProduct(int positionOfProduct) {return $$x("(//div[@class='description'])["+positionOfProduct+"]//div[@class='about']//ul/li/span[1]");}

    SelenideElement mainListingBlock() {return $x("//ul[@class='list_products']");}

    ElementsCollection titleOfProductsInListing() {return $$x("//div[@class='name']/a");}

    SelenideElement forwardLinkOfPaginator() { return $x("//span[@class='next'][1]/a");}

    ElementsCollection activeBtnAddProductToBasket() {return $$x("//div[@class='button ']");}

    SelenideElement closeAnotherPartsOfCarPopUp() {return $x("//div[@class='popup-other-cat__close']");}

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement headerGarageIcon(){ return $x("//div[@class='header-garage js-header-garage']"); }

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    ElementsCollection btnAddedProductToWishList() {return $$x("//span[@class='add-to-wishlist title_btn add-article']");}

    ElementsCollection addedProductToWishList() { return $$x("//span[@class='add-to-wishlist title_btn add-to-wishlist--added remove-article']");}

    SelenideElement iconOfWishList() { return $x("//span[@class='header__wishes link']"); }

    SelenideElement productListBlock() {return $x("//div[@class='listing_items']");}

    ElementsCollection allCharacteristicsOfProducts() {return $$x("//div[@class='prod_params_container']//li");}
}

