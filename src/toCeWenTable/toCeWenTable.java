package toCeWenTable;

import java.io.IOException;

import readBuildTables.readBuildTables;
import readDevTables.readDevTables;
import readReferTable.readReferTable;
import writeFile.WriteFile;

public class toCeWenTable {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		//filePath1 为施工记录表  filePath2 为设备型号表
//		//data1 为数据再组织规则表 ； data 为施工记录表； data2 为设备型号表 
////		readfile("C:/Users/Admin/Desktop/设备型号表-V1.0.xlsx","C:/Users/Admin/Desktop/施工记录v0.5-模拟数据.xlsx");
//		readBuildTables readbuildtable = new readBuildTables();
//		readReferTable readrefertable = new readReferTable();
//		readDevTables readdevtable = new readDevTables();
//		String data1[][] =readrefertable.readReferTable("C:/Users/Admin/Desktop/设备型号表-V1.0.xlsx");
//		String data2[][][] =readdevtable.readDevTable("C:/Users/Admin/Desktop/设备型号表-V1.0.xlsx");
//		String data[][][] = readbuildtable.readBuildTables("C:/Users/Admin/Desktop/施工记录v0.5-模拟数据.xlsx");
//		/**测温表**/
//		String textPath = "C:/Users/Admin/Desktop/test1.txt";
//		String res = toTempTxt(data1,data,data2);
//		WriteFile writefile = new WriteFile();
//		try {
//			writefile.WriteToFile(res, textPath);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	public String toTempTxt(String[][]data1,String[][][] data,String [][][] data2) {
		StringBuffer t = new StringBuffer();
		// 1.sheet 级
		int shigong = 0;// 查找 施工总图记录表 存于第一维索引存于shigong
		int wenzhao = 0;// 查找 测温传感器和测温集中器对照表 存于第一维索引存于wenzhao
		int wenpei = 0;// 查找 测温集中器现场施工配置记录 存于第一维索引存于wenpei
		for (int i = 0; i < data.length; i++) {
			if(data[i][0][0] == null) break;
			if (data[i][0][0].equals("施工总图记录表")) {
				shigong = i;
			}
			if (data[i][0][0].equals("测温传感器和测温集中器对照表")) {
				wenzhao = i;
			}
			if (data[i][0][0].equals("测温集中器现场施工配置记录")) {
				wenpei = i;
			}
		}
		// 2.表头 级
		  // 2.1 测温传感器和测温集中器对照表
		int wenchuanID = 0;// 测温传感器ID的列值所在列
		int wenjiID = 0;// 测温集中器ID的列值
		int wendiID = 0;// 测温传感器在测温集中器中地址的列值
		for (int j = 0; j < data.length; j++) {
			if (data[wenzhao][3][j] == null)
				break;
				if (data[wenzhao][3][j].equals("测温传感器ID")) {
					wenchuanID = j;
				}
				if (data[wenzhao][3][j].equals("测温集中器ID")) {
					wenjiID = j;
				}
				if (data[wenzhao][3][j].equals("测温传感器在测温集中器中地址")) {
					wendiID = j;
				}
			
		}
		  //2.2测温集中器现场施工配置记录
		int wenjiID_ = 0; //设备唯一ID（后期软件使用）所在列
		int sheBEI = 0; //设备型号所在列
		for(int j = 0;j < data[wenpei][1].length;j++){
			if (data[wenpei][1][j] == null) 
				break;
				if (data[wenpei][1][j].equals("设备唯一ID（后期软件使用）")) {
					wenjiID_ = j;
				}
				if (data[wenpei][1][j].equals("设备型号")) {
					sheBEI = j;
				}
		}
		//2.3 施工总图中的设备唯一ID
		int ID = 0;//采集设备唯一ID所在列
		for(int j = 0;j <data[shigong][6].length;j++){
			if (data[shigong][6][j] == null) 
				break;
				if (data[shigong][6][j].equals("采集设备唯一ID")) {
					ID= j;
				}
		}
		//3.获取施工记录中的关键数据
		  //3.1获取测温传感器唯一ID 
		String F_ID [] = new String[data[shigong].length]; //测温传感器的采集设备唯一ID
		int k = 0;
		for(int i=0;i<data[shigong].length;i++){
			if(data[shigong][i][ID] != null&&data[shigong][i][ID].getBytes().length == 8){
				double a = Double.parseDouble(data[shigong][i][ID]);
				if(200000<=a&&a<300000){
					F_ID[k] = data[shigong][i][ID];
					k++;
				}
			}
		}
		  //3.2读取测温集中器现场施工配置记录 关键数据
		String CEWEN_JI[][] = new String[data[wenpei].length][2];
		for(int i=2;i<data[wenpei].length;i++){
				if(data[wenpei][i][wenjiID_] != null &&data[wenpei][i][sheBEI] != null){
					CEWEN_JI[i-2][0] = data[wenpei][i][wenjiID_];
					CEWEN_JI[i-2][1] = data[wenpei][i][sheBEI];
				}
			}
		//3.3读取 测温传感器和测温集中器对照表 关键数据
		String CEWEN_ZHAO[][] = new String[data[wenzhao].length][3];
		for(int i=4;i<data[wenzhao].length;i++){
			if(data[wenzhao][i][wenchuanID] != null &&data[wenzhao][i][wenjiID] != null&&data[wenzhao][i][wendiID] != null){
				CEWEN_ZHAO[i-4][0] = data[wenzhao][i][wenchuanID];
				CEWEN_ZHAO[i-4][1] = data[wenzhao][i][wenjiID];
				CEWEN_ZHAO[i-4][2] = data[wenzhao][i][wendiID];
			}
		}
		//4.根据规则，重新组织数据
		String result[] = new String[data[shigong].length];
		int f = 0;
		for(int i=0;i<F_ID.length;i++){
			if(F_ID[i] == null) break;
			for(int j=0;j<CEWEN_ZHAO.length;j++){
				if(CEWEN_ZHAO[j][0] == null) break;
				double q = Double.valueOf(F_ID[i]);
				double p = Double.valueOf(CEWEN_ZHAO[j][0]);
				if(p==q){
					// F_ID[i] CEWEN_ZHAO[j][0] 唯一id
					//CEWEN_ZHAO[j][1]集中器 id
					//CEWEN_ZHAO[j][2] 地址
					result[f] = F_ID[i]+","+CEWEN_ZHAO[j][1]+",";
					for(int a=0;a<CEWEN_JI.length;a++){
						if(CEWEN_JI[a][0] == null) break;
						if(CEWEN_ZHAO[j][1].equals(CEWEN_JI[a][0])){
							//CEWEN_JI[a][1] 集中器编号
							result[f] = F_ID[i]+","+CEWEN_ZHAO[j][1]+",";
							for(int b =0;b<data2.length;b++){
								if(data2[b][0][0] == null) break;
								String []res = new String[data[shigong].length];
								String dd = "";
								int max = -1;
								if(data2[b][0][0].equals(CEWEN_JI[a][1])){
									for(int c=2;c<data2[b].length;c++){
										if(data2[b][c][0] == null) break;
										for(int d=1;d<data1[0].length;d++){	
											if(data1[0][d] == null) break;
											//System.out.print(data1[0][d]+"******"+data2[b][c][5]);System.out.println();
											if(data1[0][d].startsWith(data2[b][c][5])){
												//System.out.println(data1[0][d]+"$$$"+data2[b][c][5]);
												//System.out.println(d);
												double di =  Double.valueOf(CEWEN_ZHAO[j][2])+ Double.valueOf(data2[b][c][3])-1;
												 dd= String.valueOf(di)+"/"+data2[b][c][7]+"/"+data2[b][c][7]+"/"+data2[b][c][6]+",";
												//System.out.println(di+"/"+data2[b][c][7]+"/"+data2[b][c][7]+"/"+data2[b][c][6]+",");
												res[d] =dd; 
												if(d>max) max = d;
												break;
											}
										}
									}
								}
								for(int y=0;y<=max;y++){
									if(res[y]==null){
										result[f]+=",";
									}else{
										result[f]+=res[y];
									}
								}
							}
						}
					}
					f++;
				}
			}
		}
		for(int i=0;i<result.length;i++){
			if(result[i]==null)break;
			t.append(result[i]);
			t.append("\r\n");
		}
		return t.toString();
	}

}
