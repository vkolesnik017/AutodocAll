package ATD.ACC.QC_1064_BlockTopProductsOnMainChemie;

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
import static com.codeborne.selenide.Selenide.close;


public class QC_1065_BlockTopProducts {

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
    @Description(value = "Test Checks the visibility of the top products block and quantity of goods in a block ")
    public void testCheckVisibilityBlockAndQuantityProducts(String route) {
        openPage(route);
        new Index_chemicals_page_Logic().checkingOfTopProductBlock()
                .checkingTheQuantityOfGoods(6);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }


}
