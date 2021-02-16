package ATD.Wishlist.QC_2356_WishListBlock;

import ATD.LKW_Category_car_list_page_Logic;
import ATD.Main_page_Logic;
import ATD.Search_page_Logic;
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
import java.util.List;

import static ATD.CommonMethods.mailinatorMailRandom;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2569 {
    private Search_page_Logic searchPage = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list42");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks removing all products from WishList after logOut")
    public void testChecksRemoveAllProductsFromWishListAfterLogOut(String route) {
        openPage(route);

        new LKW_Category_car_list_page_Logic().visibilityOfTecDocListingBlock();
        List<String> artNumOfProduct = searchPage.addArtNumOfProductToList(1);
        searchPage.addedProductToWishList(1).goToWishListPage();
        new Main_page_Logic().registrationFromLoginButton(mailinatorMailRandom()).visibilityOfUsersName();
        new Services_wishList_page_Logic().checkChronologicalOrderOfProducts(artNumOfProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
