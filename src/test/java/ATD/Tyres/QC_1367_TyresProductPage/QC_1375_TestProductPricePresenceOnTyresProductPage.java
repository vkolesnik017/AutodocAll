package ATD.Tyres.QC_1367_TyresProductPage;


import ATD.DataBase;
import ATD.SetUp;
import ATD.TyresListing_page_Logic;
import ATD.TyresProduct_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1375_TestProductPricePresenceOnTyresProductPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Product Price Presence On Tyres Product Page")
    public void testProductPricePresenceOnTyresProductPage(String route) {
        openPage(route);
        new TyresProduct_page_Logic().checkProductPriceVisibility();
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
