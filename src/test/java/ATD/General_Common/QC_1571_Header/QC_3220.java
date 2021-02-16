package ATD.General_Common.QC_1571_Header;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3220 {
    private Main_page_Logic mainPage = new Main_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "EN");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of covid19 icon")
    public void testChecksPresenceOfCovidIcon(String route) {
        openPage(route);
        mainPage.presenceOfCovidIcon("DUE TO THE CURRENT RESTRICTIONS AND TRANSPORTATION LIMITATIONS AS A RESULT OF THE COVID-19 CRISIS, DELIVERY DELAYS MAY OCCUR")
                .closeCovidIcon()
                .absenceOfCovidIcon()
                .clickInstrumentsCategory();
        waitWhileRouteBecomeExpected("index_instruments");
        mainPage.absenceOfCovidIcon();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
