package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LKW_Search_page {

    SelenideElement listOfProductInTecDocListingBlock() {
        return $x("//ul[@class='list_products']");
    }
    ElementsCollection btnAddDangerousProductToWishList() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::li//span[@class='add-to-wishlist title_btn add-article']");}

    ElementsCollection signalWordOfDangerousProductListingView() {return $$x("//div[@class='dangerous-listing__title hazard-attention-title']");}

    ElementsCollection attributeOfWarningIcon(int positionOfProduct) {return $$x("(//span[@class='dangerous-listing__show-more'])["+positionOfProduct+"]/ancestor::li//div[@class='dangerous-listing__icons']/div");}

    ElementsCollection btnMoreOfDangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']");}

    ElementsCollection dangerousIconInWarningPopUp() {return $$x("//div[@class='popup-dangerous__icon']").filter(visible);}

    SelenideElement blackBackground() {return $x("//div[@class='overlay black hidden']");}

    SelenideElement warningPopUp() {return $x("//div[@class='popup-dangerous']");}

    SelenideElement titleOfDangerousPopUp() {return $x("//div[@class='popup-dangerous__title']");}

    SelenideElement infoTextOfDangerousPopUp() {return $x("//div[@class='popup-dangerous']//p");}

    SelenideElement loaderInTecDocListing() {
        return $x("//div[@class='preloader_wrapper']");
    }

    SelenideElement visibleExactBrand(String id) {return $x("//div[@id='selected-instalation__slider']//label[@for='cb-brand-"+id+"']");}

    SelenideElement forwardLinkAtBrandsFilter() {
        return $x("//a[contains(@class,'next')]");
    }

    SelenideElement btnCloseSelectorPopUp() {return $x("//a[@class='back']");}

    ElementsCollection allGenerics() {return $$x("//div[@class='slick-list draggable']//label");}
}
