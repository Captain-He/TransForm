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
			int requestTimes = searchTitle(array[i],"读取全部数据需要几次"); //读取数据需要几次
			int segmentNum = searchTitle(array[i],"参数分段序号"); //分段序号
			int functionCode = searchTitle(array[i],"功能码"); //功能码
			int startAddress = searchTitle(array[i],"寄存器起始地址"); //起始地址
			int registerCount = searchTitle(array[i],"寄存器数量"); //寄存器数量
			int dataMeaning = searchTitle(array[i],"数据含义"); //数据含义
			int dataType = searchTitle(array[i],"数据类型"); //数据类型
			int registerAddress = searchTitle(array[i],"寄存器地址");//寄存器地址
			int registerNum = searchTitle(array[i],"单个数据占据的寄存器数量"); //单个数据占据的寄存器数量
			int dataTimes = searchTitle(array[i],"倍率"); //倍率
			int parameter = searchTitle(array[i],"参数B"); //参数
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
		//查找 表头的列值
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
