package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Moto_Catalog_model_page_Logic extends Moto_Catalog_model_page {

    @Step("availability of moto selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step("visibility of main form of selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic openingSelector() {
        motoSelectorInCloseCondition().shouldBe(visible).click();
        motoSelectorMainForm().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for marke field in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic visibilityOfToolTipForMarkeField() {
        if (!brandOfMotoSelector().getSelectedValue().equals("0")) {
            brandOfMotoSelector().selectOptionByValue("0");
        }
        btnSearchAtSelector().shouldBe(visible).click();
        toolTipForBrandFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic visibilityOfToolTipForModelField() {
        brandOfMotoSelector().selectOptionByValue("4057");
        btnSearchAtSelector().click();
        toolTipForModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic visibilityOfToolTipForMotorField() {
        modelOfMotoSelector().selectOptionByValue("13475");
        btnSearchAtSelector().click();
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }

}
