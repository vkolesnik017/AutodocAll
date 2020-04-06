package Ersatz.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import Ersatz.Product_page_Logic;
import Ersatz.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static Ersatz.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_1695_CheckAddressBlockingOnAddressPage {

    private String file = "C://Autotests/files/res/convid_qc_1695_block.txt";
    private String email = "qc_1695_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String[] plzIT = {"00017", "84030", "02043", "84034-84036", "04022", "85047", "20070", "86012", "20077", "86016", "20080", "86077", "20097", "86079",
            "20098", "87038", "20100-20199", "87054", "25010", "87056", "25040", "87060", "25050", "88050", "25051", "88060", "25056", "88064", "25059", "88067",
            "25070", "88068", "25072", "88842", "25074", "89064", "25078", "89822", "25080", "89823", "25084", "90030", "25088", "91018", "39030-39043",
            "94011", "39045-39049", "94018", "40059", "64030", "64031", "64033-64035", "65010", "65017", "83031"};

    private String[] plzPT = {"3880-000-3880-999", "3885-000-3885-999"};

    private String[] plzES = {"10900"};

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks blocking plz on address page")
    public void testBlockingPlzOnAddressPage(String route) throws IOException {
        open(route);
        new Product_page_Logic().addProductToCart()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .checkingCOVID19Block("ES", plzES, file, "Ersatz")
                .checkingCOVID19Block("IT", plzIT, file, "Ersatz")
                .checkingCOVID19Block("PT", plzPT, file, "Ersatz");
    }
}
