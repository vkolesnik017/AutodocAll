package ATD;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
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
}
