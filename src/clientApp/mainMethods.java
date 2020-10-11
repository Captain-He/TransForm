package clientApp;

import readBuildTables.ReadBuildTables;
import readDevTables.ReadDevTables;
import readReferTable.ReadReferTable;
import convertToTxt.ConcentratorList;
import convertToTxt.DevLink;
import convertToTxt.DpuList;
import convertToTxt.SensorDevList;

public class MainMethods {

	public  boolean mainMethod(String buildPath,String devPath,String savePath) {
		//referTable为数据再组织规则表 ；buildTable 为施工记录表； devTable 为设备型号表 
		ReadDevTables  devTable = new ReadDevTables();
		String devTables[][][] = devTable.readTables(devPath);
		
		ReadReferTable referTable = new ReadReferTable();
		String referTables[][] = referTable.readReferTable(devPath);
		
		ReadBuildTables buildTable = new ReadBuildTables();
		String buildTables[][][] = buildTable.readTables(buildPath);
		
		DevLink dl = new DevLink();
		dl.toTxt(buildTables, savePath+"/dev_link.txt");
		
		ConcentratorList cl = new ConcentratorList();
		cl.toTxt(buildTables, devTables,savePath+"/concentrator_list.txt");
		
		DpuList dpuTxt = new DpuList();
		dpuTxt.toTxt(buildTables, savePath+"/dpu_list.txt");

		SensorDevList sd = new SensorDevList();
		sd.toTxt(buildTables, devTables,referTables,savePath+"/sensor_dev_list.txt");
		
		return true;
	}
}
