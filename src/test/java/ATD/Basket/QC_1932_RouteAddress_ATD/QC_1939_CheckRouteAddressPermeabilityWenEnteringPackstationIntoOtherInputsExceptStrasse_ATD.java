package ATD.Basket.QC_1932_RouteAddress_ATD;

import ATD.CartAddress_page_Logic;
import ATD.CartPayments_page_Logic;
import Common.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1939_CheckRouteAddressPermeabilityWenEnteringPackstationIntoOtherInputsExceptStrasse_ATD {

    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();
    private CartPayments_page_Logic cartPayments_page_logic = new CartPayments_page_Logic();

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0");
        String mail = "QC_1939_autotest@mailinator.com";
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password);
    }

    @DataProvider(name = "blockingWords")
    Object[] blockingWords() {
        return new Object[][]{
                {"Packstation"}, {"Backstation"}, {"Bakstation"}, {"DHLPackstation"}, {"DHL-Packstation"}, {"Paackstation"}, {"Pack Station"},
                {"Packstatiom"}, {"P-A-C-K-S-T-A-T-I-O-N"}, {"Pakcstation"}, {"P A C K S T A T I O N"}, {"P.A.C.K.S.T.A.T.I.O.N."}, {"Paketstation"},
                {"Packetstation"}, {"Pakstation"}, {"Filiale"}, {"DHL-Postfiliale"}, {"Postfiliale"}, {"post filiala"}, {"Wunschfiliale"},
                {"Packet"}, {"Paketbox"}, {"Paketkasten"}, {"Paketservice"}, {"Paketshop"}, {"Grenzpaket"}, {"Europaketshop"}, {"Swiss Paket"},
                {"Baris Paket"}, {"my-paket.com"}, {"Postnummer"}, {"Post Nr"}, {"Postbox"}, {"Postfach"}, {"Postnr."}, {"International express"},
                {"Deposito"}, {"Deutsche Post"}, {"DHL"}, {"DHL-NR."}
        };
    }

    @Owner("Chelombitko")
    @Test(dataProvider = "blockingWords")
    @Flaky
    @Description(value = "Test checks of route Address permeability when entering Packstation into other inputs except Strasse")
    public void testCheckRouteAddressPermeabilityWenEnteringPackstationIntoOtherInputsExceptStrasse(String blockingWords) throws SQLException {
        cartAddress_page_logic.vorname().shouldBe(visible).setValue(blockingWords);
        cartAddress_page_logic.nameIn().shouldBe(visible).setValue(blockingWords);
        cartAddress_page_logic.ort().shouldBe(visible).setValue(blockingWords);
        cartAddress_page_logic.nextBtnClick();
        checkingContainsUrl("/basket/payments");
        cartPayments_page_logic.clickBtnReturnTheAddressPage();
    }

    @AfterClass
    private void close() {
        closeWebDriver();
    }
}
