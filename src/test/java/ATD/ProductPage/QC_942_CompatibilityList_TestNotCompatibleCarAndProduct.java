package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;

public class QC_942_CompatibilityList_TestNotCompatibleCarAndProduct {
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private DataBase dataBase = new DataBase();
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks product compatibility with car")
    public void testNotCompatibleCarAndProduct() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product16"));
        product_page_logic.firstBrandInCompabilityList().shouldHave(text("AUTOBIANCHI"));
        product_page_logic.secondBrandInCompabilityList().shouldHave(text("FIAT"));
        product_page_logic.thirdBrandInCompabilityList().shouldHave(text("LANCIA"));
        product_page_logic.fourthBrandInCompabilityList().shouldHave(text("SEAT"));
        product_page_logic.chooseBrandModelTypeInSelector("VW", "4644", "14881");
        product_page_logic.selectorSearchBtn().click();
        product_page_logic.checkTextIsVisibleOnPage("Es tut uns leid!");
        product_page_logic.checkTextIsVisibleOnPage("Kfz-Ersatzteile für VW 166 SUV Cabrio 1.1 Benzin (24 PS, Bj ab 1942)");
        close();
    }
}
