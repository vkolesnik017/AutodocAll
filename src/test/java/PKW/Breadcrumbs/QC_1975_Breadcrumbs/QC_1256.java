package PKW.Breadcrumbs.QC_1975_Breadcrumbs;

import PKW.Motoroil_viscosity_page_Logic;
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

public class QC_1256 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity,motoroil_viscosity_brand,motoroil_specification,motoroil_release,motoroil_brand,motoroil_maker,motoroil_maker_group,motoroil_chemical_type,car_parts_motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of breadcrumbs block in listing")
    public void testChecksPresenceOfBreadCrumbsBlockInListing(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_page_Logic()
                .presenceOfBreadCrumbsBlock()
                .checkToPresenceOfTextInFirstLinkOfBreadCrumbs();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
