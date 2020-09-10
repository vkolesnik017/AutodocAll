package ATD.Tyres.QC_1354_TyresBrandBlock;


import Common.SetUp;
import ATD.Tyres_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1364_TestTitleOverBrandListPresenceOnTyresBrandCatalog {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_type_list_brands,tyres_type_list_brands2,tyres_type_list_brands3," +
                "tyres_type_list_brands4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Title Over Brand List Presence On Tyres Brand Catalog")
    public void testTitleOverBrandListPresenceOnTyresBrandCatalog(String route) {
        openPage(route);
        new Tyres_page_Logic().checkAllBrandsListTitlePresence();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
