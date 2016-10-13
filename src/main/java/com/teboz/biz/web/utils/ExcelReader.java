package com.teboz.biz.web.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 读取excel
 * 
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExcelReader {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelReader.class);

    /**
     * Excel 2003
     */
    private final static String XLS = "xls";
    /**
     * Excel 2007
     */
    private final static String XLSX = "xlsx";

    private Workbook workbook;

    public ExcelReader(InputStream is, String extName) {
        init(is, extName);
    }

    public void init(InputStream is, String extName) {
        try {
            if (extName.toLowerCase().equals(XLS)) {
                workbook = new HSSFWorkbook(is);
            } else if (extName.toLowerCase().equals(XLSX)) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public List<List<String>> readSheets() throws IOException {
        List<List<String>> list = new ArrayList<>();
        int sheetNums = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetNums; i++) {
            list.addAll(readSheet(i));
        }
        return list;
    }

    public List<List<String>> readSheet(int sheetNum) throws IOException {

        Sheet sheet = workbook.getSheetAt(sheetNum);

        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        List<List<String>> list = new ArrayList<>();

        int minRowIx = sheet.getFirstRowNum() + 1;
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            List<String> line = new ArrayList<>();

            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();
            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null) {
                    // line.add("N/A");
                    continue;
                }
                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
                switch (cellValue.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        line.add(String.valueOf(cellValue.getBooleanValue()));
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            line.add(String.valueOf(cell.getDateCellValue()));
                        } else {
                            String cellValueStr = (new BigDecimal(cellValue.getNumberValue()).toPlainString());
                            if (!StringUtils.isEmpty(cellValueStr) && cellValueStr.endsWith(".0")) {
                                cellValueStr = cellValueStr.substring(0, cellValueStr.length() - 2);
                            }
                            line.add(cellValueStr);
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        line.add(cellValue.getStringValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
            }
            list.add(line);
        }
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // ExcelReader reader = new ExcelReader(new
        // FileInputStream("D:/my/wb/rail/test.xls"), "xls");
        // reader.readSheets();
        String cellValueStr = "123.0";
        if (!StringUtils.isEmpty(cellValueStr) && cellValueStr.endsWith(".0")) {
            cellValueStr = cellValueStr.substring(0, cellValueStr.length() - 2);
        }
        System.out.println(cellValueStr);
    }

}
