package ATD.Wishlist.QC_2356_WishListBlock;

import ATD.Search_page_Logic;
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

public class QC_2402 {
    private Search_page_Logic searchPage = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search30");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks removing products from WishList and basket")
    public void testChecksRemoveProductsFromWishListAndBasket(String route) {
        openPage(route);

        searchPage
                .addedProductToWishList(1)
                .addedProductsToBasket(1)
                .goToBasket()
                .clickOnWishListLabel()
                .presenceAndCloseAddProductToWishList()
                .clickOnWishListLabel()
                .presenceAndRemoveAddedProductToWishList()
                .goToMainPage()
                .goToWishListPage()
                .presenceOfEmptyWishListBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
