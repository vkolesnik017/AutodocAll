package ATD.MOTO.QC_373_MainIssueBlockAtTecDocListing;

import ATD.Cart_page_Logic;
import ATD.Moto_Category_car_list_model_page_Logic;
import ATD.Moto_Category_car_list_page_Logic;
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

public class QC_382_AddToBasketAnalogProduct {
    private Moto_Category_car_list_page_Logic carListPage = new Moto_Category_car_list_page_Logic();
    private Moto_Category_car_list_model_page_Logic carListModelPage = new Moto_Category_car_list_model_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks add to basket an analog product")
    public void testChecksAddToBasketAnalogProduct(String route) {
        openPage(route);

        String idOfAddedProduct = carListPage.getIdOfAnalogProduct();
        carListPage.addProductToBasketFromAnalogBlock();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }


    @DataProvider(name = "routesCarListModel", parallel = true)
    Object[] dataProviderCarListModel() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2");
    }

    @Test(dataProvider = "routesCarListModel")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks add to basket an analog product")
    public void testChecksAddToBasketAnalogProductCarListModel(String route) {
        openPage(route);

        String idOfAddedProduct = carListModelPage.getIdOfAnalogProduct();
        carListModelPage.addProductToBasketFromAnalogBlock();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
