package readXlsxFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadSensorTypeFile {
	//读传感器型号列表，用来生成sql文件中的 生成表 和 触发器
	private final int max_X = 100;
	private final int max_Y = 4;
	public final String[] sensorTypeListTable;
	public static String report = "errors:\r\n";
	private int sensorTypeListTableStartRow = 6;//填表说明 表头开始的行
	private int sensorTypeListTableStartSheet = 0;//填表说明 所在sheet
	private int sensorTypeListTableTypeList = 1;//填表说明 型号 所在列
	private int sensorDevTypeTableStartRow = 0; //其他传感器类型表 表头开始的行
	public ReadSensorTypeFile(String dir){
		ReadTables readDevtables = new ReadTables();
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readDevtables.readExcel(dir);
		String[] array= new String[max_X];
		if (wb != null) {
				sheet = wb.getSheetAt(sensorTypeListTableStartSheet);
				int rownum = sheet.getPhysicalNumberOfRows();
				for (int j = sensorTypeListTableStartRow+1; j < readDevtables.getSheetRows(sheet); j++) {
					row = sheet.getRow(j);
					if (row != null) {
						int colnum = row.getPhysicalNumberOfCells();
						for (int k = 0; k < colnum; k++) {
							String cellData = (String) readDevtables.getCellFormatValue(row
									.getCell(sensorTypeListTableTypeList));
							if (cellData != null && cellData.length() > 0) {
								array[j - sensorTypeListTableStartRow-1] = cellData;
							}
						}
					}
				}
		}
		this.sensorTypeListTable = array;
	}


/*	private static int findTypeListNum(String []oneRowList,String aimStr){
		for(int i=0;i<oneRowList.length;i++){
			if(oneRowList[i] == null) continue;
			if(slim(oneRowList[i]).equals(aimStr)) return i;
		}
		return -1;
	}*/
	/*
	public static String[][][] readTables(String dir) {
		ReadTables readDevtables = new ReadTables();
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readDevtables.readExcel(dir);
		int sheetNum = wb.getNumberOfSheets(); // sheet
		String[][][] array= new String[sheetNum][max_X][max_Y];
		if (wb != null) {
			for (int i = 2; i < sheetNum; i++) {
				sheet = wb.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();
				for (int j = 0; j < rownum; j++) {
					row = sheet.getRow(j);
					if (row != null) {
						int colnum = row.getPhysicalNumberOfCells();
						for (int k = 0; k < colnum; k++) {
							String cellData = (String) readDevtables.getCellFormatValue(row
									.getCell(k));
							if (cellData != null && cellData.length() > 0) {
								array[i - 2][j][k] = cellData;
							}
						}
					}
				}
			}
		}

		DevTable = array;
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i][1].length;j++){
				if(array[i][1][j] == null) break;
				if(slim(array[i][1][j],"读取全部数据需要几次")){
					checkNum(i,j,100,1);
				}
				if(slim(array[i][1][j],"参数分段序号")){
					checkNum(i,j,100,1);
				}
				if(slim(array[i][1][j],"功能码")){
					checkNum(i,j,255,0);
				}
				if(slim(array[i][1][j],"寄存器起始地址")){
					checkNum(i,j,65535,0);
				}
				if(slim(array[i][1][j],"寄存器数量")){
					checkNum(i,j,65535,0);
				}
				if(slim(array[i][1][j],"数据类型")){
					checkStr(i,j);
				}
				if(slim(array[i][1][j],"寄存器地址")){
					checkNums(i,j,65535,0);
				}
				if(slim(array[i][1][j],"单个数据占据的寄存器数量")){
					checkNums(i,j,2,1);
				}
//				if(slim(array[i][1][j],"倍率")){
//					checkNums(i,j,1,0);
//				}
			}
		}
		return array;
	}
	public static boolean slim(String a,String b){
		return a.replaceAll("\n", "").replaceAll(" ", "").equals(b);
	}

	public static void checkNum(int i,int j,int up,int down){
		for(int k=2;k<DevTable[i].length;k++){
			if(DevTable[i][k][j] == null) break;
			double a = sTurnd(DevTable[i][k][j]);
			if(!(a<=up&&a>=down))
				report +="设备型号表文件  - "+DevTable[i][0][0]+" 表:第  "+(k+1)+" 行  第 "+(j+1)+" 列数据错误\r\n";
		}
	}
	public static void checkStr(int i,int j){
		for(int k=2;k<DevTable[i].length;k++){
			if(DevTable[i][k][j] == null) break;
			String a[] = DevTable[i][k][j].split("/");
			for(int q=0;q<a.length;q++){
				if(a[q] == null) break;
				String temp = slim(a[q]);
				if(!(temp.equals("SHORT")||temp.equals("FLOAT")||temp.equals("LONG")||temp.equals("USHORT"))){
					report +="设备型号表文件  - "+DevTable[i][0][0]+" 表:第  "+(k+1)+" 行  第 "+(j+1)+" 列数据错误\r\n";
					break;
				}
			}
		}
	}
	public static void checkNums(int i,int j,int up,int down){
		for(int k=2;k<DevTable[i].length;k++){
			if(DevTable[i][k][j] == null) break;
			String a[] = DevTable[i][k][j].split("/");
			for(int q=0;q<a.length;q++){
				if(a[q] == null) break;
				double b = sTurnd(a[q]);
				if(!(b<=up&&b>=down)){
					report +="设备型号表文件  - "+DevTable[i][0][0]+" 表:第  "+(k+1)+" 行  第 "+(j+1)+" 列数据错误\r\n";
					break;
				}
			}
		}
	}
	public static double sTurnd(String a){
		return Double.valueOf(a);
	}
	public static String slim(String a){
		return a.replaceAll("\n", "").replaceAll(" ", "");
	}
	public String errors(){
		return report;
	}
	*/
}
