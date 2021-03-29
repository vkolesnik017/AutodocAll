package ATD.Listings.QC_3503_Oil_Listing_MainProductsBlock;


import ATD.Cart_page_Logic;
import ATD.Motoroil_brand_page_Logic;
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
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3480 {

    Motoroil_brand_page_Logic motoroilBrandPageLogic = new Motoroil_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }


    @DataProvider(name = "route", parallel = false)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_viscosity2,motoroil_search3");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "test check Adding a product from displacement block on oil listing ")
    public void testCheckAddingProductFromDisplacementBlock(String route) {
        openPage(route);
        String idDisplacement = motoroilBrandPageLogic.getIdFromProductDisplacement();
        motoroilBrandPageLogic.clickAddProductToBasketFromDisplacementBlock()
                .cartClick();
        String idProduct = new Cart_page_Logic().getIdAddedProduct();
        Assert.assertEquals(idDisplacement, idProduct);
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
