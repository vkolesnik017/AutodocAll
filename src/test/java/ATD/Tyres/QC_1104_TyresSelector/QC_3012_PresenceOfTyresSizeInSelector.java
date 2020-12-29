package ATD.Tyres.QC_1104_TyresSelector;

import ATD.Tyres_page_Logic;
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

public class QC_3012_PresenceOfTyresSizeInSelector {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of tyres size in selector")
    public void testCheckPresenceOfTyresSizeInSelector(String route) {
        openPage(route);
        new Tyres_page_Logic()
                .selectWidth("315")
                .selectHeight("80")
                .selectDiameterInSelector("22_5")
                .defaultValuesOfSelector("315", "80", "22_5");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
