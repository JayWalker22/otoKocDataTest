package testTools.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private Workbook workbook;
    private Sheet sheet;
    private String path;

    // Constructor
    public ExcelUtils(String path, String sheetName) {
        this.path = path;
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            workbook = WorkbookFactory.create(fileInputStream);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                // Sayfa yoksa otomatik oluştur
                sheet = workbook.createSheet(sheetName);
                save(); // Yeni sayfayı hemen kaydet
            }

        } catch (IOException e) {
            throw new RuntimeException("Excel dosyası okunamadı: " + e.getMessage());
        }
    }

    // Hücredeki veriyi getirir
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        return (cell == null) ? "" : cell.toString();
    }

    // Satır sayısı (0'dan başladığı için +1 gerekebilir)
    public int rowCount() {
        return sheet.getLastRowNum() + 1;
    }

    // Sütun sayısı
    public int columnCount() {
        Row row = sheet.getRow(0);
        return (row != null) ? row.getLastCellNum() : 0;
    }

    // Tüm verileri 2D array olarak getirir
    public String[][] getDataArray() {
        int rows = rowCount();
        int cols = columnCount();
        String[][] data = new String[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                data[i][j] = getCellData(i, j);

        return data;
    }

    // Başlık satırını list olarak döner
    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        if (headerRow != null) {
            for (Cell cell : headerRow) {
                columns.add(cell.toString());
            }
        }
        return columns;
    }

    // Belirli bir hücreye veri yazar
    public void setCellData(String value, int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        Cell cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        cell.setCellValue(value);
        save(); // Her değişiklik sonrası kaydet
    }

    // Overload: sütun adıyla veri yaz
    public void setCellData(String value, String columnName, int rowNum) {
        int colNum = getColumnsNames().indexOf(columnName);
        if (colNum == -1) throw new IllegalArgumentException("Sütun bulunamadı: " + columnName);
        setCellData(value, rowNum, colNum);
    }

    // Başlıksız veri (ilk satırı atlar)
    public String[][] getDataArrayWithoutFirstRow() {
        int rows = rowCount() - 1;
        int cols = columnCount();
        String[][] data = new String[rows][cols];

        for (int i = 1; i < rowCount(); i++)
            for (int j = 0; j < cols; j++)
                data[i - 1][j] = getCellData(i, j);

        return data;
    }

    // Yeni sayfa oluşturur (statik)
    public static void createNewSheet(String filePath, String sheetName) {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream);
             FileOutputStream outputStream = new FileOutputStream(filePath)) {

            workbook.createSheet(sheetName);
            workbook.write(outputStream);

        } catch (IOException e) {
            throw new RuntimeException("Yeni sayfa oluşturulamadı: " + e.getMessage());
        }
    }

    // Çalışma kitabını kaydet
    private void save() {
        try (FileOutputStream out = new FileOutputStream(path)) {
            workbook.write(out);
        } catch (IOException e) {
            throw new RuntimeException("Excel dosyası kaydedilemedi: " + e.getMessage());
        }
    }

    // Manuel kapatma istersen
    public void closeWorkbook() {
        try {
            if (workbook != null) workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}