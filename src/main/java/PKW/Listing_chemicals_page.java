package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Listing_chemicals_page {


    SelenideElement titleNameListingPage() {
        return $x("//h1/span");
    }

    SelenideElement blockBreadCrumbs() {return $x("//div[@class='crabs']"); }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][1]");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][2]");
    }

    SelenideElement thirdBreadCrumb() {
        return $x("//div[@class='crabs']//a[@class='defcur']");
    }

    SelenideElement blockPagination() {
        return $x("//div[@class='pagination_wrap']");
    }

    SelenideElement btnForReturnOnFirstPageInPagination() {
        return $x("//div[@class='pagination']/span[@class='first']");
    }

    SelenideElement btnPreviousInPagination() {
        return $x("//div[@class='pagination']/span[@class='previous']");
    }

    SelenideElement btnNextInPagination() {
        return $x("//div[@class='pagination']/span[@class='next'][1]");
    }

    SelenideElement btnLastInPagination() {
        return $x("//div[@class='pagination']/span[@class='next'][2]");
    }

    SelenideElement btnSecondPageInPagination() {
    return $x("//div[@class='pagination']/span[3]");
    }

}
