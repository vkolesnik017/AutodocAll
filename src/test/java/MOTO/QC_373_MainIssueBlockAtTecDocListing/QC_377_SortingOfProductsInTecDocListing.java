package MOTO.QC_373_MainIssueBlockAtTecDocListing;

import ATD.Moto_Category_car_list_page_Logic;
import ATD.SetUp;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_377_SortingOfProductsInTecDocListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list2");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks sorting Of products in TecDoc listing")
    public void testChecksSortingOfProductsInTecDocListing(String route) {
        openPage(route);

        new Moto_Category_car_list_page_Logic().checkSortingPrice();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
