package convertToTxt;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import construction.record.equipments.GeneralMap;
import construction.record.equipments.PowerMeter;
import construction.record.equipments.TemperComparative;
import construction.record.equipments.TemperConcentrator;
import construction.record.functional.SensorsTable;
import construction.record.functional.PowerMeterTable;
import construction.record.functional.TemperComparativeTable;
import construction.record.functional.TemperConcentratorTable;
import device.model.DataCell;
import device.model.DeviceModel;
import readXlsxFile.ReadReferTable;
import sensorTypeModel.DataItem;
import sensorTypeModel.SensorType;
import writeFile.WriteFile;

public class SensorDevList {
/*�����Ǳ����´����������������(sensor_dev_list)

  �����Ǳ�ID����´�����ID
+ �����Ǳ�ID����¼�����ID
+ �������� ====== ûд
+ {Item���/�Ĵ�����ַ/��������ռ�ݵļĴ�������/����/��������}
*/
	private ArrayList<SensorType> sensorTypes;
	public void toTxt(String[][][] buildArray, String[][][]devArray , ArrayList<SensorType> sensorTypes, String saveTxtPath){
		SensorsTable gm = new SensorsTable();
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

		this.sensorTypes = sensorTypes;
		
		StringBuffer content = new StringBuffer();
		/*
		for(GeneralMap gmobj:gmlist){
			if(gmobj.getId()>=100000&&gmobj.getId()<=199999){
				String s = gmobj.getId()+" "+gmobj.getId()+" ";
				String mainMassage = "";
				int num = 0;
				for(Map.Entry<String, List<DataCell>> dmmap:map.entrySet()){
					String mapKey = dmmap.getKey();
					List<DataCell> dclist = dmmap.getValue();
					for(DataCell dcobj:dclist){
						System.out.println(gmobj.getDevType()+" --"+mapKey);
						if(gmobj.getDevType().equals(mapKey)){
							for(int i=0;i<dcobj.getDataMeaning().size();i++){
								num++;
								//Item���/�Ĵ�����ַ/��������ռ�ݵļĴ�������/����/��������
								String dataMeaning = slim(dcobj.getDataMeaning().get(i));
								String address = slim(dcobj.getRegisterAddress().get(i));
								String reNum = slim(dcobj.getRegisterNum().get(i));
								String times = slim(dcobj.getDataTimes().get(i));
								String dataType = slim(dcobj.getDataType().get(i));
								String temp = slim(dataMeaning);
								int itemNum = rf.returnItemNum(referTable, temp);
								mainMassage += itemNum +"/"+address+"/"+reNum+"/"+times+"/"+dataType+" ";
							}
						}
					}
				}
				if(mainMassage != null)
				content.append(s+" "+num+" "+mainMassage+"\n");
			}
		}
		*/
		for(GeneralMap gmobj:gmlist){
			if(gmobj.getId()>=200000&&gmobj.getId()<=299999){
				String s = gmobj.getId()+" ";
				int num = 0;
				String mainMassage = "";
				int tcJiId = 0;
				for(TemperComparative tpobj:tplist){
					if(gmobj.getId()==tpobj.getWenId()){//ʩ����ͼ�е� �豸ID ƥ�� ���ձ��е��豸ID  �ҵ����ձ��е�tpobj
						for(TemperConcentrator tcobj:tclist){
							if(tpobj.getJiId() == tcobj.getId()){//���ձ���tpobj�ļ�����ID ƥ�� ���������е�ID �ҵ�tcobj
								tcJiId = tcobj.getId();
								s+=tcobj.getId();
								for(Map.Entry<String, List<DataCell>> dmmap:map.entrySet()){
									String mapKey = dmmap.getKey();
									//System.out.println(mapKey+"********");
									List<DataCell> dclist = dmmap.getValue();
									for(DataCell dcobj:dclist){
										if(tcobj.getDeviceType().equals(mapKey)){

												num++;
												//Item���/�Ĵ�����ַ/��������ռ�ݵļĴ�������/����/��������
												String dataMeaning = slim(dcobj.getDataMeaning().get(0));
												int address = dcobj.getStartAddress()+tpobj.getFirstRelativeOffsetAddress()+ tpobj.getS()* (tpobj.getI()-1);
												int reNum = Double.valueOf(slim(dcobj.getRegisterNum().get(0))).intValue();
												String times = slim(dcobj.getDataTimes().get(0));
												String dataType = slim(dcobj.getDataType().get(0));
												String parameterB = string2double(slim(dcobj.getParameter().get(0)));
												String temp = slim(dataMeaning);
												int itemNum =returnItemNum(mapKey,temp);
												mainMassage += itemNum +"/"+address+"/"+reNum+"/"+times+"/"+parameterB+"/"+dataType+" ";
												if(tpobj.getS() >1){
													//Item���/�Ĵ�����ַ/��������ռ�ݵļĴ�������/����/��������
													for(int k=1;k< tpobj.getS();k++){
														num++;
														dataMeaning = slim(dcobj.getDataMeaning().get(k));
														reNum = Double.valueOf(slim(dcobj.getRegisterNum().get(0))).intValue();
														times = slim(dcobj.getDataTimes().get(0));
														dataType = slim(dcobj.getDataType().get(0));
														temp = slim(dataMeaning);
														itemNum =returnItemNum(mapKey,temp);
														mainMassage += itemNum +"/"+(address+k)+"/"+reNum+"/"+times+"/"+parameterB+"/"+dataType+" ";
													}
												}
										}
									}
								}
							}
						}
					}
				}
				if(mainMassage != null&&tcJiId != 0){
					System.out.println(s+" "+num+" "+mainMassage+"\n");
					content.append(s+" "+num+" "+mainMassage+"\n");
				}
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
	private int returnItemNum(String concentratorTypeName,String dataItemMean){
		int itemNum = -1;
		for (SensorType sensorType:sensorTypes){
			if(concentratorTypeName.contains(sensorType.getSensorTypeName())){
				for(DataItem dataItem:sensorType.getSensorTypeList()){
					if(dataItemMean.equals(dataItem.getDataMeans())){
						itemNum = dataItem.getItemNum();
					}
				}
			}
		}
		return itemNum;
	}
	private String string2double(String str){
		Double doubleStr = Double.parseDouble(str);
		DecimalFormat strFormat = new DecimalFormat("0.000000");
		String s = strFormat.format(doubleStr);
		return s;
	}
	public String slim(String a){
		return a.replaceAll("\n", "").replaceAll(" ", "");
	}
}
