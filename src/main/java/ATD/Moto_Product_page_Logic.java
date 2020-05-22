package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Product_page_Logic extends Moto_Product_page {

    @Step("availability of moto selector block .Moto_Product_page")
    public Moto_Product_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for marke field in selector .Moto_Product_page")
    public Moto_Product_page_Logic visibilityOfToolTipForMarkeField() {
        btnSearchAtSelector().shouldBe(visible).click();
        toolTipForBrandFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector .Moto_Product_page")
    public Moto_Product_page_Logic visibilityOfToolTipForModelField() {
        brandOfMotoSelector().selectOptionByValue("4057");
        btnSearchAtSelector().click();
        toolTipForModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector .Moto_Product_page")
    public Moto_Product_page_Logic visibilityOfToolTipForMotorField() {
        modelOfMotoSelector().selectOptionByValue("13475");
        btnSearchAtSelector().click();
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }


    @Step(" select motorcycle in horizontal selector() .Moto_Product_page")
    public Moto_Product_page_Logic selectMotoInHorizontalSelector(String brand, String model, String motor) {
        brandOfMotoSelector().selectOptionByValue(brand);
        modelOfMotoSelector().selectOptionByValue(model);
        motorOfMotoSelector().selectOptionByValue(motor);
        btnSearchWithSelectedMoto().click();
        return this;
    }


    @Step(" visibility Of Error Message .Moto_Product_page")
    public Moto_Catalog_page_Logic visibilityOfErrorMessage() {
        infoPopUp().shouldBe(visible);
        return page(Moto_Catalog_page_Logic.class);
    }
}
