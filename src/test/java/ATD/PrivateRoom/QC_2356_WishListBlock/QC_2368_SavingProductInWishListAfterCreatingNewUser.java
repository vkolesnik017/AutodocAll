package ATD.PrivateRoom.QC_2356_WishListBlock;

import ATD.Category_car_list_page_Logic;
import ATD.Main_page_Logic;
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

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2368_SavingProductInWishListAfterCreatingNewUser {

    private Category_car_list_page_Logic carListPage = new Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list48");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks saving product in wishList after creating new user")
    public void testChecksSavingProductInWishListAfterCreatingNewUser(String route) {
        openPage(route);

        List<String> artNumOfProduct = carListPage.presenceOfTecDocListing().addArtNumOfProductToList(1);
        carListPage.addProductToWishList(1);
        new Main_page_Logic().registrationFromLoginButton(mailRandom()).visibilityOfUsersName();
        new Search_page_Logic().goToWishListPage().presenceOfProductList().
                checkChronologicalOrderOfProducts(artNumOfProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
