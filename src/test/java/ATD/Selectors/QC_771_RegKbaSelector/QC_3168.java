package ATD.Selectors.QC_771_RegKbaSelector;

import ATD.Motoroil_page_Logic;
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

public class QC_3168 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("ATD").setUpShopsWithSubroute("prod", "DE,DK,FI,FR,IT,NL,NO,PT,SE,CH", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of KBA selector")
    public void testChecksPresenceOfKbaSelector(String route) {
        openPage(route);
        new Motoroil_page_Logic().presenceOfKbaSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
