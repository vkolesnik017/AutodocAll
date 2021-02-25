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

public class QC_3406 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list17,moto_category_car_list_model9");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Interaction of the brand filter and generic filter")
    public void testChecksInteractionOfBrandAndGenericFilters(String route) {
        openPage(route);
        new Moto_Category_car_list_page_Logic()
                .setGenericFilterByTitle("Lambdasonde")
                .appearsOfLoader()
                .setBrandById("100015")
                .appearsOfLoader()
                .checkListingWithSelectedGenericAndBrand("Lambdasonde", "RIDEX");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
