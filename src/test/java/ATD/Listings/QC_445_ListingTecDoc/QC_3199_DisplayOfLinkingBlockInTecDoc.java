package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Category_car_list_page_Logic;
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

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3199_DisplayOfLinkingBlockInTecDoc {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list60");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking the display of the linking block on the TOP category on the technical listing with soft-404")
    public void testCheckDisplayOfLinkingBlockInTecDoc(String route) throws SQLException {
        openPage(route);
        new Category_car_list_page_Logic().displaySoft404Form()
                .displayTopLinkingBlockInSideBar()
                .clickOnBtnAddToBasketOfTopLinkingBLockInSidebar();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "category_car_list61"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
