package ATD.General_Common.QC_1554_Footer;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1558_DroplistCountryBlock {

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
    @Description(value = "Test check country dropdown and transitions")
    public void checkingDropdownCountry(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().checkOpenAndCloseDroplistCountries()
                             .checkingCountriesSubscription();
    }
    @AfterMethod
    private void teatDown() {
        close();
    }
}
