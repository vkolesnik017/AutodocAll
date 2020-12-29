package ATD.PrivateRoom.QC_2356_WishListBlock;

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
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2369_PresenceNumberOfAddedProductsOnWishlistIcon {

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
    @Description(value = "Test checks presence number of added products on Wishlist icon in header")
    public void testChecksPresenceNumberOfAddedProductsOnWishlistIcon(String route) throws SQLException {
        openPage(route);
        List<String> artNumOfProduct = searchPage.presenceOfTecDocListing().addArtNumOfProductToList(3);
        searchPage.addedProductToWishList(3).checkCountOfAddedProductToWishListInIcon(artNumOfProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
