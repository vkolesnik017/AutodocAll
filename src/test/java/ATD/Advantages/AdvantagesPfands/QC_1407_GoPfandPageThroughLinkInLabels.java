package ATD.Advantages.AdvantagesPfands;

import ATD.Listing_page;
import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.close;

public class QC_1407_GoPfandPageThroughLinkInLabels {


    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Listing_page listing_page = new Listing_page();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "products")
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product8");
    }

    @Test(dataProvider = "products")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Going to Pfand page from product page")
    public void testGoPfandPageFromProductPage(String route) {
        openPage(route);
        product_page_logic.clickLinkPfandFromProductPage()
                .mainText().shouldBe(visible);
    }


    @DataProvider(name = "listing", parallel = true)
    Object[] dataProviderListing() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_oen7,search14,category_car_list15");
    }

    @Test(dataProvider = "listing")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Going to Pfand page from listing page")
    public void testGoPfandPageFromListingPage(String route) {
        openPage(route);
        listing_page.clickLinkPfandFromListing()
                .mainText().shouldBe(visible);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
