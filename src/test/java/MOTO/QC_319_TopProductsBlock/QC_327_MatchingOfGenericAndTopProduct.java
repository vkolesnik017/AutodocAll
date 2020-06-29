package MOTO.QC_319_TopProductsBlock;

import ATD.Moto_Category_page_Logic;
import ATD.Moto_Parent_Category_page_Logic;
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

public class QC_327_MatchingOfGenericAndTopProduct {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker");
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
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category_maker2,moto_parent_category");
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
    private void tearDown() {
        close();
    }
}
