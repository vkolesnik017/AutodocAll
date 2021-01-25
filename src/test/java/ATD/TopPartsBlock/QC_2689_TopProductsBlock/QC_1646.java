package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.CommonMethods;
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


public class QC_1646 {

    private CommonMethods commonMethods = new CommonMethods();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "routesWithBlocksOfTopProducts", parallel = false)
    Object[] routesWithBlocksOfTopProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories_maker,group_list,group_list_body,group_list_drive," +
                "group_list_fuel,group_list_year,group_list_hp,model_maker_list,model_maker_list_year,model_maker_list_hp,maker_car_list,category_name,category_maker,category_maker_body,category_maker_drive," +
                "category_group,category_group_body,category_group_drive,category_group_fuel,category_group_year,category_model");
    }

    @Test(dataProvider = "routesWithBlocksOfTopProducts")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "The test checks products not in stock in blocks of top products")
    public void testProductsNotInStockInBlockOfTopProducts(String route) {
        openPage(route);
        commonMethods.scrollToTitleOfBlockOfTopProducts().checksProductsNotInStockInBlockOfTopProducts();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}