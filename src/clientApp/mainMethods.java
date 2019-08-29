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
		//filePath1 Ϊʩ����¼��  filePath2 Ϊ�豸�ͺű�
		//referTableΪ��������֯����� ��buildTable Ϊʩ����¼�� devTable Ϊ�豸�ͺű� 
		readReferTable readrefertable = new readReferTable();
		String referTable[][] =readrefertable.readReferTable(filePath1);
		/**���±�**/
		toCeWenTable cewen = new toCeWenTable();
		String textPath = savepath+"/cewen.txt";
		String res = cewen.toTempTxt(referTable,buildTable,devTable);
		try {
			writefile.WriteToFile(res, textPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**�����Ǳ�**/
		String textPathDian = savepath+"/dianli.txt";
		toDianLiTable dianli_table = new toDianLiTable();
		String resDian = dianli_table.toDianTxt(referTable,buildTable,devTable);
		try {
			writefile.WriteToFile(resDian, textPathDian);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**�豸��֯�ṹ��**/
		String textPathstructure = savepath+"/Structure.txt";
		toStrucTable structable = new toStrucTable();
		String resStructure = structable.toStructureTxt(buildTable);
		try {
			writefile.WriteToFile(resStructure, textPathstructure);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**�������ñ���Ϣ**/
		String textPathDataConfing = savepath+"/DataConfing.txt";
		toDataConfigTable dataconfigtable = new toDataConfigTable();
		String resDataConfigTable = dataconfigtable.toDataConfigTableTxt(buildTable,devTable);
		try {
			writefile.WriteToFile(resDataConfigTable, textPathDataConfing);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**���������豸��Ӧ��ϵ��**/
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
