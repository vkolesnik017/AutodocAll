package ATD.Basket.QC_1932_RouteAddress;

import ATD.CartAddress_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1940_BlockingThePackstationWordInTheStrasseField {

    private String mail = "qc_1940_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopsWithSubroute("prod", "AT,BG,BE,CH,CZ,DK,EN,EE,ES,FI,FR,GR,HU,IT,LD,LT,LV,NL,NO,PL,PT,RO,SE,SI,SK", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the blocking of the Packstation word in the Strasse field")
    public void testBlockingThePackstationWordInTheStrasseField(String route) {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .strasse().setValue("Packstation");
        new CartAddress_page_Logic().nextBtnClick();
        checkingContainsUrl("/basket/payments");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
