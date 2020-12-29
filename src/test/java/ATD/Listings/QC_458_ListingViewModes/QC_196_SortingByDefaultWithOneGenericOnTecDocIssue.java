package ATD.Listings.QC_458_ListingViewModes;

import ATD.Category_car_list_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_196_SortingByDefaultWithOneGenericOnTecDocIssue {
    List<String> expectedGenerics = Arrays.asList("Bremsscheibe");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test check sorting on the current issue by default with one generic")
    public void testCheckSortingByDefaultWithOneGenericOnTecDocIssue(String route) {
        openPage(route);
        new Category_car_list_page_Logic()
                .checkTecDocListing(expectedGenerics);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
