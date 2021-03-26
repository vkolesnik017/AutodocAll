package ATD.PrivateProperties.QC_1016_FunctionalityMyGarageInUsersPrivatRoom;

import ATD.CommonMethods;
import ATD.Main_page_Logic;
import ATD.Payment_handler_page_Logic;
import Common.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1032 {

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
    @Description(value = "Test checks the block of passenger cars from customer orders on the Garage page in PR")
    public void testBlockPassengerCarsFromCustomerOrdersOnGaragePageInPR(String route) {
        String mail = CommonMethods.mailinatorMailRandom("1032");
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String orderNumber = new Main_page_Logic().registrationFromLoginButton(mail)
                .goToMainPage()
                .chooseBrandModelTypeInSelector("BMW", "7378", "55986")
                .goToCatalog()
                .clickOilFilterCategoryLink()
                .clickFirstProductOnListing()
                .addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillAllFieldsForShipping(shop)
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick()
                .closePopupAfterOrder()
                .getOrderNumber();
        new Payment_handler_page_Logic().goToProfilePlusPage()
                .goToMyVehiclesBlock()
                .checkPresenceInfoCarBlock("M50d 3.0 (280 KW / 381 PS) (08.2011 - 06.2014)")
                .checkTransitionToCatalogFromCarInfoBlock("/ersatzteile/bmw/x6/x6-e71-e72/55986-m-50-d");
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}