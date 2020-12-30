package ATD.PrivateRoom.QC_2356_WishListBlock;

import ATD.Cart_page_Logic;
import ATD.Search_page_Logic;
import ATD.Services_wishList_page_Logic;
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

public class QC_2399_AddProductsFRomWishListToBasket {
    private Search_page_Logic searchPage = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "search32");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks add products from WishList to basket")
    public void testChecksAddProductsFRomWishListToBasket(String route) throws SQLException {
        openPage(route);
        List<String> artNumOfActiveProduct = searchPage.presenceOfTecDocListing().addArtNumOfActiveProductToList(1);
        searchPage.addedActiveProductToWishListWithSelectedCar(1);
        List<String> artNumOfNotActiveProduct = searchPage.presenceOfTecDocListing().addArtNumOfNotActiveProductToList(1);
        searchPage.addNotActiveProductToWishList(0).goToWishListPage()
                .presenceOfProductList().clickOnBtnAddProductToBasket().clickOnRandomElementOfPopUp();
        new Cart_page_Logic().checkArticleNumberOfProduct(artNumOfActiveProduct.get(0));
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "services_wishlist"));
        new Services_wishList_page_Logic().presenceOfProductList()
                .checkCountOfProducts(artNumOfNotActiveProduct.size()).checkChronologicalOrderOfProducts(artNumOfNotActiveProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
