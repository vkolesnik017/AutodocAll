package ATD.Search.QC_536_SearchLogic;

import ATD.Main_page_Logic;
import ATD.Search_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;

public class QC_2666 {
    private String request = "RIDEX";
    Search_page_Logic searchPage = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list22");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test check availability of additional results when searching by brand")
    public void testCheckAvailabilityOfAdditionalResults(String route) {
        openPage(route);
        new Main_page_Logic().useSearch(request).displayOfAdditionResultsLabel("Ergebnisse für RIDEX für andere Fahrzeuge\n10000 Treffer gefunden");
        long sumOfMainProduct = searchPage.getSumOfMainProducts();
        long sumOfRecommendedProduct = searchPage.getSumOfRecommendedProducts();
        Assert.assertEquals(sumOfMainProduct + sumOfRecommendedProduct, 10);
    }
}
