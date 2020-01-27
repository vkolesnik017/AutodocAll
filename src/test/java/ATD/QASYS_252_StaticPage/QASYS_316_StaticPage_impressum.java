package ATD.QASYS_252_StaticPage;


import ATD.Impressum_static_page;
import ATD.Main_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.clickable;
import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_316_StaticPage_impressum {
    private Main_page mainPage = new Main_page();
    private Impressum_static_page impressumStaticPage = new Impressum_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Oleg Romanyuta")
    @Flaky
    @Description(value = "Test checks elements on impressum page")
    public void checkImpressumPageElements(String route) {
        openPage(route);
        closeCookiesFooterMessage();
        mainPage.clickImpressum();
        impressumStaticPage.title().shouldBe(visible);
        impressumStaticPage.logo().shouldBe(visible);
        impressumStaticPage.mainBlock().shouldBe(visible);
        impressumStaticPage.emailLink().shouldBe(clickable);
        impressumStaticPage.webLink().shouldBe(clickable);
        impressumStaticPage.map().shouldBe(visible);
    }
}
