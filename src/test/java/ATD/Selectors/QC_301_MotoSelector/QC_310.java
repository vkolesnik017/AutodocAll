package ATD.Selectors.QC_301_MotoSelector;

import ATD.Moto_Catalog_model_page_Logic;
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

public class QC_310 {

    @BeforeClass

    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Presence Of Opening Selector")
    public void testChecksPresenceOfOpeningSelector(String route) {
        open(route);

        new Moto_Catalog_model_page_Logic()
                .visibilityOfOpeningSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
