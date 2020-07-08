package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_page {

    public SelenideElement preloader() {
        return $x("//body/div[@class='preloader_wrapper']");
    }
}
