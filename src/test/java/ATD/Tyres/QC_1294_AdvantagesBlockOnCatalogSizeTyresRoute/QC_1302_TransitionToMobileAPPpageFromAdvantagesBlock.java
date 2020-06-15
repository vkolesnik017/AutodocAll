package ATD.Tyres.QC_1294_AdvantagesBlockOnCatalogSizeTyresRoute;


import ATD.DataBase;
import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1302_TransitionToMobileAPPpageFromAdvantagesBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_type_list,tyres_type_list2,tyres_type_list3,tyres_type_list4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To MobileAPP Page From Advantages Block")
    public void testTransitionToMobileAPPpageFromAdvantagesBlock(String route) throws SQLException {
        openPage(route);
        new Tyres_page_Logic().clickMobileAppLink();
        waitingWhileLinkBecomeExpected(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticMobileApp"));
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}