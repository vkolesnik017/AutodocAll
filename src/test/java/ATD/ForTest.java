package ATD;

import Common.DataBase;
import Common.SetUp;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.*;


public class ForTest extends SetUp {


    @Test()
    public void test() throws SQLException {
       String nameFirm = new DataBase("ATD").getFirmData("firm_atd", "NL", "Name");
        System.out.println(nameFirm);
    }


    @Test()
    public void test2() throws SQLException {
        String[] payments = {"HypoVereinsbank", "PayPal", "Be2bill"};
        int minValue = 0;
        int randomIndex = minValue + (int) (Math.random() * payments.length);
        String paymentName = payments[randomIndex];
    }

}


