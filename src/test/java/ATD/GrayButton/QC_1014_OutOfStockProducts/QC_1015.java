package ATD.GrayButton.QC_1014_OutOfStockProducts;

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

import java.util.List;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_1015 {

    private String email = "QC_1015_autotestATD@mailinator.com";

    private WebMail webMail = new WebMail();
    private WishlistReminderAvailability_aws wishlistReminderAvailability = new WishlistReminderAvailability_aws();
    private List <String> parameters;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
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
        parameters = wishlistReminderAvailability.getNumberOfRequestAndProductID(route, email, wishlistReminderAvailability.urlWithCurrentDate);
        webMail.openMail(email)
                        .checkLetterInfoText(1, "just now ago", "Wir bearbeiten");
        open(wishlistReminderAvailability.urlWithCurrentDate);
        wishlistReminderAvailability.checkAfterCountRequest(parameters);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
