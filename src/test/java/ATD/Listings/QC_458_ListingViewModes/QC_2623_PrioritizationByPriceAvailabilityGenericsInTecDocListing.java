package ATD.Listings.QC_458_ListingViewModes;

import ATD.Category_car_list_page_Logic;
import AWS.CatalogCategories_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2623_PrioritizationByPriceAvailabilityGenericsInTecDocListing {
    private List<String> genericsAws = new ArrayList<>();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Owner(value = "Kolesnik")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "test checking prioritization by price, availability and generics within a tecdoc listing")
    public void testCheckPrioritizationByPriceAvailabilityGenericsInTecDocListing(String route) throws SQLException {
        genericsAws = new CatalogCategories_aws("prod").getGenericsFromAws();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list47"));
        new Category_car_list_page_Logic()
                .checkTecDocListing(genericsAws);

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
