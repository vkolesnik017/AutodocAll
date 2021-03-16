package ATD.General_Common.QC_2320_StaticPages;

import ATD.Presse_static_page_Logic;
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

public class QC_2830 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", true);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks presence press-gallery block in press page")
    public void testChecksPressGalleryBlock() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticPresse"));
        new Presse_static_page_Logic().checkGeneralElementInGalleryBlock()
                .checkingTheBackForwardButtonsInGalleryBlock()
                .clickSecondImgAndCheckDisplayedInGallery()
                .checkingTheDownloadImage("C:/Users/User/Downloads/");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
