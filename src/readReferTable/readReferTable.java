package readReferTable;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import readTables.readTables;

public class readReferTable {

	//public static final int max_X = 1000;//数组中每张表行数的最大值
	public static final int max_Y = 100;//数组中表列数的最大值
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String a[][] = readReferTable("C:/Users/Admin/Desktop/设备型号表-V1.0.xlsx");
//
//			for(int j=0;j<a.length;j++){
//				for(int k=0;k<a[j].length;k++)
//				{
//					if(a[j][k]== null || a[j][k].length() <= 0) continue;
//					System.out.println(a[j][k]);
//				}
//			}
//			System.out.println(a[0][0]);
//	}
	// 读取数据再组织规则表，返回数组
		public String[][] readReferTable(String dir) {
			readTables readrefertable = new readTables();
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

}
