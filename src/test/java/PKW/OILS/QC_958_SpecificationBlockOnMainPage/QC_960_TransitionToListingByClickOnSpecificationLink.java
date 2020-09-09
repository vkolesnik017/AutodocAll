package PKW.OILS.QC_958_SpecificationBlockOnMainPage;

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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_960_TransitionToListingByClickOnSpecificationLink {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition to listing by click on Specification link")
    public void testChecksTransitionToListingByClickOnSpecificationLink(String route) {
        openPage(route);

        new Motoroil_page_Logic()
                .presenceOfSpecificationBlock()
                .clickOnSpecificationLink(3)
                .checkListingWithSelectedToleranceFilter("ACEA A5")
                .checkSelectorWithSelectedSpecification("acea-a5")
                .checkSelectedSpecificationInSideBar("ACEA A5");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
