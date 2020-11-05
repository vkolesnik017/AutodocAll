package ATD.PrivateProperties.QC_1016_FunctionalityMyGarageInUsersPrivatRoom;

import ATD.Main_page_Logic;
import ATD.Maker_car_list_page_Logic;
import ATD.Profile_plus_page_Logic;
import AWS.Order_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2699_SavedVINNumberInTheOrder {
    private String mail = "QC_2699_autotest@mailinator.com";
    private String vinNum = "1245263214521";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Check saving VIN number in the order from the Garage")
    public void testCheckSavingVINNumberInOrderFromMyGarage(String routes) throws SQLException {
        openPage(routes);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail);
        new Profile_plus_page_Logic().clickOnGarageIconInHeader()
                .checkWorkAddingVinNumInPopupOfGarage(vinNum)
                .checkRedirectToCatalogRoute();
        clickOfBuyBtnForAllPages();
        String orderNumber = new Maker_car_list_page_Logic().cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()

                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");



    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
