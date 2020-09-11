package ATD.LKW_trucks.QC_200_Separation_of_selector_session_of_LKW_from_PKW_and_Moto;

import ATD.*;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_201_Separation_of_car_selector_session_from_LKW {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks separation of a car selector session from LKW")
    public void testChecksSeparationOfCarSelectorSessionFromLKW(String route) {
        openPage(route);
        new Main_page_Logic().chooseBrandModelTypeInSelector("MERCEDES-BENZ", "38539", "130593")
                .clickSearchBtnInVerticalSelectorWhenSelectedAllFields()
                .checkSuccessfullyPageLoading()
                .selectLKWCategory().checkSuccessfullyLKWPageLoading()
                .checkOfEmptySelector().selectChildCategory()
                .checkSuccessfullyChildCategoryPageLoading();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
