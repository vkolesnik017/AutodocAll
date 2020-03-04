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

public class QC_1565_DisplayingOfNon_validEmailTooltip {

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
    @Description(value = "Test checks displaying of non-valid email tooltip")
    public void checkValidationField(String route) {
        openPage(route);
        new Main_page_Logic().checkTooltipForInvalidEmail();

    }
}