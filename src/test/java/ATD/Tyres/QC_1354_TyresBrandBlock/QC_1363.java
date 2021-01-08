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

public class QC_1363 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_type_list_brands,tyres_type_list_brands2,tyres_type_list_brands3," +
                "tyres_type_list_brands4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Brand List Presence On Tyres Brand Catalog")
    public void testBrandListPresenceOnTyresBrandCatalog(String route) {
        openPage(route);
        new Tyres_page_Logic().checkAllBrandsListPresence();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}