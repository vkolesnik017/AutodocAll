package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.MotoroilViscosity_page_Logic;
import Common.SetUp;
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

public class QC_3479 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block In Motoroil_viscosity route")
    public void testChecksSeoBlockInMotoroilViscosityRoute(String route) throws SQLException {
        openPage(route);
        new MotoroilViscosity_page_Logic().checkEngineOilPropertiesText("engineOilProperties");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
