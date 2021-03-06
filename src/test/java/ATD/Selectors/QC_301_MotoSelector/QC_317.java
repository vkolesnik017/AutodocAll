package ATD.Selectors.QC_301_MotoSelector;

import ATD.Moto_Catalog_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_317 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to product page with out compatible motorcycle")
    public void testChecksTransitionToProductPageWithOutCompatibleMoto(String route) {
        open(route);

        new Moto_Catalog_page_Logic()
                .selectProductInSearchField("HF163")
                .presenceOfMotoIncompatibilityMessage("Erfahren Sie passende alternative Produkte für Ihr Motorrad.");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
