package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_chemicals_page {


    SelenideElement titleNameListingPage() {
        return $x("//h1/span");
    }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][1]");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][2]");
    }

    SelenideElement thirdBreadCrumb() {
        return $x("//div[@class='crabs']//a[@class='defcur']");
    }
}
