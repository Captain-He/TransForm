package readReferTable;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import readTables.ReadTables;

public class ReadReferTable {

	public static final int max_Y = 100;//数组中表列数的最大值
	public static final int max_x= 10;//数组中表行数的最大值
	// 读取数据再组织规则表，返回数组
		public  String[][] readReferTable(String dir) {
			ReadTables readrefertable = new ReadTables();
			Workbook wb = null;
			Sheet sheet = null;
			Row row = null;
			wb = readrefertable.readExcel(dir);
			sheet = wb.getSheetAt(1); // 获取第2个sheet
			int rownum = sheet.getPhysicalNumberOfRows(); // 获取最大行数
			String[][] referTable = new String[rownum][max_Y];
			for (int i = 3; i < rownum; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					int colnum = row.getPhysicalNumberOfCells(); // 获取最大列数
					for (int k = 1; k < colnum; k++) {
						String cellData = (String) readrefertable.getCellFormatValue(row
								.getCell(k));
						if (cellData != null && cellData.length() > 0) {
							referTable[i - 3][k] = cellData;
						}
					}
				}
			}
			return referTable;
		}
		public int returnItemNum(String array[][],String value){
			int index = 0;
			for(int i=0;i<array.length;i++){
				for(int j=0;j<array[i].length;j++){
					if(array[i][j] == null) continue;
					if(slim(array[i][j]).equals(value)){
						index = j;
					}
						
				}
			}
			return index;
		}
		public static String slim(String str){
			String a = "";
			char array[] = str.replaceAll("\n", "").replaceAll(" ", "").replace("\r", "").toCharArray();
			for(int i=0;i<array.length;i++){
				if(array[i]=='|'){
					break;
				}
				a+=array[i];
			}
			return a;
		}
}
