package ATD.Search.QC_536_SearchLogic;

import ATD.Main_page_Logic;
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

public class QC_3128 {

    Main_page_Logic mainPage = new Main_page_Logic();
    private String generic = "Akkuschrauber";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test check searching by ACC generic")
    public void testSearchByACCGeneric(String route) {
        openPage(route);
        mainPage.useSearch(generic).checkListingWithSelectedGeneric(generic);
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test check searching by ACC generic")
    public void testSearchByACCGenericLKW(String route) {
        openPage(route);
        mainPage.useSearch(generic).checkListingWithSelectedGeneric(generic);
    }

    @DataProvider(name = "routeMOTO")
    Object[] dataProviderMOTO() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "main");
    }

    @Test(dataProvider = "routeMOTO")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test check searching by ACC generic")
    public void testSearchByACCGenericMOTO(String route) {
        openPage(route);
        mainPage.useSearch(generic).checkListingWithSelectedGeneric(generic);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
