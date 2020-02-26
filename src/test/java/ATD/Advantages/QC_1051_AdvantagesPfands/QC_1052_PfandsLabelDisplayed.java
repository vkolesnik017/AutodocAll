package ATD.Advantages.QC_1051_AdvantagesPfands;

import ATD.*;
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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;


public class QC_1052_PfandsLabelDisplayed {

    private Listing_page listingPage = new Listing_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "products", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product8,product9,product10");
    }

    @Test(dataProvider = "products")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checks availability pfand on product page")
    public void testAvailabilityPfandOnProductPage(String route) {
        openPage(route);
        new Product_page_Logic().pfandBlock().shouldBe(visible);
    }

    @DataProvider(name = "search", parallel = true)
    Object[] dataProviderSearch() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search12,search13,search14");
    }

    @Test(dataProvider = "search")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checks availability pfand on search listing")
    public void testAvailabilityPfandOnSearchListing(String route) {
        openPage(route);
        listingPage.pfandBlock().shouldBe(visible);
    }

    @DataProvider(name = "tecdoc", parallel = true)
    Object[] dataProviderTecdoc() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list14,category_car_list15,category_car_list16");
    }

    @Test(dataProvider = "tecdoc")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checks availability pfand on tecdoc listing")
    public void testAvailabilityPfandOnTecDocListing(String route) {
        openPage(route);
        listingPage.pfandBlock().shouldBe(visible);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
