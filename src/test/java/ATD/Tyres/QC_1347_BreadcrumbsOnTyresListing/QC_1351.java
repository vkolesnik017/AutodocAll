package ATD.Tyres.QC_1347_BreadcrumbsOnTyresListing;


import Common.SetUp;
import ATD.TyresListing_page_Logic;
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

public class QC_1351 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Tyres Breadcrumbs Transport Type And Dimension")
    public void testTyresBreadcrumbsTransportTypeAndDimension(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkBreadcrumbsFirstButtonTransiton()
                                    .checkBreadcrumbsSecondButtonTransiton("llkw")
                                    .checkBreadcrumbsLastButton("195 70 R15");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
