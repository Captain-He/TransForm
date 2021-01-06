package readXlsxFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import readXlsxFile.ReadTables;

public class ReadDevFile {

	public static final int max_X = 10000;
	public static final int max_Y = 100;
	public static String[][][] DevTable;
	public static String report = "errors:\r\n";


	public static String[][][] readTables(String dir) {
		ReadTables readDevtables = new ReadTables();
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readDevtables.readExcel(dir);
		int sheetNum = wb.getNumberOfSheets(); // sheet
		String[][][] array= new String[sheetNum][max_X][max_Y];
		if (wb != null) {
			for (int i = 1; i < sheetNum; i++) {
				sheet = wb.getSheetAt(i);
				//int rownum = sheet.getPhysicalNumberOfRows();
				for (int j = 0; j < readDevtables.getSheetRows(sheet); j++) {
					row = sheet.getRow(j);
					if (row != null) {
						int colnum = row.getPhysicalNumberOfCells();
						for (int k = 0; k < colnum; k++) {
							String cellData = (String) readDevtables.getCellFormatValue(row
									.getCell(k));
							if (cellData != null && cellData.length() > 0) {
								array[i - 1][j][k] = cellData;
							}
						}
					}
				}
			}
		}

		DevTable = array;
		/*
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i][1].length;j++){
				if(array[i][1][j] == null) break;
				if(slim(array[i][1][j],"��ȡȫ��������Ҫ����")){
					checkNum(i,j,100,1);
				}
				if(slim(array[i][1][j],"�����ֶ����")){
					checkNum(i,j,100,1);
				}
				if(slim(array[i][1][j],"������")){
					checkNum(i,j,255,0);
				}
				if(slim(array[i][1][j],"�Ĵ�����ʼ��ַ")){
					checkNum(i,j,65535,0);
				}
				if(slim(array[i][1][j],"�Ĵ�������")){
					checkNum(i,j,65535,0);
				}
				if(slim(array[i][1][j],"��������")){
					checkStr(i,j);
				}
				if(slim(array[i][1][j],"�Ĵ�����ַ")){
					checkNums(i,j,65535,0);
				}
				if(slim(array[i][1][j],"��������ռ�ݵļĴ�������")){
					checkNums(i,j,2,1);
				}
//				if(slim(array[i][1][j],"����")){
//					checkNums(i,j,1,0);
//				}
			}
		}

		 */
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
				report +="�豸�ͺű��ļ�  - "+DevTable[i][0][0]+" ��:��  "+(k+1)+" ��  �� "+(j+1)+" �����ݴ���\r\n";
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
					report +="�豸�ͺű��ļ�  - "+DevTable[i][0][0]+" ��:��  "+(k+1)+" ��  �� "+(j+1)+" �����ݴ���\r\n";
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
					report +="�豸�ͺű��ļ�  - "+DevTable[i][0][0]+" ��:��  "+(k+1)+" ��  �� "+(j+1)+" �����ݴ���\r\n";
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