package Common;

import com.codeborne.pdftest.PDF;
import io.qameta.allure.Step;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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

    @Step("Checking the content in the opened browser pdf")
    public static String readPdfContent(String url) throws IOException {
        URL pdfUrl = new URL(url);
        InputStream inputStream = pdfUrl.openStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        PDDocument doc = PDDocument.load(bufferedInputStream);
        String content = new PDFTextStripper().getText(doc).replaceAll(" ", "").replaceAll("\\W", "");
        bufferedInputStream.close();
        return content;
    }
}
