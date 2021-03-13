package ATD.Catalog_Mark.QC_2568_MarkPages;

import ATD.Tyres_model_page_Logic;
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


public class QC_3405 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_model");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "test Checking the block of top models on the tires routes")
    public void testCheckingBlockTopModelsOnTiresRoutes(String route) {
        openPage(route);
        new Tyres_model_page_Logic().checkPresenceTopModelBlock()
                .checkingLocationTopModelsBlock()
                .checkTransitionOnAllModelsInTopModelsBlock();
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}