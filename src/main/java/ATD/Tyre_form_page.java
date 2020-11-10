package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Tyre_form_page {

    SelenideElement productsList() {
        return $x("//ul[@class='list_products']");
    }

    ElementsCollection btnOutOfStock() {
        return $$x("//div[@class='button not_active']/a");
    }

    SelenideElement feedBackPopUp() {
        return $x("//div[@class='popup-available']");
    }

    SelenideElement emailFieldOfFeedBackPopUp() {
        return $(byId("form_AvailabilityReminder[email]"));
    }

    SelenideElement checkBoxOfFeedBackPopUp() {
        return $x("//label[@class='popup-available__label']");
    }

    SelenideElement btnSendOfFeedBackPopUp() {
        return $x("//input[@class='popup-available__button']");
    }

    SelenideElement popUPAboutSuccessfulSending() {
        return $(byId("news_subscribe"));
    }

    SelenideElement popUPAboutAnUnsetCheckbox() {
        return $(byId("popup_update"));
    }

    SelenideElement popUPAboutInvalidEmail() {
        return $x("//div[@id='popup_update']//div[@class='txt ']");
    }

    SelenideElement productListBlock() {return $x("//ul[@class='list_products']");}

    ElementsCollection priceOfProduct() {return $$x("//div[@class='actual_price']");}

    ElementsCollection attributeOfBtnAddedToBasket() { return $$x("//div[@class='count']/following-sibling::div");}

    ElementsCollection productsFromListBlock() {return $$x("//ul[@class='list_products']/li");}

    SelenideElement forwardOfListing() {return $x("//span[@class='next'][1]/a");}

    ElementsCollection mpnNumberOfProduct() {return $$x("//div[@class='name']/span[contains(text(),'MPN')]");}

    SelenideElement nameBrandProduct() {
        return $x("//div[@class='description']//div[@class='name']/a");
    }

    ElementsCollection btnAddProductToWishList() { return $$x("//span[@class='add-to-wishlist title_btn add-article']");}

    ElementsCollection addedProductToWishList() { return $$x("//span[@class='add-to-wishlist title_btn add-to-wishlist--added remove-article']");}

    SelenideElement iconOfWishList() { return $x("//span[@class='header__wishes link']"); }

    SelenideElement btnPrevInPagination() {
        return $x("//div[@class='pagination']//span[@class='previous']/a");
    }

    SelenideElement btnLastInPagination() {
       return  $x("//div[@class='pagination']//span[@class='last']/a");
    }

    ElementsCollection visibleBrands() {return $$x("//div[@class='slick-list draggable']//li");}

    SelenideElement anySelectedBrand() {return $x("//li[@class='js-filter-item ga-click-criteria-filter active']");}

    SelenideElement countOfProductsInPage() {return $x("//div[@class='items_on_page']");}

    ElementsCollection iconOfBrandsInProductList() {return $$x("//div[@class='image']/span/img");}


    ElementsCollection btnAddedOutOfStockProductToWishList() {
        return $$x("//div[@class='button not_active']/ancestor::div[@class='price_box']/span");
    }

    SelenideElement popUpSelector() {
        return $x("//div[@class='new_popup popup_content']");
    }

    SelenideElement closePopUpSelector() {
        return $x("//a[@class='back']");
    }
}
