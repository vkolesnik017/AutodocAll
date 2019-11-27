package ATD.QASYS_252_StaticPage;


import ATD.Contact_static_page;
import ATD.Main_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_313_StaticPage_contacts {
    private Main_page mainPage = new Main_page();
    private Contact_static_page contactStaticPage = new Contact_static_page();

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
    @Description(value = "Test checks elements on contacts page")
    public void checkContactsPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickContact();
        contactStaticPage.contactsBlock().shouldBe(visible);
        contactStaticPage.addressBlock().shouldBe(visible);
        contactStaticPage.orderEmailInput().shouldNotBe(visible);
        contactStaticPage.haveOrderButton().click();
        contactStaticPage.haveOrderBlock().shouldBe(visible);
        contactStaticPage.submitOrderButton().click();
        contactStaticPage.orderIdError().shouldBe(visible);
        contactStaticPage.phoneNumberError().shouldBe(visible);
        contactStaticPage.emailError().shouldBe(visible);
        contactStaticPage.haveNoOrderButton().click();
        contactStaticPage.noOrderEmailInput().shouldBe(visible);
        contactStaticPage.haveNoOrderBlock().shouldBe(visible);
        contactStaticPage.submitNoOrderButton().click();
        contactStaticPage.noOrderEmailError().shouldBe(visible);
        contactStaticPage.noOrderDetailError().shouldBe(visible);
    }
}
