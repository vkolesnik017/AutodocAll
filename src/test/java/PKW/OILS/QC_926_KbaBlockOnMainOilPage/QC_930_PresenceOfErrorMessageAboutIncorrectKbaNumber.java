package PKW.OILS.QC_926_KbaBlockOnMainOilPage;

import PKW.Motoroil_page_Logic;
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

public class QC_930_PresenceOfErrorMessageAboutIncorrectKbaNumber {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of KBA selector on main page")
    public void testChecksPresenceOfErrorMessageAboutIncorrectKbaNumber(String route) {
        openPage(route);

        new Motoroil_page_Logic()
                .presenceOfKbaSelector()
                .selectVehicleInKbaSelectorWithIncorrectNumber("64", "sds")
                .visibilityKbaErrorToolTip();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
