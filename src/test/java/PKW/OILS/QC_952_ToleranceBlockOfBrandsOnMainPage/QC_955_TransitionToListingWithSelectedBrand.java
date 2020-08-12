package PKW.OILS.QC_952_ToleranceBlockOfBrandsOnMainPage;

import PKW.DataBase;
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
import static ATD.SetUp.setUpBrowser;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_955_TransitionToListingWithSelectedBrand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition to Listing with selected brand")
    public void testChecksTransitionToListingWithSelectedBrand(String route) throws SQLException {
        openPage(route);

        new Motoroil_page_Logic()
                .presenceOfToleranceBlock()
        .selectToleranceLink(5);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil_release"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
