package ATD;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class ForTest {

    @BeforeClass
    public void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }


    @Test()
    public void testWithBug() {
        open("https://github.com/cbeust/testng/issues/1241");
        Assert.assertEquals(2, 3);
        new CommonMethods().scrollToBlockOfTopProducts();
    }

    @Test()
    public void test() {
        open("https://github.com/cbeust/testng/issues/1241");
        Assert.assertEquals(2, 2);
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}



