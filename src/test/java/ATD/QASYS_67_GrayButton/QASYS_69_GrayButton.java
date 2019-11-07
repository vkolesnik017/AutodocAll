package ATD.QASYS_67_GrayButton;

import ATD.Main_page;
import ATD.Product_page;
import ATD.Search_page;
import ATD.SetUp;
import AWS.Login_aws;
import AWS.WishlistReminderAvailability_aws;
import io.qameta.allure.Step;
import mailinator.Mailinator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QASYS_69_GrayButton {

    private String email = "CheckGrayButton@mailinator.com";

    private Mailinator mailinator = new Mailinator();
    private Search_page search_page = new Search_page();
    private Product_page product_page = new Product_page();
    private WishlistReminderAvailability_aws wishlistReminderAvailability = new WishlistReminderAvailability_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Step("я тут")
    @Test(dataProvider = "route")
    public void testGrayButton(String route) {
        new Login_aws().loginInAws();
        open(wishlistReminderAvailability.urlWithParameters);
        String articleProduct = wishlistReminderAvailability.articleOfFirstProduct().text();
        String idProduct = wishlistReminderAvailability.idOfFirstProduct().text();
        int beforeCountRequests = Integer.parseInt(wishlistReminderAvailability.numberOfRequestsInFirstProduct().text());
        open(route);
        new Main_page().useSearch(articleProduct);
        closeCookiesFooterMessage();
        search_page.buttonProductById(idProduct).click();
        search_page.emailFieldInPopUpOfGrayBtn().setValue(email);
        search_page.checkboxInPopUpOfGrayBtn().click();
        search_page.sendButtonInPopUpOfGrayBtn().click();
        search_page.closeSuccessPopUpOfGrayBtn().click();
        search_page.imageProductById(idProduct).click();
        product_page.grayButton().click();
        product_page.emailFieldInPopUpOfGrayBtn().setValue(email);
        product_page.checkboxInPopUpOfGrayBtn().click();
        product_page.sendButtonInPopUpOfGrayBtn().click();
        product_page.closeSuccessPopUpOfGrayBtn().click();
        mailinator.openEmail(email);
        mailinator.letter(1).shouldHave(text("moments ago")).shouldHave(text("Wir bearbeiten"));
        mailinator.letter(2).shouldHave(text("moments ago")).shouldHave(text("Wir bearbeiten"));
        open(wishlistReminderAvailability.urlWithParameters);
        int afterCountRequests = Integer.parseInt(wishlistReminderAvailability.numberOfRequestsInProductByHisId(idProduct).text());
        assertEquals(afterCountRequests, beforeCountRequests + 2);
        close();
    }

}
