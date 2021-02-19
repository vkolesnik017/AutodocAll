package ATD.FiltersSorting.QC_2594_Filters_CriterionCharacteristics;

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
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.checkingNotContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3250 {
    private String firstIdValue = "BMW Longlife-01";
    private String secondIdValue = "BMW Longlife-04";
    Motoroil_brand_page_Logic motoroilPage = new Motoroil_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_viscosity2");  // ,motoroil_search - БАГА
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Application of the OEM-FREIGABE filter on oil listings")
    public void testCheckApplicationOemFreigabeFilter(String route) {
        openPage(route);

        motoroilPage.displayOemFreigabeFilterInSidebar()
                .setOemFreigabeFilterInSidebar(firstIdValue)
                .appearsOfLoader()
                .checkOemFreigabeSelector(firstIdValue)
                .checkSelectedCheckBoxOemFreigabeFilter(firstIdValue)
                .checkDisplaySelectedOemFreigabeFilterInProducts(firstIdValue);
        checkingContainsUrl(firstIdValue.replaceAll(" ", "-").toLowerCase());
        motoroilPage.setOemFreigabeFilterInSidebar(secondIdValue)
                .appearsOfLoader()
                .checkOemFreigabeSelector(secondIdValue)
                .checkSelectedCheckBoxOemFreigabeFilter(secondIdValue)
                .checkDisplaySelectedOemFreigabeFilterInProducts(secondIdValue);
        checkingContainsUrl(secondIdValue.replaceAll(" ", "-").toLowerCase());
        motoroilPage.setOemFreigabeFilterInSidebar(secondIdValue)
                .appearsOfLoader()
                .checkCheckboxOemFreigabeFilter(false, secondIdValue)
                .checkSizeValuesOemFreigabeFilterInProducts();
        checkingNotContainsUrl(secondIdValue.replaceAll(" ", "-").toLowerCase());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
