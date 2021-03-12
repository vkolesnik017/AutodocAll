package ATD.Breadcrumbs.QC_2683_BreadcrumbsBlock;

import ATD.Motoroil_brand_page_Logic;
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
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;


public class QC_3243 {

     Motoroil_brand_page_Logic motoroilBrandPageLogic = new Motoroil_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_viscosity2,motoroil_search");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test check structure of bread crumbs on MotorOil listing page")
    public void testCheckStructureOfBreadcrumbsOnMotorOilPage(String route) {
        openPage(route);
        refresh();
        motoroilBrandPageLogic.checkingQuantityBreadCrumbs()
                .clickFirstBreadCrumb();
        checkingContainsUrl("autoteile");
        back();
        motoroilBrandPageLogic.clickSecondBreadCrumb();
        checkingContainsUrl("motoroel");
        back();
        motoroilBrandPageLogic.checkingPresenceAndNotClickableLastBreadCrumb();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }



}
