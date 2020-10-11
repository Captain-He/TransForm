package convertToTxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import readBuildTables.ReadBuildTables;
import readReferTable.ReadReferTable;
import writeFile.WriteFile;

public class Sql {
	public static String trigger = "";
	public void toTxt(String devPath,String savepath) {
		trigger +="DROP TRIGGER IF EXISTS sensorDataUpdate;\n"
				+ "CREATE TRIGGER sensorDataUpdate AFTER INSERT ON sensordata FOR EACH ROW begin\n"
				+ "	declare tmpType varchar(100);\n"
				+ "	select sensorType into tmpType\n"
				+ "		 from SENSORTYPE\n"
				+ "	where SourceAddr=new.SourceAddr and GroupAddr=new.GroupAddr limit 1;\n"
				+ "\n";

		ReadBuildTables buildTables = new ReadBuildTables();
		String [][][]buildTablesArray = buildTables.readTables(devPath);
		StringBuffer content = new StringBuffer();
		StringBuffer content2 = new StringBuffer();
		List<String> list = new ArrayList<String>();
		content2.append("delete from sensortype;\n");
		for(int i=8;i<buildTablesArray[0].length;i++){
			if( buildTablesArray[0][i][6]== null)continue;
			String type = buildTablesArray[0][i][5];
			content2.append("insert into SENSORTYPE(sourceAddr,groupAddr,sensorType) values("+Double.valueOf(buildTablesArray[0][i][7]).intValue()+",0,'"+type.replace("-", "_")+"');\n");
			int j=0;
			for(String s:list){
				if(s.equals(type)){
					j++;
				}
			}
			if(j==0) list.add(type);
		}
		for(String a:list){
			if(a == null) continue;
			if(a.equals("TDHD-K")||a.equals("TDHD-G")){
				content.append(createTable(a,33));
			}
			if(a.equals("PZ42L-E4-C")||a.equals("PZ42L-AI3")||a.equals("PD194Z-E")||a.equals("PD1134Z")){
				content.append(createTable(a,44));
			}
			if(a.equals("BWD-3K130A")){
				content.append(createTable(a,4));
			}
			if(a.equals("ACS510")||a.equals("ACS800")){
				content.append(createTable(a,39));
			}
			else {
				content.append(createTable(a,4));
			}
				
		}
		trigger +="end;";
		content.append(trigger);
		WriteFile writefile = new WriteFile();
		try {
			writefile.WriteToFile(content.toString(), savepath+"/createadvancetable.sql");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writefile.WriteToFile(content2.toString(), savepath+"/insertsensorinfo.sql");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String createTable(String tableName,int itemNum){
		tableName = tableName.replace("-", "_");
		String item = "";
		String item2 = "";
		String item3 = "";
		for(int i=1;i<=itemNum;i++){
			item2 +=",Item"+i;
			item3 += ",new.Item"+i;
			item +="	item"+i+" double DEFAULT NULL,\n";
		}
		trigger+="	if strcmp(tmpType,'"+tableName+"')=0 then\n"
				+ "		insert into "+tableName+" (SourceAddr,GroupAddr,SamplingTime"+item2+")\n"
				+ "			values(new.SourceAddr,new.GroupAddr,new.SamplingTime"+item3+");\n"
				+ "	end if;\n"
				+ "\n";
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
}
