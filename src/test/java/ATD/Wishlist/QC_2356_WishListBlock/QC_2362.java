package ATD.Wishlist.QC_2356_WishListBlock;

import ATD.Main_page_Logic;
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

public class QC_2362 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "main");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks elements of WishList when user is not logged in")
    public void testChecksElementsOfWishListWhenUserIsNotLoggedIn(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().goToWishListPage().checkElementsWithNotLoggedUser().clickOnWishListItemInSidebar().closeLoginPopUp().goToCatalog();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "categories"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
