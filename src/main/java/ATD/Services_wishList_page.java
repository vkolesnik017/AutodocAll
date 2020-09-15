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

    ElementsCollection artNumOfProduct() {return $$x("//div[@class='name']/span[1]");}

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
}
