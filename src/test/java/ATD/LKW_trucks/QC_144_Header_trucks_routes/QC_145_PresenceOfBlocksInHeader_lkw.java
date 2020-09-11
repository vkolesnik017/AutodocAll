package ATD.LKW_trucks.QC_144_Header_trucks_routes;

import ATD.LKW_main_page_Logic;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_145_PresenceOfBlocksInHeader_lkw {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks elements on main page LKW")
    public void testChecksElementsOnMainPageLKW(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .checkPagesIsSuccessfulyLoaded()
                .checkingOfVisibilityOfLogoInHeader()
                .checkAppearanceOfHintBlock()
                .checkAppearanceOfBeispielPopUp();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

