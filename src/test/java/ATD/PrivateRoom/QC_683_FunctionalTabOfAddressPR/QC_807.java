package ATD.PrivateRoom.QC_683_FunctionalTabOfAddressPR;

import ATD.Main_page_Logic;
import ATD.Profile_addresses_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_807 {

    private String mail = "QC_807_autotest@mailinator.com";
    private int numberUserAddress;

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
    @Description(value = "Test checks the addition of a new delivery address")
    public void testAdditionOfNewDeliveryAddress(String route) {
        openPage(route);
        numberUserAddress = new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileAddressesPage()
                .getNumberOfUserDeliveryAddress();
        new Profile_addresses_page_Logic().clickBtnAddDeliveryAddress()
                .checkThatRadioBtnHerrIsActive()
                .fillingFieldsAddress("Autotest", "Autotest", "Autotest", "Autotest",
                        "Autotest", "1111", "Autotest", "200+002")
                .chooseCountryInAddressForm("Deutschland")
                .clickSaveBtn()
                .checkPresenceAndClosePopUpUpdate()
                .checkThatNumberOfDeliveryAddressHasIncreased(numberUserAddress)
                .deleteDeliveryAddress();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}