package ATD.PrivatRoom.FunctionalTabOfAddresPR;

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

public class QC_864_CheckingBtnAbbrechenInTheDeliveryAddressEditingBlock {

    private String mail = "QC_864_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks button (Abbrechen) in the delivery address editing block")
    public void testCheckBtnAbbrechenInTheDeliveryAddressEditingBlock(String route) {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileAddressesPage()
                .checkPresenceBillingAddressBlock()
                .checkPresenceDeliveryAddressBlock()
                .clickShippingEditButton()
                .checkPresenceShippingAddressForm()
                .clickBackupCancelBtn()
                .checkAbsenceShippingAddressForm();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}