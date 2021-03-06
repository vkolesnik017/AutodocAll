package ATD.SpecificTests.Orders_AWS_Delivery.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import ATD.Product_page_Logic;
import Common.SetUp;
import files.ForAllSkins;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1695 {

    private String file = "C://Autotests/files/res/convid_qc_1695_block.txt";
    private String email = "qc_1695_autotestCOVID19@mailinator.com";

    private ForAllSkins forAllSkins = new ForAllSkins();

    private String[] plzIT = forAllSkins.getPlzIT();

    private String[] plzPT = forAllSkins.getPlzPT();

    private String[] plzES = forAllSkins.getPlzES();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks blocking plz on address page")
    public void testBlockingPlzOnAddressPage(String route) throws IOException {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .checkingCOVID19Block("ES", plzES, file, "ATD")
                .checkingCOVID19Block("IT", plzIT, file, "ATD")
                .checkingCOVID19Block("PT", plzPT, file, "ATD");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
