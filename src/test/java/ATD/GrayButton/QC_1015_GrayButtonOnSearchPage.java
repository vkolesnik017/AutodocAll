package ATD.GrayButton;

import ATD.Main_page_Logic;
import Common.SetUp;
import AWS.Login_aws;
import AWS.WishlistReminderAvailability_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_1015_GrayButtonOnSearchPage {

    private String email = "QC_1015_autotestATD@mailinator.com";

    private Mailinator mailinator = new Mailinator();
    private WishlistReminderAvailability_aws wishlistReminderAvailability = new WishlistReminderAvailability_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "Checking gray button on listing and product page and check requests in AWS")
    public void testGrayButton(String route) {
        new Login_aws().loginInAwsWithOpen();
        open(wishlistReminderAvailability.urlWithCurrentDate);
        String articleProduct = wishlistReminderAvailability.getTextFromArticle();
        String idProduct = wishlistReminderAvailability.getTextFromId();
        int beforeCountRequests = wishlistReminderAvailability.getBeforeCountRequests();
        openPage(route);
        new Main_page_Logic().useSearch(articleProduct)
                         .clickButtonProductById(idProduct)
                         .sendRequestByGrayButtonFromSearchPage(email);
        mailinator.openEmail(email)
                        .checkLetterInfoText(1, "moments ago", "Wir bearbeiten");
        open(wishlistReminderAvailability.urlWithCurrentDate);
        wishlistReminderAvailability.checkAfterCountRequest(beforeCountRequests, idProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
