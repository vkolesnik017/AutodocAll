package ATD.QASYS_225_General_Common;

import ATD.CommonMethods;
import ATD.DataBase;
import ATD.Main_page;
import ATD.SetUp;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.testMail;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

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
    String Blog = "/info";
    String Altolentsorgung = "/services/hinweise-zur-altolentsorgung";
    String Agb = "/services/agb";
    String Widerruf = "/services/widerruf";
    String Datenschutz = "/services/datenschutz";
    String Zahlung = "/services/zahlung";
    String Versand = "/services/versand";
    String Contact = "/services/contacts";
    String Retouren = "/retoure";
    String Austauschartikel = "/services/austauschartikel";

    String Beleuchtung = "/autoteile/beleuchtung";
    String Stobdampfer = "/autoteile/stossdampfer-10221";
    String Kupplungssatz = "/autoteile/kupplungssatz-10151";
    String Querlenker = "/autoteile/lenker-quer-langs-schrag-10671";
    String Radlager = "/autoteile/radlager-10679";
    String Autopflege = "/autopflege";
    String SucheNachAutomodelle = "/ersatzteile";
    String NachHerstellerEinkaufen = "/ersatzteile-models";
    String NachModellEinkaufen = "/ersatzteile-cars";


    private Main_page main_page = new Main_page();
    private CommonMethods commonMethods = new CommonMethods();
    private DataBase db = new DataBase();

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
    @Test(dataProvider = "route", enabled = false)
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
    public void checkingLinksInFooter(String route) throws SQLException {
        open(route);
        main_page.footerForm().scrollTo();
        main_page.clickAboutUs();
        commonMethods.checkingUrl(route + aboutUs);
        main_page.clickImpressum();
        commonMethods.checkingUrl(route + Impressum);
        main_page.clickVacancies();
        commonMethods.checkingUrl(route + Vacancies);
        main_page.clickBonusprogramm();
        commonMethods.checkingUrl(route + Bonusprogramm);
        main_page.clickSponsorship();
        commonMethods.checkingUrl(route + Sponsorship);
        main_page.clickPartnership();
        commonMethods.checkingUrl(route + Partnership);
        main_page.clickPresse();
        commonMethods.checkingUrl(route + Presse);
        main_page.clickMobileApp();
        commonMethods.checkingUrl(route + MobileApp);
        main_page.clickAutodocClub();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club") + "/?_ga=");
        main_page.clickBlog();
        commonMethods.checkingUrl(route + Blog);
        main_page.clickVideoTutorials();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club") + "/manuals?_ga=");
        main_page.clickAltolentsorgung();
        commonMethods.checkingUrl(route + Altolentsorgung);
        main_page.clickAgb();
        commonMethods.checkingUrl(route + Agb);
        main_page.clickWiderruf();
        commonMethods.checkingUrl(route + Widerruf);
        main_page.clickDatenschutz();
        commonMethods.checkingUrl(route + Datenschutz);
        main_page.clickZahlung();
        commonMethods.checkingUrl(route + Zahlung);
        main_page.clickVersand();
        commonMethods.checkingUrl(route + Versand);
        main_page.clickContact();
        commonMethods.checkingUrl(route + Contact);
        main_page.clickRetouren();
        commonMethods.checkingUrl(route + Retouren);
        main_page.clickAustauschartikel();
        commonMethods.checkingUrl(route + Austauschartikel);
        main_page.clickBeleuchtung();
        commonMethods.checkingUrl(route + Beleuchtung);
        main_page.clickStobdampfer();
        commonMethods.checkingUrl(route + Stobdampfer);
        main_page.clickKupplungssatz();
        commonMethods.checkingUrl(route + Kupplungssatz);
        main_page.clickQuerlenker();
        commonMethods.checkingUrl(route + Querlenker);
        main_page.clickRadlager();
        commonMethods.checkingUrl(route + Radlager);
        main_page.clickAutopflege();
        commonMethods.checkingUrl(route + Autopflege);
        main_page.clickSucheNachAutomodelle();
        commonMethods.checkingUrl(route + SucheNachAutomodelle);
        main_page.clickNachHerstellerEinkaufen();
        commonMethods.checkingUrl(route + NachHerstellerEinkaufen);
        main_page.clickNachModellEinkaufen();
        commonMethods.checkingUrl(route + NachModellEinkaufen);
    }

    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route")
    @Description(value = "Test check newsletter subscription field")
    public void checkingSubscriptionField(String route) {
        open(route);
        main_page.footerForm().scrollTo();
        main_page.subscriptionButton().click();
        main_page.subscriptionTooltipPopup().shouldHave(Condition.text("Bitte geben Sie eine gültige E-mail Adresse an"));
        main_page.subscriptionField().setValue("123456");
        main_page.subscriptionButton().click();
        main_page.subscriptionTooltipPopup().shouldHave(Condition.text("Bitte geben Sie eine gültige E-mail Adresse an"));
        main_page.subscriptionField().setValue(testMail);
        main_page.subscriptionButton().click();
        main_page.subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        main_page.subscriptionPopupClose().click();
        main_page.subscriptionMailCheckbox().click();
        main_page.subscriptionButton().click();
        main_page.subscriptionSuccessPopup().shouldHave(Condition.text("Herzlichen Dank! Viel Spaß beim Shoppen!"));
        main_page.subscriptionPopupClose().click();
        main_page.clickDatenschutzInSubscription();
        commonMethods.checkingUrl(route + Datenschutz);

    }
}
