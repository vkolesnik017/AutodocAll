package ATD;

import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

public class Group_list_page_Logic extends Group_list_page {

    @Step("Checks elements in the pdf Manual block. Group_list_page")
    public Group_list_page_Logic checkElementsInPDFManualBlock() {
        pdfManualTitleBlock().shouldBe(visible);
        Assert.assertFalse(pdfManualTitleBlock().text().isEmpty());
        for (int i = 0; i < previewImages().size(); i++) {
            previewImages().get(i).shouldBe(visible);
            titlesOfManuals().get(i).shouldBe(visible);
            Assert.assertFalse(titlesOfManuals().get(i).text().isEmpty());
            downloadLinkOfManuals().get(i).shouldBe(visible);
            Assert.assertFalse(downloadLinkOfManuals().get(i).text().isEmpty());
            sizeFile().get(i).shouldBe(visible);
            Assert.assertFalse(sizeFile().get(i).text().isEmpty());
            typeFile().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Checks elements in the pdf Manual block. Group_list_page")
    public Group_list_page_Logic checkTransitionToClubPageFromPDFManualBlock() throws SQLException {
        pdfManualBlock().shouldBe(visible);
        for (int i = 0; i < previewImages().size(); i++) {
            titlesOfManuals().get(i).shouldBe(visible).click();
            switchTo().window(1);
            checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "club_manuals_home"));
            closeWindow();
            switchTo().window(0);
        }
        return this;
    }
}
