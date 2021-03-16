package ATD.General_Common.QC_2320_StaticPages;

import ATD.Presse_static_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.refresh;

public class QC_2819 {

    private ArrayList<String> listWithLinksToLogo() {
        ArrayList<String> imgLink = new ArrayList<>();
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/AmeriCar.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/Automobilwoche.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/AmeriCar.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/Autohaus.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/bussines_on.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/MoneySpecial.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/amz.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/logo-pop.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/ft.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/logo-30ans.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/gopuplogo.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/berliner.png");
        imgLink.add("https://www.autodoc.de/atd/img/pages/press-release/logo-pop.png");
        return imgLink;
    }

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the atd-presse block on the press page")
    public void testCheckingBlocksAtdPresse() throws SQLException {
        Presse_static_page_Logic presse_static_page_logic = new Presse_static_page_Logic();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticPresse"));
        presse_static_page_logic
                .checkElementsInPresBlock(" AUTODOC IN DER PRESSE ")
                .checkingTheBackForwardButtonsForAtdPresseBlock()
                .checkingTheActiveArticleForAtdPresseBlock();
        refresh();
        presse_static_page_logic.comparesCurrentLogoLinkToExpectedOne(listWithLinksToLogo());
    }

    @AfterClass
    private void close() {
        closeWebDriver();
    }
}