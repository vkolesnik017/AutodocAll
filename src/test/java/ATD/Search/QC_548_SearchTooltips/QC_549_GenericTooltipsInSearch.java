package ATD.Search.QC_548_SearchTooltips;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_549_GenericTooltipsInSearch {

    private String genericName = "lenkersatz";

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
    @Description(value = "The test verifies there are no duplicates in the dropdown menu with tips when entering the generic lenkersatz in search bar")
    public void testGenericTooltipsInSearch(String route) {
        open(route);
        new Main_page_Logic().inputTextInSearchBar(genericName)
                .checkingThatNoDuplicatesInTooltipsToSearch();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
