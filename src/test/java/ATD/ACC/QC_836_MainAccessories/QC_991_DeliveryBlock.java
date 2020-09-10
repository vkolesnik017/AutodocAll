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


public class QC_991_DeliveryBlock {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks the Presence of the delivery service block")
    public void testCheckingPresenceOfDeliveryBlock(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkingPresenceOfDeliveryBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }



}
