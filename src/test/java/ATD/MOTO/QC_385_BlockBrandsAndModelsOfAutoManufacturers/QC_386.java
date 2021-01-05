package ATD.MOTO.QC_385_BlockBrandsAndModelsOfAutoManufacturers;

import ATD.Moto_Categories_maker_page_Logic;
import ATD.Moto_Categories_page_Logic;
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

public class QC_386 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2,moto_category_maker3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks the presence of the title block brands and models  of automakers")
    public void testChecksPresenceOfBrandsAndModelsTitleOfAutomakers(String route) {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .presenceOfModelsTitle("MOTORCYCLES");
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks the presence of the title block brands and models  of automakers")
    public void testChecksPresenceOfBrandsAndModelsTitleOfAutomakersCategories(String route) {
        openPage(route);

        new Moto_Categories_page_Logic()
                .presenceOfBrandsTitle("Beliebte Motorrad und Rollermarken");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
