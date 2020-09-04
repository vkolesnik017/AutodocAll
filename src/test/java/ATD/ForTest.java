package ATD;

import AWS.CatalogCategories_aws;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
public class ForTest extends SetUp {


    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test()
    public void test() throws SQLException {
        String selector = "1.5 (40 KW / 55 PS) (09.1968 - ...)";

        String carName = "1.5";
        String kw = "40";
        String hp = "55";
        String yearBegin = "196809";
        String yearEnd = "0";


        String yearBeginMonth = yearBegin.substring(4);
        yearBegin = yearBeginMonth.concat(".").concat(yearBegin.substring(0,4)).trim();

        if(!yearEnd.equals("0")){
            String yearEndMonth = yearEnd.substring(4);
            yearEnd = yearEndMonth.concat(".").concat(yearEnd.substring(0, 4)).trim();
        }else {
            yearEnd = "...";
        }
        selector = selector.replace(" ", "");

        String carNameFull = carName + "(" + kw + "KW" + "/" + hp + "PS" + ")" + "(" + yearBegin + "-" +yearEnd + ")";

        System.out.println(carNameFull);
        System.out.println(selector);
        System.out.println(carNameFull.equals(selector));
    }

}


