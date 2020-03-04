package ATD.General_Common.QC_1554_Footer;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_1555_ApplicationInstallBanner {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Flaky
    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route", enabled = true)
    @Description(value = "Test check App installation links")
    public void checkingAppLinks(String route) {
        openPage(route);
        new Main_page_Logic().checkApplicationLinks("https://apps.apple.com/US/app/id1014949597?mt=8");
    }
}
