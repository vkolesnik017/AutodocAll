package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;

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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3443 {


    private String literIdValue = "1L";
    private String spezifikationIdValue = "ACEA A1";

    private Motoroil_brand_page_Logic motoroilBrandPageLogic = new Motoroil_brand_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_viscosity2,motoroil_search");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Interaction of the INHALT [LITER] tank filter with the SPEZIFIKATION  line filter on oil listings")
    public void testInteractionInhaltAndOemFiltersOnOilListing(String route) {
        openPage(route);
        motoroilBrandPageLogic.setInhaltFilterInSidebar(literIdValue)
                .appearsOfLoader()
                .checkSelectedCheckBoxInhaltFilter(literIdValue)
                .checkDisplaySelectedInhaltFilterInProducts(literIdValue);
        checkingContainsUrl("volume=1");
        motoroilBrandPageLogic.setSpecificationFilterInSidebar(spezifikationIdValue)
                .appearsOfLoader()
                .checkSelectedCheckBoxSpecificationFilter(spezifikationIdValue)
                .checkDisplaySelectedSpecificationFilterInProducts(spezifikationIdValue)
                .checkSelectedCheckBoxInhaltFilter(literIdValue)
                .checkDisplaySelectedInhaltFilterInProducts(literIdValue);
        checkingContainsUrl("volume=1");
        checkingContainsUrl("specification=acea-a1");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
