package PKW.Retoure.QC_793_CheckingLettersOfReturns_PKW;

import AWS.Order_aws;
import Common.SetUp;
import PKW.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static PKW.CommonMethods.password;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_798 {

    private String mail = "QC_798_autotest@mailinator.com", orderNumber;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "DE", "main", "product10");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Verification of the letter 'Confirmation of receipt of the application' from AWS")
    public void testLetterConfirmationOfReturnFromAws(String route) throws SQLException {
        openPage(route);
        orderNumber = new Product_page_Logic().addProductToCart()
                .closeBtnOFPopupReviewIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .closePopupAfterOrder()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .setStatusOrderToVersendetVorkasse()
                .addDeliveryConditionGLS("0", "GLS")
                .openPopupOfAddReclamation()
                .chooseRandomCauseReturnInSelect()
                .fillInFormForMessageReture()
                .clickSaveReclamationButton();
        new WebMail().openMail(mail)
                .letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("Ihre Reklamation zur Bestellnummer: ".concat(orderNumber)));
    }

    @AfterMethod
    public void setStatusTestToOrder() {
        new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
        closeWebDriver();
    }
}
