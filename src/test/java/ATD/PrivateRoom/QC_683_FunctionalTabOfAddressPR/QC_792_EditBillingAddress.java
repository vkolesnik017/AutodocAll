package ATD.PrivateRoom.QC_683_FunctionalTabOfAddressPR;

import ATD.Main_page_Logic;
import ATD.Profile_addresses_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_792_EditBillingAddress {

    private String mail = "QC_792_autotest@mailinator.com", billingAddress, newBillingAddress;

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
    @Description(value = "Test checks edit billing address")
    public void testEditBillingAddress(String route) {
        openPage(route);
        billingAddress = new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileAddressesPage()
                .checkPresenceBillingAddressBlock()
                .checkPresenceDeliveryAddressBlock()
                .clickBtnAddBillingAddress()
                .fillingFieldsAddress("Autotest", "Autotest", "Autotest", "Autotest",
                        "Autotest", "1111", "Autotest", "200+002")
                .chooseCountryInAddressForm("Deutschland")
                .clickSaveBtn()
                .checkPresenceAndClosePopUpUpdate()
                .getDailyBillingAddress();
        newBillingAddress = new Profile_addresses_page_Logic().clickBillingEditButton()
                .fillingFieldsAddress("Test", "Test", "Test", "Test",
                        "Test", "2222", "Test", "1111")
                .chooseCountryInAddressForm("Estland")
                .clickSaveBtn()
                .checkPresenceAndClosePopUpUpdate()
                .getDailyBillingAddress();
        Assert.assertNotEquals(billingAddress, newBillingAddress);
        new Profile_addresses_page_Logic().deleteBillingAddress();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
