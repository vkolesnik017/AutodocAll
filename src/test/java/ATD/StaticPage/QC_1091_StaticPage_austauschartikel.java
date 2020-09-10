package ATD.StaticPage;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1091_StaticPage_austauschartikel {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Chelombitko")
    @Flaky
    @Description(value = "Test checks elements on austauschartikel page")
    public void checkAustauschartikelPageElements(String route) throws Exception {
        openPage(route);
        new Main_page_Logic().clickAustauschartikel()
                .checkItemsTopPage()
                .checkInstructionBlock()
                .checkReturnPolicyBlock()
                .checkCategoriesPfandBlock()
                .checkRequirementForPartsBlock()
                .SelectsRandomCategory()
                .getStatusImageCod()
                .checkTitleAndDDescriptionOfCategory()
                .checkDepositRefundForm();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
