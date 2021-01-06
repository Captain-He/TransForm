package construction.record.functional;

import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.GeneralMap;
import readXlsxFile.ReadBuildFile;

public class SensorsTable {
	private static int beginNum =12;
	public List<GeneralMap> getList(String arr[][][]){
		String array[][] = arr[ReadBuildFile.chuanganqi];
		List <GeneralMap> list = new ArrayList<GeneralMap>();
		int id = searchTitle(array,"采集设备唯一ID");//采集设备唯一ID
		int devType = searchTitle(array,"采集设备型号");
		int devModel = searchTitle(array,"采集设备类型");
		
		for(int i= beginNum+1;i<array.length;i++){
			if(array[i][id] == null) continue;
			GeneralMap object = new GeneralMap();
			object.setId(strToInt(array[i][id]));
			object.setDevType(array[i][devType]);
			object.setDevModel(array[i][devModel]);
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
	public static  int strToInt(String str){
		return (int)Double.parseDouble(str);
	}

}
