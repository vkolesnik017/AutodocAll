package ATD.Basket.QC_1932_RouteAddress_ATD;

import ATD.CartAddress_page_Logic;
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

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1938_BlockingThePackstationWordInTheStrasseFieldForShopDE_ATD {


    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0");
        String mail = "QC_1938_autotest@mailinator.com";
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password);
    }

    @DataProvider(name = "blockingWords", parallel = false)
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
    @Description(value = "Test checks the blocking the Packstation wordIn the Strasse Field for shop DE")
    public void testBlockingThePackstationWordInTheStrasseFieldForShopDE(String blockingWords) throws SQLException {
        cartAddress_page_logic.checkPresenceNotesAndTextInsideBlock("(Die Angabe einer DHL-Packstation, Postfiliale " +
                "oder eines Paketshops u. a. m. ist nicht zulässig!)")
                .strasse().setValue(blockingWords);
        cartAddress_page_logic.nextBtnClick();
        cartAddress_page_logic.checkCorrectTextInErrorMessage("Die Angabe einer DHL-Packstation, Postfiliale oder " +
                "eines Paketshops u. a. m. ist nicht zulässig!");
    }

    @AfterClass
    private void close() {
        closeWebDriver();
    }
}