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

public class QC_3258 {

    private String firstIdValue = "1L";
    private String secondIdValue = "4L";
    Motoroil_brand_page_Logic motoroilPage = new Motoroil_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_viscosity2,motoroil_search");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Application of the Inhalt filter on oil listings")
    public void testCheckApplicationInhaltFilter(String route) {
        openPage(route);

        motoroilPage
                .presenceInhaltFilterInSidebar()
                .setInhaltFilterInSidebar(firstIdValue)
                .appearsOfLoader()
                .checkSelectedCheckBoxInhaltFilter(firstIdValue)
                .checkDisplaySelectedInhaltFilterInProducts(firstIdValue);
        checkingContainsUrl(firstIdValue.replaceAll("[^0-9]", ""));
        motoroilPage
                .setInhaltFilterInSidebar(secondIdValue)
                .appearsOfLoader()
                .checkSelectedCheckBoxInhaltFilter(secondIdValue)
                .checkDisplaySelectedInhaltFilterInProducts(secondIdValue);
        checkingContainsUrl(secondIdValue.replaceAll("[^0-9]", ""));
        motoroilPage.setInhaltFilterInSidebar(secondIdValue)
                .appearsOfLoader()
                .checkCheckboxInhaltFilter(false, secondIdValue)
                .checkSizeValuesInhaltFilterInProducts();
        checkingNotContainsUrl(secondIdValue.replaceAll(" ", "-").toLowerCase());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
