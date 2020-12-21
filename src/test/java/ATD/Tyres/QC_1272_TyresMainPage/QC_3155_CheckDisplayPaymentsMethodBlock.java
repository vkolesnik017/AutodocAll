package ATD.Tyres.QC_1272_TyresMainPage;

import ATD.Tyres_maker_page_Logic;
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

public class QC_3155_CheckDisplayPaymentsMethodBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking the presence of a block of payment methods")
    public void testCheckCheckDisplayPaymentsMethodBlock(String route) {
        openPage(route);

        new Tyres_maker_page_Logic().checkPaymentMethodsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
