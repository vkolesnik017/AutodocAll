package ATD.Listings.QC_2187_TyresListing;

import Common.SetUp;
import ATD.TyresListing_page_Logic;
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

public class QC_2064 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form,tyres_season,offroad_tyres_brand,tyres_group_season_brand," +
                "tyres_size3,tyres_dimension3,tyres_season_size,tyres_season_dimension,tyres_brand_size2,tyres_brand_dimension");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test check presence of a block of the main distribution of tires")
    public void testPresenceBlockOfTheMainDistributionOfTires(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkPresenceProductListing();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }}