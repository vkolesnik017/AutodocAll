package ATD.Wishlist.QC_2356_WishListBlock;

import ATD.Main_page_Logic;
import ATD.Maker_car_list_page_Logic;
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

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2365 {
    private String email = "QC_2365_WishListElements@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks elements of WishList when user is logged in")
    public void testChecksElementsOfWishlistWhenUserIsLoggedIn(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).goToWishListBlock().checkElementsWithLoggedUser().
                goToCatalog().presenceOfTecDocCatalog().selectVehicleInSelector("121", "1994", "8799");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "maker_car_list3"));
        new Maker_car_list_page_Logic().goToWishListWithLoggedUser().goToCatalog();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "maker_car_list3"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
