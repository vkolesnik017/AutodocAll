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

public class QC_2669 {

    private String request = "95 667 811 RIDEX";
    private String oenNum = "95667811";
    private String brand = "RIDEX";

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
    @Description(value = "The test check correctness of issue by searching OEN number and brand")
    public void testCheckCorrectnessOfIssueBySearchingOenAndBrand(String route) {
        openPage(route);
        new Main_page_Logic().useSearch(request).presenceOfTecDocListing().presenceOenNumInProductPage(brand, oenNum);
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test check correctness of issue by searching OEN number and brand")
    public void testCheckCorrectnessOfIssueBySearchingOenAndBrandLKW(String route) {
        openPage(route);
        new Main_page_Logic().useSearch(request).presenceOfTecDocListing().presenceOenNumInProductPage(brand, oenNum);
    }

    @DataProvider(name = "routeMOTO")
    Object[] dataProviderMOTO() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "main");
    }

    @Test(dataProvider = "routeMOTO")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "The test check correctness of issue by searching OEN number and brand")
    public void testCheckCorrectnessOfIssueBySearchingOenAndBrandMOTO(String route) {
        openPage(route);
        new Main_page_Logic().useSearch(request).presenceOfTecDocListing().presenceOenNumInProductPage(brand, oenNum);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
