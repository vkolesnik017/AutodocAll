package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Makers_page_Logic extends Makers_page{

    @Step("presence of Brands block. Makers_page")
    public Makers_page_Logic presenceOfBrandsBlock() {
        brandsBlock().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Makers_page")
    public Makers_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Makers_page")
    public Makers_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("check expected values in selector. Makers_page")
    public Makers_page_Logic checkValuesInSelector(String marke, String model, String motor) {
        brandSelectorInVerticalCarSelector().shouldHave(value(marke));
        modelSelectorInVerticalCarSelector().shouldHave(value(model));
        typeSelectorInVerticalCarSelector().shouldHave(value(motor));
        return this;
    }
}
