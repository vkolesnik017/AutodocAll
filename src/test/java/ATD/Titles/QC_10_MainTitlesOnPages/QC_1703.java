package ATD.Titles.QC_10_MainTitlesOnPages;

import ATD.LKW_Categories_page_Logic;
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

public class QC_1703 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list11,lkw_maker_car_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of headline in Catalog route")
    public void testChecksAvailabilityOfHeadLineInTecDocCatalogAndModelCatalog(String route) {
        openPage(route);
        new LKW_Categories_page_Logic().visibilityOfHeadLine();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
