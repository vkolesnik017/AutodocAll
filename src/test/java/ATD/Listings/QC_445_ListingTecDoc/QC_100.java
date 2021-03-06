package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Cart_page_Logic;
import ATD.LKW_Category_car_list_page_Logic;
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

public class QC_100 {
    private LKW_Category_car_list_page_Logic tecDokListPage = new LKW_Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list10");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks block of analog in TecDoc listing ")
    public void testChecksBlockOfAnalogInTecDocListing(String route) {
        openPage(route);
        tecDokListPage.checkOfAppearsAnalogBlock().checkOfVisibilityPopUpAboutSubscribe();
        String idOfAddedProduct = tecDokListPage.getIdOfProductFromTecDocListingInAnalogBlock();
        tecDokListPage.addProductToBasketFromAnalogBlock();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
