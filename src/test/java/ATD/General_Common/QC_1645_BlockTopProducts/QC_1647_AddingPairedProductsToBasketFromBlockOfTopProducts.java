package ATD.General_Common.QC_1645_BlockTopProducts;

import ATD.CommonMethods;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1647_AddingPairedProductsToBasketFromBlockOfTopProducts {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesWithBlocksOfTopPairedProducts", parallel = true)
    Object[] routesWithBlocksOfTopPairedProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name,category_maker,category_maker_body," +
                "category_maker_drive,category_group,category_group_body,category_group_drive,category_group_fuel,category_group_year,category_model");
    }

    @Test(dataProvider = "routesWithBlocksOfTopPairedProducts")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "The test checks adding paired products to basket from block of top products")
    public void testAddingPairedProductsToBasketFromBlockOfTopProducts(String route) {
        openPage(route);
        new CommonMethods().scrollToBlockOfTopProducts();
        clickOfBuyBtnForAllPages();
        new Product_page_Logic().checksPresentProductInCartPopup()
                .cartClick()
                .fieldWithQuantityOfProducts().shouldHave(value("2"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
