package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Moto_Category_car_list_model_page_Logic;
import AWS.ProductCard_aws;
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
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3292 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model12,moto_category_car_list20");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking for Generic 3465 Products in Model Listings and 43213 TecDoc Category")
    public void testCheckDisplayOfProductWithGeneric(String route) {
        openPage(route);
        List<String> productId = new Moto_Category_car_list_model_page_Logic()
                .presenceOfProductList()
                .getIdOfProducts();
        List<String> generics = new ProductCard_aws().getProductListOfGenerics(productId);
        Assert.assertTrue(generics.contains("3465"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
