package ATD.General_Common.QC_2320_StaticPages;

import ATD.Main_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1835 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks static page lending Autodoc Plus")
    public void testChecksStaticPageAutodocPlus() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteName("prod", "DE", "main"));
        new Main_page_Logic().checkPresenceDropdownPlus()
                .clickOnAutodocPlusInHeader()
                .checkElementsInPlusServiceBlock()
                .checkElementsInBasePack("Basis","Aktivieren")
                .checkElementsInOptimalPack("Optimum", "Aktivieren")
                .checkElementsInProfPack("Professionell", "Aktivieren")
                .checkElementsInExpertPack("Expert", "Aktivieren")
                .checkElementsInTrialPack()
                .checkElementsInDeliveryBlock()
                .checkElementsInExpertBlock()
                .checkElementsInDiscountBlock()
                .checkElementsInPersonalBlock()
                .checkElementsInPlusSafeBlock()
                .checkElementsInPlusReadyBlock()
                .checkElementsInPlusReturnBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
