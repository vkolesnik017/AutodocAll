package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Tyres_page {


    SelenideElement  carTabs() {
        return $x("//div[@class='car_tabs ']");
    }

    SelenideElement brandInput() {
        return $x("//select[@id='form_maker_id']");
    }

    SelenideElement modelInput() {
        return $x("//select[@id='form_model_id']");
    }

    SelenideElement motorInput() {
        return $x("//select[@id='form_car_id']");
    }


    SelenideElement linkingBlockByBrands() {return $x("//div[@class='featured_manufacturers']");}

    SelenideElement brandByName(String titleOfBrand) {return $x("//div[@class='featured_manufacturers']//a/img[contains(@alt,'"+titleOfBrand+"')]");}

    SelenideElement btnAllBrands() {return $x("//div[@class='type_list_all_brands']/a");}

    SelenideElement relinkBlock() {
        return $x("//div[@class='other_pr']");
    }

    SelenideElement sizeDiameterFromRelinkBlock() {
        return $x("//div[@class='zoll']//li/a");
    }

    SelenideElement activeMotorInput() {
        return $x("//div[@id='car-select' and @class='mainblock-search__select slt-selectbox-wrapper filled active select-full']");
    }

    SelenideElement relinkBlockBySeasons() {return $x("//div[contains(@class,'seasons ')]");}

    SelenideElement winterSeasonsFromRelinkBlock() {return $x("//div[contains(@class,'seasons ')]//a[@data-ga-action='winter']");}

    SelenideElement relinkByCarBlock() {return $x("//div[@class='listing_microdata bottom_box_mod listing_microdata--brand']");}

    SelenideElement modelFromRelinkByCarBlock() {return $x("//div[@class='bx-wrapper']//ul/li/a");}

    SelenideElement btnMoreFromRelinkByCarBlock() {return $x("//div[@class='listing_microdata bottom_box_mod listing_microdata--brand']//a[@class='button']");}

    SelenideElement btnAllSizeFromRelinkBySizeBlock() {return $x("//div[@class='other_pr']/a[@class='button']");}



}
