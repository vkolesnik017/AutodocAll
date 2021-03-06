package ATD.Selectors.QC_87_Trucks;

import ATD.Moto_main_page_Logic;
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

public class QC_206 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
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
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
