package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Moto_Product_page {

    SelenideElement motoSelectorBlock() {return $x("//div[@class='atd-carselector']");}
}
