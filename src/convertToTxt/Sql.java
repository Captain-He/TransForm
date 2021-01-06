package convertToTxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.GeneralMap;
import construction.record.functional.SensorsTable;
import readXlsxFile.ReadBuildFile;
import sensorTypeModel.DataItem;
import sensorTypeModel.SensorType;
import writeFile.WriteFile;

public class Sql {
	List<GeneralMap> gmlist;
	private String savepath;
	private ArrayList<SensorType> sensorTypes;
	private StringBuffer insertConent = new StringBuffer();
	private StringBuffer tableConent = new StringBuffer();
	public Sql(String buildPath, ArrayList<SensorType> sensorTypes, String savepath) {
		ReadBuildFile buildTables = new ReadBuildFile();
		this.savepath = savepath;
		this.sensorTypes = sensorTypes;
		SensorsTable gm = new SensorsTable();
		this.gmlist =gm.getList(buildTables.readTables(buildPath));

	}

	public void toTxt() {
		String trigger ="DROP TRIGGER IF EXISTS sensorDataUpdate;\n"
				+ "CREATE TRIGGER sensorDataUpdate AFTER INSERT ON sensordata FOR EACH ROW begin\n"
				+ "	declare tmpType varchar(100);\n"
				+ "	select sensorType into tmpType\n"
				+ "		 from SENSORTYPE\n"
				+ "	where SourceAddr=new.SourceAddr and GroupAddr=new.GroupAddr limit 1;\n"
				+ "\n";
		for (SensorType sensorType:sensorTypes){
			int itemNum = 0;
				for(DataItem dataItem:sensorType.getSensorTypeList()){
					if(dataItem.getDataMeans() == null)continue;
						itemNum++;
				}
			tableConent.append(createTable(sensorType.getSensorTypeName(),itemNum));
			trigger+=createTrigger(sensorType.getSensorTypeName(),itemNum);
		}

		insertConent.append("delete from sensortype;");
		for(GeneralMap gm:gmlist){
			createInsertSql(gm.getId(), gm.getDevType());
		}
		trigger +="end;";
		tableConent.append(trigger);
		WriteFile writefile = new WriteFile();
		try {
			writefile.WriteToFile(tableConent.toString().trim(), savepath+"/createadvancetable.sql");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writefile.WriteToFile(insertConent.toString().trim(), savepath+"/insertsensorinfo.sql");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void createInsertSql(int id,String devType){
		insertConent.append("insert into SENSORTYPE(sourceAddr,groupAddr,sensorType) values("+id+",0,'"+devType.replace("-", "_").replaceAll("\\(\\d*\\)","")+"');\n");
	}
	private String createTable(String tableName,int itemNum){
		tableName = tableName.replace("-", "_").replaceAll("\\(\\d*\\)","");
		String item = "";
		for(int i=1;i<=itemNum;i++){
			item +="	item"+i+" double DEFAULT NULL,\n";
		}
		String str = "DROP TABLE IF EXISTS "+tableName+";\n"
				+ "CREATE TABLE "+tableName+"(\n"
				+ "	sensorDataID bigint(20) NOT NULL AUTO_INCREMENT,\n"
				+ "	sourceAddr int(11) DEFAULT NULL,\n"
				+ "	groupAddr int(11) DEFAULT NULL,\n"
				+ "	samplingTime bigint(20) DEFAULT NULL,\n" 
				+ item
				+" PRIMARY KEY (sensorDataID)\n"
				+ ");\n";
		return str;				  
	}
	private String createTrigger(String tableName,int itemNum){
		tableName = tableName.replace("-", "_").replaceAll("\\(\\d*\\)","");
		String str  = "";
		String item2 = "";
		String item3 = "";
		for(int i=1;i<=itemNum;i++){
			item2 +=",Item"+i;
			item3 += ",new.Item"+i;
		}
		str="	if strcmp(tmpType,'"+tableName+"')=0 then\n"
				+ "		insert into "+tableName+" (SourceAddr,GroupAddr,SamplingTime"+item2+")\n"
				+ "			values(new.SourceAddr,new.GroupAddr,new.SamplingTime"+item3+");\n"
				+ "	end if;\n"
				+ "\n";

		return str;
	}
}
