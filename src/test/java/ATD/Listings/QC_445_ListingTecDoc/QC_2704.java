package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Listing_page_Logic;
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

public class QC_2704 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "de", "main","category_oen,category_car_list,tyre_form,listing_accessories3,listing_instruments,listing_chemicals,search6");
    }

    @DataProvider(name = "routesLkw", parallel = true)
    Object[] dataProviderLkw() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list38,lkw_search6");
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list,moto_search");
    }
    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test check work scroll progress bar")
    public void testCheckWorkScrollProgressBar(String route) {
        openPage(route);
      new Listing_page_Logic().checkWorkScrollProgressBar();
    }

    @Test(dataProvider = "routesLkw")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test check work scroll progress bar")
    public void testCheckWorkScrollProgressBarForLkw(String route) {
        openPage(route);
        new Listing_page_Logic().checkWorkScrollProgressBar();
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test check work scroll progress bar")
    public void testCheckWorkScrollProgressBarForMoto(String route) {
        openPage(route);
        new Listing_page_Logic().checkWorkScrollProgressBar();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
