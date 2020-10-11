package convertToTxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import construction.record.equipments.GeneralMap;
import construction.record.equipments.PowerMeter;
import construction.record.equipments.TemperComparative;
import construction.record.equipments.TemperConcentrator;
import construction.record.functional.GeneralMapTable;
import construction.record.functional.PowerMeterTable;
import construction.record.functional.TemperComparativeTable;
import construction.record.functional.TemperConcentratorTable;
import device.model.DataCell;
import device.model.DeviceModel;
import readReferTable.ReadReferTable;
import writeFile.WriteFile;

public class SensorDevList {
/*电力仪表或测温传感器数据数据项表(sensor_dev_list)

  电力仪表ID或测温传感器ID
+ 电力仪表ID或测温集中器ID
+ 数据数量 ====== 没写
+ {Item序号/寄存器地址/单个数据占据的寄存器数量/倍率/数据类型}
*/
	
	public void toTxt(String[][][] buildArray,String[][][]devArray  ,String [][]referTable,String savePath){
		GeneralMapTable gm = new GeneralMapTable();
		List<GeneralMap> gmlist = gm.getList(buildArray);
		gm.getList(buildArray);
		
		PowerMeterTable pm = new PowerMeterTable();
		List<PowerMeter> pmlist = pm.getList(buildArray);
		
		TemperComparativeTable tp = new TemperComparativeTable();
		List<TemperComparative> tplist = tp.getList(buildArray);
		
		TemperConcentratorTable tc = new TemperConcentratorTable();
		List<TemperConcentrator> tclist =  tc.getList(buildArray);
		
		DeviceModel dm = new DeviceModel();
		Map<String,List<DataCell>> map = dm.getList(devArray);

		ReadReferTable rf = new ReadReferTable();
		
		StringBuffer content = new StringBuffer();
		
		for(GeneralMap gmobj:gmlist){
			if(gmobj.getId()>=100000&&gmobj.getId()<=199999){
				String s = gmobj.getId()+" "+gmobj.getId()+" ";
				String ss = "";
				int num = 0;
				for(Map.Entry<String, List<DataCell>> dmmap:map.entrySet()){
					String mapKey = dmmap.getKey();
					List<DataCell> dclist = dmmap.getValue();
					for(DataCell dcobj:dclist){
						System.out.println(gmobj.getDevType()+" --"+mapKey);
						if(gmobj.getDevType().equals(mapKey)){
							for(int i=0;i<dcobj.getDataMeaning().size();i++){
								num++;
								//Item序号/寄存器地址/单个数据占据的寄存器数量/倍率/数据类型
								String dataMeaning = slim(dcobj.getDataMeaning().get(i));
								String address = slim(dcobj.getRegisterAddress().get(i));
								int reNum = Double.valueOf(slim(dcobj.getRegisterNum().get(i))).intValue();
								String times = slim(dcobj.getDataTimes().get(i));
								String dataType = slim(dcobj.getDataType().get(i));
								String temp = slim(dataMeaning);
								int itemNum = rf.returnItemNum(referTable, temp);
								ss += itemNum +"/"+address+"/"+reNum+"/"+times+"/"+dataType+" ";
							}
						}
					}
				}
				content.append(s+" "+num+" "+ss+"\n");
			}
		}
		for(GeneralMap gmobj:gmlist){
			if(gmobj.getId()>=200000&&gmobj.getId()<=299999){
				String s = gmobj.getId()+" ";
				String ss = "";
				int num = 0;
				//System.out.println(s+" --");
				for(TemperComparative tpobj:tplist){
					//System.out.println(tpobj.getAddress()+"@@@");
					if(gmobj.getId()==tpobj.getWenId()){
						//tcobj.getWenId()
						for(TemperConcentrator tcobj:tclist){
							if(tpobj.getJiId() == tcobj.getId()){
								//tcobj.getDeviceType()
								s+=tpobj.getJiId();
								for(Map.Entry<String, List<DataCell>> dmmap:map.entrySet()){
									String mapKey = dmmap.getKey();
									List<DataCell> dclist = dmmap.getValue();
									for(DataCell dcobj:dclist){
										if(tcobj.getDeviceType().equals(mapKey)){
											for(int i=0;i<dcobj.getDataMeaning().size();i++){
												num++;
												//System.out.println(tpobj.getAddress()+"@@@"+dcobj.getStartAddress());
												//Item序号/寄存器地址/单个数据占据的寄存器数量/倍率/数据类型
												String dataMeaning = slim(dcobj.getDataMeaning().get(i));
												int address = dcobj.getStartAddress()+tpobj.getAddress();
												int reNum = Double.valueOf(slim(dcobj.getRegisterNum().get(i))).intValue();
												String times = slim(dcobj.getDataTimes().get(i));
												String dataType = slim(dcobj.getDataType().get(i));
												String temp = slim(dataMeaning);
												int itemNum = rf.returnItemNum(referTable, temp);
												ss += itemNum +"/"+address+"/"+reNum+"/"+times+"/"+dataType+" ";
											}
										}
									}
								}
							}
						}
					}
				}
				content.append(s+" "+num+" "+ss+"\n");
			}
		}
		WriteFile writefile = new WriteFile();
		try {
			writefile.WriteToFile(content.toString(), savePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String slim(String a){
		return a.replaceAll("\n", "").replaceAll(" ", "");
	}
}
