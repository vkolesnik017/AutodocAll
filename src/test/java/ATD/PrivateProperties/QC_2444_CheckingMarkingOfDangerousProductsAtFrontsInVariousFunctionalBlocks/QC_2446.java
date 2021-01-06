package ATD.PrivateProperties.QC_2444_CheckingMarkingOfDangerousProductsAtFrontsInVariousFunctionalBlocks;

import ATD.Product_page_Logic;
import AWS.ProductCard_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;
import java.io.IOException;
import java.sql.SQLException;
import static ATD.CommonMethods.openPage;
import static ATD.Presse_static_page_Logic.readPdfContent;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2446 {

    private Product_page_Logic productPageLogic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        new ProductCard_aws("1888870").openProductCardPageAndLogin().quantityLanguageInPassportManagement();
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "DE,CZ,DK,ES,FI,FR,HU,IT,NL,NO,PL,PT,RO,SE,EN", "main", "productDangerousGoods5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking displayed on product page link on dangerous goods safety data sheet")
    public void testCheckingDisplayedLinkOnDangerousGoodsSafetyDataSheet(String routes) throws IOException {
        openPage(routes);
        String nameLink = productPageLogic.getNameLinkSafetyDataSheet();
        String url = productPageLogic.clickLinkSafetyDataSheetAndGetUrl();
        String text = readPdfContent(url);
        text.contains(nameLink);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
