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
		int wenIdStart = searchTitle(array,"传感器ID起始值");//采集设备唯一ID
		int wenIdEnd = searchTitle(array,"传感器ID终止值");//采集设备唯一ID
		int jiId = searchTitle(array,"集中器ID");//测温集中器ID
		int address = searchTitle(array,"第一个传感器的地址相对偏移量"); //测温传感器在测温集中器中地址
		int S = searchTitle(array,"该地址数据间隔存储情况");
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
		//查找 表头的列值
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
