package ATD.Search.QC_548_SearchTooltips;

import ATD.Main_page_Logic;
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

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

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

    @Test(dataProvider = "route", enabled = false) // false, details in comment QC_554
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Going to listing through a generic synonym. All products have the expected generic in title")
    public void testGoToListingFromSynonymTooltipSearch(String route) {
        open(categoriesSynonymsAws.urlWithSynonymTurboAndShopDE);
        new Login_aws().loginInAws();
        String generic = categoriesSynonymsAws.getRandomGenericFromSearchBlock();
        String synonymForGeneric = categoriesSynonymsAws.getSynonymByGenericInSearchBlock(generic);
        System.out.println(generic + " >>> " + synonymForGeneric);
        open(route);
        new Main_page_Logic().inputTextInSearchBar(synonymForGeneric)
                .clickTooltipInSearchByExactText(synonymForGeneric)
                .checksProductTitlesContainExpectedTextGoingAllPagination(generic);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }

}
