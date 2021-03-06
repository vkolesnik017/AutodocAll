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

import static ATD.CommonMethods.mailinatorMailRandom;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1936 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "SE", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks entering incorrect data into the input")
    public void testEnteringIncorrectDataIntoTheInputPersonnummer(String route) throws SQLException {
        String mail = mailinatorMailRandom("1935");
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .registrationFromCart(mail)
                .fillingFieldPersonalNumber("610707")
                .clickGetMyAddressBtn()
                .checkCorrectTextInErrorMessage("Fältet \"Personnummer\" måste innehålla minst 10 tecken")
                .fillingFieldPersonalNumber("6107071111")
                .clickGetMyAddressBtn()
                .checkCorrectTextInErrorMessage("Objektnr är felaktigt/ofullständigt angivet.");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}