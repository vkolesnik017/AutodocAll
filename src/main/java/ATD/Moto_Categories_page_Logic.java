package ATD;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.visible;

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
}
