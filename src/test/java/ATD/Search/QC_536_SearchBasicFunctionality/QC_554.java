package ATD.Search.QC_536_SearchBasicFunctionality;

import ATD.Search_page_Logic;
import AWS.CategoriesSynonyms_aws;
import AWS.Login_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_554 {

    private CategoriesSynonyms_aws categoriesSynonymsAws = new CategoriesSynonyms_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list6");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Going to listing through a generic synonym. All products have the expected generic in title")
    public void testGoToListingFromSynonymTooltipSearch(String route) {
        new Login_aws().loginInAwsWithOpen();
        open(categoriesSynonymsAws.urlWithSynonymTurboAndShopDE);
        ArrayList<String> synonymsFromAWS = categoriesSynonymsAws.getGenericSynonymsAWS("2234");
        openPage(route);
        new Search_page_Logic().checkGenericSynonyms("lader", synonymsFromAWS);
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Going to listing through a generic synonym. All products have the expected generic in title")
    public void testGoToListingFromSynonymTooltipSearchLKW(String route) {
        new Login_aws().loginInAwsWithOpen();
        open(categoriesSynonymsAws.urlWithSynonymTurboAndShopDE);
        ArrayList<String> synonymsFromAWS = categoriesSynonymsAws.getGenericSynonymsAWS("2234");
        open(route);
        new Search_page_Logic().checkGenericSynonyms("lader", synonymsFromAWS);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
