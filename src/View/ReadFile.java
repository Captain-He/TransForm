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
//		readfile("C:/Users/Admin/Desktop/�豸�ͺű�-V1.0.xlsx","C:/Users/Admin/Desktop/ʩ����¼v0.5-ģ������.xlsx");
//	}

	public void readfile(String filePath1,String filePath2,String savepath) {
		WriteFile writefile = new WriteFile();
		//filePath1 Ϊʩ����¼��  filePath2 Ϊ�豸�ͺű�
		//data1 Ϊ��������֯����� �� data Ϊʩ����¼�� data2 Ϊ�豸�ͺű� 
		String data1[][] =readReferTable(filePath1);
		String data2[][][] =readDevTable(filePath1);
		String data[][][] = readBuildTable(filePath2);
		/**���±�**/
		String textPath = savepath+"/test.txt";
		CeWenTable cewen_table = new CeWenTable();
		String res = cewen_table.toTempTxt(data1,data,data2);
		try {
			writefile.WriteToFile(res, textPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**�����Ǳ�**/
		String textPathDian = savepath+"/testdian.txt";
		DianLiTable dianli_table = new DianLiTable();
		String resDian = dianli_table.toDianTxt(data1,data,data2);
		try {
			writefile.WriteToFile(resDian, textPathDian);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**�豸��֯�ṹ��**/
		String textPathstructure = savepath+"/testStructure.txt";
		StrucTable structable = new StrucTable();
		String resStructure = structable.toStructureTxt(data);
		try {
			writefile.WriteToFile(resStructure, textPathstructure);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**�������ñ���Ϣ**/
		String textPathDataConfing = savepath+"/textPathDataConfing.txt";
		DataConfigTable dataconfigtable = new DataConfigTable();
		String resDataConfigTable = dataconfigtable.toDataConfigTableTxt(data,data2);
		try {
			writefile.WriteToFile(resDataConfigTable, textPathDataConfing);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**���������豸��Ӧ��ϵ��**/
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
		int sheetNum = wb.getNumberOfSheets(); // sheet����
		String[][][] BuildTable = new String[sheetNum][30][30];
		if (wb != null) {
			// ��ȡ��һ��sheet
			for (int i = 0; i < sheetNum; i++) {
				sheet = wb.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();// ��ȡ�������
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
		int sheetNum = wb.getNumberOfSheets(); // sheet����
		String[][][] DevTable = new String[sheetNum][10][20];
		if (wb != null) {
			// ��ȡ��һ��sheet
			for (int i = 2; i < sheetNum; i++) {
				sheet = wb.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();// ��ȡ�������
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

	// ��ȡ��������֯�������������
	public static String[][] readReferTable(String dir) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readExcel(dir);
		sheet = wb.getSheetAt(1); // ��ȡ��2��sheet
		int rownum = sheet.getPhysicalNumberOfRows(); // ��ȡ�������
		String[][] referTable = new String[rownum][100];
		for (int i = 3; i < rownum; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				int colnum = row.getPhysicalNumberOfCells(); // ��ȡ�������
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

	// ��ȡexcel
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
	
}
