package ATD.ACC.QC_2129_ResetFiltersBlockOnPageChemistryListing;

import ATD.Listing_chemicals_Page_Logic;
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

public class QC_2138_WorkResetFiltersWhenChoosingSeveralCriteriaOnChemistryListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks work btn reset all filter from filter block if selected generic and brand")
    public void testCheckingWorkBtnResetAllFilterFromFilterBlock(String route) {
        openPage(route);
        new Listing_chemicals_Page_Logic().checkingWorkBtnResetAllFilterFromFilterBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }


}
