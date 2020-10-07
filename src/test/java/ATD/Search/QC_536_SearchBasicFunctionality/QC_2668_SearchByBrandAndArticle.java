package ATD.Search.QC_536_SearchBasicFunctionality;

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

public class QC_2668_SearchByBrandAndArticle {

    private String request = "2049 3M";
    private String artNum = "2049";
    private String brand = "3M";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test verifies that at the listing have only products of brand 3M and expected article number")
    public void testSearchByBrandAndArticle(String route) {
        openPage(route);
        new Main_page_Logic().useSearch(request).checkImageOfBrandAtPresenceOfArtAndBrand(brand, artNum);
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test verifies that at the listing have only products of brand 3M and expected article number")
    public void testSearchByBrandAndArticleLKW(String route) {
        openPage(route);
        new Main_page_Logic().useSearch(request).checkImageOfBrandAtPresenceOfArtAndBrand(brand, artNum);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
