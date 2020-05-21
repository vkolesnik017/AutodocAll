package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Category_page_Logic extends Moto_Category_page {

    @Step("check successfully child category page loading. Moto_Category_page")
    public Moto_Category_page_Logic checkSuccessfullyChildCategoryPageLoading() {
        imageOfChildCategory().shouldBe(visible);
        Assert.assertEquals(url(), "https://moto.autodoc.de/ersatzteile/motorrad-luftfilter-43063");
        return this;
    }


    @Step(" availability Of Moto Selector. Moto_Category_page")
    public Moto_Category_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }


    @Step(" appears of tooltip for marke field in selector. Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfToolTipForMarkeField() {
              if (!brandOfMotoField().getSelectedValue().equals("0")) {
                  brandOfMotoField().selectOptionByValue("0");
              }
                btnSearchAtSelector().click();
                tooltipOfMarkeField().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector. Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfToolTipForModelField() {
        brandOfMotoField().selectOptionByValue("4057");
        btnSearchAtSelector().click();
        tooltipOfModelField().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector. Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfToolTipForMotorField() {
        modelFiledInSelector().selectOptionByValue("13475");
        btnSearchAtSelector().click();
        tooltipOfMotorField().shouldBe(visible);
        return this;
    }


    @Step(" selecting motorcycle in selector . Moto_Category_page")
    public Moto_Category_car_list_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue(marke);
        modelFiledInSelector().selectOptionByValue(model);
        motorFiledInSelector().selectOptionByValue(motor);
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }

}
