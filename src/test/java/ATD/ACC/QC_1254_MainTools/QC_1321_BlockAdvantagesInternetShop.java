package ATD.ACC.QC_1254_MainTools;

import ATD.Index_instruments_page_Logic;
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

public class QC_1321_BlockAdvantagesInternetShop {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence block Advantages and information popup after hover on tab number 2-3 ")
    public void testCheckPresenceAdvantagesBlockAndInformationPopup(String route) {
        openPage(route);
        new Index_instruments_page_Logic().checkingPresenceAdvantagesBlockAndInformationPopup();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
