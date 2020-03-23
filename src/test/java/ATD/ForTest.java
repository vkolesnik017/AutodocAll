package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class ForTest {

    @BeforeClass
    public void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    SelenideElement counterValue = $(By.xpath("//div[@class='count product-count']/input"));
    SelenideElement counterPlus = $(By.xpath("//div[@class='count product-count']/a[2]"));

    @Test(dataProvider = "route")
    public void testRetryVisibility(String route) {
        open(route);
        new CommonMethods().checkingCounterIncrease(3, counterValue, counterPlus);
    }

//    @AfterMethod
//    private void teatDown() {
//        close();
//    }


}



