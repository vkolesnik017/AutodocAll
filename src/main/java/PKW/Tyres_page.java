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

}
