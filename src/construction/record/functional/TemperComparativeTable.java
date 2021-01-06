package construction.record.functional;

import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.TemperComparative;
import readXlsxFile.ReadBuildFile;

public class TemperComparativeTable {
	private static int beginNum = 16;
	public List<TemperComparative> getList(String arr[][][]){
		String array[][] = arr[ReadBuildFile.wenzhao];
		List <TemperComparative> list = new ArrayList<TemperComparative>();
		int wenIdStart = searchTitle(array,"������ID��ʼֵ");//�ɼ��豸ΨһID
		int wenIdEnd = searchTitle(array,"������ID��ֵֹ");//�ɼ��豸ΨһID
		int jiId = searchTitle(array,"������ID");//���¼�����ID
		int address = searchTitle(array,"��һ���������ĵ�ַ���ƫ����"); //���´������ڲ��¼������е�ַ
		int S = searchTitle(array,"�õ�ַ���ݼ���洢���");
		for(int i= beginNum+1;i<array.length;i++){
			if (array[i][wenIdStart] == null) continue;
			for(int j=0;j<=(int)Double.parseDouble(array[i][wenIdEnd])-(int)Double.parseDouble(array[i][wenIdStart]);j++) {
				TemperComparative object = new TemperComparative();
				object.setWenId((int) Double.parseDouble( array[i][wenIdStart]) + j);
				object.setJiId(array[i][jiId]);
				object.setFirstRelativeOffsetAddress(array[i][address]);
				object.setS(array[i][S]);
				object.setI(j+1);
				list.add(object);
			}
		}
		return list;
	}
	public static int searchTitle(String array[][],String titleName){
		//���� ��ͷ����ֵ
		int num = 0;
		for(int i=0;i<array[beginNum].length;i++){
			if(array[beginNum][i] == null) continue;
			if(array[beginNum][i].contains(titleName)){
				num = i;
			}
		}
		return num;
	}
}
