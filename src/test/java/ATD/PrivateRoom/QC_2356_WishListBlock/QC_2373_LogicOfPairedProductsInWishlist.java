package ATD.PrivateRoom.QC_2356_WishListBlock;

import ATD.Search_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2373_LogicOfPairedProductsInWishlist {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "main", "search19");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to WishList with logged user")
    public void testChecksTransitionToWishListWithLoggedUser(String route) {
        openPage(route);

        new Search_page_Logic().presenceOfTecDocListing().addedProductToWishList(0).goToWishListPage().increaseQuantityOfProduct(1);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
