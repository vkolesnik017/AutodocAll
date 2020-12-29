package ATD.Associated.QC_790_AlternativeProductsOutputTecdoc;


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

public class QC_791_MatchElementsInAlternativeBlockAndInProductPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product23");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Match Elements In Alternative Block And In Product Page")
    public void testMatchElementsInAlternativeBlockAndInProductPage(String route) {
        openPage(route);
        new Product_page_Logic().checkLinkInAlternativeBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
