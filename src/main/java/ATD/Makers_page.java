package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Makers_page {

    SelenideElement brandsBlock() {return $x("//section[@class='cars-list']");}

    SelenideElement headerGarageIcon(){
        return $x("//div[@class='header-garage js-header-garage']");
    }

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    public SelenideElement brandSelectorInVerticalCarSelector() {
        return $("#form_maker_id");
    }

    SelenideElement modelSelectorInVerticalCarSelector() {
        return $("#form_model_id");
    }

    SelenideElement typeSelectorInVerticalCarSelector() {
        return $("#form_car_id");
    }
}
