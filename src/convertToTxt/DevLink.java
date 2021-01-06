package convertToTxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.CommunicationManager;
import construction.record.equipments.PowerMeter;
import construction.record.equipments.TemperConcentrator;
import construction.record.functional.CommunicationManagerTable;
import construction.record.functional.PowerMeterTable;
import construction.record.functional.TemperConcentratorTable;
import writeFile.WriteFile;

public class DevLink {
	/*
+ 设备链ID
+ 隶属的PC编号
+ 通信协议
+ 接入方式
+ [
  集中器ID
  |
  通信管理机编号+ 通信管理机通道号+ 集中器数量+ 1{集中器ID}
  ]
	*/

	public void toTxt(String buildFile[][][],String saveTxtPath) {
		PowerMeterTable pm = new PowerMeterTable();
		List<PowerMeter> powerMeterList = pm.getList(buildFile);
		
		TemperConcentratorTable tc = new TemperConcentratorTable();
		List<TemperConcentrator> temperConcentratorList =tc.getList(buildFile);
		
		CommunicationManagerTable cm = new CommunicationManagerTable();
		List <CommunicationManager> communicationManagerList = cm.getList(buildFile);

		StringBuffer content = new StringBuffer();
		int i = 1;
		int len = powerMeterList.size()+temperConcentratorList.size()+2;
		String check[][] = new String[len][8];//处理重复行，最后一位标记
		for(PowerMeter obj:powerMeterList){
			for(CommunicationManager cmobj:communicationManagerList){
					if(obj.getUnderNum()==cmobj.getId()){
						check[i][0] = i+"";
						check[i][1] = obj.getId()+"";
						check[i][2] = obj.getAccessType();
						check[i][3] = obj.getModbusType();
						check[i][4] = cmobj.getPcId()+"";
						check[i][5] = obj.getUnderNum()+"";
						check[i][6] = comNum(obj.getComNum())+"";
						i++;
					}
			}
		}

		for(TemperConcentrator obj:temperConcentratorList){
			for(CommunicationManager cmobj:communicationManagerList){
					if(obj.getUnderNum()==cmobj.getId()){
						check[i][0] = i+"";
						//System.out.println(obj.getId()+" "+powerMeterList.size()+communicationManagerList.size()+1);
						check[i][1] = obj.getId()+"";
						check[i][2] = obj.getAccessType();
						check[i][3] = obj.getModbusType();
						check[i][4] = cmobj.getPcId()+"";
						check[i][5] = obj.getUnderNum()+"";
						check[i][6] = comNum(obj.getComNum())+"";
						i++;
					}
			}
		}

			int flag = 0;//编组标记
			int ln=1; //设备链ID
			for(int j=1;j<check.length;j++){
				if(check[j][0] == null)continue;
				String temp = check[j][2]+check[j][3]+check[j][4]+check[j][5]+check[j][6];
				String s = check[j][4]+" "+check[j][3]+" "+check[j][2]+" "+check[j][5]+" "+check[j][6];
				String ss = "";
				int n = 0;//集中器数量
				if(check[j][7]==null){
					for(int k=1;k<check.length;k++){
						String temp2 = check[k][2]+check[k][3]+check[k][4]+check[k][5]+check[k][6];
						if(temp.equals(temp2)){
							check[k][7] = String.valueOf(flag);
							ss+=" "+check[k][1];
							n++;
						}
					}
					flag ++;
					content.append(ln+" "+s+" "+n+ss+"\n");
					ln++;
				}
			}
		WriteFile writefile = new WriteFile();
		try {
			writefile.WriteToFile(content.toString().trim(), saveTxtPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private int STurnI(String str){
		return (int)Double.parseDouble(str);
	}
	private int comNum(String str){
		String tempStr = slim(str);
		String s =  tempStr .substring(tempStr.length()-1, tempStr.length());
		return STurnI(s);
	}
	private static String slim(String a){
		return a.replaceAll("\n", "").replaceAll(" ", "");
	}
}
