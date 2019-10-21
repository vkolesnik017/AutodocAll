package ATD;

import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QASYS_361_GDPR_Contacts {
    private Contact_static_page contact_static_page = new Contact_static_page();
    private Main_page mainPage = new Main_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new CommonMethods().setUpShop("prod", "AT");
    }

    @Test(dataProvider = "route", enabled = false)
    public void checkingNoOrderTab(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickContact();
        contact_static_page.openedTabTitle().shouldHave(Condition.text("Ich habe noch keine Bestellung aufgegeben"));
        contact_static_page.privacyPolicyDatenschutzerklarungLink().click();
        switchTo().window(1);
        new Datenschutz_page().titlePage().shouldHave(Condition.text("DATENSCHUTZERKLÄRUNG"));
        switchTo().window(0);
        contact_static_page.fillRequiredFieldsNoOrder()
                .sendenButton().click();
        contact_static_page.successPopup().should(Condition.appear).shouldHave(Condition.text("Vielen Dank!"));
        contact_static_page.successPopupButton();
        close();
    }

    @Test(dataProvider = "route")
    public void checkingOrderTab(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickContact();
        contact_static_page.orderTab().click();
        contact_static_page.openedTabTitle().shouldHave(Condition.text("Ich habe schon einen Auftrag erteilt"));
        contact_static_page.privacyPolicyDatenschutzerklarungLink().click();
        switchTo().window(1);
        String test = "DATENSCHUTZERKLÄRUNG";
        test.replace("Ä", "A");
        new Datenschutz_page().titlePage().shouldHave(Condition.text(test));
        switchTo().window(0);
        contact_static_page.fillRequiredFieldsOrder()
                .sendenButton().click();
        contact_static_page.successPopup().should(Condition.appear).shouldHave(Condition.text("Vielen Dank!"));
        contact_static_page.successPopupButton();
        close();
    }
}

