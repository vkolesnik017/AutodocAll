package LKW_trucks.QC_200_Separation_of_selector_session_of_LKW_from_PKW_and_Moto;

import ATD.Moto_main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_206__Separation_of_moto_selector_session_from_LKW {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks separation of Moto selector session from LKW")
    public void testChecksSeparationOfMotoSelectorSessionFromLKW(String route) {
        openPage(route);

        new Moto_main_page_Logic().selectMotoInHorizontalMotoSelector("4081", "12008", "115569")
                .checkSuccessfullyMotoCatalogPageLoading()
                .selectLKWCategory().checkSuccessfullyLKWPageLoading()
                .checkOfEmptySelector().selectChildCategory()
                .checkSuccessfullyChildCategoryPageLoading();
    }
}