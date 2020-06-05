package ATD.PrivateRoom.QC_600_FunctionalTabBankPR;

import ATD.*;
import AWS.Customer_search_aws;
import AWS.Customer_view_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_601_FillingOutFormInBankTab {

    private String mail = "QC_601_autotest@mailinator.com", nameReceiver, ibanNum, nameReceiverAWS, ibanNumAWS;
    private Profile_bank_page_Logic profile_bank_page_logic = new Profile_bank_page_Logic();
    private Customer_view_aws customer_view_aws = new Customer_view_aws();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the filling of the form in the Bank tab")
    public void testFillingOutFormInBankTab(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileBankPage()
                .clickBtnAddNewBankInfo()
                .fillingFieldReceiver("Test")
                .fillingFieldIBAN("RO18 BACX 0000 0015 3328 2000")
                .clickSaveFormBtn()
                .checkPresenceAndClosePopUpUpdate()
                .checkPresenceCurrentBankBlock();
        nameReceiver = profile_bank_page_logic.getNameInReceiverInCurrentBankBlock();
        ibanNum = profile_bank_page_logic.getIbanNumInCurrentBankBlock();
        new Customer_search_aws().openSearchInAwsWithLogin()
                .enterMailAndClickSearch(mail)
                .transitionOnCustomerViewPage()
                .checkPresenceBankDataBlock();
        nameReceiverAWS = customer_view_aws.getNameInReceiverInCurrentBankBlock();
        ibanNumAWS = customer_view_aws.getIbanNumInCurrentBankBlock();
        Assert.assertEquals(nameReceiver, nameReceiverAWS);
        Assert.assertEquals(ibanNum, ibanNumAWS);
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod","DE", "main","profile_plus"));
        new Profile_plus_page_Logic().goToProfileBankPage();
        closeCookiesFooterMessage(); //TODO temporarily until the defect is fixed SITES-8560
        profile_bank_page_logic.clickDeleteBankDataBtn()
                .clickSaveFormBtn()
                .checkPresenceAndClosePopUpUpdate();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}