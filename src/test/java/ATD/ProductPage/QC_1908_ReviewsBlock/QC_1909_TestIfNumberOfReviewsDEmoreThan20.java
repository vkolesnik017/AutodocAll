package ATD.ProductPage.QC_1908_ReviewsBlock;


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

public class QC_1909_TestIfNumberOfReviewsDEmoreThan20 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product30");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks If Number Of Reviews DE more Than 20")
    public void testIfNumberOfReviewsDEmoreThan20(String route) {
        openPage(route);
        new Product_page_Logic().checkIfReviewsDEmoreThan20();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
