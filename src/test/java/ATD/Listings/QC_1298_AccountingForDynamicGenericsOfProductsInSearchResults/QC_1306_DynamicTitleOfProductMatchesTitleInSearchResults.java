package ATD.Listings.QC_1298_AccountingForDynamicGenericsOfProductsInSearchResults;

import ATD.Search_page_Logic;
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

public class QC_1306_DynamicTitleOfProductMatchesTitleInSearchResults {
    private String generic = "Glühlampe, Abbiegescheinwerfer";
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search29");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checks the dynamic name of the product on the product page matches the name in the search results")
    public void testCheckDynamicTitleOfProductMatchesTitleInSearchResults(String route) {
        openPage(route);

        new Search_page_Logic()
        .presenceOfTecDocListing().checkingApplicabilityOfProductForSelectedGeneric(generic);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
