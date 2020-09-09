package LKW_trucks.QC_82_TruckSelectorBlock;

import ATD.LKW_main_page_Logic;
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

public class QC_157_PopUpOfSpecificationsInTruckSelector {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks pop-up of specification in truck selector")
    public void testChecksPopUpOfSpecificationInTruckSelector(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .visibilityOfPopUpSpecification()
                .selectSpecificationInPopUp()
                .checkSuccessfullyMakerCarListPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/volvo/fh-16-ii?car_id=1021790");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
