package ATD.Wishlist.QC_2356_WishListBlock;

import ATD.Search_page_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2370 {

    private Search_page_Logic searchPage = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "search31");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks remove products from WishList")
    public void testChecksRemoveProductsFromWishList(String route) throws SQLException {
        openPage(route);
        List<String> artNumOfProduct = searchPage.presenceOfTecDocListing().addArtNumOfProductToList(3);
        searchPage.addedProductToWishList(3).goToWishListPage().presenceOfProductList().
                checkChronologicalOrderOfProducts(artNumOfProduct).removeProductFromWishList(1);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "search31"));
        searchPage.presenceOfTecDocListing().removeProductsFromWishList(1);
        List<String> artNumOfAddedProductToWishList = searchPage.addArtNumAddedProductOfWishlistToList();
        searchPage.goToWishListPage().presenceOfProductList().
                checkChronologicalOrderOfProducts(artNumOfAddedProductToWishList);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
