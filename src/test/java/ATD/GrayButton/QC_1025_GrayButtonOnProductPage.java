package ATD.GrayButton;

import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.Login_aws;
import AWS.WishlistReminderAvailability_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_1025_GrayButtonOnProductPage {

    private String email = "QC_1025_autotestATD@mailinator.com";

    private WebMail webMail = new WebMail();
    private Product_page_Logic product_page = new Product_page_Logic();
    private WishlistReminderAvailability_aws wishlistReminderAvailability = new WishlistReminderAvailability_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "Checking gray button on listing and product page and check requests in AWS")
    public void testGrayButton(String route) {
        new Login_aws().loginInAwsWithOpen();
        open(wishlistReminderAvailability.urlWithCurrentDate);
        String idProduct = wishlistReminderAvailability.getTextFromId();
        int beforeCountRequests = wishlistReminderAvailability.getBeforeCountRequests();
        product_page.openProductPageById(route, idProduct)
                .sendRequestByGrayButtonFromProductPage(email);
        webMail.openMail(email)
                .checkLetterInfoText(1, "moments ago", "Wir bearbeiten");
        open(wishlistReminderAvailability.urlWithCurrentDate);
        wishlistReminderAvailability.checkAfterCountRequest(beforeCountRequests, idProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
