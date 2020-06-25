package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_accessories_page {

    SelenideElement mainTitleListingPage() {
        return $x("//h1/span");
    }
}
