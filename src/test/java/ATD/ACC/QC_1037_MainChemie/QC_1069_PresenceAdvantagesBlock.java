package ATD.ACC.QC_1037_MainChemie;

import ATD.Index_chemicals_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1069_PresenceAdvantagesBlock {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking block Advantages and popup describing the Advantage")
    public void testCheckingPresenceOfAdvantagesBlockAndAdvantageDescription(String route) {
        openPage(route);
        new Index_chemicals_page_Logic().checkingPresenceOfAdvantagesBlockAndAdvantageDescription();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
