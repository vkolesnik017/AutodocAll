package ATD.QASYS_225_General_Common;

import ATD.CommonMethods;
import ATD.Main_page;
import ATD.SetUp;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QASYS_349_Footer {
    private String appUrl = "https://apps.apple.com/US/app/id1014949597?mt=8";

    String aboutUs = "/services/about_us_big";
    String Impressum = "/services/impressum";
    String Vacancies = "/services/vacancies";
    String Bonusprogramm = "/bonus-system";
    String Sponsorship = "/services/sponsorship";
    String Partnership = "/services/partnership";
    String Presse = "/services/presse";
    String MobileApp = "/services/mobile-app";
    String AutodocClub = "";
    String Blog = "/info";
    String VideoTutorials = "";
    String Altolentsorgung = "/services/hinweise-zur-altolentsorgung";
    String Agb = "/services/agb";
    String Widerruf = "/services/widerruf";
    String Datenschutz = "/services/datenschutz";
    String Zahlung = "/services/zahlung";
    String Versand = "/services/versand";
    String Contact = "/services/contacts";
    String Retouren = "/retoure";
    String Austauschartikel = "/services/austauschartikel";


    private Main_page main_page = new Main_page();
    private CommonMethods commonMethods = new CommonMethods();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }


    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route")
    @Description(value = "Test check App installation links")
    public void checkingAppLinks(String route) {
        open(route);
        main_page.footerForm().scrollTo();
        main_page.appGoogleButton().click();
        commonMethods.checkingUrl(appUrl);
        main_page.appAppleButton().click();
        commonMethods.checkingUrl(appUrl);

    }

    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route", enabled = false)
    @Description(value = "Test follow the link and check url")
    public void checkingLinksInFooter(String route) {
        open(route);
        main_page.footerForm().scrollTo();

    }

}
