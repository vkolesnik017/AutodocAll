package ATD.PrivateRoom.QC_2356_WishListBlock;

import ATD.Tyre_form_page_Logic;
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

public class QC_2121_CheckingOfAddingOutOfStockTyresToWishList {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form5,tyres_season13,tyres_brand8,tyres_group_season_brand2,tyres_size12,tyres_season_size13,tyres_season_dimension6,tyres_brand_size3,tyres_brand_dimension6");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking of adding out of stock tyres to WishList and the impossibility of adding a product to the cart is not in stock")
    public void testCheckingOfAddingOutOfStockTyresToWishList(String route) {
        openPage(route);
        new Tyre_form_page_Logic().addedOutOfStockProductToWishList(1).goToWishListPage().clickOnGrayBtnOfProduct().clickOnGrayBtnOfProduct().presenceOfAvailablePopUp();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
