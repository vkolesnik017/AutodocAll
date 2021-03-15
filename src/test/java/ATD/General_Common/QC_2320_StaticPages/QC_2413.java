package ATD.General_Common.QC_2320_StaticPages;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2413 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = true) //TODO test works but disabled method ".checkingTitleInHelpBlockAgainstInPDF() " . Line 44 . defect SHOP-758
    @Owner(value = "LavrynenkoOlha")
    @Flaky
    @Description(value = "Test checks elements and functionality on the Presse page")
    public void checkPressePageElements(String route) throws IOException {
        openPage(route);
        new Main_page_Logic().goToPressePage()
                .checkingPresReleaseHeaderAndPressInfoBlock()
                .checkAndOpenTheArticleBlock()
                .checkingTheTitlesInTheCards()
                .checkingTheTexts()
                .getStatusCodForImgInHelpBlock()
                .getStatusCodForImgInPressInfoBlock()
                .checkingTheDownloadsPDF()
                .checkingTheDownloadsJPG()
                .checkingTheDownloadsPdfInHelpBlock()
//                .checkingTitleInHelpBlockAgainstInPDF()  //TODO defect SHOP-758
                .checkingTheActiveArticle()
                .checkingTheBackForwardButtons()
                .checkingTheImagesStatusCode()
                .checkingThePresentation("/tmp/ATD2020.pdf")
                .checkingThePresentationImage()
                .checkingTheDownloadImage("C:/Users/User/Downloads/")
                .checkingTheBackForwardButtonsPresentation()
                .checkingTheMainImagesStatusCode();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

    @AfterClass
    public void deleteDirectory() throws IOException {
        FileUtils.deleteDirectory(new File("C:\\Users\\User\\IdeaProjects\\automation_perfection\\build\\reports\\tests"));
    }
}
