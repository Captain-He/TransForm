package construction.record.functional;

import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.TemperComparative;

public class TemperComparativeTable {
	private static int beginNum = 4;
	private static int sheetNum = 4;
	public List<TemperComparative> getList(String arr[][][]){
		String array[][] = arr[sheetNum];
		List <TemperComparative> list = new ArrayList<TemperComparative>();
		int wenId = searchTitle(array,"测温传感器ID");//采集设备唯一ID
		int jiId = searchTitle(array,"测温集中器ID");//测温集中器ID
		int address = searchTitle(array,"测温传感器在测温集中器中地址"); //测温传感器在测温集中器中地址
		
		for(int i= beginNum+1;i<array.length;i++){
			if(array[i][wenId] == null) continue;
			TemperComparative object = new TemperComparative();
			object.setWenId(array[i][wenId]);
			object.setJiId(array[i][jiId]);
			object.setAddress(array[i][address]);
			list.add(object);
		}
		return list;
	}
	public static int searchTitle(String array[][],String titleName){
		//查找 表头的列值
		int num = 0;
		for(int i=0;i<array[beginNum].length;i++){
			System.out.println(array[beginNum][i]);
			if(array[beginNum][i] == null) continue;
			if(array[beginNum][i].contains(titleName)){
				num = i;
			}
		}
		return num;
	}
}
