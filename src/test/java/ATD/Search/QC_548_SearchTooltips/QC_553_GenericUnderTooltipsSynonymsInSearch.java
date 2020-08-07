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

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_553_GenericUnderTooltipsSynonymsInSearch {

    private String searchText = "turbo";

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
    @Description(value = "Generics is displayed under synonyms in the search tooltips")
    public void testGenericUnderTooltipsSynonymsInSearch(String route) {
        openPage(route);
        new Main_page_Logic().checkingThatGenericsAreUnderSynonymsInSearchTooltips(searchText);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
