package ATD;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;

public class Moto_Categories_page_Logic extends Moto_Categories_page {


    @Step(" Select brand of motorcycle .Moto_Categories_page")
    public Moto_Categories_page_Logic selectBrandOfMoto(String markeOfMoto) {
        selectorInCloseCondition().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        brandOfMotoField().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Categories_page")
    public Moto_Categories_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }


    @Step(" reset of motorcycle selector .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfEmptyValuesInSelector() {
        selectorInCloseCondition().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        brandOfMotoField().shouldHave(exactValue("0"));
        modelFiledInSelector().shouldHave(exactValue("0"));
        motorFiledInSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" check current url  .Moto_Categories_page")
    public Moto_Categories_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }


    @Step("presence of main Headline block .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfMainHeadlineBlock() {
        mainHeadline().shouldBe(visible);
        return this;
    }

    @Step("check components of model block .Moto_makers_page")
    public Moto_Categories_page_Logic checkComponentsOfBrandsBlock() {
        brandsBlock().shouldBe(visible);
        for (int i = 0; i < linksOfBrands().size(); i++) {
            imageOfMotoBrands().get(i).shouldBe(visible);
            titleOfMotoBrands().get(i).shouldBe(visible);
            linksOfBrands().get(i).shouldHave(attribute("href"));
        }
        return this;
    }

    @Step("presence of models title .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfBrandsTitle(String title) {
        brandsTitle().shouldBe(visible).shouldHave(text(title));
        return this;
    }
}
