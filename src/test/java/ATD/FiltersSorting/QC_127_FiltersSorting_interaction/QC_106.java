package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;

import ATD.LKW_Category_car_list_page_Logic;
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

public class QC_106 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list12");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks  interaction of brands and side filters")
    public void testChecksInteractionOfBrandsAndSideFilters(String route) throws SQLException {
        openPage(route);

        new LKW_Category_car_list_page_Logic()
                .selectBrandFromFilterOfBrands("lkw_category_car_list21","39")
                .checkTecDocListingWithSelectingBrandAndInstallationSide();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
