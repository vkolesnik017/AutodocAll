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

public class QC_3253 {
    private String firstIdValue = "ACEA A1";
    private String secondIdValue = "ACEA A3";
    Motoroil_brand_page_Logic motoroilPage = new Motoroil_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_viscosity2");  // ,motoroil_search - бага
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Application of the Specification filter on oil listings")
    public void testCheckApplicationSpecificationFilter(String route) {
        openPage(route);

        motoroilPage.displaySpecificationFilterInSidebar()
                .setSpecificationFilterInSidebar(firstIdValue)
                .appearsOfLoader()
                .checkSpecificationSelector(firstIdValue)
                .checkSelectedCheckBoxSpecificationFilter(firstIdValue)
                .checkDisplaySelectedSpecificationFilterInProducts(firstIdValue);
        checkingContainsUrl(firstIdValue.replaceAll(" ", "-").toLowerCase());
        motoroilPage.setSpecificationFilterInSidebar(secondIdValue)
                .appearsOfLoader()
                .checkSpecificationSelector(secondIdValue)
                .checkSelectedCheckBoxSpecificationFilter(secondIdValue)
                .checkDisplaySelectedSpecificationFilterInProducts(secondIdValue);
        checkingContainsUrl(secondIdValue.replaceAll(" ", "-").toLowerCase());
        motoroilPage.setSpecificationFilterInSidebar(secondIdValue)
                .appearsOfLoader()
                .checkCheckboxSpecificationFilter(false, secondIdValue)
                .checkSizeValuesSpecificationFilterInProducts();
        checkingNotContainsUrl(secondIdValue.replaceAll(" ", "-").toLowerCase());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
