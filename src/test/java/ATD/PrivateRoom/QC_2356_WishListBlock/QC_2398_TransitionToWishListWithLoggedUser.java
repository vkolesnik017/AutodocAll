package ATD.PrivateRoom.QC_2356_WishListBlock;

import Common.DataBase;
import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2398_TransitionToWishListWithLoggedUser {
    private String email = "QC_2398_transitionToWishList@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "main", "main");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to WishList with logged user")
    public void testChecksTransitionToWishListWithLoggedUser(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
        .loginAndTransitionToProfilePlusPage(email).goToWishListBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE","profile_wishlist"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
