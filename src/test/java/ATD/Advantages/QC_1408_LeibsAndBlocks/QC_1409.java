package ATD.Advantages.QC_1408_LeibsAndBlocks;

import ATD.Product_page_Logic;
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


public class QC_1409 {


    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "product")
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product11");
    }

    @Test(dataProvider = "product")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Show labels with payment methods and benefits")
    public void testShowLabelsPaymentAndAdvantages(String route) {
        openPage(route);
        product_page_logic.checkLabelsPaymenMethodstAndAdvantages();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}