package ATD.General_Common.QC_1571_Header;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1604 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }


    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks presence categories in header")
    public void testPresenceCategoriesInHeader(String route){
        openPage(route);
        new Main_page_Logic().checkPresenceCategoriesInHeader();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
