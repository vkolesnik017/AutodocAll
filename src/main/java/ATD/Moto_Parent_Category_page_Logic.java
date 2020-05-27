package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class Moto_Parent_Category_page_Logic  extends Moto_Parent_Category_page{

    @Step(" selecting motorcycle in selector . Moto_Parent_Category_page")
    public Moto_Catalog_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue(marke);
        modelFiledInSelector().selectOptionByValue(model);
        motorFiledInSelector().selectOptionByValue(motor);
        searchButton().click();
        return page(Moto_Catalog_page_Logic.class);
    }
}
