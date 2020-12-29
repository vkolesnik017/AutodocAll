package PKW.SpecificTests.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import Common.SetUp;
import PKW.Product_page_Logic;
import files.ForAllSkins;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;


public class QC_1695_CheckAddressBlockingOnAddressPage {

    private String file = "C://Autotests/files/res/convid_qc_1695_block.txt";
    private String email = "qc_1695_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

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
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks blocking plz on address page")
    public void testBlockingPlzOnAddressPage(String route) throws IOException {
        open(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .checkingCOVID19Block("ES", plzES, file, "PKW")
                .checkingCOVID19Block("IT", plzIT, file, "PKW")
                .checkingCOVID19Block("PT", plzPT, file, "PKW");
    }
}
