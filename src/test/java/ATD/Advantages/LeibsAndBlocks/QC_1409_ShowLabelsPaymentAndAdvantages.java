package ATD.Advantages.LeibsAndBlocks;

import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;


public class QC_1409_ShowLabelsPaymentAndAdvantages {


    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "product")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product11");
    }

    @Test(dataProvider = "product")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Show labels with payment methods and benefits")
    public void testShowLabelsPaymentAndAdvantages(String route) {
        openPage(route);
        product_page_logic.testShowLabelsPaymentAndAdvantages();
    }
}