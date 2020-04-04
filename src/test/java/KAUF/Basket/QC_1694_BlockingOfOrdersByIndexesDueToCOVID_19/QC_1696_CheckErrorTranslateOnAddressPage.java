package KAUF.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;


import KAUF.Product_page_Logic;
import KAUF.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static KAUF.CommonMethods.getCurrentShopFromJSVarInHTML;
import static KAUF.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_1696_CheckErrorTranslateOnAddressPage {

    private SetUp setUp = new SetUp();

    private String email = "qc_1695_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String plzIT = "00100";
    private String plzES = "10900";
    private String plzAT = "6450";
    private String plzCZ = "78321";
    private String plzFR = "67111";
    private String plzPT = "3880-365";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
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
        open("https://www.ersatzteilekauf24.de/autoteile/abs-7714780");
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .checkingCOVID19TooltipTranslate("IT", plzIT, shop)
                .checkingCOVID19TooltipTranslate("ES", plzES, shop)
//                .checkingCOVID19TooltipTranslate("AT", plzAT, shop)
//                .checkingCOVID19TooltipTranslate("CZ", plzCZ, shop)
                .checkingCOVID19TooltipTranslate("FR", plzFR, shop)
                .checkingCOVID19TooltipTranslate("PT", plzPT, shop);

    }
}