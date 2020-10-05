package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Tyres_dimension_page {

    SelenideElement productsList() {
        return $x("//ul[@class='list_products']");
    }

    ElementsCollection btnOutOfStock() {
        return $$x("//div[@class='button not_active']/a");
    }

    SelenideElement feedBackPopUp() {
        return $x("//div[@class='popup-available']");
    }

    SelenideElement popUPAboutInvalidEmail() {
        return $x("//div[@id='popup_update']//div[@class='txt ']");
    }

    SelenideElement btnSendOfFeedBackPopUp() {
        return $x("//input[@class='popup-available__button']");
    }

    SelenideElement emailFieldOfFeedBackPopUp() {
        return $(byId("form_AvailabilityReminder[email]"));
    }

    SelenideElement productListBlock() {return $x("//ul[@class='list_products']");}

    ElementsCollection mpnNumberOfProduct() {return $$x("//div[@class='name']/span[contains(text(),'MPN')]");}

    ElementsCollection btnAddProductToWishList() { return $$x("//span[@class='add-to-wishlist title_btn add-article']");}

    ElementsCollection addedProductToWishList() { return $$x("//span[@class='add-to-wishlist title_btn add-to-wishlist--added remove-article']");}

    SelenideElement iconOfWishList() { return $x("//span[@class='header__wishes link']"); }

    ElementsCollection visibleBrands() {return $$x("//li[@class='js-filter-item ga-click-criteria-filter slick-slide slick-active']");}

    SelenideElement anySelectedBrand() {return $x("//li[@class='js-filter-item ga-click-criteria-filter active slick-slide slick-current slick-active']");}

    SelenideElement countOfProductsInPage() {return $x("//div[@class='items_on_page']");}

    SelenideElement btnLastPaginator() {return $x("//span[@class='last']/a");}

    SelenideElement activePagePaginator() {return $x("//span[@class='active']/a");}

    SelenideElement additionInfoFilterBlock() {return $x("//div[@class='additional_filter']");}

    ElementsCollection additionIfoFilters() {return $$x("//div[@class='additional_filter']//ul/li");}

    ElementsCollection eanOfProducts() {return $$x("//span[@class='article_number']");}

    SelenideElement grayButtonByEan(String ean) {return $x("//span[contains(text(),'"+ean+"')]/ancestor::li//div[@class='button not_active']/a");}

    SelenideElement btnNextPaginator() {return $x("//span[@class='next']");}

    SelenideElement popUpNotifyAboutAvailability() {return $x("//div[@class='popup-available']");}

    SelenideElement labelOfPopUpNotifyAboutAvailability() {return $x("//label[@class='popup-available__label']");}

    SelenideElement btnCloseSentLetterPopUp() {return $x("//a[@class='close']");}
}
