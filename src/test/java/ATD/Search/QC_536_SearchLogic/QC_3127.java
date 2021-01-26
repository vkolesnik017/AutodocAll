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

public class QC_3127 {

    Main_page_Logic mainPage = new Main_page_Logic();
    private String value = "bremsscheibe ATE";

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
    @Description(value = "test Check Transition to search listing With Brand And Generic")
    public void testCheckTransitionWithBrandAndGeneric(String route) {
        openPage(route);
        mainPage.useSearch(value).presenceBrandAndGenericInProductTitle("ATE", "bremsscheibe");
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test Check Transition to search listing With Brand And Generic")
    public void testCheckTransitionWithBrandAndGenericLKW(String route) {
        openPage(route);
        mainPage.useSearch(value).presenceBrandAndGenericInProductTitle("ATE", "bremsscheibe");
    }

    @DataProvider(name = "routeMoto")
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "main");
    }

    @Test(dataProvider = "routeMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test Check Transition to search listing With Brand And Generic")
    public void testCheckTransitionWithBrandAndGenericMoto(String route) {
        openPage(route);
        mainPage.useSearch(value).presenceBrandAndGenericInProductTitle("ATE", "bremsscheibe");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
