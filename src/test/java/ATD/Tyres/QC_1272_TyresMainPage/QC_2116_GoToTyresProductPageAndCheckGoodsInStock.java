package ATD.Tyres.QC_1272_TyresMainPage;

import ATD.Tyres_page_Logic;
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

public class QC_2116_GoToTyresProductPageAndCheckGoodsInStock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test Checks Transition To Product Page From Tyres Top Block And Checking The Product On A Stock")
    public void testGoToProductPageFromTyresTopBlockAndCheckingProductsOnAStock(String route) {
        openPage(route);
        new Tyres_page_Logic().clickTyresInTopBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

