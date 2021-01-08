package ATD.PrivateRoom.QC_2356_WishListBlock;

import ATD.Main_page_Logic;
import ATD.Search_page_Logic;
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

public class QC_2571 {

    private String email = "QC_2571_autotest@mailinator.com";
    private Search_page_Logic searchPage = new Search_page_Logic();
    private Main_page_Logic mainPage = new Main_page_Logic();
    private DataBase db = new DataBase("ATD");

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
    @Description(value = "Test checks presence of previously added products with logged user")
    public void testChecksPresenceOfPreviouslyAddedProductsWithLoggedUser(String route) throws SQLException {
        openPage(route);
        mainPage.loginAndTransitionToProfilePlusPage(email);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search30"));
        List<String> artNumOfProduct = searchPage.presenceOfTecDocListing().addArtNumOfProductToList(1);
        searchPage.addedProductToWishList(1);
        mainPage.logOutClick();
        mainPage.loginAndTransitionToProfilePlusPage(email);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search30"));
        mainPage.goToWishListPage().presenceOfProductList().checkChronologicalOrderOfProducts(artNumOfProduct).removeAllProductsFromWishList();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
