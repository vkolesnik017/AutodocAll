package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.exactValue;
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
        brandOfMotoField().shouldHave(exactValue(marke));
        modelFiledInSelector().selectOptionByValue(model);
        modelFiledInSelector().shouldHave(exactValue(model));
        motorFiledInSelector().selectOptionByValue(motor);
        motorFiledInSelector().shouldHave(exactValue(motor));
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }

    @Step(" check current url. Moto_Category_page")
    public Moto_Category_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }


    @Step(" Select brand of motorcycle .Moto_Category_page")
    public Moto_Category_page_Logic selectBrandOfMoto(String markeOfMoto) {
        brandOfMotoField().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Category_page")
    public Moto_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }


    @Step(" reset of motorcycle selector .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfEmptyValuesInSelector() {
        brandOfMotoField().shouldHave(exactValue("0"));
        modelFiledInSelector().shouldHave(exactValue("0"));
        motorFiledInSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" select motorcycle model in selector .Moto_Category_page")
    public Moto_Category_page_Logic selectMotoModel(String model) {
        brandOfMotoField().shouldNotHave(exactValue("0"));
        modelFiledInSelector().shouldBe(visible).selectOptionByValue(model);
        return this;
    }

    @Step(" select motorcycle motor in selector .Moto_Category_page")
    public Moto_Category_page_Logic selectMotoMotor(String motor) {
        motorFiledInSelector().shouldBe(visible).selectOptionByValue(motor);
        return this;
    }

    @Step(" click on Search button in selector .Moto_Category_page")
    public Moto_Category_car_list_page_Logic clickOnSearchButton() {
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }


    @Step("presence of benefits block .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfBenefitsBlock() {
        benefitsBlock().shouldBe(visible);
        return this;
    }


    @Step("presence of headline at benefits block .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfHeadlineAtBenefitsBlock() {
        headlineBenefitsBlock().shouldBe(visible);
        return this;
    }


    @Step("presence of benefits links .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfBenefitsLinks() {
        benefitsLinks().shouldHaveSize(2);
        return this;
    }


    @Step("visibility of tooltip at benefits links .Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfTooltipAtBenefitsLinks() {
        benefitsBlock().shouldBe(visible);
        for (int i = 0; i < imageOfBenefitsLinks().size(); i++) {
            imageOfBenefitsLinks().get(i).hover();
            tooltipOfBenefitsLinks().get(i).shouldBe(visible);
        }
        return this;
    }


    @Step("presence Of elements at benefit block .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfElementsAtBenefitBlock() {
        benefitsBlock().shouldBe(visible);
        for (int i = 0; i < imageOfBenefitsLinks().size(); i++) {
            imageOfBenefitsLinks().get(i).shouldBe(visible);
            textOfBenefitsLinks().get(i).shouldBe(visible);
        }
        return this;
    }


    @Step("presence of main image at child category .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfMainImageAtChildCategory() {
        mainImageOfChildCategory().shouldBe(visible);
        int widthOfMainImageAtChildCategory = mainImageOfChildCategory().getSize().getWidth();
        int heightOfMainImageAtChildCategory = mainImageOfChildCategory().getSize().getHeight();
        Assert.assertEquals(widthOfMainImageAtChildCategory, 200);
        Assert.assertEquals(heightOfMainImageAtChildCategory, 200);

        return this;
    }

}
