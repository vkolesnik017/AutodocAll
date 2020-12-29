package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import PKW.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1905_GdprFaqOnProductPage {

    private String mail;
    private Product_page_Logic productPageLogic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working of faq form on product page")
    public void testGdprWorkingFaqFormProductPage(String route) {
        openPage(route);
        mail = productPageLogic.clickBtnFaqTab().clickBtnAskQuestionInFaqForm()
                .checkingDatenschutzerklarungLinkBehavior(productPageLogic.faqFormDatenschutzerklarungLink())
                .fillRequiredFieldsFaqFormAndCheckBehavior("qc_1905_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyForMail(this.mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
