package sensorTypeModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import readXlsxFile.ReadTables;

import java.util.ArrayList;

public class SensorTypeTables {
	//读传感器型号列表，用来生成sql文件中的 生成表 和 触发器
	private int max_X = 100;
	private int max_Y = 4;
	public ArrayList<SensorType> sensorTypes;
	public String[] sensorTypeListTable;
	public static String report = "errors:\r\n";
	private int sensorTypeListTableStartRow = 6;//填表说明 表头开始的行
	private int sensorTypeListTableStartSheet = 0;//填表说明 所在sheet
	private int sensorTypeListTableTypeList = 1;//填表说明 型号 所在列
	private int sensorDevTypeTableStartRow = 1; //其他传感器类型表 表头开始的行
	public SensorTypeTables(String dir){
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
					String cellData = (String) readDevtables.getCellFormatValue(row
								.getCell(sensorTypeListTableTypeList));
					if (cellData != null && cellData.length() > 0) {
						array[j - sensorTypeListTableStartRow-1] = cellData;
					}
				}
			}
		}
		this.sensorTypeListTable = array;
		sensorTypes = new ArrayList<>();
		for(int l=0;l<array.length;l++){
			if(array[l] == null)break;
			SensorType sensorType = new SensorType();
			if (wb != null) {
				sheet = wb.getSheetAt(l+1);
				int rownum = sheet.getPhysicalNumberOfRows();
				sensorType.setSensorTypeName((String) readDevtables.getCellFormatValue(sheet.getRow(0).getCell(0)));
				ArrayList<DataItem> dataItemArrayList = new ArrayList<>();
				for (int j = sensorDevTypeTableStartRow+1; j < readDevtables.getSheetRows(sheet); j++) {
					row = sheet.getRow(j);
					if (row != null) {
						String cellData = (String) readDevtables.getCellFormatValue(row
								.getCell(0));
						DataItem dataItem = new DataItem();
						dataItem.setItemNum(j-sensorDevTypeTableStartRow);
						dataItem.setDataType((String) readDevtables.getCellFormatValue(row
								.getCell(0)));
						dataItem.setDataMeans((String) readDevtables.getCellFormatValue(row
								.getCell(1)));
						dataItemArrayList.add(dataItem);
						}
					}
				sensorType.setSensorTypeList(dataItemArrayList);
				}
			sensorTypes.add(sensorType);
			}
	}
}

