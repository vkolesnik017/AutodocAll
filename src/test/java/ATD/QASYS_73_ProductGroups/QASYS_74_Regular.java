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
import static com.codeborne.selenide.Selenide.open;

public class QASYS_74_Regular {

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
    @Description(value = "Test check making order with regular product")
    @Flaky
    public void checkingOrderWithRegular(String route) throws SQLException {
        String shop = getShopFromRoute(route);
        open(route + "/" + new DataBase().getRouteByRouteName(shop, "product2"));
        String testMail = "atdautotest@mailinator.com";
        new Product_page_Logic().addProductToCart().closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(testMail, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .nextBtnClick()
                .closePopupAfterOrder().successTextInHeader().shouldHave(Condition.text("Vielen Dank"));
    }
}
