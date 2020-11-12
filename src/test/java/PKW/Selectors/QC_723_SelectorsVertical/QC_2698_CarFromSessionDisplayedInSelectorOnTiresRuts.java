package PKW.Selectors.QC_723_SelectorsVertical;

import PKW.Catalog_page_Logic;
import PKW.Main_page_Logic;
import PKW.Tyres_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2698_CarFromSessionDisplayedInSelectorOnTiresRuts {

    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider()  {
        return new Common.SetUp("PKW").setUpShopsWithMainRoute("prod", "DE", "main");
    }


    @Test(dataProvider = "routes")
    @Owner(value = "Sergey_QA")
    @Description(value = "Car from session displayed in selector on tires ruts")
    public void testCheckCarFromSessionDisplayedInSelectorOnTiresRuts(String route) {
        openPage(route);
        mainPageLogic.chooseBrandInVerticalCarSelector("121")
                .chooseModelInVerticalCarSelector("8636")
                .chooseTypeInVerticalCarSelector("33470")
                .clickSearchBtnInVerticalSelectorWhenSelectedAllFields();
        new Catalog_page_Logic().checkPresenceTitleWithVehicleModel()
                .clickBtnTyresInHeader();
        new Tyres_page_Logic().checkPresenceCarTabs();

    }




    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
