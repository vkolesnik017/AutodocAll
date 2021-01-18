package ATD.PartsGroups.QC_2711_ProductGroups;

import ATD.Search_page_Logic;
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

public class QC_1586 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "searchRidex,searchStark");
    }

    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Description(value = "Test check making order with Ridex and Stark product")
    @Flaky
    public void checkingOrderWithRidex(String route){
        String testMail = "QC_1586_autotestATD@mailinator.com";
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Search_page_Logic().addFirstProductAndGoToCart()
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

