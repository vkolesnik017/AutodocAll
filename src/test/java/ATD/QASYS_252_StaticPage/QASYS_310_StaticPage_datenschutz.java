package ATD.QASYS_252_StaticPage;


import ATD.Datenschutz_page;
import ATD.Main_page;
import ATD.SetUp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_310_StaticPage_datenschutz {
    private Main_page mainPage = new Main_page();
    private Datenschutz_page datenschutzPage = new Datenschutz_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    public void checkDatenschutzPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickDatenschutz();
        datenschutzPage.titlePage().shouldBe(visible);
        datenschutzPage.logo().shouldBe(visible);
        datenschutzPage.mainBlock().shouldBe(visible);
        close();
    }
}
