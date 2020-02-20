package ATD.GrayButton;

import ATD.Main_page;
import ATD.Product_page_Logic;
import ATD.Search_page;
import ATD.SetUp;
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

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QC_1025_GrayButtonOnProductPage {

    private String email = "CheckGrayButton@mailinator.com";

    private Mailinator mailinator = new Mailinator();
    private Search_page search_page = new Search_page();
    private Product_page_Logic product_page = new Product_page_Logic();
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
        String articleProduct = wishlistReminderAvailability.articleOfFirstProduct().text();
        String idProduct = wishlistReminderAvailability.idOfFirstProduct().text();
        int beforeCountRequests = Integer.parseInt(wishlistReminderAvailability.numberOfRequestsInFirstProduct().text());
        open(route);
        new Main_page().useSearch(articleProduct);
        closeCookiesFooterMessage();
        search_page.imageProductById(idProduct).click();
        product_page.grayButton().click();
        product_page.emailFieldInPopUpOfGrayBtn().setValue(email);
        product_page.checkboxInPopUpOfGrayBtn().click();
        product_page.sendButtonInPopUpOfGrayBtn().click();
        product_page.closeSuccessPopUpOfGrayBtn().click();
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
