package ATD.Listings.QC_1298_AccountingForDynamicGenericsOfProductsInSearchResults;

import ATD.Cart_page_Logic;
import ATD.Category_car_list_page_Logic;
import ATD.Search_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1305_SearchByNameOfDynamicGeneric {
    private String generic = "Glühlampe, Abbiegescheinwerfer";
    private Search_page_Logic searchPage = new Search_page_Logic();
    private Cart_page_Logic cartPage = new Cart_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "AT", "main", "category_car_list33");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checks search by the name of the dynamic generic, accounting in the search results of products applicable by the dynamic generic")
    public void testCheckSearchByNameOfDynamicGeneric(String route) {
        openPage(route);

        new Category_car_list_page_Logic()
                .inputGenericInSearchField(generic).checkListingWithSelectedGeneric(generic);
        List<String> idOfAddedProducts = new ArrayList<>(searchPage.getIdOfAddedProductsToBasket(1));
        searchPage.addedProductsToBasket(1).cartClick();
        List<String> idOfAddedProductsFromBasket = new ArrayList<>(cartPage.getIdAddedProductsToList());
        Assert.assertEquals(idOfAddedProducts, idOfAddedProductsFromBasket);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
