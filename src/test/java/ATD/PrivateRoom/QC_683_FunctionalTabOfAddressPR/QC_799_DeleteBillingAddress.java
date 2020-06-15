package ATD.PrivateRoom.QC_683_FunctionalTabOfAddressPR;

import ATD.Main_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_799_DeleteBillingAddress {

    private String mail = "QC_799_autotest@mailinator.com";

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
    @Description(value = "Test checks delete billing address")
    public void testDeleteBillingAddress(String route) {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileAddressesPage()
                .checkPresenceBillingAddressBlock()
                .checkPresenceDeliveryAddressBlock()
                .deleteBillingAddress()
                .checkAbsenceBillingAddress()
                .clickBtnAddBillingAddress()
                .fillingFieldsAddress("Autotest", "Autotest", "Autotest", "Autotest",
                        "Autotest", "1111", "Autotest", "200+002")
                .clickSaveBtn()
                .checkPresenceAndClosePopUpUpdate();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}