package ATD.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;

public class QC_1695_CheckAddressBlockingOnAddressPage {

    private String email = "qc_1695_autotestCOVID19@mailinator.com";
    private String actualText, textFromSite;

    //    String[] plzFCZ = {"78321", "78324", "78391", "78401"};
    String[] plzFCZ = {"78321"};
    private String[] plzIt = {"04000-04999", "05100-05199", "07000-07100", "10000-10100", "16000-17100", "20000-20100", "23800-23900", "24000-24100", "25000-25100", "37000-37100",
            "40000-40199", "41000-41100", "42000-42100", "46000-46100", "64000-64999", "65000-65100", "83000-83100", "84000-89100"};


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
    @Description(value = "Test checks blocking plz on address page")
    public void testBlockingPlzOnAddressPage(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        actualText = new DataBase().getTranslate("convir_translate", shop, "addres");
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password);
//                .checkingCOVID19Tooltip("CZ", plzFCZ, shop)
    }
}