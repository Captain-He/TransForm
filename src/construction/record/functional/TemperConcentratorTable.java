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
		int id = searchTitle(array,"������ΨһID");//�ɼ��豸ΨһID
		int deviceType = searchTitle(array,"�������ͺ�");//�豸�ͺ�
		int accessType = searchTitle(array,"���뷽ʽ"); //���뷽ʽ(��̫��/����)
		int modbusType = searchTitle(array,"ͨ��Э��"); //ͨ��Э��(modbusTCP/modbusRTU)
		int underNum = searchTitle(array,"������ͨ�Ź�������"); //������ͨ�Ź�������
		int comNum = searchTitle(array,"���ڱ��"); //���ڱ��
		int bpsNum = searchTitle(array,"������"); //������
		int dataBit = searchTitle(array,"����λ"); //����λ
		int checkBit = searchTitle(array,"У��λ"); //У��λ
		int stopBit = searchTitle(array,"ֹͣλ"); //ֹͣλ
		int fluidControl = searchTitle(array,"����");//����
		int slaveAddress = searchTitle(array,"��վ��ַ"); //��վ��ַ
		
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
