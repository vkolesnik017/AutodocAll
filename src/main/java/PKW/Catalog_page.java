package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Catalog_page {

    SelenideElement btnTyresInHeader() {
        return $x("//div[@class='header__navigation']//li[@id='header_menu_tyres']");
    }

    SelenideElement titleWithModelVehicle() {
        return $x("//div[@class='title']//span[@class='model']");
    }




}
