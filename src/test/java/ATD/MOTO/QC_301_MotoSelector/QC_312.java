package ATD.MOTO.QC_301_MotoSelector;

import ATD.Moto_Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_312 {

    private Moto_Product_page_Logic productPage = new Moto_Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks filling the selector by compatible motorcycle on Product page")
    public void testChecksFillingSelectorByCompatibleMoto(String route) {
        open(route);

        productPage.selectMotoInHorizontalSelector("4081", "12111", "104173");
        String brandOfMoto = productPage.getBrandAndModelOfMoto();
        productPage.presenceOfMotoBrandAtInfoMessage(brandOfMoto);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
