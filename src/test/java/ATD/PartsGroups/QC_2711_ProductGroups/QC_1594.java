package ATD.PartsGroups.QC_2711_ProductGroups;

import ATD.Product_page_Logic;
import Common.DataBase;
import ATD.Payment_handler_page_Logic;
import ATD.Search_page_Logic;
import Common.SetUp;
import AWS.Order_aws;
import com.codeborne.selenide.Condition;
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


public class QC_1594 {

    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }


    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Description(value = "Test check making order with paired product")
    @Flaky
    public void checkingOrderWithPaired(String route) throws SQLException {
        String shop = getShopFromRoute(route);
        openPage(route + "/" + new DataBase("ATD").getRouteByRouteName(shop, "search8"));
        String testMail = "QC_1594_autotestATD@mailinator.com";
        new Search_page_Logic().counterIncreaseForPaired("2").counterDecreaseForPaired("4").closeFooterMessageCookies().detailsClick()
                .counterIncreaseForPaired("2").counterDecreaseForPaired("4").counterIncreaseForPaired("2");
        product_page_logic.buyButton().click();
        product_page_logic.checksPresentProductInCartPopup()
                .closePopupOtherCategoryIfYes()
                .checkingNumberOfProductInCart(4)
                .cartClick()
                .counterIncreaseForPaired("4").counterDecreaseForPaired("6").nextButtonClick()
                .signIn(testMail, password)
                .fillAllFieldsForShipping(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .counterIncreaseForPaired("4").counterDecreaseForPaired("6").nextBtnClick()
                .closePopupAfterOrder().successTextInHeader().shouldHave(Condition.text("Vielen Dank"));
        String orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin().checkQuantityOfProduct(4).checkTooltipByAddingIncorrectProductQuantity("3");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
