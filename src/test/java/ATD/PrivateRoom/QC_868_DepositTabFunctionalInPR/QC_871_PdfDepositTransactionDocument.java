package ATD.PrivateRoom.QC_868_DepositTabFunctionalInPR;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_871_PdfDepositTransactionDocument {

    private String mail = "QC_868_depositTestATD@mailinator.com";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the opening of a PDF document with deposit operations")
    public void testPdfDepositTransactionDocument(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToDepositPage()
                .checkDepositTable()
                .openPdfDocWithDepositOperation();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}