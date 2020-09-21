package ATD.Tyres.QC_2066_CheckProductsOutOfStock;

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
import static com.codeborne.selenide.Selenide.*;

public class QC_2068_AvailabilityOfPopUpOfAppearanceOfOutOfStockProduct {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "tyre_form5,tyres_season13,tyres_brand8,tyres_group_season_brand2,tyres_size9,tyres_season_size13,tyres_brand_size3,tyres_dimension11,tyres_season_dimension7,tyres_brand_dimension8");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Availability of pop-ups for notification of the appearance of a product in stock when clicking on a link on products not in stock ")
    public void testChecksAvailabilityOfPopUpOfAppearanceOfOutOfStockProduct(String route) {
        open(route);

        new Tyre_form_page_Logic().displayingCustomerFeedbackPopUp();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
