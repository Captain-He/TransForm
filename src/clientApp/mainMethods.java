package clientApp;

import java.io.IOException;

import readBuildTables.readBuildTables;
import readDevTables.readDevTables;
import readReferTable.readReferTable;
import toCeWenTable.toCeWenTable;
import toDataConfigTable.toDataConfigTable;
import toDevRelationTable.toDevRelationTable;
import toDianLiTable.toDianLiTable;
import toStrucTable.toStrucTable;
import writeFile.WriteFile;

public class mainMethods {

	public void mainMethod(String filePath1,String filePath2,String savepath,String buildTable[][][],String devTable[][][]) {
		WriteFile writefile = new WriteFile();
		//filePath1 为施工记录表  filePath2 为设备型号表
		//referTable为数据再组织规则表 ；buildTable 为施工记录表； devTable 为设备型号表 
		readReferTable readrefertable = new readReferTable();
		String referTable[][] =readrefertable.readReferTable(filePath1);
		/**测温表**/
		toCeWenTable cewen = new toCeWenTable();
		String textPath = savepath+"/cewen.txt";
		String res = cewen.toTempTxt(referTable,buildTable,devTable);
		try {
			writefile.WriteToFile(res, textPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**电力仪表**/
		String textPathDian = savepath+"/dianli.txt";
		toDianLiTable dianli_table = new toDianLiTable();
		String resDian = dianli_table.toDianTxt(referTable,buildTable,devTable);
		try {
			writefile.WriteToFile(resDian, textPathDian);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**设备组织结构表**/
		String textPathstructure = savepath+"/Structure.txt";
		toStrucTable structable = new toStrucTable();
		String resStructure = structable.toStructureTxt(buildTable);
		try {
			writefile.WriteToFile(resStructure, textPathstructure);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**数据配置表信息**/
		String textPathDataConfing = savepath+"/DataConfing.txt";
		toDataConfigTable dataconfigtable = new toDataConfigTable();
		String resDataConfigTable = dataconfigtable.toDataConfigTableTxt(buildTable,devTable);
		try {
			writefile.WriteToFile(resDataConfigTable, textPathDataConfing);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**传感器与设备对应关系表**/
		String textpathDevRelation = savepath+"/DevRelation.txt";
		toDevRelationTable devrelateiontable = new toDevRelationTable();
		String canzhao = structable.backdata();
		String resDevRelationTable = devrelateiontable.toDevRelationTableTxt(buildTable,canzhao);
		try {
			writefile.WriteToFile(resDevRelationTable, textpathDevRelation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
