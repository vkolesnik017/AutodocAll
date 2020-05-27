package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Category_car_list_page_Logic extends Moto_Category_car_list_page {

    @Step("visibility of TecDoc listing .Moto_Category_car_list_pag")
    public Moto_Category_car_list_page_Logic visibilityOfTecDocListing() {
        tecDocListingBlock().shouldBe(visible);
        return this;
    }

    @Step(" selecting motorcycle in selector .Moto_Category_car_list_pag")
    public Moto_Category_car_list_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue(marke);
        modelFiledInSelector().selectOptionByValue(model);
        motorFiledInSelector().selectOptionByValue(motor);
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }

    @Step(" select Brand Of Motorcycle in selector .Moto_Category_car_list_pag")
    public Moto_Category_car_list_page_Logic selectBrandOfMotoInSelector(String marke) {
        brandOfMotoField().selectOptionByValue(marke);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Category_car_list_pag")
    public Moto_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Category_page_Logic.class);
    }


}
