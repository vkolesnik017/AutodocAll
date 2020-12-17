package ATD;

import Common.DataBase;
import Common.SetUp;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.*;

import static Common.CommonMethods.generationRandomDates;


public class ForTest extends SetUp {


    @Test()
    public void test() throws SQLException {
        List<String> test = new DataBase().getNameRequisitesMethod("bank_requisites_atd", "other", "Owner", "Bank", "IBAN", "Sort Code");
        }


    @Test()
    public void test2() throws SQLException {
        String date = generationRandomDates(5);
        System.out.println(date);
    }
    }



