package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;

import ATD.Moto_Category_car_list_model_page_Logic;
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

public class QC_3399 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model11,moto_category_car_list19");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checks Sorting of TecDoc listing by default")
    public void testCheckSortingInTecDocListingByDefault(String route) {
        openPage(route);

        new Moto_Category_car_list_model_page_Logic()
                .checkSortingOfProductByAvailableAndPrice();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
