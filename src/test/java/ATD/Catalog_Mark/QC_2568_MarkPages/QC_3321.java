package ATD.Catalog_Mark.QC_2568_MarkPages;

import ATD.Tyres_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_3321 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "test Checking the block of TOP car brands on the main tire page")
    public void testCheckingBlockTopCarBrandsOnMainTirePage(String route) {
        openPage(route);
        new Tyres_page_Logic().checkWorkTopCarBrandBlock()
                .clickOnCarBrand();
        waitWhileRouteBecomeExpected("tyres_maker");
        checkingContainsUrl("vw");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }


}
