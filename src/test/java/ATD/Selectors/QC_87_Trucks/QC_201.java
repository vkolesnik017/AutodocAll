package ATD.Selectors.QC_87_Trucks;

import ATD.*;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_201 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks separation of a car selector session from LKW")
    public void testChecksSeparationOfCarSelectorSessionFromLKW(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().chooseBrandModelTypeInSelector("MERCEDES-BENZ", "38539", "130593")
                .clickSearchBtnInVerticalSelectorWhenSelectedAllFields();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "maker_car_list17"));
        new Categories_page_Logic().selectLKWCategory().checkSuccessfullyLKWPageLoading()
                .checkOfEmptySelector().selectChildCategory()
                .checkSuccessfullyChildCategoryPageLoading();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
