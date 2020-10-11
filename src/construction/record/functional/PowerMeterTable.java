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
		int id = searchTitle(array,"�ɼ��豸ΨһID");//�ɼ��豸ΨһID
		int accessType = searchTitle(array,"���뷽ʽ(��̫��/����)"); //���뷽ʽ(��̫��/����)
		int modbusType = searchTitle(array,"ͨ��Э��"); //ͨ��Э��(modbusTCP/modbusRTU)
		int underNum = searchTitle(array,"������ͨ�Ź�������"); //������ͨ�Ź�������
		int comNum = searchTitle(array,"���ڱ��"); //���ڱ��
		int bpsNum = searchTitle(array,"������"); //������
		int dataBit = searchTitle(array,"����λ"); //����λ
		int checkBit = searchTitle(array,"У��λ"); //У��λ
		int stopBit = searchTitle(array,"ֹͣλ"); //ֹͣλ
		int fluidControl = searchTitle(array,"����");//����
		int slaveAddress = searchTitle(array,"��վ��ַ"); //��վ��ַ
		
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
		//���� ��ͷ����ֵ
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
