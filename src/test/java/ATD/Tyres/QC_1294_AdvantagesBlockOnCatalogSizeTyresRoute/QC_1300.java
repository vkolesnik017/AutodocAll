package ATD.Tyres.QC_1294_AdvantagesBlockOnCatalogSizeTyresRoute;


import Common.DataBase;
import Common.SetUp;
import ATD.Tyres_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_1300 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_type_list,tyres_type_list2,tyres_type_list3,tyres_type_list4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To Club Page From Advantages Block")
    public void testTransitionToClubPageFromAdvantagesBlock(String route) throws SQLException {
        openPage(route);
        new Tyres_page_Logic().clickVideoLink();
        switchTo().window(1);
        waitingWhileLinkBecomeExpected(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "club_main", "club_manuals_home"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
