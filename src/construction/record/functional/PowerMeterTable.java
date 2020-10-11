package construction.record.functional;

import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.PowerMeter;

public class PowerMeterTable {
	private static int sheetNum = 2;
	private static int beginNum = 5;
	public List<PowerMeter> getList(String arr[][][]){
		String array[][] = arr[sheetNum];
		List<PowerMeter> power;
		int id = searchTitle(array,"采集设备唯一ID");//采集设备唯一ID
		int accessType = searchTitle(array,"接入方式(以太网/串口)"); //接入方式(以太网/串口)
		int modbusType = searchTitle(array,"通信协议"); //通信协议(modbusTCP/modbusRTU)
		int underNum = searchTitle(array,"隶属的通信管理机编号"); //隶属的通信管理机编号
		int comNum = searchTitle(array,"串口编号"); //串口编号
		int bpsNum = searchTitle(array,"波特率"); //波特率
		int dataBit = searchTitle(array,"数据位"); //数据位
		int checkBit = searchTitle(array,"校验位"); //校验位
		int stopBit = searchTitle(array,"停止位"); //停止位
		int fluidControl = searchTitle(array,"流控");//流控
		int slaveAddress = searchTitle(array,"从站地址"); //从站地址
		
		power = new ArrayList<PowerMeter>();
		
		for(int i=beginNum+1;i<array.length;i++){
			if(array[i][0] == null) break;
			PowerMeter powerMeter = new PowerMeter();
			powerMeter.setId(strToInt(array[i][id]));
			powerMeter.setAccessType(array[i][accessType]);
			powerMeter.setModbusType(array[i][modbusType]);
			powerMeter.setUnderNum(strToInt(array[i][underNum]));
			powerMeter.setComNum(array[i][comNum]);
			powerMeter.setBpsNum(strToInt(array[i][bpsNum]));
			powerMeter.setDataBit(strToInt(array[i][dataBit]));
			powerMeter.setCheckBit(strToChar(array[i][checkBit]));
			powerMeter.setStopBit(strToInt(array[i][stopBit]));
			powerMeter.setFluidControl(strToChar(array[i][fluidControl]));
			powerMeter.setSlaveAddress(strToInt(array[i][slaveAddress]));
			power.add(powerMeter);
		}
		return power;
	}

	public static  int strToInt(String str){
		return (int)Double.parseDouble(str);
	}
	public static  char strToChar(String str){
		char[] ch = str.toCharArray();
		return ch[0];
	}
	public static int searchTitle(String array[][],String titleName){
		//查找 表头的列值
		int num = 0;
		for(int i=0;i<array[beginNum].length;i++){
			if(array[beginNum][i] == null) break;
			if(array[beginNum][i].contains(titleName)){
				num = i;
			}
		}
		return num;
	}
}
