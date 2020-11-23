package ATD.Selectors.QC_729_PopUpsOfSelectors;

import ATD.Main_page_Logic;
import ATD.Maker_car_list_page_Logic;
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
import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_2788_UsingAutoInPopupWindowWithSelector {

    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @DataProvider(name = "routes2", parallel = true)
    Object[] dataProvider2() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories_maker");
    }

    @DataProvider(name = "routes3", parallel = true)
    Object[] dataProvider3() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Using auto in a popup window with a selector")
    public void testCheckUsingAutoInPopupWindowWithSelector(String route) {
        open(route);
        mainPageLogic.fillNumberKba("0000", "000")
                .clickKbaBtn();
        mainPageLogic.fillNumberKbaInPopup("0603", "419")
                .clickKbaBtnInPopup();
        new Maker_car_list_page_Logic().checkPresenceBlockWithSelectedVehicle();
        Assert.assertEquals(getNameRouteFromJSVarInHTML(), "maker_car_list");
    }

    @Test(dataProvider = "routes2")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Using auto in a popup window with a selector")
    public void test2CheckUsingAutoInPopupWindowWithSelector(String route) {
        open(route);
        mainPageLogic.fillNumberKba("0000", "000")
                .clickKbaBtn();
        mainPageLogic.fillNumberKbaInPopup("0603", "419")
                .clickKbaBtnInPopup();
        new Maker_car_list_page_Logic().checkPresenceBlockWithSelectedVehicle();
        Assert.assertEquals(getNameRouteFromJSVarInHTML(), "maker_car_list");
    }

    @Test(dataProvider = "routes3")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Using auto in a popup window with a selector")
    public void test3CheckUsingAutoInPopupWindowWithSelector(String route) {
        open(route);
        String urlPage = url();
        mainPageLogic.fillNumberKba("0000", "000")
                .clickKbaBtn();
        mainPageLogic.fillNumberKbaInPopup("0603", "419")
                .clickKbaBtnInPopup();
        mainPageLogic.checkAbsenceSelectorPopup();
        String urlAfterSearchVehicle = url();
        Assert.assertEquals(urlPage,urlAfterSearchVehicle);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
