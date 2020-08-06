package ATD.PrivateRoom.QC_683_FunctionalTabOfAddressPR;

import ATD.Main_page_Logic;
import ATD.Profile_addresses_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_673_AddNewBillingAddress {

    private String mail = "QC_673_autotest@mailinator.com";
    private int numberUserAddress;

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
    @Description(value = "Test checks the add of a new billing address")
    public void testAddOfNewBillingAddress(String route) {
        openPage(route);
        numberUserAddress = new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileAddressesPage()
                .checkPresenceBillingAddressBlock()
                .checkPresenceDeliveryAddressBlock()
                .getNumberOfUserBillingAddress();
        new Profile_addresses_page_Logic().clickBtnAddBillingAddress()
                .checkThatRadioBtnHerrIsActive()
                .fillingFieldsAddress("Autotest", "Autotest", "Autotest", "Autotest",
                        "Autotest", "1111", "Autotest", "200+002")
                .chooseCountryInAddressForm("Deutschland")
                .clickSaveBtn()
                .checkPresenceAndClosePopUpUpdate()
                .checkThatNumberOfBillingAddressHasIncreased(numberUserAddress)
                .deleteBillingAddress();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}