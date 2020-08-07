package ATD.Search.QC_548_SearchTooltips;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_550_GoToListingFromTooltipInSearch {

    private String searchText = "Lenkersatz";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Go to search listing by click tooltip in search")
    public void testGoToListingFromTooltipInSearch(String route) {
        open(route);
        new Main_page_Logic().inputTextInSearchBar(searchText)
                .clickTooltipInSearchByExactText(searchText)
                .verifyNameRouteEqualsSearch()
                .verifyTextInSearchBar(searchText);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
