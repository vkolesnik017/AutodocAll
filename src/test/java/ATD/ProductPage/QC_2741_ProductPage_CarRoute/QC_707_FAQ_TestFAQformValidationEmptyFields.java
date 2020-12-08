package ATD.ProductPage.QC_2741_ProductPage_CarRoute;


import Common.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_707_FAQ_TestFAQformValidationEmptyFields {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks faq form validation with empty fields")
    public void testInputValidationFAQ() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product14"));
        new Product_page_Logic().faqValidationEmptyFields()
                                .faqValidationWithName()
                                .faqValidationWithMail()
                                .faqValidationWithMessage();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
