package PKW;

import io.qameta.allure.Step;

public class Parts_group_page_Logic extends Parts_group_page {

    @Step("Get name title page. Parts_group_page")
    public String getNameTitlePage() {
        return titlePage().getText();
    }
}
