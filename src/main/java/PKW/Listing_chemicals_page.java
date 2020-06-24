package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_chemicals_page {


    SelenideElement titleNameListingPage() {
        return $x("//h1/span");
    }
}
