package ATD;

import Common.DataBase;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.page;

public class Moto_makers_page_Logic extends Moto_makers_page {

    @Step(" Select brand of motorcycle .Moto_makers_page")
    public Moto_makers_page_Logic selectBrandOfMoto(String markeOfMoto) {
        brandOfMotoField().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_makers_page")
    public Moto_makers_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_makers_page")
    public Moto_makers_page_Logic presenceOfEmptyValuesInSelector() {
        brandOfMotoField().shouldHave(exactValue("0"));
        modelFiledInSelector().shouldHave(exactValue("0"));
        motorFiledInSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" check current url  .Moto_makers_page")
    public Moto_makers_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }

    @Step(" presence of main headline block  .Moto_makers_page")
    public Moto_makers_page_Logic presenceOfMainHeadlineBlock() {
        mainHeadline().shouldBe(visible);
        return this;
    }

    @Step(" get total сount of models from title  .Moto_makers_page")
    public int getCountOfModelsFromTitle() {
        int totalCountOfBrands = Integer.parseInt(headlineOfBrandsBlock().getText().replaceAll("[^0-9]", ""));
        return totalCountOfBrands;
    }

    @Step("comparing quantity of models .Moto_makers_page")
    public Moto_makers_page_Logic comparingQuantityOfModels(int expectedCountOfModels) {
        linksOfBrands().shouldHaveSize(expectedCountOfModels);
        return this;
    }

    @Step("check components of model block .Moto_makers_page")
    public Moto_makers_page_Logic checkComponentsOfBrandsBlock() {
        brandsBlock().shouldBe(visible);
        for (int i = 0; i < linksOfBrands().size(); i++) {
            imageOfMotoBrands().get(i).shouldBe(visible);
            titleOfMotoBrands().get(i).shouldBe(visible);
            countOfMotoBrands().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("presence of automakers block .Moto_makers_page")
    public Moto_makers_page_Logic presenceOfAutomakersBlock() {
        brandsBlock().shouldBe(visible);
        linksOfBrands().shouldHaveSize(154);
        return this;
    }

    @Step("click on image of motomaker .Moto_makers_page")
    public Moto_Categories_maker_page_Logic clickOnImageOfMotomaker(int position) {
        linksOfBrands().get(position).shouldBe(visible).click();
        return page(Moto_Categories_maker_page_Logic.class);
    }

    @Step("check elements of linking block .Moto_makers_page")
    public Moto_makers_page_Logic checkElementsOfLinkingBlock() {
        headlineOfLinkingBlock().shouldBe(visible);
        topModelsLinkingBlock().shouldHaveSize(5);
        for (int i = 0; i < topModelsLinkingBlock().size(); i++) {
            linksOfTopModelsAtLinkingBlock(i + 1).shouldHaveSize(6);
        }
        return this;
    }

    @Step("click on TOP model in linking block .Moto_makers_page")
    public Moto_Catalog_model_page_Logic clickOnTopModelInLinkingBlock(int block, int model) {
        linksOfTopModelsAtLinkingBlock(block).get(model).shouldBe(visible).click();
        return page(Moto_Catalog_model_page_Logic.class);
    }

    @Step("click on Garage icon in header. Moto_makers_page")
    public Moto_makers_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Moto_makers_page")
    public Moto_makers_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("check expected values in selector. Moto_makers_page")
    public Moto_makers_page_Logic checkValuesInSelector(String marke, String model, String motor) {
        brandOfMotoField().shouldHave(value(marke));
        modelFiledInSelector().shouldHave(value(model));
        motorFiledInSelector().shouldHave(value(motor));
        return this;
    }
}
