package ATD;

import Common.DataBase;
import Common.SetUp;
import mailinator.WebMail;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class ForTest extends SetUp {


    @Test()
    public void test() throws SQLException {
        List<String> test = new DataBase().getNameRequisitesMethod("bank_requisites_atd", "BG", "Owner","Bank","IBAN");
        for (String d : test) {
            System.out.println(d);
        }
    }
}


