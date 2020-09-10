package MOTO.QC_330_BlockOfMainHeadline;

import ATD.Moto_Catalog_model_page_Logic;
import ATD.Moto_Category_car_list_page_Logic;
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

public class QC_334_PresenceBrandModelValuesInHeadline {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2,moto_catalog_model2");
    }

      @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of brand and model values in main headline")
    public void testChecksPresenceBrandModelValuesInHeadline(String route) {
        openPage(route);

        new Moto_Catalog_model_page_Logic().presenceBrandAndModelInHeadLine("SUZUKI Access");
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2,moto_category_car_list2");
    }


    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of brand and model values in main headline")
    public void testChecksPresenceBrandModelValuesInHeadlineCarList(String route) {
        openPage(route);

        new Moto_Category_car_list_page_Logic().presenceBrandAndModelInHeadLine("BMW K");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
