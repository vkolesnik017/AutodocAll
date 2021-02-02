package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.Moto_Category_page_Logic;
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

public class QC_3291 {
    Moto_Category_page_Logic motoCategoryPage = new Moto_Category_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category22,moto_category_maker9");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of product of 3465 generic of 43213 category in TOP product")
    public void testChecksPresenceOfExpectedGenericProduct(String route) {
        openPage(route);
        List<String> idOfTopProduct = motoCategoryPage.presenceOfTopProductsBlock().getIdOfAllTopProducts();
        motoCategoryPage.checkProductAndGenericConformity(idOfTopProduct, "3465");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
