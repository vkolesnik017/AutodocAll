package ATD.Listings.QC_458_ListingSortingAndPrioritization;

import Common.SetUp;
import ATD.Tyre_form_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_2070 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "tyre_form5,tyres_season12,offroad_tyres_brand,tyres_group_season_brand3,tyres_size10,tyre_form6,tyres_season_size,tyres_brand_size3,tyres_brand_dimension7");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks sorting of product with Grey button ")
    public void testChecksSortingOfProductWithGreyButton(String route) {
        open(route);

        new Tyre_form_page_Logic()
                .presenceOfListingBlock()
                .checkSortingOfProductsWithGreyButton();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
