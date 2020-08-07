package MOTO.QC_364_TopCategoriesByBrandBlock;

import ATD.Moto_Categories_maker_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_366_PresenceOfTopParentCategoriesBlock {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence and structure of TOP parent categories block")
    public void testChecksPresenceOfTopParentCategoriesBlock(String route)  {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
        .presenceOfTopParentAndChildBlock()
        .presenceTopParentBlock()
        .checkElementsOfTopParentBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
