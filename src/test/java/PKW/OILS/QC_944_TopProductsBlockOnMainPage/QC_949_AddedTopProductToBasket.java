package PKW.OILS.QC_944_TopProductsBlockOnMainPage;

import ATD.Cart_page_Logic;
import PKW.Motoroil_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;

public class QC_949_AddedTopProductToBasket {
    private Motoroil_page_Logic motoroilPage = new Motoroil_page_Logic();
    private Cart_page_Logic cartPage = new Cart_page_Logic();
    int countOfAddedProduct = 4;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks added TOP product to basket")
    public void testChecksAddedTopProductToBasket(String route) {
        openPage(route);
        String idOfAddedProduct = motoroilPage.getIdOfTopProduct(0);
        motoroilPage.addedTopProductWithCountToBasket(0, countOfAddedProduct);
        cartPage.checkOfIdAddedProductInBasket(idOfAddedProduct);
        int amountOfAddedProduct = Integer.parseInt(cartPage.getValueQuantityCounter());
        Assert.assertEquals(countOfAddedProduct, amountOfAddedProduct);
    }
}
