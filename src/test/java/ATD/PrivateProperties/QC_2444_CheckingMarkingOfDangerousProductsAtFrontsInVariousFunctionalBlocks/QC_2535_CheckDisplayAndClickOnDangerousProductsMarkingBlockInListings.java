package ATD.PrivateProperties.QC_2444_CheckingMarkingOfDangerousProductsAtFrontsInVariousFunctionalBlocks;

import ATD.Category_oen_Page_Logic;
import ATD.LKW_Category_car_list_page_Logic;
import ATD.Listing_chemicals_Page_Logic;
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

public class QC_2535_CheckDisplayAndClickOnDangerousProductsMarkingBlockInListings {
    private LKW_Category_car_list_page_Logic carListPage = new LKW_Category_car_list_page_Logic();
    private Listing_chemicals_Page_Logic chemicalsPage = new Listing_chemicals_Page_Logic();
    private Category_oen_Page_Logic categoryOenPage = new Category_oen_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list36,lkw_category_car_list37,lkw_search12");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check the display and click on the dangerous products marking block on the listings")
    public void testCheckDisplayAndClickOnDangerousProductsMarkingBlockInListings(String route) {
        openPage(route);
        String signalWord = carListPage.presenceOfDangerousProducts().getSignalWordFromFirstDangerousProduct(1);
        carListPage.clickOnDangerousLabel(1, signalWord);
    }


    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list44,search34");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check the display and click on the dangerous products marking block on the listings")
    public void testCheckDisplayAndClickOnDangerousProductsMarkingBlockInListingsMain(String route) {
        openPage(route);
        String signalWord = carListPage.presenceOfDangerousProducts().getSignalWordFromFirstDangerousProduct(0);
        carListPage.clickOnDangerousLabel(0, signalWord);
    }

    @DataProvider(name = "routesChemical", parallel = true)
    Object[] dataProviderChemical() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals4");
    }

    @Test(dataProvider = "routesChemical")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check the display and click on the dangerous products marking block on the listings")
    public void testCheckDisplayAndClickOnDangerousProductsMarkingBlockInListingsChemical(String route) {
        openPage(route);
        String signalWord = chemicalsPage.presenceOfDangerousProducts().getSignalWordFromFirstDangerousProduct(0);
        carListPage.clickOnDangerousLabel(0, signalWord);
    }

    @DataProvider(name = "routesOEN", parallel = true)
    Object[] dataProviderOEN() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen17,category_oen18,category_oen19");
    }

    @Test(dataProvider = "routesOEN")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check the display and click on the dangerous products marking block on the listings")
    public void testCheckDisplayAndClickOnDangerousProductsMarkingBlockInListingsOEN(String route) {
        openPage(route);
        String signalWord = categoryOenPage.presenceOfDangerousProducts().getSignalWordFromFirstDangerousProduct(0);
        carListPage.clickOnDangerousLabel(0, signalWord);

    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
