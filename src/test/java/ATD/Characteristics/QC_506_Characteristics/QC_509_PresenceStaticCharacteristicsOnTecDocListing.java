package ATD.Characteristics.QC_506_Characteristics;

import ATD.CommonMethods;
import Common.DataBase;
import ATD.Listing_page_Logic;
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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_509_PresenceStaticCharacteristicsOnTecDocListing {

    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private CommonMethods commonMethods = new CommonMethods();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list7");
    }

    @Test
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Checks presence static characteristics on TecDoc listing")
    public void testPresenceStaticCharacteristicsOnTecDocListing() throws SQLException {

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
          expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100");
        expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n8,9");
        expectedCharacteristics.add("Mindestdicke \\[mm]:\\n7");
        expectedCharacteristics.add("Höhe \\[mm]:\\n33,4");


        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list"));
        String articleProduct = "Artikelnummer: 82B0691";
        ElementsCollection actualCharacteristics = listingPage.getCharacteristicsDesiredProduct(articleProduct);
        commonMethods.compareCharacteristics(actualCharacteristics, expectedCharacteristics);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
