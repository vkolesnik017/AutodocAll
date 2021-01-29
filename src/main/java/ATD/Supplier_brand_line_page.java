package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Supplier_brand_line_page {

    SelenideElement anotherBrandsBlock() {return $(".summary-table__table");}

    ElementsCollection anotherBrandLinks() {return $$(".summary-table__table a");}

    SelenideElement mainHeadline() {return $(".catalog-title__block h1");}

    SelenideElement topProductsBlock() {return $(".top-product-block");}

    ElementsCollection btnAddProductToWishList() {return $$x("//div[@class='add-to-wishlist add-article']");   }

    ElementsCollection btnAddTopProductToBasket() {return $$x("//div[@class='price_box product-list__item__button']/a");}

    SelenideElement basketDropMenu() { return $x("//div[@class='cart-items-block ']");}

    SelenideElement basket() { return $x("//a[@class='header-cart__link']"); }

    ElementsCollection imageOfTopProducts() {return $$(".product-list__item__image> a> img");}

    ElementsCollection topProductsPopUp() {return $$(".product-list__item__popup");}

    SelenideElement headlineOfTopProductBlock() {return $(".top-product-block__title span");}

    ElementsCollection titleOfTopProducts() {return $$(".product-list__item__title>span");}

    ElementsCollection detailsOfTopProducts() {return $$(".item-table-box__details>span");}

    SelenideElement lineValueInTopPopUp(int position) {return $x("(//span[contains(text(),'Produktreihe:  ')]/following-sibling::span)["+position+"]");}
}
