package ATD.Catalog_Brands.QC_33_BrandPageAndBlocks;

import ATD.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3499 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        //return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");   // НУЖНО ПРОСТАВИТЬ ПРАВИЛЬНЫЙ РУТ ИЗ БД
        return new Object[][]{{"https://www.autodoc.de/"}};
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks displaying and transition of brands block on category_name")
    public void testChecksDisplayAndTransitionOfBrandsBlock(String route) throws IOException {
        openPage(route);

        new Main_page_Logic()
                .checkElementsOfTopBrandsBlock()
                .checkTransitionToBrandPageByAllBrands();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
