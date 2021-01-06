package construction.record.functional;

import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.CollectPc;

public class CollectPcTable {
	private static int beginNum = 7;
	
	public List<CollectPc> getList(String array[][]){
		List <CollectPc> list = new ArrayList<CollectPc>();
		int id = searchTitle(array,"设备编号（自定义唯一编号）");//采集设备唯一ID
		int type = searchTitle(array,"设备类型");//测温集中器ID
		int ipAddress = searchTitle(array,"IP地址"); //测温传感器在测温集中器中地址
		int comNum = searchTitle(array,"端口号");
		
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
