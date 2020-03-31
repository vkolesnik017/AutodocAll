package ATD;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;


public class ForTest {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search9");
    }

    String email = "qc_1488_autotestDE@mailinator.com";
    String password = "atdtest";

    @Test(dataProvider = "route")
    public void testRetryVisibility(String route) throws SQLException {
        String shop= "DE";
//        openPage(route);
//        clickOfBuyBtnForAllPages();
//        new Search_page_Logic().closePopupOtherCategoryIfYes()
//                .cartClick().nextButtonClick()
//                .signIn(email, password).fillingPostalCodeField(97100);


        /*
        String expectedText = new DataBase().getTranslate("convir_translate", shop, "test");
        expectedText = replace(expectedText, "54564", "textar");
        System.out.println(expectedText);
        */
        System.out.println(new DataBase().getFullRouteByRouteAndSubroute("subprod", shop, "lkw_main", "lkw_category_car_list"));
    }

    public String replace(String textForReplace, String order, String article){
        String text;
        text = textForReplace.replace("{Engine}", order).replace("{LP565}", article);
        return text;
    }
//    @AfterMethod
//    private void teatDown() {
//        close();
//    }


}



