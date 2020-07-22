package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_instruments_Page {

    SelenideElement titleCategory() {
        return $x("//h1/span");
    }
}
