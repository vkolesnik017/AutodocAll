package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_accessories_page {

    SelenideElement mainTitleListingPage() {
        return $x("//h1/span");
    }

    SelenideElement counterValueInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//input[@class='ammount']");
    }

    SelenideElement btnPlusInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click plus']");
    }

    SelenideElement btnMinusInQuantityCounter() {
        return $x("//div[@class='brand-products'][1]//a[@class='ga-click minus']");
    }

}
