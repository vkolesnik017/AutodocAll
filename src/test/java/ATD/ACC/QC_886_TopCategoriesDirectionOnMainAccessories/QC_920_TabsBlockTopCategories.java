package ATD.ACC.QC_886_TopCategoriesDirectionOnMainAccessories;

import ATD.Index_accessories_page_Logic;
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

public class QC_920_TabsBlockTopCategories {

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
    @Description(value = "Test Checks the number of categories in each tab of the top accessories block")
    public void testCheckNumberOfCategoriesInEachTabBlockTopAcc(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkNumberOfCategoriesInEachTabBlockTopAcc();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
