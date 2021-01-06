package device.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DeviceModel {
	private static int beginNum = 1;
	private static int beginSheet = 0;
	public Map<String,List<DataCell>> getList(String array[][][]){
		
		Map<String,List<DataCell>> map = new HashMap<String,List<DataCell>>();
		
		for(int i=beginSheet;i<array.length;i++){
			if(array[i][0][0] == null) continue;
			//System.out.println(array[i][0][0]);//sheet
			int requestTimes = searchTitle(array[i],"��ȡȫ��������Ҫ����"); //��ȡ������Ҫ����
			int segmentNum = searchTitle(array[i],"�����ֶ����"); //�ֶ����
			int functionCode = searchTitle(array[i],"������"); //������
			int startAddress = searchTitle(array[i],"�Ĵ�����ʼ��ַ"); //��ʼ��ַ
			int registerCount = searchTitle(array[i],"�Ĵ�������"); //�Ĵ�������
			int dataMeaning = searchTitle(array[i],"���ݺ���"); //���ݺ���
			int dataType = searchTitle(array[i],"��������"); //��������
			int registerAddress = searchTitle(array[i],"�Ĵ�����ַ");//�Ĵ�����ַ
			int registerNum = searchTitle(array[i],"��������ռ�ݵļĴ�������"); //��������ռ�ݵļĴ�������
			int dataTimes = searchTitle(array[i],"����"); //����
			int parameter = searchTitle(array[i],"����B"); //����
			List<DataCell> list = new ArrayList<DataCell>();
			for(int j=beginNum+1;j<array[i].length;j++){
				if(array[i][j][0] == null||array[i][j][0].equals("#####")) continue;
				DataCell object = new DataCell();
				object.setRequestTimes(array[i][j][requestTimes]);
				object.setSegmentNum(array[i][j][segmentNum]);
				object.setFunctionCode(array[i][j][functionCode]);
				object.setStartAddress(array[i][j][startAddress]);
				object.setRegisterCount(array[i][j][registerCount]);
				object.setDataMeaning(array[i][j][dataMeaning]);
				object.setDataType(array[i][j][dataType]);
				object.setRegisterAddress(array[i][j][registerAddress]);
				object.setRegisterNum(array[i][j][registerNum]);
				object.setDataTimes(array[i][j][dataTimes]);
				object.setParameter(array[i][j][parameter]);
				list.add(object);
			}
			map.put(array[i][0][0],list);
		}
		return map;
	}
	public static int searchTitle(String array[][],String titleName){
		//���� ��ͷ����ֵ
		int num = 0;
		for(int i=0;i<array[beginNum].length;i++){
			if(array[beginNum][i] == null) continue;
			if(slim(array[beginNum][i]).equals(titleName)){
				num = i;
			}
		}
		return num;
	}
	public static String slim(String a){
		return a.replaceAll("\n", "").replaceAll(" ", "");
	}

}
