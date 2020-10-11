package convertToTxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import construction.record.equipments.GeneralMap;
import construction.record.equipments.PowerMeter;
import construction.record.equipments.TemperConcentrator;
import construction.record.functional.GeneralMapTable;
import construction.record.functional.PowerMeterTable;
import construction.record.functional.TemperConcentratorTable;
import device.model.DataCell;
import device.model.DeviceModel;
import writeFile.WriteFile;

public class ConcentratorList {
/*集中器或电力仪表(concentrator_list.txt)

  测温集中器ID或电力仪表ID     （int，300001~399999 或 100001~199999）
+ 串口编号                    （string，COM1~COM16）
+ 波特率                      （int，1200、2400、4800、9600、14400、19200、38400、43000、57600、76800、115200、128000、230400、256000、460800、921600、1382400）
+ 数据位                      （int，5~8）
+ 校验位                      （char，N/O/E）
+ 停止位                      （float，1/1.5/2）
+ 流控                        （string，N/R/D/RD）
+ 从站地址                    （int，1~255）
+ 读取全部数据需要几次         （int，1~100）
+ {分段序号/+ 功能码 （int，0~255）/寄存器起始地址/寄存器数量}*/
	
	public void toTxt(String buildArray[][][],String devArray[][][],String savePath){
		GeneralMapTable gm = new GeneralMapTable();
		List<GeneralMap> gmlist =gm.getList(buildArray);

		
		PowerMeterTable pm = new PowerMeterTable();
		List<PowerMeter> pmlist = pm.getList(buildArray);
		
		TemperConcentratorTable tc = new TemperConcentratorTable();
		List<TemperConcentrator> tclist = tc.getList(buildArray);
		
		DeviceModel dm = new DeviceModel();
		Map<String,List<DataCell>> map = dm.getList(devArray);
		
		StringBuffer content = new StringBuffer();
		
		for(GeneralMap gmobj:gmlist){
			if(gmobj.getId()<=199999&&gmobj.getId()>=100000){
				for(PowerMeter pmobj:pmlist){
					if(gmobj.getId() == pmobj.getId()){
						String s = pmobj.getId()+" "+pmobj.getComNum()+" "+pmobj.getBpsNum()+" "+pmobj.getDataBit()+" "+pmobj.getCheckBit()+" "+pmobj.getStopBit()+" "+pmobj.getFluidControl()+" "+pmobj.getSlaveAddress()+" ";
						String ss = "";
						int a = 0;
						for(Map.Entry<String, List<DataCell>> dmmap:map.entrySet()){
							String mapKey = dmmap.getKey();
							List<DataCell> dclist = dmmap.getValue();
							for(DataCell dcobj:dclist){
								if(gmobj.getDevType().equals(mapKey)){
									a = dcobj.getRequestTimes();
									ss += dcobj.getSegmentNum()+"/"+dcobj.getFunctionCode()+"/"+dcobj.getStartAddress()+"/"+dcobj.getRegisterCount()+" ";
								}
							}
						}
						content.append(s+a+" "+ss+"\n");
					}
				}
			}
			
		}
		for(TemperConcentrator tcobj:tclist){
			String s = tcobj.getId()+" "+tcobj.getComNum()+" "+tcobj.getBpsNum()+" "+tcobj.getDataBit()+" "+tcobj.getCheckBit()+" "+tcobj.getStopBit()+" "+tcobj.getFluidControl()+" "+tcobj.getSlaveAddress()+" ";
			String ss = "";
			int a = 0;
			for(Map.Entry<String, List<DataCell>> dmmap:map.entrySet()){
				String mapKey = dmmap.getKey();
				List<DataCell> dclist = dmmap.getValue();
				for(DataCell dcobj:dclist){
					if(tcobj.getDeviceType().equals(mapKey)){
						a = dcobj.getRequestTimes();
						ss += dcobj.getSegmentNum()+"/"+dcobj.getFunctionCode()+"/"+dcobj.getStartAddress()+"/"+dcobj.getRegisterCount()+" ";
					}
				}
			}
			content.append(s+a+" "+ss+"\n");
		}

		WriteFile writefile = new WriteFile();
		try {
			writefile.WriteToFile(content.toString(), savePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
