package PKW.Selectors.QC_723_SelectorsVertical;

import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_724 {

    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @DataProvider(name = "routesSubRut", parallel = true)
    Object[] dataProviderSubRut() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil,parts_group,listing_lights,listing_lights_car");
    }


    @Test(dataProvider = "routes")
    @Owner(value = "Sergey_QA")
    @Description(value = "tooltip with error when selector has empty field with mark")
    public void testCheckTooltipErrorWhenSelectorEmptyFieldWithMark(String route) {
        openPage(route);
        mainPageLogic.selectVehicleInSelector("0","0","0");
        mainPageLogic .checkErrorTooltipForMarkInSelector();
    }

    @Test(dataProvider = "routesSubRut")
    @Owner(value = "Sergey_QA")
    @Description(value = "tooltip with error when selector has empty field with mark")
    public void testCheckTooltipErrorWhenSelectorEmptyFieldWithMark_SubRut(String route) {
        openPage(route);
        mainPageLogic.selectVehicleInSelector("0","0","0");
        mainPageLogic .checkErrorTooltipForMarkInSelector();
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
