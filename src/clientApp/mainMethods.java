package clientApp;

import convertToTxt.*;
import readXlsxFile.ReadBuildFile;
import readXlsxFile.ReadDevFile;
import readXlsxFile.ReadReferTable;
import sensorTypeModel.SensorTypeTables;

public class MainMethods {

	public  boolean mainMethod(String buildFilePath,String concentratorFilePath,String sensorTypeFilePath, String saveTxtPath) {
		//referTableΪ��������֯����� ��buildTable Ϊʩ����¼�� devTable Ϊ�豸�ͺű�

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
