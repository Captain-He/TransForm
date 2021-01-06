package readXlsxFile;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTables {
	// ��ȡexcel
	public  Workbook readExcel(String filePath) {
		Workbook wb = null;
		if (filePath == null) {
			return null;
		}
		String extString = filePath.substring(filePath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb = null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	public  Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			// �ж�cell����
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: {
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			}
			case Cell.CELL_TYPE_FORMULA: {
				// �ж�cell�Ƿ�Ϊ���ڸ�ʽ
				if (DateUtil.isCellDateFormatted(cell)) {
					// ת��Ϊ���ڸ�ʽYYYY-mm-dd
					cellValue = cell.getDateCellValue();
				} else {
					// ����
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING: {
				cellValue = cell.getRichStringCellValue().getString();
				break;
			}
			default:
				cellValue = "";
			}
		} else {
			cellValue = "";
		}
		return cellValue;
	}
	public int getSheetRows(Sheet sheet) {
		//以#####为结束的行数
		Row row = null;
		int rownum = sheet.getPhysicalNumberOfRows();// 获取最大行数
		int rows = 0;
		for (int j = 0; j <= rownum; j++) {
			row = sheet.getRow(j);
			if (row != null) {

				String cellData00 = (String) getCellFormatValue(row
						.getCell(0));
				if ("######".equals(slim(cellData00))) break;
				rows =j;
			}
		}
		return rows;
	}
	public static String slim(String a){
		return a.replaceAll("\n", "").replaceAll(" ", "");
	}
}
