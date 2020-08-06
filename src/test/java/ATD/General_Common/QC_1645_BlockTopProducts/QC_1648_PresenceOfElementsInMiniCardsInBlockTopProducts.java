package ATD.General_Common.QC_1645_BlockTopProducts;

import ATD.CommonMethods;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1648_PresenceOfElementsInMiniCardsInBlockTopProducts {

    private CommonMethods commonMethods = new CommonMethods();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] routes() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_maker,group_list,category_maker_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "The test checks presence elements in mini-card in blocks of top products")
    public void testPresenceElementsInMiniCardInBlocksOfTopProducts(String route) {
        openPage(route);
        commonMethods.scrollToBlockOfTopProducts().checksPresenceElementsInMiniCardInBlocksOfTopProducts();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
