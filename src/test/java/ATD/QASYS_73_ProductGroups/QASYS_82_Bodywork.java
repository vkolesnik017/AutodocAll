package ATD.QASYS_73_ProductGroups;

import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.SetUp;
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
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_82_Bodywork {

    private Product_page_Logic product_page_logic = new Product_page_Logic();

    private String urlProductForBodyFR = "https://www.auto-doc.fr/valeo/1059854";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }


    @Owner(value = "alex_qa")
    @Test(dataProvider = "route")
    @Description(value = "Test check making order with body product")
    @Flaky
    public void checkingOrderWithBody(String route) throws SQLException {
        open(urlProductForBodyFR);
        product_page_logic.clickAddToCartAndCheckPopupFR();
        String shop = getShopFromRoute(route);
        open(route + "/" + new DataBase().getRouteByRouteName(shop, "product7"));
        String testMail = "atdautotest_qasys_82_bodywork@mailinator.com";
        product_page_logic
                .cartClick()
                .nextButtonClick()
                .signIn(testMail, password)
                .fillAllFields("FR").nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .closePopupDeliveryImpossibleAndCheckEmptyCart();
        close();
        open(route + "/" + new DataBase().getRouteByRouteName(shop, "product7"));
        product_page_logic
                .cartClick()
                .nextButtonClick()
                .signIn(testMail, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .nextBtnClick()
                .closePopupAfterOrder().successTextInHeader().shouldHave(Condition.text("Vielen Dank"));
    }
}
