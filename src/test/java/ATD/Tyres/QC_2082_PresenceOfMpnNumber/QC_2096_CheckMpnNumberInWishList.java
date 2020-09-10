package ATD.Tyres.QC_2082_PresenceOfMpnNumber;

import ATD.Services_wishList_page_Logic;
import Common.SetUp;
import ATD.Tyre_form_page_Logic;
import ATD.Tyres_dimension_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2096_CheckMpnNumberInWishList {
    private Tyre_form_page_Logic tyreFormPage = new Tyre_form_page_Logic();
    private Tyres_dimension_page_Logic dimensionPage = new Tyres_dimension_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_form5,tyres_season,offroad_tyres_brand,tyres_group_season_brand3,tyres_size10");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check MPN number in Wishlist")
    public void testCheckCheckMpnNumberInWishList(String route) {
        openPage(route);
        String mpnOfProduct = tyreFormPage.presenceOfListingBlock().getMpnNumberOfProduct(0);
        tyreFormPage.addProductToWishList(0).goToWishListPage();
        String mpnOfProductFromWishList = new Services_wishList_page_Logic().presenceOfProductList().getMpnNumberOfProduct(0);
        Assert.assertEquals(mpnOfProduct, mpnOfProductFromWishList);
    }

    @DataProvider(name = "routesDimension", parallel = true)
    Object[] dataProviderDimension() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension9,tyres_season_size,tyres_season_dimension6,tyres_brand_size3,tyres_brand_dimension6");
    }

    @Test(dataProvider = "routesDimension")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check MPN number in Wishlist")
    public void testCheckCheckMpnNumberInWishListDimension(String route) {
        openPage(route);
        String mpnOfProduct = dimensionPage.presenceOfListingBlock().getMpnNumberOfProduct(0);
        dimensionPage.addProductToWishList(0).goToWishListPage();
        String mpnOfProductFromWishList = new Services_wishList_page_Logic().presenceOfProductList().getMpnNumberOfProduct(0);
        Assert.assertEquals(mpnOfProduct, mpnOfProductFromWishList);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
