package readDevTables;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import readTables.ReadTables;

public class ReadDevTables {

	public static final int max_X = 1000;//数组中每张表行数的最大值
	public static final int max_Y = 100;//数组中每张表列数的最大值
	public static String[][][] DevTable; //设备型号表
	public static String report = "errors:\r\n";

	// 读取设备型号表，返回数组
	public String[][][] readTables(String dir) {
		ReadTables readdevtables = new ReadTables(); 
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readdevtables.readExcel(dir);
		int sheetNum = wb.getNumberOfSheets(); // sheet数量
		String[][][] array= new String[sheetNum][max_X][max_Y]; //设备型号表
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
							String cellData = (String) readdevtables.getCellFormatValue(row
									.getCell(k));
							// System.out.print(cellData);
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
}
