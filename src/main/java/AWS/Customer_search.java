package AWS;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Customer_search {

    private final String urlPage = "https://aws.autodoc.de/customer/search";

    public Customer_search openSearchInAwsWithLogin() {
        open(urlPage);
        new Login_aws().loginInAws();
        return this;
    }

    public SelenideElement emailFiled() {
        return $(By.xpath("//input[@name='Filter[email]']"));
    }

    public SelenideElement searchBtn() {
        return $(By.xpath("//div[@class='form-group']/input[@name='search']"));
    }

    public SelenideElement idClientField() {
        return $(By.xpath("//td[@class='customer-id-cell']/a[2]"));
    }


    public Customer_search enterMailAndClickSearch(String mail){
        emailFiled().setValue(mail);
        searchBtn().click();
        return this;
    }

    public String getClientId(){
        return idClientField().getText();
    }

}
