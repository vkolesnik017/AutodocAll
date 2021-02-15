package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Main_page_Logic;
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
import static com.codeborne.selenide.Selenide.refresh;

public class QC_3132 {
    Product_page_Logic productPage = new Product_page_Logic();
    Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product80");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCar(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("9bBh1J0H1S0");
    }

    @DataProvider(name = "routeSecond")
    Object[] dataProviderSecond() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product81");
    }

    @Test(dataProvider = "routeSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCarSecond(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("XZzIO0DiqaM");
    }

    @DataProvider(name = "routeThird")
    Object[] dataProviderThird() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product82");
    }

    @Test(dataProvider = "routeThird")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCarThird(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("_2yoa51LPzs");
    }

    @DataProvider(name = "routeFourth")
    Object[] dataProviderFourth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product83");
    }

    @Test(dataProvider = "routeFourth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCarFourth(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("B0CBL_HVJ14");
    }

    @DataProvider(name = "routeFifth")
    Object[] dataProviderFifth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product84");
    }

    @Test(dataProvider = "routeFifth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCarFifth(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("XsOt7En8oYU");
    }

    @DataProvider(name = "routeSixth")
    Object[] dataProviderSixth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product85");
    }

    @Test(dataProvider = "routeSixth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCarSixth(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("EAj2Iyn_-Hw");
    }

    @DataProvider(name = "routeSeventh")
    Object[] dataProviderSeventh() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product86");
    }

    @Test(dataProvider = "routeSeventh")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCarSeventh(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("_MX_EhPlaW4");
    }

    @DataProvider(name = "routeEighth")
    Object[] dataProviderEighth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product87");
    }

    @Test(dataProvider = "routeEighth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCarEighth(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("hJ5FSb3mbhQ");
    }

    @DataProvider(name = "routeNinth")
    Object[] dataProviderNinth() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product88");
    }

    @Test(dataProvider = "routeNinth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video with selected car on product page")
    public void testCheckDisplayingOfRidexVideoWithSelectedCarNinth(String route) {
        openPage(route);
        String idOfCurrentVideo = productPage.
                chooseBrandInHorizontalCarSelector("BMW")
                .selectModelInHorizontalCarSelector("3999").getIdOfCurrentVideoFile();
        mainPage.displayAllCategoriesInHeader();
        refresh();
        productPage
                .checkChangeOfCurrentVideo(idOfCurrentVideo)
                .presenceVideoBlock()
                .checkIdOfVideoFile("R1DgJd04VuY");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
