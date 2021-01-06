package construction.record.functional;

import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.TemperConcentrator;
import readXlsxFile.ReadBuildFile;

public class TemperConcentratorTable {
	private static int beginNum = 8;
	public List <TemperConcentrator> getList(String arr[][][]){
		String array[][] = arr[ReadBuildFile.jizhongqi];
		List <TemperConcentrator> wen;
		int id = searchTitle(array,"集中器唯一ID");//采集设备唯一ID
		int deviceType = searchTitle(array,"集中器型号");//设备型号
		int accessType = searchTitle(array,"接入方式"); //接入方式(以太网/串口)
		int modbusType = searchTitle(array,"通信协议"); //通信协议(modbusTCP/modbusRTU)
		int underNum = searchTitle(array,"隶属的通信管理机编号"); //隶属的通信管理机编号
		int comNum = searchTitle(array,"串口编号"); //串口编号
		int bpsNum = searchTitle(array,"波特率"); //波特率
		int dataBit = searchTitle(array,"数据位"); //数据位
		int checkBit = searchTitle(array,"校验位"); //校验位
		int stopBit = searchTitle(array,"停止位"); //停止位
		int fluidControl = searchTitle(array,"流控");//流控
		int slaveAddress = searchTitle(array,"从站地址"); //从站地址
		
		wen =  new ArrayList<TemperConcentrator>();
		
		for(int i=beginNum+1;i<array.length;i++){
			if(array[i][0] == null) continue;
			TemperConcentrator temper = new TemperConcentrator();
			temper.setId(strToInt(array[i][id]));
			temper.setDeviceType(array[i][deviceType]);
			temper.setAccessType(array[i][accessType]);
			temper.setModbusType(array[i][modbusType]);
			temper.setUnderNum(strToInt(array[i][underNum]));
			temper.setComNum(array[i][comNum]);
			temper.setBpsNum(strToInt(array[i][bpsNum]));
			temper.setDataBit(strToInt(array[i][dataBit]));
			temper.setCheckBit(strToChar(array[i][checkBit]));
			temper.setStopBit(strToInt(array[i][stopBit]));
			temper.setFluidControl(strToChar(array[i][fluidControl]));
			temper.setSlaveAddress(strToInt(array[i][slaveAddress]));
			wen.add(temper);
		}
		return wen;
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
