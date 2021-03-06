package ATD.Search.QC_536_SearchLogic;

import ATD.Main_page_Logic;
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

import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_540 {
    Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "The test verifies redirect to tires catalog after search by text reifen")
    public void testSearchByValueReifen(String route) throws SQLException {
        open(route);
        mainPage.useSearch("Reifen");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "tyres"));
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test verifies redirect to tires catalog after search by text reifen")
    public void testSearchByValueReifenLKW(String route) throws SQLException {
        open(route);
        mainPage.useSearch("Reifen");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "tyres"));
    }

    @DataProvider(name = "routeMoto")
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routeMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test verifies redirect to tires catalog after search by text reifen")
    public void testSearchByValueReifenMoto(String route) throws SQLException {
        open(route);
        mainPage.useSearch("Reifen");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "tyres"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
