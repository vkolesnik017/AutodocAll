package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;

import ATD.Category_car_list_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3145 {
    private Category_car_list_page_Logic carListPage = new Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list65,search47,listing_accessories5"); // category_oen27 - БАГА
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check position of Ridex brand")
    public void testCheckPositionOfRidexBrand(String route) {
        openPage(route);
        carListPage.displayOfBrandsBlock()
                .checkPositionOfBrandInBlockById(0, "100015")
                .selectBrandInBlock("100015");
        List<String> brandOfProductsInList = carListPage.getProductBrandsFromList();
        Assert.assertTrue(brandOfProductsInList.contains("RIDEX"));
        System.out.println();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
