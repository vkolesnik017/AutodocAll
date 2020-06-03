package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Category_car_list_page {

    SelenideElement tecDocListingBlock() {return $x("//ul[contains(@class,'list_products')]");}

    SelenideElement brandOfMotoField() {
        return $(byId("form_maker_id"));
    }

    SelenideElement modelFiledInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorFiledInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement searchButton() {return $x("//a[contains(@class,'search_button')]");}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

    SelenideElement headline() {return $x("//div[@class='title_count_search ']/h2");}

    SelenideElement countOfProduct() {return $x("//div[@class='product_count']");}

    SelenideElement lastForwardOfPagination() {return $x("//span[@class='last']/a");}

    SelenideElement nextForwardOfPagination() {return $x("//span[@class='next']/a");}

    ElementsCollection productsAtTecDocListing() {return $$x("//ul[@class='list_products']/li");}

    ElementsCollection btnOutOfStockProducts() {return $$x("//div[@class='button not_active']/a");}

    SelenideElement popUpOfSubscription() {return $x("//div[@class='popup-available']");}

    SelenideElement btnClosePopUpOfSubscription() {return $x("//div[@class='popup-available__close ga-popup-available-close']");}

    ElementsCollection   imageOfProductTecDocListingBlock() {return $$x("//div[@class='image']//span[2]/img");}

    ElementsCollection    titleOfProductInTecDocListingBlock()   {return $$x("//div[@class='name']/*[self::a or self::span][1]");}

    ElementsCollection   imageBrandOfProductTecDocListingBlock() {return $$x("//div[@class='image']//span[2]/img");}


}
