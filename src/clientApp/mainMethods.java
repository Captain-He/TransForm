package clientApp;

import convertToTxt.*;
import readXlsxFile.ReadBuildFile;
import readXlsxFile.ReadDevFile;
import readXlsxFile.ReadReferTable;
import sensorTypeModel.SensorTypeTables;

public class MainMethods {

	public  boolean mainMethod(String buildFilePath,String concentratorFilePath,String sensorTypeFilePath, String saveTxtPath) {
		//referTable为数据再组织规则表 ；buildTable 为施工记录表； devTable 为设备型号表

		String concentratorDevFile[][][] = ReadDevFile.readTables(concentratorFilePath);
		String buildFile[][][] = ReadBuildFile.readTables(buildFilePath);

		SensorTypeTables sensorTypeTables = new SensorTypeTables(sensorTypeFilePath);

		DevLink dl = new DevLink();
		dl.toTxt(buildFile,saveTxtPath+"dev_link.txt");

		DpuList dpuTxt = new DpuList();
		dpuTxt.toTxt(buildFile, saveTxtPath+"dpu_list.txt");

		ConcentratorList cl = new ConcentratorList();
		cl.toTxt(buildFile, concentratorDevFile,saveTxtPath+"concentrator_list.txt");

		ReadReferTable referTable = new ReadReferTable();
		String referTables[][] = referTable.readReferTable(concentratorFilePath);

		SensorDevList sd = new SensorDevList();
		sd.toTxt(buildFile, concentratorDevFile,sensorTypeTables.sensorTypes,saveTxtPath+"sensor_dev_list.txt");
		Sql a = new Sql(buildFilePath,sensorTypeTables.sensorTypes, saveTxtPath);
		a.toTxt();
		return true;
	}
}
