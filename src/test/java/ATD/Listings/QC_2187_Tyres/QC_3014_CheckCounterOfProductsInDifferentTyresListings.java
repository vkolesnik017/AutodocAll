package ATD.Listings.QC_2187_Tyres;

import ATD.Tyres_dimension_page_Logic;
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

public class QC_3014_CheckCounterOfProductsInDifferentTyresListings {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension12,tyres_dimension10,tyres_dimension16");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check counter of products in different tyres listings")
    public void testCheckCounterOfProductsInDifferentTyresListings(String route) {
        openPage(route);
        new Tyres_dimension_page_Logic().defaultCountOfProduct(4).checkCounterOnDecreaseAndIncrease().minCountOfBuyProduct(2);
    }

    @DataProvider(name = "routesDimension", parallel = true)
    Object[] dataProviderDimension() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension15");
    }

    @Test(dataProvider = "routesDimension")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check counter of products in different tyres listings")
    public void testCheckCounterOfProductsInDifferentTyresListingsDimension(String route) {
        openPage(route);
        new Tyres_dimension_page_Logic().defaultCountOfProduct(2).checkCounterOnDecreaseAndIncrease().minCountOfBuyProduct(2);
    }

    @DataProvider(name = "routesSecond", parallel = true)
    Object[] dataProviderSecond() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension4");
    }

    @Test(dataProvider = "routesSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check counter of products in different tyres listings")
    public void testCheckCounterOfProductsInDifferentTyresListingsSecond(String route) {
        openPage(route);
        new Tyres_dimension_page_Logic().defaultCountOfProduct(1).checkCounterOnDecreaseAndIncrease().minCountOfBuyProduct(1);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
