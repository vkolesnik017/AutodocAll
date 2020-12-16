package ATD.General_Common.QC_2774_CheckTopSectionsBlockOnMainPage;

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
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_2773_TransitionsByTabInTopSectionsBlockOnMainPage {

    private Main_page_Logic mainPageLogic = new Main_page_Logic();
    private DataBase db = new DataBase("ATD");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks transitions by tab in top sections block on main page")
    public void testChecksTransitionsByTabInTopSectionsBlockOnMainPage(String route) throws SQLException {
        openPage(route);
        mainPageLogic.checkPresenceAllTabInTopBlock()
                .selectTabTopCarBrandsBlock()
                .clickTabLkwIntopBlock();
        switchTo().window(1);
        String urlLkwPage = url().replaceAll("\\/[^\\/]*$", "");
        String expectedLkwUrl = db.getFullRouteByRouteName("subprod", "DE", "lkw_main");
        Assert.assertEquals(urlLkwPage, expectedLkwUrl);
        closeWindow();
        switchTo().window(0);
        mainPageLogic.clickTabMotoInTopBlock();
        switchTo().window(1);
        String urlMotoPage = url().replaceAll("\\/[^\\/]*$", "");
        String expectedMotoUrl = db.getFullRouteByRouteName("subprod", "DE", "moto_main");
        Assert.assertEquals(urlMotoPage, expectedMotoUrl);
        closeWindow();
        switchTo().window(0);
        mainPageLogic.clickTabAccessoriesInTopBlock();
        String urlAccessoriesPage = url();
        String expectedAccessoriesUrl = db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_accessories");
        Assert.assertEquals(urlAccessoriesPage, expectedAccessoriesUrl);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
