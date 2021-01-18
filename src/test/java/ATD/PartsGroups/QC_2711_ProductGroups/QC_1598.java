package ATD.PartsGroups.QC_2711_ProductGroups;

import Common.DataBase;
import ATD.Product_page_Logic;
import Common.SetUp;
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


public class QC_1598 {

    private Product_page_Logic product_page_logic = new Product_page_Logic();

    private String urlProductForBodyFR = "https://www.auto-doc.fr/valeo/1059854";

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
    @Description(value = "Test check making order with body product")
    @Flaky
    public void checkingOrderWithBody(String route) throws SQLException {
        openPage(urlProductForBodyFR);
        product_page_logic.clickAddToCartAndCheckPopupFR();
        String shop = getShopFromRoute(route);
        openPage(route + "/" + new DataBase("ATD").getRouteByRouteName(shop, "product7"));
        String testMail = "atdautotest_QC_1598_bodywork@mailinator.com";
        product_page_logic.addProductToCart().closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(testMail, password)
                .fillAllFields("FR").nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .closePopupDeliveryImpossibleAndCheckEmptyCart();
        close();
        openPage(route + "/" + new DataBase("ATD").getRouteByRouteName(shop, "product7"));
        product_page_logic.addProductToCart().closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(testMail, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .nextBtnClick()
                .closePopupAfterOrder().successTextInHeader().shouldHave(Condition.text("Vielen Dank"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
