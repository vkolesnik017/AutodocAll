package ATD.Catalog_Brands.QC_33_BrandPage;

import ATD.Motoroil_page_Logic;
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

public class QC_3175_CheckPopularBrandBlock {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "OlhaLavrynenko")
    @Description(value = "Test checks popular Brand block Presence on the Main oil page ")
    public void testChecksPopularBrandBlockPresence(String route) {
        openPage(route);
        new Motoroil_page_Logic().checkPopularBrandsBlockPresence();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}