package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("resource")
public class ReadFile {
//	public static void main(String[] args) throws IOException {
//		readfile("C:/Users/Admin/Desktop/设备型号表-V1.0.xlsx","C:/Users/Admin/Desktop/施工记录v0.5-模拟数据.xlsx");
//	}

	public void readfile(String filePath1,String filePath2,String savepath) {
		WriteFile writefile = new WriteFile();
		//filePath1 为施工记录表  filePath2 为设备型号表
		//data1 为数据再组织规则表 ； data 为施工记录表； data2 为设备型号表 
		String data1[][] =readReferTable(filePath1);
		String data2[][][] =readDevTable(filePath1);
		String data[][][] = readBuildTable(filePath2);
		/**测温表**/
		String textPath = savepath+"/test.txt";
		CeWenTable cewen_table = new CeWenTable();
		String res = cewen_table.toTempTxt(data1,data,data2);
		try {
			writefile.WriteToFile(res, textPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**电力仪表**/
		String textPathDian = savepath+"/testdian.txt";
		DianLiTable dianli_table = new DianLiTable();
		String resDian = dianli_table.toDianTxt(data1,data,data2);
		try {
			writefile.WriteToFile(resDian, textPathDian);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**设备组织结构表**/
		String textPathstructure = savepath+"/testStructure.txt";
		StrucTable structable = new StrucTable();
		String resStructure = structable.toStructureTxt(data);
		try {
			writefile.WriteToFile(resStructure, textPathstructure);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**数据配置表信息**/
		String textPathDataConfing = savepath+"/textPathDataConfing.txt";
		DataConfigTable dataconfigtable = new DataConfigTable();
		String resDataConfigTable = dataconfigtable.toDataConfigTableTxt(data,data2);
		try {
			writefile.WriteToFile(resDataConfigTable, textPathDataConfing);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**传感器与设备对应关系表**/
		String textpathDevRelation = savepath+"/textDevRelation.txt";
		DevRelationTable devrelateiontable = new DevRelationTable();
		String canzhao = structable.backdata();
		String resDevRelationTable = devrelateiontable.toDevRelationTableTxt(data,canzhao);
		try {
			writefile.WriteToFile(resDevRelationTable, textpathDevRelation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  String[][][] readBuildTable(String dir) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readExcel(dir);
		int sheetNum = wb.getNumberOfSheets(); // sheet数量
		String[][][] BuildTable = new String[sheetNum][30][30];
		if (wb != null) {
			// 获取第一个sheet
			for (int i = 0; i < sheetNum; i++) {
				sheet = wb.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();// 获取最大行数
				for (int j = 0; j < rownum; j++) {
					row = sheet.getRow(j);
					if (row != null) {
						int colnum = row.getPhysicalNumberOfCells();
						for (int k = 0; k < colnum; k++) {
							String cellData = (String) getCellFormatValue(row
									.getCell(k));
							if (cellData != null && cellData.length() > 0) {
								BuildTable[i][j][k] = cellData;
							}
						}
					}
				}
			}
		}
		return BuildTable;
	}

	public static String[][][] readDevTable(String dir) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readExcel(dir);
		int sheetNum = wb.getNumberOfSheets(); // sheet数量
		String[][][] DevTable = new String[sheetNum][10][20];
		if (wb != null) {
			// 获取第一个sheet
			for (int i = 2; i < sheetNum; i++) {
				sheet = wb.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();// 获取最大行数
				for (int j = 0; j < rownum; j++) {
					row = sheet.getRow(j);
					if (row != null) {
						int colnum = row.getPhysicalNumberOfCells();
						for (int k = 0; k < colnum; k++) {
							String cellData = (String) getCellFormatValue(row
									.getCell(k));
							// System.out.print(cellData);
							if (cellData != null && cellData.length() > 0) {
								DevTable[i - 2][j][k] = cellData;
							}
						}
					}
				}
			}
		}
		return DevTable;
	}

	// 读取数据再组织规则表，返回数组
	public static String[][] readReferTable(String dir) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readExcel(dir);
		sheet = wb.getSheetAt(1); // 获取第2个sheet
		int rownum = sheet.getPhysicalNumberOfRows(); // 获取最大行数
		String[][] referTable = new String[rownum][100];
		for (int i = 3; i < rownum; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				int colnum = row.getPhysicalNumberOfCells(); // 获取最大列数
				for (int k = 1; k < colnum; k++) {
					String cellData = (String) getCellFormatValue(row
							.getCell(k));
					if (cellData != null && cellData.length() > 0) {
						referTable[i - 3][k] = cellData;
					}
				}
			}
		}
		return referTable;
	}

	// 读取excel
	public static Workbook readExcel(String filePath) {
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

	public static Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			// 判断cell类型
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: {
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			}
			case Cell.CELL_TYPE_FORMULA: {
				// 判断cell是否为日期格式
				if (DateUtil.isCellDateFormatted(cell)) {
					// 转换为日期格式YYYY-mm-dd
					cellValue = cell.getDateCellValue();
				} else {
					// 数字
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
	
}
