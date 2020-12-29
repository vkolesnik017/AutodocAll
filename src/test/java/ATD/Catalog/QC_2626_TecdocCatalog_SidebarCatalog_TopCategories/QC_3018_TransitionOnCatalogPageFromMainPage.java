package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Main_page_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_3018_TransitionOnCatalogPageFromMainPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking transition on catalog page from main page")
    public void testTransitionOnCatalogPageFromMainPage(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().clickOnMoreSparePartsLink();
        String actualPage = url();
        String expectedPage = new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "categories");
        Assert.assertEquals(actualPage, expectedPage);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
