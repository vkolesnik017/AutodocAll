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
        for (String d : test) {
            System.out.println(d);
        }
    }

    @Test()
    public void test2() throws SQLException {
        ArrayList<String> requisites;
        ArrayList<String> requisitesParameter = new ArrayList<>();
        requisitesParameter.add("Owner");
        requisitesParameter.add("Account number");
        requisitesParameter.add("Sort Code");
        requisitesParameter.add("Bank");
        requisitesParameter.add("IBAN");
        requisitesParameter.add("BIC_SWIFT");
        for (int i = 0; i < requisitesParameter.size(); i++) {
            requisites = new DataBase("PKW").getNameRequisitesMethod("bank_requisites_pkw", "HU", String.valueOf(requisitesParameter.get(i)));
            //String requisitesStringFormat = requisites.toString().replaceAll("(\\[)(.+)(\\])", "$2");
            //System.out.println(requisitesStringFormat);
            System.out.println(requisites);
        }
    }
}


