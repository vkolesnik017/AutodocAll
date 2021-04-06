package ATD.Catalog_Brands.QC_33_BrandPageAndBlocks;

import ATD.Category_model_brand_page_Logic;
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

public class QC_3526 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        //return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker_drive");   // НУЖНО ПРОСТАВИТЬ ПРАВИЛЬНЫЙ РУТ ИЗ БД
        return new Object[][]{{"https://www.autodoc.de/autoteile/keilrippenriemen-10531/mercedes-benz"},
                {"https://www.autodoc.de/autoteile/keilrippenriemen-10531/mercedes-benz/drive-allrad"},
                {"https://www.autodoc.de/autoteile/keilrippenriemen-10531/mercedes-benz/body-bus"}};
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks displaying and transition of brands block on category_maker_drive")
    public void testChecksDisplayAndTransitionOfBrandsBlock(String route) throws IOException {
        openPage(route);
        new Category_model_brand_page_Logic()
                .displayOfBrandsBlock()
                .checkCountOfVisibleBrands(6)
                .clickOnBtnMoreOfBrandsBlock()
                .checkCountOfVisibleBrands(24)
                .checkTransitionOfBrandsLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
