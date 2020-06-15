package MOTO.QC_373_MainIssueBlockAtTecDocListing;

import ATD.Cart_page_Logic;
import ATD.Moto_Category_car_list_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_376_AddProductToBasketFromTecDocListing {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2,moto_category_car_list2");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks add product to basket from TecDoc listing")
    public void testChecksAddProductToBasketFromTecDocListing(String route) {
        openPage(route);

        String idOfAddedProduct = new Moto_Category_car_list_page_Logic().getIdOfProductFromTecDocListing();
        new Moto_Category_car_list_page_Logic().addProductToBasket();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}