package TOP.SpecificTests.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;


import Common.SetUp;
import TOP.Cart_page_Logic;
import TOP.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static TOP.CommonMethods.getCurrentShopFromJSVarInHTML;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_1696 {

    private SetUp setUp = new SetUp("TOP");

    private String email = "qc_1695_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String plzIT = "00017";
    private String plzES = "10900";
    private String plzPT = "3880-365";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return setUp.setUpShopsWithSubroute("prod", setUp.getShopsDesktop(), "main", "product");
    }

    @Test//(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks translation of error popup on address page")
    public void testCheckErrorTranslateOnAddressPage()/*(String route)*/ throws SQLException {
        open("https://www.topersatzteile.de/hersteller/mapco/67962");
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .cartClick()
                .makePriceForMinimumOrderForCH(shop);
        new Cart_page_Logic().nextButtonClick()
                .signIn(email, password)
                .checkingCOVID19TooltipTranslate("PT", plzPT, shop)
                .checkingCOVID19TooltipTranslate("IT", plzIT, shop)
                .checkingCOVID19TooltipTranslate("ES", plzES, shop);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}