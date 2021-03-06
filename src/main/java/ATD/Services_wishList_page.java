package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Services_wishList_page {

    SelenideElement productList() {return $x("//ul[@class='list_products']"); }

    ElementsCollection mpnNumberOfProduct() {return $$x("//div[@class='name']/span[contains(text(),'MPN')]");}

    SelenideElement productBlock() { return $(byId("content-wishlist"));}

    SelenideElement countOfProduct() {
        return $x("//div[@class='count']/input");
    }

    SelenideElement btnIncreaseOfProduct() {
        return $x("//a[@class='ga-click plus add']");
    }

    ElementsCollection blocksInSideBar() { return $$x("//ul[@class='menu_member']/li/a").filter(visible);}

    ElementsCollection artNumOfProduct() {return $$x("//div[@class='name']/span[@class='article_number'][1]");}

    ElementsCollection btnRemoveProduct() {return $$x("//span[@class='wishlist__remove remove-article-wishlist']");}

    SelenideElement dialogPopUp() {return $(byId("popup_update"));}

    SelenideElement btnRemoveInDialogPopUp() {return $x("//a[@class='submit']");}

    SelenideElement currentCountOfProductInWishList() {return $x("//span[@class='wishlist-articles-count']");}

    SelenideElement btnRemoveProductsPopUp() {return $(byId("delete_wishlist"));}

    SelenideElement dialogTextOfRemoveAProductsPopUp() {return $x("//div[@class='txt ']");}

    SelenideElement btnCloseRemoveProductPopUp() {return $x("//div[@class='buttons']//a[1]/span[2]");}

    SelenideElement btnDeleteProductPopUp() { return $x("//div[@class='buttons']//a[2]/span[2]");    }

    SelenideElement btnCloseXRemoveProductPopUp() {  return $x("//div[@class='popup_top']/a"); }

    SelenideElement btnBuyAllProducts() {return $(byId("add-wishlist-to-cart"));}

    ElementsCollection listOfProducts() {return $$x("//ul[@class='list_products']/li");}

    ElementsCollection btnAlternativeProducts() {return $$x("//div[@class='show_alternative__btn ga-click button additional_btn']");}

    SelenideElement alternativeBlock() { return $x("//div[@class='top-small-products top-small-products--alternative']");  }

    ElementsCollection imageOfAlternativeProducts() {return $$x("//div[@class='top-small-products-items__item']//div[@class='small-prod-image']");}

    ElementsCollection descriptionPopUpOfAlternativeProduct() {return $$x("//div[@class='rec_prod_info_popup']");}

    ElementsCollection btnAddAlternativeProductToBasket() {return $$x("//div[@class='small-product-button price_box']");}

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement infoText() {return $x("//p[contains(text(),'Ihre Wunschliste ist leer. Gehen Sie zum Katalog, um ein Produkt auszuw??hlen.')]");}

    SelenideElement btnOpenCatalog() {return $x("//div[@class='profile-msg']/a");}

    SelenideElement activeWishListItemInSidebar() {return $x("//li[@class='wishlist_link active']/a");}

    SelenideElement loginPopUp() {return $x("//div[@class='autodoc_login_popup popup_login pass']");}

    SelenideElement btnCloseLoginPopUp() {return $x("//div[@class='top_info']/a");}

    ElementsCollection imageOfBrandInListing() {return $$x("//div[@class='image']/span[1]/img");}

    ElementsCollection priceBlock() {return $$x("//div[@class='price_box']");}

    ElementsCollection visibleCharacteristicsOfProducts(int position) {
        return $$x("(//div[@class='about'])[" + position + "]//ul/li");
    }

    ElementsCollection amountQuantityOfProduct() {return $$x("//div[@class='count']/input");}

    SelenideElement authorizationBlock() {return $(byId("login-alert-wishlist"));}

    SelenideElement infoTextOfAuthorizationBlock() {return $x("//div[@class='profile-msg']/p");}

    SelenideElement btnCloseOfAuthorizationBlock() {return $x("//span[@class='profile-msg__close']");}

    SelenideElement btnRegistrationOfAuthorizationBlock() {return $x("//a[@class='profile-msg__action']");}

    SelenideElement emptyWishListBlock() {return $x("//div[@class='cont profile_mein_a']");}

    SelenideElement btnOutOfStockProduct() {return $x("//div[@class='button not_active']/a");}

    SelenideElement availablePopUp() {return $x("//div[@class='popup-available']");}

    SelenideElement btn360Functionality() {
        return $x("//a[contains(@class,'photos360-popup')]");
    }

    SelenideElement popup360Functionality() {
        return $x("//div[@id='wr360container_f8097571']");
    }

}