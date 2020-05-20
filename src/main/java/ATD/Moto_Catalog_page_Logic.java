package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Catalog_page_Logic extends Moto_Catalog_page {

    @Step("Сheck that the page loads successfully. Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkSuccessfullyMotoCatalogPageLoading() {
        catalogTecDoc().shouldBe(visible);
        Assert.assertEquals(url(), "https://moto.autodoc.de/motorradteile/bmw-motorcycles/c?car_id=115569");
        return this;
    }

    @Step("Select Car category. Moto_Catalog_page")
    public Main_page_Logic selectCarCategory() {
        carCategory().click();
        return page(Main_page_Logic.class);
    }

    @Step("Select LKW category. Moto_Catalog_page")
    public LKW_main_page_Logic selectLKWCategory() {
        lkwCategory().click();
        return page(LKW_main_page_Logic.class);
    }

    @Step(" availability Of Moto Selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for marke field in selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic visibilityOfToolTipForMarkeField() {
        motoSelectorBlock().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        if (!brandOfMotoSelector().getSelectedValue().equals("0")) {
            brandOfMotoSelector().selectOptionByValue("0");
        }
        btnSearchAtSelector().click();
        toolTipForBrandFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic visibilityOfToolTipForModelField() {
        brandOfMotoSelector().selectOptionByValue("4057");
        btnSearchAtSelector().click();
        toolTipForModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic visibilityOfToolTipForMotorField() {
        modelOfMotoSelector().selectOptionByValue("13475");
        btnSearchAtSelector().click();
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }

}
