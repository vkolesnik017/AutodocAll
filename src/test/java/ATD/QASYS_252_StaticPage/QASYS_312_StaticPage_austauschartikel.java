package ATD.QASYS_252_StaticPage;


import ATD.Austauschartikel_static_page;
import ATD.Main_page;
import ATD.SetUp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_312_StaticPage_austauschartikel {
    private Main_page mainPage = new Main_page();
    private Austauschartikel_static_page austauschartikelStaticPage = new Austauschartikel_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    public void checkAustauschartikelPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickAustauschartikel();
        close();
    }
}
