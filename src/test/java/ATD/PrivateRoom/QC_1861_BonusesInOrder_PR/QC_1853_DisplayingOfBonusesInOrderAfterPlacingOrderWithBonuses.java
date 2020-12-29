package ATD.PrivateRoom.QC_1861_BonusesInOrder_PR;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1853_DisplayingOfBonusesInOrderAfterPlacingOrderWithBonuses {

    private String mail = "QC_1853_autotest@mailinator.com", orderNumber, orderIdOfOrder, bonusOrderSum;
    private Main_page_Logic main_page_logic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the display of bonuses in the order after placing an order with bonuses")
    public void testDisplayingOfBonusesInOrder(String route) throws SQLException {
        openPage(route);
        main_page_logic.loginAndTransitionToProfilePlusPage(mail);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product57"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresenceBonusSticker()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkPresenceBonusSticker()
                .nextBtnClick();
        orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        orderIdOfOrder = order_aws.openOrderInAwsWithLogin()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .getOrderIdOfOrder();
        Assert.assertEquals(orderNumber, orderIdOfOrder);
        openPage(route);
        bonusOrderSum = main_page_logic.profilePlusBtnClickInHeader()
                .goToMyOrdersPage()
                .clickDetailsOrderBtn()
                .checkAbsenceOrderBonus()
                .clickExitOrderDetailsBtn()
                .getBonusOrderSum();
        Assert.assertEquals("0", bonusOrderSum);
        order_aws.openOrderInAwsWithoutLogin()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}