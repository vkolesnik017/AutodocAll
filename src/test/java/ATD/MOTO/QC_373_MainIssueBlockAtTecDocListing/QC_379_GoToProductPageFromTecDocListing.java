package ATD.MOTO.QC_373_MainIssueBlockAtTecDocListing;

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

public class QC_379_GoToProductPageFromTecDocListing {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list2,moto_category_car_list_model2");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition on Product page from TecDoc listing")
    public void testChecksGoToProductPageFromTecDocListing(String route) {
        openPage(route);
        String urlOfSelectedProduct = new Moto_Category_car_list_page_Logic().getIdOfProduct(0);

        new Moto_Category_car_list_page_Logic()
                .goToProductPageFromImageBrandTitle(urlOfSelectedProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
