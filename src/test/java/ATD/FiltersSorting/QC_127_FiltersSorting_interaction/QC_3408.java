package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;

import ATD.Moto_Category_car_list_page_Logic;
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

public class QC_3408 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model8,moto_category_car_list16");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Interaction of the brand filter and the plinth filter")
    public void testChecksInteractionOfBrandAndPlinthFilters(String route) {
        openPage(route);
        new Moto_Category_car_list_page_Logic()
                .setPlinthFilterByTitle("H1")
                .appearsOfLoader()
                .setBrandById("100015")
                .appearsOfLoader()
                .checkListingWithSelectedPlinthAndBrand("H1", "RIDEX");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
