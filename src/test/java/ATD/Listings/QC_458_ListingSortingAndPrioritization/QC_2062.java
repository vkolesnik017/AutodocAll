package ATD.Listings.QC_458_ListingSortingAndPrioritization;

import ATD.Category_oen_Page_Logic;
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

public class QC_2062 {
    private Category_oen_Page_Logic oenPage = new Category_oen_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen14");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "kolesnik")
    @Description(value = "Checks Ridex prioritization in OEN listing")
    public void testCheckRidexPrioritizationInOENListing(String route) {
        openPage(route);
        int activeRidexProducts = oenPage.getSIzeOfActiveProductsWithBrand("RIDEX");
        oenPage.checkOenListing(activeRidexProducts, "RIDEX");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
