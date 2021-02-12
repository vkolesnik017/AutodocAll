package ATD.Listings.QC_433_ListingSearch;

import ATD.Category_car_list_page_Logic;
import ATD.Search_page_Logic;
import AWS.PriceProductDescription_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3121 {
    Category_car_list_page_Logic carListPage = new Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search48");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test Checking the display of the price per meter item")
    public void testCheckDisplayOfPricePerMeterItem(String route) {
        openPage(route);
        new Search_page_Logic().presenceOfProductWithPricePerMeterLabel();
        List<String> idOfProduct = carListPage.getIdOfProductTableView();
        List<String> priceTitle = carListPage.getPriceTitleTableView();
        new PriceProductDescription_aws().openPriceDescriptionPage().checkPresenceOfPriceTitle(idOfProduct, priceTitle);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
