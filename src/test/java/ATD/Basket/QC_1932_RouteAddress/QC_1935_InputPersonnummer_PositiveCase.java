package ATD.Basket.QC_1932_RouteAddress;

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
import static com.codeborne.selenide.Selenide.close;

public class QC_1935_InputPersonnummer_PositiveCase {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "SE", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks presence input Personnummer")
    public void testInputPersonnummer_PositiveCase(String route) throws SQLException {
        String mail = mailRandomMailinator("1935");
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .registrationFromCart(mail)
                .checkCorrectTextInPersonalNumberTooltip("Du kan logga in med ditt ID-nummer nu. " +
                        "På så vis slipper du ange personliga uppgifter när du beställer. " +
                        "Vi garanterar komplett sekrtess och säker lagring av dina personliga uppgifter.")
                .fillingFieldPersonalNumber("610707-8575")
                .clickGetMyAddressBtn()
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick();
        checkingContainsUrl("/basket/payments");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}