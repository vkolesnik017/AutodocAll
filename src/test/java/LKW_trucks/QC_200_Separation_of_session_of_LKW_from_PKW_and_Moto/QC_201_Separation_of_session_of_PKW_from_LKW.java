package LKW_trucks.QC_200_Separation_of_session_of_LKW_from_PKW_and_Moto;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_201_Separation_of_session_of_PKW_from_LKW {
    private Main_page_Logic mainPage = new Main_page_Logic();
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "main","main");
    }
    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks elements on main page LKW")
    public void testChecksSeparationOfSessionOfPKWFromLKW(String route) {
        open(route);
        mainPage.chooseBrandModelTypeInSelector("MERCEDES-BENZ","38539","130593");
        Categories_page_Logic categoriesPage = mainPage.clickSearchBtnInVerticalSelectorWhenSelectedAllFields();
        categoriesPage.checkSuccessfullyPageLoading();
        LKW_main_page_Logic lkwPage = categoriesPage.selectLKWCategory();
        lkwPage.checkSuccessfullyLKWPageLoading().checkOfEmptySelector().selectChildCategory();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
