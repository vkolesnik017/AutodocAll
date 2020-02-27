package ATD.StaticPage;

import ATD.Main_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_1175_StaticPage_contacts {

    private Main_page mainPage = new Main_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Chelombitko")
    @Flaky
    @Description(value = "Test checks elements on contacts page")
    public void checkContactsPageElements(String route) {
        openPage(route);
        mainPage.clickContact()
                .checkContactsBlock()
                .checkAlreadyPlacedOrderBlock()
                .checkNonPlacedOrderBlock();
    }
}