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

public class QC_3458 {


    private String oemIdValue = "BMW Longlife-01";
    private String spezifikationIdValue = "ACEA A3";

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
    @Description(value = "Test Interaction of the OEM-FREIGABE tank filter with the SPEZIFIKATION  line filter on oil listings")
    public void testInteractionOemAndSpezifikatioFiltersOnOilListing(String route) {
        openPage(route);
        motoroilBrandPageLogic.setOemFreigabeFilterInSidebar(oemIdValue)
                .appearsOfLoader()
                .checkSelectedCheckBoxOemFreigabeFilter(oemIdValue)
                .checkDisplaySelectedOemFreigabeFilterInProducts(oemIdValue);
        checkingContainsUrl("bmw-longlife-01");
        motoroilBrandPageLogic.setSpecificationFilterInSidebar(spezifikationIdValue)
                .appearsOfLoader()
                .checkSelectedCheckBoxSpecificationFilter(spezifikationIdValue)
                .checkDisplaySelectedSpecificationFilterInProducts(spezifikationIdValue)
                .checkSelectedCheckBoxOemFreigabeFilter(oemIdValue)
                .checkDisplaySelectedOemFreigabeFilterInProducts(oemIdValue);
        checkingContainsUrl("bmw-longlife-01");
        checkingContainsUrl("specification=acea-a3");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
