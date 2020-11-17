package ATD;

import Common.DataBase;
import Common.SetUp;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static Common.CommonMethods.getExpectedCalendarData;

public class ForTest extends SetUp {


    @Test()
    public void test() throws SQLException {
        List<String> test = new DataBase().getNameRequisitesMethod("bank_requisites_atd", "other", "Owner", "Bank", "IBAN", "Sort Code");
        }


    @Test()
    public void test2() throws SQLException {
        String data = getExpectedCalendarData("yyyy-MM-dd", 1,1);
        String moths = String.valueOf(LocalDateTime.now().getDayOfMonth());
        System.out.println(data);
        System.out.println(moths);
    }
    }



