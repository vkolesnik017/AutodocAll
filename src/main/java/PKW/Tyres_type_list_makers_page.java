package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Tyres_type_list_makers_page {

    SelenideElement allCarsBlock() {
        return $x("//div[@class='cont cont_car_tires_sizes']");
    }
}
