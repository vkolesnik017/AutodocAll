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

    SelenideElement relinkBlock() {
        return $x("//div[@class='other_pr']");
    }

    SelenideElement sizeDiameterFromRelinkBlock() {
        return $x("//div[@class='zoll']//li");
    }

    SelenideElement activeMotorInput() {
        return $x("//div[@id='car-select' and @class='mainblock-search__select slt-selectbox-wrapper filled active select-full']");
    }



}
