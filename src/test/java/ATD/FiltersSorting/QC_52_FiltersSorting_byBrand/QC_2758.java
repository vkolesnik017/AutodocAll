package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;

import ATD.Tyre_form_page_Logic;
import ATD.Tyres_dimension_page_Logic;
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

public class QC_2758 {

    private Tyres_dimension_page_Logic tyresPage = new Tyres_dimension_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }
    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension2,tyres_dimension3,tyres_dimension5,tyres_dimension4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check Work of SliderInGenericBlock ")
    public void testCheckWorkOfSliderInGenericBlock(String route) {
        openPage(route);
        String totalCountOfProduct = tyresPage.getTotalCountOfProductInListing();
        tyresPage.selectAnyBrand(0).compareTotalCountOfProduct(totalCountOfProduct);
    }

    @DataProvider(name = "routesTyres", parallel = true)
    Object[] dataProviderTyres() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_size6,tyres_size7,tyres_size4,tyres_size5");
    }

    @Test(dataProvider = "routesTyres")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check Work of SliderInGenericBlock ")
    public void testCheckWorkOfSliderInGenericBlockTyres(String route) {
        openPage(route);
        String totalCountOfProduct = tyresPage.getTotalCountOfProductInListing();
        tyresPage.selectAnyBrand(0).compareTotalCountOfProduct(totalCountOfProduct);
    }

    @DataProvider(name = "routesTyresForm", parallel = true)
    Object[] dataProviderTyresForm() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form,tyre_form2,tyre_form3");
    }

    @Test(dataProvider = "routesTyresForm")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check Work of SliderInGenericBlock ")
    public void testCheckWorkOfSliderInGenericBlockTyresForm(String route) {
        openPage(route);
        String totalCountOfProduct = tyresPage.getTotalCountOfProductInListing();
        tyresPage.selectAnyBrand(0).compareTotalCountOfProduct(totalCountOfProduct);
    }

    @DataProvider(name = "routesForm", parallel = true)
    Object[] dataProviderForm() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form4");
    }

    @Test(dataProvider = "routesForm")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check Work of SliderInGenericBlock ")
    public void testCheckWorkOfSliderInGenericBlockForm(String route) {
        openPage(route);
        String totalCountOfProduct = tyresPage.getTotalCountOfProductInListing();
        new Tyre_form_page_Logic().selectAnyOneBrand(0);
        tyresPage.compareTotalCountOfProduct(totalCountOfProduct);
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
