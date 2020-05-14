package ATD.ACC.QC_836_MainAccessories;

import ATD.Index_accessories_page_Logic;
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


public class QC_991_DeliveryBlock {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks the visibility of the delivery service block on the site page")
    public void testCheckingVisibilityOfDeliveryBlock(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkingVisibilityOfDeliveryBlock();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }



}
