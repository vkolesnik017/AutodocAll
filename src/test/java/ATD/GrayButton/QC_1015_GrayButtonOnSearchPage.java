package ATD.GrayButton;

import ATD.*;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QC_1015_GrayButtonOnSearchPage {

    private String email = "CheckGrayButton@mailinator.com";

    private Mailinator mailinator = new Mailinator();
    private Search_page search_page = new Search_page();
    private WishlistReminderAvailability_aws wishlistReminderAvailability = new WishlistReminderAvailability_aws();
    private Search_page_Logic search_page_logic = new Search_page_Logic();

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
        String articleProduct = wishlistReminderAvailability.articleOfFirstProduct().text();
        String idProduct = wishlistReminderAvailability.idOfFirstProduct().text();
        int beforeCountRequests = Integer.parseInt(wishlistReminderAvailability.numberOfRequestsInFirstProduct().text());
        openPage(route);
        new Main_page().useSearch(articleProduct);
        search_page_logic.clickButtonProductById(idProduct)
                         .sendRequestByGrayButtonFromSearchPage(email);
        mailinator.openEmail(email);
        mailinator.letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("Wir bearbeiten"));
        open(wishlistReminderAvailability.urlWithCurrentDate);
        int afterCountRequests = Integer.parseInt(wishlistReminderAvailability.numberOfRequestsInProductByHisId(idProduct).text());
        assertEquals(afterCountRequests, beforeCountRequests + 1);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
