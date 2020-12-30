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

public class QC_2366_CompareElementsOfProductInListingAndWishList {
    private Search_page_Logic searchPage = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search30");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks compare elements of product in listing and WishList")
    public void testChecksCompareElementsOfProductInListingAndWishList(String route) {
        openPage(route);
        String priceBox = searchPage.getElementsFromPriceBlock(0);
        String urlOfImageProduct = searchPage.getUrlOfProductImageBrand(0);
        String amountQuantityOfProduct = searchPage.getAmountQuantityOfProduct(0);
        List<String> listOfProductCharacteristics = searchPage.getAllProductCharacteristics(0);
        searchPage.addedProductToWishList(1).goToWishListPage().presenceOfProductList().compareElementsFromListingInWishList(0, urlOfImageProduct, priceBox, listOfProductCharacteristics, amountQuantityOfProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
