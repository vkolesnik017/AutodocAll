package ATD.Characteristics.QC_506_StaticCharacteristics;

import ATD.Category_car_list_page_Logic;
import ATD.Category_oen_Page_Logic;
import ATD.Search_page_Logic;
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

public class QC_2074_AbsenceQuantityForFebiBilsteinAndSwagInListing {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search28,category_car_list30");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking for the absence of the characteristic 'quantity' 563 in listings for FEBI BILSTEIN and SWAG brands")
    public void testCheckAbsenceQuantityForFEBIBILSTEINAndSWAGInListing(String route) {
        openPage(route);

        new Search_page_Logic()
                .selectBrandInBlock("101")
                .selectBrandInBlock("1156")
                .checkListingWithSelectedBrands("101,1156")
                .checkAbsenceOfQuantityCharacteristicInProductDescriptionBlock();
    }

    @DataProvider(name = "routesOEN", parallel = true)
    Object[] dataProviderOEN() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen12,category_oen13");
    }

    @Test(dataProvider = "routesOEN")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking for the absence of the characteristic 'quantity' 563 in listings for FEBI BILSTEIN and SWAG brands")
    public void testCheckAbsenceQuantityForFEBIBILSTEINAndSWAGInListingOEN(String route) {
        openPage(route);

        new Category_oen_Page_Logic()
                .selectBrandInBlock("101")
                .selectBrandInBlock("1156")
                .checkListingWithSelectedBrands("101,1156")
                .checkAbsenceOfQuantityCharacteristicInProductDescriptionBlock();
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list28,category_car_list29");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking for the absence of the characteristic 'quantity' 563 in listings for FEBI BILSTEIN and SWAG brands")
    public void testCheckAbsenceQuantityForFEBIBILSTEINAndSWAGInListingCarList(String route) {
        openPage(route);
        new Category_car_list_page_Logic()
                .selectBrandInBlock("101")
                .checkListingWithSelectedBrands("101")
                .checkAbsenceOfQuantityCharacteristicInProductDescriptionBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
