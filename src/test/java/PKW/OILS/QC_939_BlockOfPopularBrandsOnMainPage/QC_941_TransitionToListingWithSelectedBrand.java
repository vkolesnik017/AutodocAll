package PKW.OILS.QC_939_BlockOfPopularBrandsOnMainPage;

import Common.DataBase;
import PKW.Motoroil_page_Logic;
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
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_941_TransitionToListingWithSelectedBrand {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to Listing with selected brand")
    public void testChecksTransitionToListingWithSelectedBrand(String route) throws SQLException {
        openPage(route);

        new Motoroil_page_Logic()
                .selectBrandInBlock(24);
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil_brand3"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
