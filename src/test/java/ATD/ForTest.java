package ATD;

import Common.DataBase;
import Common.SetUp;
import mailinator.WebMail;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForTest extends SetUp {


    @Test()
    public void test() throws SQLException {
        List<String> test = new DataBase().getNameRequisitesMethod("bank_requisites_atd", "other", "Owner", "Bank", "IBAN", "Sort Code");
        }


    @Test()
    public void test2() throws SQLException {
        System.out.println(new DataBase().getAWSTranslationCountries("Österreich"));
        }
    }



