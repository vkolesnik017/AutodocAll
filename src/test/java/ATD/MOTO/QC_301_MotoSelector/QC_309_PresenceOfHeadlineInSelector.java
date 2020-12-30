package ATD.MOTO.QC_301_MotoSelector;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_309_PresenceOfHeadlineInSelector {
    @BeforeClass

    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2,moto_catalog2,moto_category_car_list4");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Presence Of Headline In Selector")
    public void testChecksPresenceOfHeadlineInSelector(String route) {
        openPage(route);

        new Moto_Catalog_page_Logic()
        .presenceOfHeadlineInSelector()
        .presenceOfSelectedMotoInHeadline();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
