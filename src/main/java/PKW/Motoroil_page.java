package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_page {

   SelenideElement brandsBlock() {return $x("//div[@class='featured_manufacturers featured_manufacturers--oil']");}
}
