package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.Categories_page_Logic;
import ATD.Category_name_page_Logic;
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

public class QC_3111 {
    private String markeValue = "121";
    Categories_page_Logic categoriesPage = new Categories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Check Applicability of products from the TOP products block according to the brand in the auto selector")
    public void testCheckApplicabilityOfTopProductsToCarFromSelector(String route) {
        openPage(route);
        categoriesPage.setMarkeInSelectorAndRefreshPage(markeValue)
                .clickOnTopProductTitle(0)
                .displayMarkeInSelector(markeValue);
    }

    @DataProvider(name = "routesBrakeSystem", parallel = true)
    Object[] dataProviderBrakeSystem() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "brake_system,category_name14,category_name_brand14");
    }

    @Test(dataProvider = "routesBrakeSystem")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Check Applicability of products from the TOP products block according to the brand in the auto selector")
    public void testCheckApplicabilityOfTopProductsToCarFromSelectorBrakeSystem(String route) {
        openPage(route);
        new Category_name_page_Logic().setMarkeInSelectorAndRefreshPage(markeValue)
                .clickOnTopProductTitle(0)
                .displayMarkeInSelector(markeValue);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
