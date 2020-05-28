package ATD;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Parent_Category_page_Logic  extends Moto_Parent_Category_page{

    @Step(" selecting motorcycle in selector  .Moto_Parent_Category_page")
    public Moto_Catalog_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue(marke);
        brandOfMotoField().shouldHave(exactValue(marke));
        modelFiledInSelector().selectOptionByValue(model);
        modelFiledInSelector().shouldHave(exactValue(model));
        motorFiledInSelector().selectOptionByValue(motor);
        motorFiledInSelector().shouldHave(exactValue(motor));
        searchButton().click();
        return page(Moto_Catalog_page_Logic.class);
    }


    @Step(" Select brand of motorcycle .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic selectBrandOfMoto(String markeOfMoto) {
        brandOfMotoField().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }


    @Step(" reset of motorcycle selector .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic presenceOfEmptyValuesInSelector() {
        brandOfMotoField().shouldHave(exactValue("0"));
        modelFiledInSelector().shouldHave(exactValue("0"));
        motorFiledInSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" check current url  .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        CommonMethods commonMethods = new CommonMethods();
        DataBase db = new DataBase();
        commonMethods.checkingContainsUrl(db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }
}
