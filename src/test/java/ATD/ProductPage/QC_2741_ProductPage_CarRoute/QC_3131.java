package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Product_page_Logic;
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

public class QC_3131 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product30");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideo(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("B0CBL_HVJ14");
    }

    @DataProvider(name = "routeSecond")
    Object[] dataProviderSecond() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product15");
    }

    @Test(dataProvider = "routeSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoSecond(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("XsOt7En8oYU");
    }

    @DataProvider(name = "routeThird")
    Object[] dataProviderThird() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product72");
    }

    @Test(dataProvider = "routeThird")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoThird(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("9bBh1J0H1S0");
    }

    @DataProvider(name = "routeFourth")
    Object[] dataProviderFourth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product73");
    }

    @Test(dataProvider = "routeFourth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoFourth(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("XZzIO0DiqaM");
    }

    @DataProvider(name = "routeFifth")
    Object[] dataProviderFifth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product74");
    }

    @Test(dataProvider = "routeFifth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoFifth(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("_2yoa51LPzs");
    }

    @DataProvider(name = "routeSixth")
    Object[] dataProviderSixth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product75");
    }

    @Test(dataProvider = "routeSixth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoSixth(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("EAj2Iyn_-Hw");
    }

    @DataProvider(name = "routeSeventh")
    Object[] dataProviderSeventh() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product76");
    }

    @Test(dataProvider = "routeSeventh")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoSeventh(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("_MX_EhPlaW4");
    }

    @DataProvider(name = "routeEighth")
    Object[] dataProviderEighth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product77");
    }

    @Test(dataProvider = "routeEighth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoEighth(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("hJ5FSb3mbhQ");
    }

    @DataProvider(name = "routeNinth")
    Object[] dataProviderNinth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product78");
    }

    @Test(dataProvider = "routeNinth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoNinth(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("R1DgJd04VuY");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
