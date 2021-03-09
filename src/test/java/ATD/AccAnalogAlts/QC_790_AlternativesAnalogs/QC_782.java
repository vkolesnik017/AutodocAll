package ATD.AccAnalogAlts.QC_790_AlternativesAnalogs;


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

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_782 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product22");
    }

    @Test(dataProvider = "routes", enabled = false)  //TODO  disappeared Popup - Waiting for a reply from a reporter
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Related Products Block Functionality")
    public void testRelatedProductsBlockFunctionality(String route) {
        new Product_page_Logic().checkRelatedProductPopupClose(route)
                    .checkRelatedProductPopupBack(route)
                    .checkRelatedProductPopupGoToBasket(route)
                    .checkRealatedPopupCategories(route)
                    .checkRelatedPopupOverlay(route);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
