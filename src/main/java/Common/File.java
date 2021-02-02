package Common;

import com.codeborne.pdftest.PDF;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import java.io.IOException;
import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;

public class File {

    @Step("Checks the text in the downloaded PDF file and deleted file")
    public static void assertThatPdfContainsText(String path, String expectedText) throws IOException {
        java.io.File file = new java.io.File(path);
        if (file.isFile()) {
            System.out.println("File exists");
        } else {
            Assert.fail("File does not exist");
        }
        PDF pdf = new PDF(new java.io.File(path));
        sleep(5000);
        assertThat(pdf, containsText(expectedText));
        if (file.delete()) {
            System.out.println(path + " File deleted");
        } else  Assert.fail(path + " File not found");
    }

    @Step("Rename download file")
    public static void renameDownloadFile(String oldNameFile, String newNameFile) {
        java.io.File oldFile = new java.io.File(oldNameFile);
        java.io.File newFile = new java.io.File(newNameFile);
        sleep(5000);
        if(oldFile.renameTo(newFile)){
            System.out.println("Rename succesful");
        }else{
            Assert.fail("Rename failed");
        }
    }

    @Step("Delete directory")
    public static void deleteDirectory(String path) throws IOException {
        java.io.File file = new java.io.File(path);
        FileUtils.deleteDirectory(file);
    }

}
