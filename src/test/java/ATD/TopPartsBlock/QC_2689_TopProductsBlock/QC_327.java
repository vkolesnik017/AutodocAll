package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.Moto_Category_page_Logic;
import ATD.Moto_Parent_Category_page_Logic;
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

public class QC_327 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks matching of generic and TOP product")
    public void testChecksMatchingOfGenericAndTopProduct(String route) {
        openPage(route);
        new Moto_Category_page_Logic()
                .checkGenericAndTopProduct();
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category_maker2,moto_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks matching of generic and TOP product")
    public void testChecksMatchingOfGenericAndTopProductParentCategory(String route) {
        openPage(route);
        new Moto_Parent_Category_page_Logic()
                .checkGenericAndTopProduct();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
