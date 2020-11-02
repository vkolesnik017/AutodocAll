package Common;

import com.codeborne.pdftest.PDF;
import io.qameta.allure.Step;

import java.io.IOException;

import static com.codeborne.pdftest.PDF.containsText;
import static org.hamcrest.MatcherAssert.assertThat;

public class File {

    @Step("Checks the text in the downloaded PDF file and deleted file")
    public static void assertThatPdfContainsText(String path, String expectedText) throws IOException {
        java.io.File file = new java.io.File(path);
        PDF pdf = new PDF(new java.io.File(path));
        assertThat(pdf, containsText(expectedText));
        if (file.delete()) {
            System.out.println(path + " File deleted");
        } else System.out.println(path + " File not found");
    }

    @Step("Rename download file")
    public static void renameDownloadFile(String oldNameFile, String newNameFile) {
        java.io.File oldFile = new java.io.File(oldNameFile);
        java.io.File newFile = new java.io.File(newNameFile);
        if(oldFile.renameTo(newFile)){
            System.out.println("Rename succesful");
        }else{
            System.out.println("Rename failed");
        }
    }
}
