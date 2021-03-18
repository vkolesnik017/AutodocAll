package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;

import ATD.Motoroil_brand_page_Logic;
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

public class QC_3240 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_viscosity,motoroil_search");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Bobryshev")
    @Description (value = "Check Availability of filter by brand on oil listings")

    public void testCheckAvailabilityOfFilterByBrand(String routes) {
        openPage(routes);
        new Motoroil_brand_page_Logic().checkVisibleFilterBrandBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
