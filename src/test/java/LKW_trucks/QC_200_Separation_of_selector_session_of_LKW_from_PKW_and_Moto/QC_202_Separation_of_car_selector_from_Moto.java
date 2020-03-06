package LKW_trucks.QC_200_Separation_of_selector_session_of_LKW_from_PKW_and_Moto;

import ATD.Main_page_Logic;
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

public class QC_202_Separation_of_car_selector_from_Moto {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks separation of a car selector session from Motorcycle")
    public void testChecksSeparationOfCarSelectorSessionFromMoto(String route) {
        openPage(route);
        new Main_page_Logic().chooseBrandModelTypeInSelector("MERCEDES-BENZ", "38539", "130593")
                .clickSearchBtnInVerticalSelectorWhenSelectedAllFields()
                .checkSuccessfullyPageLoading()
                .selectMotoCategory().checkSuccessfullyMotoPageLoading()
                .checkOfEmptyMotoSelector().selectChildCategory()
                .checkSuccessfullyChildCategoryPageLoading();
    }
}
