package construction.record.functional;

import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.CollectPc;

public class CollectPcTable {
	private static int beginNum = 7;
	
	public List<CollectPc> getList(String array[][]){
		List <CollectPc> list = new ArrayList<CollectPc>();
		int id = searchTitle(array,"�豸��ţ��Զ���Ψһ��ţ�");//�ɼ��豸ΨһID
		int type = searchTitle(array,"�豸����");//���¼�����ID
		int ipAddress = searchTitle(array,"IP��ַ"); //���´������ڲ��¼������е�ַ
		int comNum = searchTitle(array,"�˿ں�");
		
		for(int i= beginNum+1;i<array.length;i++){
			CollectPc object = new CollectPc();
			object.setId(array[i][id]);
			object.setType(array[i][type]);
			object.setIpAdress(array[i][ipAddress]);
			object.SetComNum(array[i][comNum]);
			list.add(object);
		}
		return list;
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
