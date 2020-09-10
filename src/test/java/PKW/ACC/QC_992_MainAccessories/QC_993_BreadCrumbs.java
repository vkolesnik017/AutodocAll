package PKW.ACC.QC_992_MainAccessories;

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
import static PKW.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_993_BreadCrumbs {

    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition for two breadcrumbs. ")
    public void testCheckTransitionForTwoBreadCrumbs(String route) {
        openPage(route);
        index_accessories_page_logic.checkingPresenceBlockBreadCrumbs()
                .clickOnFirstBreadCrumb();
        checkingContainsUrl("ersatzteile");
        back();
        index_accessories_page_logic.checkingPresenceAndNotClickableSecondBreadCrumb();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
