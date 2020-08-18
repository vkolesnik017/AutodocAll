package PKW.OILS.QC_926_KbaBlockOnMainOilPage;

import PKW.Motoroil_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_931_PresenceOfErrorMessageAboutEmptyFields {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of an error message about empty fields in selector")
    public void testChecksPresenceOfErrorMessageAboutEmpyFields(String route) {
        openPage(route);

        new Motoroil_page_Logic()
                .presenceOfKbaSelector()
                .clickOnBtnSearchOfKbaSelector()
                .visibilityErrorMessageAboutEmptyKbaFields();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
