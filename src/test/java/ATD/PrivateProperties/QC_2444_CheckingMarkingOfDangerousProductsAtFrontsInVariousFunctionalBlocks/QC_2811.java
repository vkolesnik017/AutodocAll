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

public class QC_2811 {

    private final String file = "C://Autotests/files/data/QC_2811.xls";
    private ProductCard_aws productPageAws = new ProductCard_aws("8596803");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product59");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test Verify that the display of 'incomplete (no signal word)' dangerous products labeling in AWS and on the product page matches")
    public void testCheckCompareOfIncompleteDangerousProductLabel(String routes) {

        List<String> dangerousPictogramFromAws = productPageAws
                .openProductCardPageAndLogin()
                .displayOfTurnOnOfDangerousProduct()
                .emptyDangerousLabel()
                .getDangerousPictogram();
        List<String> hazardStatementFromAws = productPageAws.getStatementLabels();
        openPage(routes);
        new Product_page_Logic()
                .displayOfDangerousInfoBlock()
                .displayOfDangerousSignalWord("Beachten Sie!")
                .compareDangerousPictogramWithAws(dangerousPictogramFromAws)
                .compareDangerousInfoTextWithAws(hazardStatementFromAws, file);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
