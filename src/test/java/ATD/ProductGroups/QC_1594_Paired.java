package ATD.ProductGroups;

import ATD.DataBase;
import ATD.Payment_handler_page_Logic;
import ATD.Search_page_Logic;
import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;


public class QC_1594_Paired {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }


    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Description(value = "Test check making order with paired product")
    @Flaky
    public void checkingOrderWithPaired(String route) throws SQLException {
        String shop = getShopFromRoute(route);
        openPage(route + "/" + new DataBase().getRouteByRouteName(shop, "search8"));
        String testMail = "atdautotest_qasys_80_paired@mailinator.com";
        new Search_page_Logic().counterIncrease("2").counterDecrease("4").closeFooterMessageCookies().detailsClick()
                .counterIncrease("2").counterDecrease("4").counterIncrease("2").addProductToCart().closePopupOtherCategoryIfYes().checkingNumberOfProductInCart(4).cartClick()
                .counterIncrease("4").counterDecrease("6").nextButtonClick()
                .signIn(testMail, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .counterIncrease("4").counterDecrease("6").nextBtnClick()
                .closePopupAfterOrder().successTextInHeader().shouldHave(Condition.text("Vielen Dank"));
        String orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin().checkQuantityOfProduct(4).checkTooltipByAddingIncorrectProductQuantity("3");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
