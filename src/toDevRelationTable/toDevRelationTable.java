package toDevRelationTable;

public class toDevRelationTable {
	public  String toDevRelationTableTxt(String[][][]data,String str){
		// 1.表头 级
		int id= 0;// 采集设备唯一ID
		int type = 0;// 采集设备类型
		int location = 0;// 测量位置
		int dev = 0;//设备型号
		int cell = 0;//功能单元
		for (int j = 0; j < data[0].length; j++) {
			if (data[0][6][j] == null)
				break;
				if (data[0][6][j].equals("采集设备唯一ID")) {
					id = j;
				}
				if (data[0][6][j].equals("采集设备类型")) {
					type = j;
				}
				if (data[0][6][j].equals("测量位置")) {
					location = j;
				}
				if (data[0][6][j].equals("设备型号")) {
					dev = j;
				}
				if (data[0][6][j].equals("功能单元")) {
					cell = j;
				}
			
		}
		String strArray[] = str.split("\t\n");
		//2.读取重要数据  采集设备ID 暂时按功能单元处理
		String result [] = new String[data[0].length];
		for(int i=7;i<data[0].length;i++){
			if(data[0][i][0] == null||data[0][i][0].length()<=0) break;
			String a = "";
			for(int j=0;j<strArray.length;j++){
				String strArrayCell[] = strArray[j].split(",");
				if(data[0][i][cell].equals(strArrayCell[1])){
					a+=data[0][i][id]+","+data[0][i][type]+","+strArrayCell[0]+","+data[0][i][location]+","+data[0][i][dev]+",";
					result[i-7] = a;
				}
			}
		}
		StringBuffer t = new StringBuffer();
		for(int i=0;i<result.length;i++){
			if(result[i] == null||result[i].length()<=0) break;
			t.append(result[i]);
			t.append("\t\n");
		}
		
		return t.toString();
	} 
}
