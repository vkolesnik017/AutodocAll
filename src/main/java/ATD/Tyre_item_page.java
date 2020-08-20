package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Tyre_item_page {

    SelenideElement horizontalSelector() {return $x("//div[@class='atd-carselector__content atd-carselector__tyres']");}

    SelenideElement grayButtonOfProduct() { return $x("//div[@class='product-button button not_active out-of-stock']/a");   }

    SelenideElement popUpAboutLackOfProduct() {return $x("//div[@class='popup-available']");}
}
