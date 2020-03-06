package ATD.GDRP;

import ATD.Main_page_Logic;
import ATD.SetUp;
import AWS.Customer_search_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_551_CheckingClientId {

    private String id, idAws;
    private String mail = "QC_551_checkingclientid@mailinator.com";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verifies that ID Number of client are correct in profile page and in AWS")
    public void testVerificationClientId(String route) {
        openPage(route);
        id = new Main_page_Logic().loginUserFromMain(mail).getDigitClientId();
        idAws = new Customer_search_aws().openSearchInAwsWithLogin().enterMailAndClickSearch(mail).getClientId();
        Assert.assertEquals(id, idAws);
    }
}


