package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import ATD.Listing_page_Logic;
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

public class QC_254 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list66");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romanuta")
    @Description(value = "Test checks helping press block")
    public void testChecksHelpingPressBlock(String route) {
        openPage(route);
        new Listing_page_Logic().clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear()
                .checkAddToBasketButtonsSorting();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
