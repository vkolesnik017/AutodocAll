package ATD.Wishlist.QC_2356_WishListBlock;

import ATD.Main_page_Logic;
import ATD.Search_page_Logic;
import ATD.Tyre_item_page_Logic;
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

public class QC_2396 {

    private Search_page_Logic searchPage = new Search_page_Logic();
    private Tyre_item_page_Logic itemPage = new Tyre_item_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search37");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks add product to Wishlist from basket")
    public void testChecksAddProductToWishlistFromBasket(String route) throws SQLException {
        openPage(route);

        List<String> artNumOfProduct = searchPage.getArtNumOfProduct(3);
        searchPage.addedProductsToBasket(3);
        itemPage.goToBasket().addedAllProductToWishList();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "main"));
        new Main_page_Logic().goToWishListPage().presenceOfProductList().checkChronologicalOrderOfProducts(artNumOfProduct).removeAllProductsFromWishList();
        itemPage.goToBasket().presenceLabelAddProductToWishList().removeAllProductsFromBasket();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
