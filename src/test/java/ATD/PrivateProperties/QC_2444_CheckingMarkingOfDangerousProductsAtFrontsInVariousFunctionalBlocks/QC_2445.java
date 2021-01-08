package ATD.PrivateProperties.QC_2444_CheckingMarkingOfDangerousProductsAtFrontsInVariousFunctionalBlocks;

import ATD.Product_page_Logic;
import AWS.ProductCard_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.List;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2445 {

    private Product_page_Logic productPageLogic = new Product_page_Logic();
    private ProductCard_aws productCardAws = new ProductCard_aws();
    private String dataFile = "C:/Autotests/files/data/QC-2445_data.xls";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "productDangerousGoods3,productDangerousGoods4,productDangerousGoods5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Check compliance display dangerous products between Aws and product page")
    public void testCheckDisplayDangerousProductsBetweenAwsAndProductPage(String routes) throws SQLException {
        openPage(routes);
        String idOfDangerousProduct = productPageLogic.getProductId();
        String signalWord = productPageLogic.getSignalWordFromDangerousProduct();
        List<String> attributeOfWarningIcon = productPageLogic.getAttributeOfWarningIconInPopUp();
        productPageLogic.checkingPresenceDangerousBlock();
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIcon, signalWord);
        List<String> hazardStatement = productCardAws.getAllActiveHazardStatement();
        openPage(routes);
        productPageLogic.getTextFromExcelAndCheckingItWithSafetyDataSheetTable(dataFile, hazardStatement);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
