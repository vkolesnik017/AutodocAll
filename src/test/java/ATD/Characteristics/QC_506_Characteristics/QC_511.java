package ATD.Characteristics.QC_506_Characteristics;

import ATD.CommonMethods;
import Common.DataBase;
import ATD.Search_page_Logic;
import Common.SetUp;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_511 {

    private DataBase db = new DataBase("ATD");
    private Search_page_Logic searchPageLogic = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Checks presence static characteristics on search listing")
    public void testPresenceStaticCharacteristicOnSearchListing(String route) throws SQLException {
        ArrayList<String> expectedCharacteristics = new ArrayList<>();
        expectedCharacteristics.add("");
        expectedCharacteristics.add("Antriebsart:\\nAllrad permanent");
        expectedCharacteristics.add("für PR-Nummer:\\n1KE");
        expectedCharacteristics.add("Einbauseite:\\nHinterachse");
        expectedCharacteristics.add("Durchmesser \\[mm]:\\n239");
        expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
        expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n65");
        expectedCharacteristics.add("Lochanzahl:\\n5");
        expectedCharacteristics.add("Felge Lochzahl:\\n5");
        expectedCharacteristics.add("Bohrung-Ø 2 \\[mm]:\\n6,6");
        expectedCharacteristics.add("Bohrung-Ø 1 \\[mm]:\\n15,75");
        expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100");
        expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n8,9");
        expectedCharacteristics.add("Mindestdicke \\[mm]:\\n7");
        expectedCharacteristics.add("Innendurchmesser \\[mm]:\\n132,2");
        expectedCharacteristics.add("Höhe \\[mm]:\\n33,4");
        expectedCharacteristics.add("Bohrung-Ø \\[mm]:\\n15,8");

        openPage(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "search10"));
        String articleProductForSearchListing = "SKBD-0022841";
        ElementsCollection actualCharacteristics = searchPageLogic.getCharacteristicsDesiredProductForSearch(articleProductForSearchListing);
        new CommonMethods().compareCharacteristics(actualCharacteristics, expectedCharacteristics);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
