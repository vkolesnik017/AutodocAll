package PKW.General_Common.QC_1912_Footer;

import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1913 {


    private Main_page_Logic mainPageLogic = new Main_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("PKW").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Positive Sending The Subscribe Form in the Footer")
    public void testSendingTheSubscribeForm(String route) {
        openPage(route);
        String mail = mainPageLogic.openRegistrationFormInHeader()
                .fillingRegistrationFields("qc_1913_");
        mainPageLogic.clickBtnToPRFromPopupAuthorizationSuccessfully()
                .checkingPresenceIdUser();
        mainPageLogic.positiveSendingSubscribeForm(mail);
        new WebMail().openMail(mail)
                .checkLetterInfoText(1, "just now", "Noch ein weiterer Schritt und Sie haben unseren Newsletter abonniert.")
                .openLetter(1);



    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}