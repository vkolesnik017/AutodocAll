package ATD.Tyres.QC_1272_TyresMainPage;

import ATD.SetUp;
import ATD.Tyres_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1336_GoToAllBrandsPageFromTyresMainPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To All Brands Page From Tyres Main Page")
    public void testGoToAllBrandsPageFromTyresMainPage(String route) {
        openPage(route);
        new Tyres_page_Logic().clickAllTyresBrandsButton();
        waitWhileRouteBecomeExpected("tyres_type_list_brands");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
