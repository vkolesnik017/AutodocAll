package ATD;

import Common.DataBase;
import Common.SetUp;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.*;

import static Common.CommonMethods.generationRandomDates;
import static Common.CommonMethods.roundOfTheCost;


public class ForTest extends SetUp {


    @Test()
    public void test() throws SQLException {
        List<String> test = new DataBase().getNameRequisitesMethod("bank_requisites_atd", "other", "Owner", "Bank", "IBAN", "Sort Code");
        }


    @Test()
    public void test2() throws SQLException {
        float test = roundOfTheCost(4.765f, 4.77f);
        System.out.println(test);
    }
    }



