package ATD.PrivateRoom.QC_2356_WishListBlock;

import ATD.Cart_page_Logic;
import ATD.Product_page_Logic;
import ATD.Services_wishList_page_Logic;
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

public class QC_2375_AnalogBlockForOutOfStockProducts {

    private Services_wishList_page_Logic wishListPage = new Services_wishList_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "product38");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks analog block for Out of stock products")
    public void testChecksAnalogBlockForOutOfStockProducts(String route) {
        openPage(route);

        new Product_page_Logic().addProductToWishList().goToWishListPage()
                .presenceOfProductList().clickOnShowAlternativeProducts(0)
                .hoverOnAlternativeProduct(0);
        String idOfAddedProduct = wishListPage.getIdOfAlternativeProduct(0);
        wishListPage.addAlternativeProductToBasket(0);
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
