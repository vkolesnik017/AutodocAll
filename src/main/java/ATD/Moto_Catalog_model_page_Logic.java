package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

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

    @Step(" selecting motorcycle in selector .Moto_Catalog_model_page")
    public Moto_Catalog_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        motoSelectorBlock().click();
        brandOfMotoSelector().selectOptionByValue(marke);
        modelOfMotoSelector().selectOptionByValue(model);
        motorOfMotoSelector().selectOptionByValue(motor);
        btnSearchAtSelector().click();
        return page(Moto_Catalog_page_Logic.class);
    }

    @Step("get brand of  motorcycle from Url .Moto_Catalog_model_page")
    public String getBrandOfMotoFromUrl() {
        String[] pathParts = url().split("/");
        String brandOfMoto = pathParts[pathParts.length - 2];
        return brandOfMoto;
    }

    @Step("get model of  motorcycle from Url .Moto_Catalog_model_page")
    public String getModelOfMotoFromUrl() {
        String[] pathParts = url().split("/");
        String modelOfMoto = pathParts[pathParts.length - 1];
        return modelOfMoto;
    }


    @Step(" appears of tooltip for motor field in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic presenceBrandAndModelOfMotoInSelector(String brand, String model) {
        String brandOfMotoFromSelector = brandOfMotoSelector().getSelectedText().replaceAll("[^A-Z]", "");
        String modelOfMotoFromSelector = modelOfMotoSelector().getSelectedText().replaceAll("[^a-zA-Z]", "");
        Assert.assertEquals(brandOfMotoFromSelector, brand.replaceAll("[^a-z]", "").toUpperCase());
        Assert.assertEquals(modelOfMotoFromSelector, model.replaceAll("[^a-z]", "").toUpperCase());
        return this;
    }

    @Step(" visibility of opening selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic visibilityOfOpeningSelector() {
        motoSelectorBlock().shouldBe(visible);
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Catalog_model_page")
    public Moto_Categories_page_Logic resetOfMotoSelector() {
        mainFormOfSelector().shouldBe(visible);
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }

}
