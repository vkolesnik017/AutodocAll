package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page;
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
    private Product_page productPage = new Product_page();
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
        productPage.firstBrandInCompabilityList().shouldHave(text("AUTOBIANCHI"));
        productPage.secondBrandInCompabilityList().shouldHave(text("FIAT"));
        productPage.thirdBrandInCompabilityList().shouldHave(text("LANCIA"));
        productPage.fourthBrandInCompabilityList().shouldHave(text("SEAT"));
        productPage.chooseBrandModelTypeInSelector("VW", "4644", "14881");
        productPage.selectorSearchBtn().click();
        productPage.checkTextIsVisibleOnPage("Es tut uns leid!");
        productPage.checkTextIsVisibleOnPage("Kfz-Ersatzteile für VW 166 SUV Cabrio 1.1 Benzin (24 PS, Bj ab 1942)");
        close();
    }
}
