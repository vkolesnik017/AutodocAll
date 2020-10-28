package ATD.PrivateProperties.QC_2444_CheckingMarkingOfDangerousProductsAtFrontsInVariousFunctionalBlocks;

import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
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

public class QC_2451_CheckDisplayingAndClickOnTheDangerMarkAnalogBlock {

    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private Listing_page_Logic listing_page_logic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product46");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Check displaying and click on the danger goods block in the Analog Block")
    public void testCheckDisplayingDisplayDangerousProductsInTheAnalogBlock(String routes) {
        openPage(routes);
        new Product_page_Logic().presenceOfTheAnalogBlockWithPictograms()
                .presenceOfTheTextAndWarningWordInPopUp();
        main_page_logic.useSearch("03.9901-5801");
        listing_page_logic.checkProductsByArticle("03.9901-5801")
                .checkDisplayingAnalogBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}