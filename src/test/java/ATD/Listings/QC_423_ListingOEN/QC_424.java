package ATD.Listings.QC_423_ListingOEN;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_424 {

    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

   /* @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }*/

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Going to route category_oen from search bar")
    public void testGoingToRouteCategoryOenFromSearch(String route) {
        List<String> projectName = Arrays.asList("ios_atd_se", "ios_atd_de", "ios_atd_uk");
        List<String> searchLanguage = Arrays.asList("uk", "de");
        String phrase = searchElement(projectName, searchLanguage);
        System.out.println(phrase);
    }


    public static String searchElement(List<String> firstList, List<String> secondList) {
        int sumOfСoincidences = 0;
        for (int i = 0; i < firstList.size(); i++) {
            for (int j = 0; j < secondList.size(); j++) {
                if (firstList.get(i).contains(secondList.get(j))) {
                    sumOfСoincidences++;
                }
            }
        }
        if (sumOfСoincidences > 2) {
            return "It's a big digit";
        } else
            return "It's a small digit";
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
