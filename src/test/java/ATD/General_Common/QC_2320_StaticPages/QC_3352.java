package ATD.General_Common.QC_2320_StaticPages;

import ATD.Versand_static_page_Logic;
import AWS.Delivery_prices_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3352 {

    private ArrayList<String> priceWithAWS = new ArrayList<>();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", true);
        Delivery_prices_aws delivery_prices_aws = new Delivery_prices_aws();
        delivery_prices_aws.openAndLoginDeliveryPriceAwsPage();
        float firstPrice = delivery_prices_aws.getDeliveryPriceForIslandOrRegion("Büsingen am Hochrhein");
        float secondPrice = delivery_prices_aws.getDeliveryPriceForIslandOrRegion("Helgoland");
        priceWithAWS.add(String.valueOf(firstPrice));
        priceWithAWS.add(String.valueOf(secondPrice));
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the block with information about shipping to regions/islands on the shipping page")
    public void testChecksBlockWithInformationAboutShippingToRegionAndIslands() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        new Versand_static_page_Logic()
                .checkPresenceTitleInIslandBlock("VERSANDKOSTEN FÜR INSELN UND ABGELEGENE GEBIETE IN DEUTSCHLAND")
                .comparesPricesForShippingToIslandWithPriceInAWS(priceWithAWS);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}