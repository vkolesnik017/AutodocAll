package ATD.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;

public class QC_1695_CheckAddressBlockingOnAddressPage {

    private String email = "qc_1695_autotestCOVID19@mailinator.com";
    private String actualText, textFromSite;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks verification of islands + Firm, billing is undivided, incorrect company data")
    public void testChecksVerificationIslandsAndFirmIncorrectCompanyData(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        actualText = new DataBase().getTranslate("convir_translate", shop, "addres");
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .fillingPostalCodeField(00100)
                .nextBtnClick();
        textFromSite = new CartAddress_page_Logic().getTextFromTooltipCOVID19();
        Assert.assertEquals(textFromSite, actualText);
    }
}