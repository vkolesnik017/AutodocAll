package ATD.FiltersSorting.QC_179_FiltersSorting_byGeneric;

import ATD.Category_car_list_page_Logic;
import ATD.Listing_instruments_page_Logic;
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

public class QC_2766 {

    private Category_car_list_page_Logic carListPage = new Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of generic block")
    public void testCheckHeadlineOfGenericBlock(String route) {
        openPage(route);
        new Listing_instruments_page_Logic().checkHeadlineOfGenericsBlockInSideBar("WÄHLEN SIE DIE GEWÜNSCHTE KATEGORIE");
    }

    @DataProvider(name = "routesInstrument", parallel = true)
    Object[] dataProviderInstrument() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments6,listing_chemicals3");
    }

    @Test(dataProvider = "routesInstrument")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of generic block")
    public void testCheckHeadlineOfGenericBlockInstrument(String route) {
        openPage(route);
        new Listing_instruments_page_Logic().checkHeadlineOfGenericsBlock("WÄHLEN SIE DIE GEWÜNSCHTE KATEGORIE");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list44,lkw_search18");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of generic block")
    public void testCheckHeadlineOfGenericBlockLKW(String route) {
        openPage(route);
        new Listing_instruments_page_Logic().checkHeadlineOfGenericsBlock("WÄHLEN SIE DIE GEWÜNSCHTE TEILE-KATEGORIE");
    }

    @DataProvider(name = "routesSearch", parallel = true)
    Object[] dataProviderSearch() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list53,search46");
    }

    @Test(dataProvider = "routesSearch")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check headline of generic block")
    public void testCheckHeadlineOfGenericBlockSearch(String route) {
        openPage(route);
        new Listing_instruments_page_Logic().checkHeadlineOfGenericsBlock("WÄHLEN SIE DIE GEWÜNSCHTE TEILE-KATEGORIE");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
