package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Moto_Category_car_list_page {

    SelenideElement tecDocListingBlock() {return $x("//ul[contains(@class,'list_products')]");}
}
