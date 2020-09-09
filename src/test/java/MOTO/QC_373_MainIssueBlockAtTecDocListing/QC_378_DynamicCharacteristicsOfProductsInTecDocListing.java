package MOTO.QC_373_MainIssueBlockAtTecDocListing;

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

public class QC_378_DynamicCharacteristicsOfProductsInTecDocListing {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list6");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks dynamic characteristics of products in TecDoc listing")
    public void testChecksDynamicCharacteristicsOfProductsInTecDocListing(String route) {
        openPage(route);

        new Moto_Category_car_list_page_Logic()
                .presenceOfDynamicCharacteristics()
                .checkDynamicCharacteristicBlock("Artikelnummer: SKLS-0140083");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
