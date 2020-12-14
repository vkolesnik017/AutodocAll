package ATD.Associated.QC_790_AlternativeProductsOutputTecdoc;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_738_AnalogProductBlock {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }


    @DataProvider(name = "routesProduct", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product6");
    }

    @Test(dataProvider = "routesProduct")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks related product block")
    public void testChecksRelatedProductBlock(String route) {
        openPage(route);

        new Moto_Product_page_Logic()
                .presenceOfRelatedProductBlock();
    }



    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
