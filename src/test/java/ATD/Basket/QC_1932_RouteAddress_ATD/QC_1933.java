package ATD.Basket.QC_1932_RouteAddress_ATD;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1933 {

    private String mail = "QC_1933_autotestATD@mailinator.com";
    private String expectedZipMasksAT = "1111";
    private String expectedZipMasksFI = "11111";
    private String expectedZipMasksGR = "111 11";
    private String expectedZipMasksPT = "1111-111";
    private String expectedZipMasksES = "11111";
    private String expectedZipMasksFR = "11111";
    private String expectedZipMasksCZ = "111 11";
    private String expectedZipMasksSE = "111 11";
    private String expectedZipMasksNL = "1111 XX";
    private String expectedZipMasksIT = "11111";
    private String expectedZipMasksPL = "11-111";
    private String expectedZipMasksSK = "111 11";
    private String expectedZipMasksSI = "1111";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "EN", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the zip masks")
    public void testZipMasks(String route) throws SQLException {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .checkValidationEnteredNumberIntoZipCodeField("AT", "111111111", expectedZipMasksAT, expectedZipMasksAT)
                .checkValidationEnteredNumberIntoZipCodeField("FI", "111111111", expectedZipMasksFI, expectedZipMasksFI)
                .checkValidationEnteredNumberIntoZipCodeField("GR", "111111111", expectedZipMasksGR, expectedZipMasksGR)
                .checkValidationEnteredNumberIntoZipCodeField("PT", "111111111", expectedZipMasksPT, expectedZipMasksPT)
                .checkValidationEnteredNumberIntoZipCodeField("ES", "111111111", expectedZipMasksES, expectedZipMasksES)
                .checkValidationEnteredNumberIntoZipCodeField("FR", "111111111", expectedZipMasksFR, expectedZipMasksFR)
                .checkValidationEnteredNumberIntoZipCodeField("CZ", "111111111", expectedZipMasksCZ, expectedZipMasksCZ)
                .checkValidationEnteredNumberIntoZipCodeField("SE", "111111111", expectedZipMasksSE, expectedZipMasksSE)
                .checkValidationEnteredNumberIntoZipCodeField("NL", "1111XXXXX", expectedZipMasksNL, expectedZipMasksNL)
                .checkValidationEnteredNumberIntoZipCodeField("IT", "111111111", expectedZipMasksIT, expectedZipMasksIT)
                .checkValidationEnteredNumberIntoZipCodeField("PL", "111111111", expectedZipMasksPL, expectedZipMasksPL)
                .checkValidationEnteredNumberIntoZipCodeField("SK", "111111111", expectedZipMasksSK, expectedZipMasksSK)
                .checkValidationEnteredNumberIntoZipCodeField("SI", "111111111", expectedZipMasksSI, expectedZipMasksSI);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}