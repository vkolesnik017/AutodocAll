package ATD.Wishlist.QC_2356_WishListBlock;

import ATD.Product_page_Logic;
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

public class QC_2374 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product3");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks 360 functionality on products in wishlist")
    public void testChecks360FunctionalityOnProductsInWishlist(String route) {
        openPage(route);
        new Product_page_Logic().addProductToWishList()
                .goToWishListPage()
                .presenceOfProductList()
                .clickBtn360FunctionalityAndCheckPresencePopup();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
