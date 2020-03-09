package ATD;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excel {

    // read from Excel cell setUpFromExcel(file, 0);
    public Object[] setUpFromExcel(String file, int cellNumber) {
        return readFromExcel(file, cellNumber).toArray();
    }

    // read from Excel cell with choosing sheet name setUpFromExcel(file, "first sheet", 0);
    public Object[] setUpFromExcel(String file, String sheetName, int cellNumber) {
        return readFromExcel(file, sheetName, cellNumber).toArray();
    }

    // read from Excel all rows and cells setUpFromExcel(file);
    //Example: 2#37292#1453#ALFA ROMEO#Stevlio (949_)
    // Use with method parseExcel. String text = parseExcel(str From DataProvider)[1];
    // You get: 37292
    public Object[] setUpAllCellFromExcel(String file) {
        return readAllCellFromExcel(file).toArray();
    }

    public Object[] setUpAllCellFromExcel(String file, String sheetName) {
        return readAllCellFromExcel(file, sheetName).toArray();
    }

   public static String[] parseExcel(String str) {
        return str.split("#");
    }

    private List<String> readFromExcel(String file, int cellNumber) {
        List<String> finalList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Workbook wb = new HSSFWorkbook(fileInputStream);
            Sheet sheet1 = wb.getSheetAt(0);
            for (Row row : sheet1) {
                String cellValue = formatter.formatCellValue(row.getCell(cellNumber));
                finalList.add(cellValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;
    }

    private List<String> readFromExcel(String file, String sheetName, int cellNumber) {
        List<String> finalList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Workbook wb = new HSSFWorkbook(fileInputStream);
            Sheet sheet1 = wb.getSheet(sheetName);
            for (Row row : sheet1) {
                String cellValue = formatter.formatCellValue(row.getCell(cellNumber));
                finalList.add(cellValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;
    }

    private List<String> readAllCellFromExcel(String file) {
        DataFormatter formatter = new DataFormatter();
        List<String> listCell = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Workbook wb = new HSSFWorkbook(fileInputStream);
            Sheet sheet = wb.getSheetAt(0);
            listCell = new ArrayList<>();
            int rowNum = sheet.getLastRowNum();
            for (int i = 0; i <= rowNum; i++) {
                int cellNum = sheet.getRow(i).getLastCellNum();
                StringBuilder txt = new StringBuilder();
                for (int e = 0; e <= cellNum; e++) {
                    String getText = formatter.formatCellValue(sheet.getRow(i).getCell(e));
                    txt.append(getText).append("#");
                }
                listCell.add(txt.substring(0, txt.lastIndexOf("##")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCell;
    }

    private List<String> readAllCellFromExcel(String file, String sheetName) {
        DataFormatter formatter = new DataFormatter();
        List<String> listCell = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Workbook wb = new HSSFWorkbook(fileInputStream);
            Sheet sheet = wb.getSheet(sheetName.toUpperCase());
            listCell = new ArrayList<>();
            int rowNum = sheet.getLastRowNum();
            for (int i = 0; i <= rowNum; i++) {
                int cellNum = sheet.getRow(i).getLastCellNum();
                StringBuilder txt = new StringBuilder();
                for (int e = 0; e <= cellNum; e++) {
                    String getText = formatter.formatCellValue(sheet.getRow(i).getCell(e));
                    txt.append(getText).append("#");
                }
                listCell.add(txt.substring(0, txt.lastIndexOf("##")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCell;
    }


}
