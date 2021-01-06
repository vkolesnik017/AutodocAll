package ATD.Selectors.QC_87_Trucks;

import ATD.*;
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

public class QC_84 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesWithVerticalSelector", parallel = true)
    Object[] dataProviderVerticalSelector() throws SQLException {
       return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main,lkw_parent_category,lkw_category2,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand,lkw_category_car_list10,lkw_makers,lkw_categories_maker,404");

    }

    @Test(dataProvider = "routesWithVerticalSelector")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks required fields for vertical selector")
    public void testChecksRequiredFieldsSelectionInVerticalSelector(String route) {
        openPage(route);
        new LKW_Category_maker_Logic()
                .setupDefaultValueForVerticalSelector()
                .visibilityOfTooltipForMarkeFieldInSelector()
                .visibilityOfTooltipForModelFieldInSelector()
                .visibilityOfTooltipForMotorFieldInSelector();
    }

    @DataProvider(name = "routesWithCloseVerticalSelector", parallel = true)
    Object[] dataProviderCloseVerticalSelector() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories,lkw_category_car_list11,lkw_maker_car_list2");

    }

    @Test(dataProvider = "routesWithCloseVerticalSelector")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks required fields for vertical selector")
    public void testChecksRequiredFieldsSelectionInCloseVerticalSelector(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic().openVerticalSelector()
                .setupDefaultValueForVerticalSelector()
                .visibilityOfTooltipForMarkeFieldInCloseSelector()
                .visibilityOfTooltipForModelFieldInCloseSelector()
                .visibilityOfTooltipForMotorFieldInCloseSelector();
    }


    @DataProvider(name = "routesWithHorizontalSelector", parallel = true)
    Object[] dataProviderHorizontalSelector() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product3");

    }

    @Test(dataProvider = "routesWithHorizontalSelector")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks required fields for horizontal selector")
    public void testChecksRequiredFieldsSelectionInHorizontalSelector(String route) {
        openPage(route);
        new LKW_Product_page_Logic()
                .visibilityOfTooltipForMarkeFieldInHorizontalSelector()
                .visibilityOfTooltipForModelFieldInHorizontalSelector()
                .visibilityOfTooltipForMotorFieldInHorizontalSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
