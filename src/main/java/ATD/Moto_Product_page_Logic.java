package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.exactValue;
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


    @Step(" select motorcycle in horizontal selector .Moto_Product_page")
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


    @Step(" get brand and model Of motorcycle .Moto_Product_page")
    public String getBrandAndModelOfMoto() {
        String brandOfMoto = getBrandOfMoto();
        String motorOfMoto = getMotorOfMoto();
        String brandAndModel = brandOfMoto + " " + motorOfMoto;
        return brandAndModel;
    }

    @Step(" get brand Of motorcycle .Moto_Product_page")
    public String getBrandOfMoto() {
        productCompatibilityHeader().shouldBe(visible);
        String brandOfMoto = brandOfMotoSelector().getSelectedText();
        return brandOfMoto;
    }

    @Step(" get brand Of motorcycle .Moto_Product_page")
    public String getMotorOfMoto() {
        String selectedMotorOfMoto = motorOfMotoSelector().getSelectedText().replace(motorOfMotoSelector().getSelectedText().substring(motorOfMotoSelector().getSelectedText().lastIndexOf("/")), "");
        String motorOfMoto = selectedMotorOfMoto.replace(selectedMotorOfMoto.substring(selectedMotorOfMoto.lastIndexOf(")")), "");

        return motorOfMoto;
    }


    @Step("presence  of motorcycle brand and model in an information message .Moto_Product_page")
    public Moto_Product_page_Logic presenceOfMotoBrandAtInfoMessage(String brandOfMoto) {
        Assert.assertTrue(motoBrandFromInfoMessage().getText().contains(brandOfMoto));
        return this;
    }



    @Step(" Select brand of motorcycle .Moto_Product_page")
    public Moto_Product_page_Logic selectBrandOfMoto(String markeOfMoto) {
        brandOfMotoSelector().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Product_page")
    public Moto_Product_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }


    @Step(" reset of motorcycle selector .Moto_Product_page")
    public Moto_Product_page_Logic presenceOfEmptyValuesInSelector() {
        brandOfMotoSelector().shouldHave(exactValue("0"));
        modelOfMotoSelector().shouldHave(exactValue("0"));
        motorOfMotoSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" check current url  .Moto_Product_page")
    public Moto_Product_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        CommonMethods commonMethods = new CommonMethods();
        DataBase db = new DataBase();
        commonMethods.checkingContainsUrl(db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }
}
