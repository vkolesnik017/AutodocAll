package ATD.QASYS_73_ProductGroups;

import ATD.DataBase;
import ATD.Payment_handler_page;
import ATD.Search_page;
import ATD.SetUp;
import AWS.Order_aws;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.CommonMethods.password;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_80_Paired {

    private String orderNumber;


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }


    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route", enabled = true)
    @Description(value = "Test check making order with paired product")
    public void checkingOrderWithPaired(String route) throws SQLException {
        String shop = getShopFromRoute(route);
        open(route + "/" + new DataBase().getRouteByRouteName(shop, "search8"));
        String testMail = "atdautotest_qasys_80_paired@mailinator.com";
        new Search_page().counterIncrease("2").counterDecrease("4").closeFooterMessageCookies().detailsClick()
                .counterIncrease("2").counterDecrease("4").counterIncrease("2").addProductToCart().closePopupOtherCategoryIfYes().checkingNumberOfProductInCart(4).cartClick()
                .counterIncrease("4").counterDecrease("6").nextButtonClick()
                .signIn(testMail, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .counterIncrease("4").counterDecrease("6").nextBtnClick()
                .closePopupAfterOrder().successTextInHeader().shouldHave(Condition.text("Vielen Dank"));
        orderNumber = new Payment_handler_page().getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin().checkQuantityOfProduct(4).checkTooltipByAddingIncorrectProductQuantity("3");
    }
}
