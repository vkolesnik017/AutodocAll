package ATD.Search.QC_548_SearchTooltips;

import ATD.Search_page_Logic;
import ATD.SetUp;
import AWS.CategoriesSynonyms_aws;
import AWS.Login_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_554_GoToLisingFromSynonymTooltipInSearch {

    private CategoriesSynonyms_aws categoriesSynonymsAws = new CategoriesSynonyms_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list6");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Going to listing through a generic synonym. All products have the expected generic in title")
    public void testGoToListingFromSynonymTooltipSearch(String route) {
        new Login_aws().loginInAwsWithOpen();
        open(categoriesSynonymsAws.urlWithSynonymTurboAndShopDE);
        String genericName = categoriesSynonymsAws.getGenericNameFromAWS("2234");
        ArrayList<String> synonymsFromAWS = categoriesSynonymsAws.getGenericSynonymsAWS("2234");
        open(route);
        new Search_page_Logic().checkGenericSynonyms(genericName, synonymsFromAWS);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
