package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Main_page_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3010 {
    Main_page_Logic mainPage = new Main_page_Logic();
    DataBase db = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking prioritization of child categories in TOP block on main page")
    public void testCheckPrioritizationChildsInTopBlock(String route) throws SQLException {
        openPage(route);
        List<String> childWithOutCar = mainPage.presenceOfTopBrandsBlock().getAllChildCategories();
        mainPage.chooseBrandModelTypeInSelector("GAZ", "36959", "121901")
                .clickSearchBtnInVerticalSelectorWhenSelectedAllFields();
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list20"));
        mainPage.clickOnMainLogo();
        checkingContainsUrl(db.getRouteByRouteName("DE", "main"));
        List<String> childWithCar = mainPage.getAllChildCategories();
        Assert.assertEquals(childWithCar, childWithOutCar);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
