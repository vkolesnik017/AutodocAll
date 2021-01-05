package ATD.LKW_trucks.QC_200_Separation_of_selector_session_of_LKW_from_PKW_and_Moto;

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

public class QC_204 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks separation of LKW selector session from Moto")
    public void testChecksSeparationOfLKWSelectorSessionFromMoto(String route) {
        openPage(route);
        new LKW_main_page_Logic().selectTruckInSelector("24", "714", "1004434")
                .checkSuccessfullyPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/daf/45?car_id=1004434")
                .selectMotoCategory().checkSuccessfullyMotoPageLoading()
                .checkOfEmptyMotoSelector().selectChildCategory()
                .checkSuccessfullyChildCategoryPageLoading();

    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
