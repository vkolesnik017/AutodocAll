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

public class QC_810_SetAsPrimaryDeliveryAddress {

    private String mail = "QC_810_autotest@mailinator.com";

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
    @Description(value = "Test: Set as primary delivery address")
    public void testSetAsPrimaryDeliveryAddress(String route) {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileAddressesPage()
                .checkPresenceBillingAddressBlock()
                .checkPresenceDeliveryAddressBlock()
                .clickDeliveryEditButton()
                .checkPresenceBtnUnderFormEditAddress()
                .clickUseBtnAsMainAddress()
                .checkPresenceAndClosePopUpUpdate()
                .checkPresenceMainAddressLabel()
                .deleteDeliveryAddress()
                .clickBtnAddDeliveryAddress()
                .fillingFieldsAddress()
                .clickSaveBtn()
                .checkPresenceAndClosePopUpUpdate();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}