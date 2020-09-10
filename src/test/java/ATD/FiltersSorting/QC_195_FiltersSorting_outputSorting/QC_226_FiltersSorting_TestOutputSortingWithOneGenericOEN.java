package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import ATD.Listing_page_Logic;
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

public class QC_226_FiltersSorting_TestOutputSortingWithOneGenericOEN {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price and addToBasket buttons sorting with one generic OEN listing")
    public void testSortingOneGenericOEN(String route) {
        openPage(route);
        new Listing_page_Logic().checkOutptuSortingWithGeneric();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
