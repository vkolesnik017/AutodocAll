package ATD.General_Common.QC_1571_Header;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;


public class QC_1603_TransitionLinksFromBlockHeaderTop {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks transition links from a header top block")
    public void testTransitionLinksFromHeaderTopBlock (String route) {
        openPage(route);
        new Main_page_Logic().checkTransitionToMainPageThroughTheLogoInHeader()
                .checkAppearanceOfLoginPopUpAfterClickingOnLoginButtonInHeader()
                .checkTransitionOnPrivacyPolicyLinkInHeader()
                .checkAppearanceOfPopUpWhenHoverOverImageOfGarageInHeader();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
