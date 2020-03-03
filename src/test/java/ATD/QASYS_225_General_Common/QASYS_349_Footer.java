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

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_349_Footer {

/*    private String appUrl = "https://apps.apple.com/US/app/id1014949597?mt=8";
    private String facebook = "https://www.facebook.com/autodoc.de/";
    private String youTube = "https://www.youtube.com/channel/UCH1orNkIIGZ1jJRjhgY4JeA";
    private String instagram = "https://www.instagram.com/autodoc_autoparts/";


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
    @Test(dataProvider = "route", enabled = true)
    @Description(value = "TC1 Test check App installation links")
    public void checkingAppLinks(String route) {
        openPage(route);
        main_page.footerForm().scrollTo();
        main_page.appGoogleButton().click();
        commonMethods.checkingUrl(appUrl);
        main_page.appAppleButton().click();
        commonMethods.checkingUrl(appUrl);
    }

    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route")
    @Description(value = "TC2 Test follow the link and check url")
    public void checkingLinksInFooter(String route) throws SQLException {
        String shop = getShopFromRoute(route);
        openPage(route);
        main_page.footerForm().scrollTo();
        main_page.clickAboutUs();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAboutUs"));
        main_page.clickImpressum();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticImpressum"));
        main_page.clickVacancies();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "vacancies"));
        main_page.clickBonusprogramm();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "bonus_system"));
//        main_page.clickSponsorship();
//        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticSponsorship"));
        main_page.clickPartnership();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticPartnership"));
        main_page.clickPresse();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticPresse"));
        main_page.clickMobileApp();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticMobileApp"));
        main_page.clickAutodocClub();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club_main") + "/?_ga=");
        main_page.clickBlog();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "info_section_index"));
        main_page.clickVideoTutorials();
        commonMethods.checkingUrlAndCloseTab(db.getRouteByRouteName(getCurrentShopFromJSVarInHTML(), "club_main") + "/manuals?_ga=");
        main_page.clickAltolentsorgung();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAltolentsorgung"));
        main_page.clickAgb();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAgb"));
        main_page.clickWiderruf();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticWiderruf"));
        main_page.clickDatenschutz();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticDatenschutz"));
        main_page.clickZahlung();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticZahlung"));
        main_page.clickVersand();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticVersand"));
        main_page.clickContact();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticContact"));
        main_page.clickRetouren();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "return_return"));
        main_page.clickAustauschartikel();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "staticAustauschartikel"));
        main_page.clickBeleuchtung();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name_parent2"));
        main_page.clickStobdampfer();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name2"));
        main_page.clickKupplungssatz();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name3"));
        main_page.clickQuerlenker();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name4"));
        main_page.clickRadlager();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "category_name5"));
        main_page.clickAutopflege();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "index_chemicals"));
        main_page.clickSucheNachAutomodelle();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "makers"));
        main_page.clickNachHerstellerEinkaufen();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "hub_groups"));
        main_page.clickNachModellEinkaufen();
        commonMethods.checkingUrl(route + "/" + db.getRouteByRouteName(shop, "hub_models"));
        }

    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route", enabled = true)
    @Description(value = "TC3 Test check country dropdown and transitions")
    public void checkingDropdownCountry(String route) throws SQLException {
        openPage(route);
        main_page.footerForm().scrollTo();
        main_page.checkingCountriesSubscription();
    }

    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route", enabled = true)
    @Description(value = "TC4 Test check newsletter subscription field")
    public void checkingSubscriptionField(String route) {
        openPage(route);
        main_page.footerForm().scrollTo();
        main_page.subscriptionButton().click();
        main_page.subscriptionErrTooltip().shouldHave(Condition.text("Bitte geben Sie eine gültige E-mail Adresse an"));
        main_page.subscriptionMailField().setValue("123456");
        main_page.subscriptionButton().click();
        main_page.subscriptionErrTooltip().shouldHave(Condition.text("Bitte geben Sie eine gültige E-mail Adresse an"));
        main_page.subscriptionMailField().setValue(testMail);
        main_page.subscriptionButton().click();
        main_page.subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        main_page.subscriptionPopupClose().click();
        main_page.subscriptionMailCheckbox().click();
        main_page.subscriptionButton().click();
        main_page.subscriptionSuccessPopup().shouldHave(Condition.text("Herzlichen Dank! Viel Spaß beim Shoppen!"));
        main_page.subscriptionPopupClose().click();

    }

    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route", enabled = true)
    @Description(value = "TC5 Test check social network links transitions and footer center blocks")
    public void checkingSocial(String route) {
        openPage(route);
        main_page.footerForm().scrollTo();
        main_page.techAllianceBlock().shouldBe(Condition.visible);
        main_page.workTimeBlock().shouldBe(Condition.visible);
        main_page.copyrightBlock().shouldBe(Condition.visible);
        main_page.facebookButton().click();
        commonMethods.checkingUrlAndCloseTab(facebook);
        main_page.youtubeButton().click();
        commonMethods.checkingUrlAndCloseTab(youTube);
        main_page.instagramButton().click();
        commonMethods.checkingUrlAndCloseTab(instagram);
    }

    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route", enabled = true)
    @Description(value = "TC6 Test check Datenschutzerklärung link")
    public void checkingDatenschutzLink(String route) throws SQLException {
        openPage(route);
        main_page.footerForm().scrollTo();
        main_page.clickDatenschutzInSubscribeBlock().click();
        commonMethods.checkingUrlAndCloseTab(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "staticDatenschutz"));
    }*/
}
