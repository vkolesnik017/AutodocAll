package PKW.ACC.QC_994_MainBlockCategoriesOnMainPageAcc;

import Common.SetUp;
import PKW.Index_accessories_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_995_TitleMainBlockCategories {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_accessories,index_accessories_group");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks presence main title page")
    public void testCheckingPresenceMainTitle(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkingPresenceTitleNamePage();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
