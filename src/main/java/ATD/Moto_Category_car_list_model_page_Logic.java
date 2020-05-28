package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class Moto_Category_car_list_model_page_Logic extends Moto_Category_car_list_model_page {

    @Step(" selecting motorcycle in selector .Moto_Category_car_list_model_page")
    public Moto_Category_car_list_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue("0");
        brandOfMotoField().selectOptionByValue(marke);
        modelFiledInSelector().selectOptionByValue(model);
        motorFiledInSelector().selectOptionByValue(motor);
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }
}
