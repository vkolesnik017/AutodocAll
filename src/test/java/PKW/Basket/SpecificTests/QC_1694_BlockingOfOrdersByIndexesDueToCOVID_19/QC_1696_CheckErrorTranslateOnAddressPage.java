package PKW.Basket.SpecificTests.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import PKW.Cart_page_Logic;
import PKW.Product_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static PKW.CommonMethods.getCurrentShopFromJSVarInHTML;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_1696_CheckErrorTranslateOnAddressPage {
    private SetUp setUp = new SetUp();

    private String email = "qc_1695_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String plzIT = "00017";
    private String plzES = "10900";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return setUp.setUpShopsWithSubroute("prod", setUp.getShopsDesktop(), "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks translation of error popup on address page")
    public void testCheckErrorTranslateOnAddressPage(String route) throws SQLException {
        open(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().closeBtnOFPopupReviewIfYes()
                .addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .makePriceForMinimumOrderForCH(shop);
                new Cart_page_Logic().nextButtonClick()
                .signIn(email, password)
                        .checkingCOVID19TooltipTranslate("IT", plzIT, shop)
                        .checkingCOVID19TooltipTranslate("ES", plzES, shop);
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}