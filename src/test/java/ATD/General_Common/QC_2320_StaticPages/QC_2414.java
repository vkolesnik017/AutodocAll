package ATD.General_Common.QC_2320_StaticPages;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2414 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = false) //TODO отключен из-за дефекта SHOP-2505
    @Owner(value = "LavrynenkoOlha")
    @Flaky
    @Description(value = "Test checks elements on the About Us page")
    public void checkAboutUsPageElements(String route) throws IOException, SQLException, InterruptedException {
        openPage(route);
        new Main_page_Logic().goToAboutUsPage()
                .checkElementsInMainHeaderBanner()
                .checkingNumberAndTextInMainHeaderBlock()
                .checkingElementsInGalleryBlock()
                .checkingMehrButtonInGalleryBlock()
                .checkingElementsPresenceInOwnerBlock()
                .checkPhotoOfOwnersFunctionality()
                .checkElementsInYearsInfoBlock()
                .checkingMapInfoBlock()
                .checkingStatisticsBlock()
                .checkingGraphBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

