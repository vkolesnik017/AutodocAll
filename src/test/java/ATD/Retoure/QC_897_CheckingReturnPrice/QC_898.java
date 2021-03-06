package ATD.Retoure.QC_897_CheckingReturnPrice;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
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
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class QC_898 {

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Profile_page profilePage = new Profile_page();
    private DataBase db = new DataBase("ATD");

    private String idUserAws = "13785243";
    private String orderNumber;
    private String mail = "QC_898_retoure@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Verify that product prices match the order on AWS and on the profile")
    public void testMatchPricesInAwsOrderAndProfile(String route) throws SQLException {
        orderNumber = product_page_logic.openProductPageById(route, idPfandProduct)
                .addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        Float productPriceInAwsOrder = new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkOrderHasTestStatus()
                .setStatusOrderToVersendetVorkasse()
                .addDeliveryConditionGLS("0", "GLS")
                .getSellingProductPrice();
        open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "profile_orders"));
        Float productPriceOnRetourenPage = new Profile_page_Logic().clickBestelldetailsButton(orderNumber)
                .clickReturnOrReplaceItemButton()
                .getProductPriceForReturn();
        assertEquals(productPriceInAwsOrder, productPriceOnRetourenPage);
    }

    @AfterMethod
    public void setStatusTestToOrder() {
        new Order_aws(orderNumber).setStatusOrderToTestbestellungen();
        closeWebDriver();
    }

}
