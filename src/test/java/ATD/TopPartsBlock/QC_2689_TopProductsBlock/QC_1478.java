package ATD.TopPartsBlock.QC_2689_TopProductsBlock;


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

public class QC_1478 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form,tyres_dimension6,tyres_size6," +
                "tyres_season_dimension2,tyres_dimension7,tyres_size7,tyres_season_dimension3,tyres_dimension8,tyres_size8,tyres_season_dimension4,tyres_size5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Products In Top Block Not Adjust To Brand Filter")
    public void testProductsInTopBlockNotAdjustToBrandFilter(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkProductsInTopBlockAndBrandFilter();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
