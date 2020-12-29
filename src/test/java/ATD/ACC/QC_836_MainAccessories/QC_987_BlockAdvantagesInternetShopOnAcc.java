package ATD.ACC.QC_836_MainAccessories;

import ATD.Index_accessories_page_Logic;
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

public class QC_987_BlockAdvantagesInternetShopOnAcc {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence block Advantages and information popup after hover on tab number 2-3")
    public void testCheckPresenceAdvantagesBlockAndInformationPopup(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkingPresenceAdvantagesBlockAndInformationPopup();
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
