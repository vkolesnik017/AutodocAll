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

public class QC_801 {

    private String mail = "QC_801_autotest@mailinator.com", deliveryAddress, newDeliveryAddress;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks edit delivery address")
    public void testEditDeliveryAddress(String route) {
        openPage(route);
        deliveryAddress = new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileAddressesPage()
                .checkPresenceBillingAddressBlock()
                .checkPresenceDeliveryAddressBlock()
                .clickBtnAddDeliveryAddress()
                .fillingFieldsAddress("Autotest", "Autotest", "Autotest", "Autotest",
                        "Autotest", "1111", "Autotest", "200+002")
                .chooseCountryInAddressForm("Deutschland")
                .clickSaveBtn()
                .checkPresenceAndClosePopUpUpdate()
                .getDailyDeliveryAddress();
        newDeliveryAddress = new Profile_addresses_page_Logic().clickDeliveryEditButton()
                .fillingFieldsAddress("Test", "Test", "Test", "Test",
                        "Test", "2222", "Test", "1111")
                .chooseCountryInAddressForm("Estland")
                .clickSaveBtn()
                .checkPresenceAndClosePopUpUpdate()
                .getDailyDeliveryAddress();
        Assert.assertNotEquals(deliveryAddress, newDeliveryAddress);
        new Profile_addresses_page_Logic().deleteDeliveryAddress();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}