package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Maker_car_list_page_Logic;
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

public class QC_2917 {

    private String dataFile = "C:/Autotests/files/data/QC-2917_data.xls";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",  "maker_car_list3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking prioritizing parent by sales on catalog page with top car")
    public void testPrioritizingParentBySalesOnCatalogPageWithTopCar(String route) {
        openPage(route);
        new Maker_car_list_page_Logic().checkCorrectDisplayParentWithExcel(dataFile);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
