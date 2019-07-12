package View;

import java.util.HashMap;
import java.util.Map;

public class DianLiTable {
	public String toDianTxt(String[][]data1,String[][][] data,String [][][] data2){
		//data1 为数据再组织规则表01开始 ； data 为施工记录表； data2 为设备型号表 
		//1.获取表中关键数据
		 //1.1获取施工记录表中 电力仪表的  采集设备唯一ID（ID） 和 设备型号（devNum）
		Map <Double,String> shiGong = new HashMap<Double,String>();
		int devNum = 0;
		int ID = 0;
		for(int i=0;i<data[0][6].length;i++){
			if(data[0][6][i] == null) break;
			if(data[0][6][i].equals("设备型号")){
				devNum = i;
			}
			if(data[0][6][i].equals("采集设备唯一ID")){
				ID = i;
			}
		}
		for(int i=7;i<data[0].length;i++){
			if(data[0][i][ID] == null) break;
			double a = Double.parseDouble(data[0][i][ID]);
			if(a>100000&&a<200000){
				shiGong.put(a,data[0][i][devNum]);
			}
		}
		//System.out.println(shiGong);
		 //1.2设备型号表 重要数据整理 
		int hanYi = 0;
		int leiXing = 0;
		int di = 0;
		int jiNum = 0;
		int beiLv = 0;
		for(int i=0;i<data2[2][1].length;i++){
			if(data2[2][1][i] == null) break;
			if(data2[2][1][i].equals("数据含义")){
				hanYi = i;
			}
			if(data2[2][1][i].equals("数据类型")){
				leiXing = i;
			}
			if(data2[2][1][i].equals("寄存器地址")){
				di = i;
			}
			if(data2[2][1][i].replaceAll("\n", "").replaceAll(" ", "").equals("单个数据占据的寄存器数量")){
				jiNum = i;
			}
			if(data2[2][1][i].equals("倍率")){
				beiLv = i;
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<data2.length-4;i++){
			String a = "";
			for(int j=2;j<data2[i].length;j++){
				if(data2[i][j][0] == null) break;
				//data2[i][j][hanYi] to 字符串分割 重新整理
				a+=toSplit(data2[i][j][hanYi].replaceAll("\n", "").replaceAll(" ", ""),data2[i][j][leiXing].replaceAll("\n", "").replaceAll(" ", ""),data2[i][j][di].replaceAll("\n", "").replaceAll(" ", ""),data2[i][j][jiNum].replaceAll("\n", "").replaceAll(" ", ""),data2[i][j][beiLv].replaceAll("\n", "").replaceAll(" ", ""));

			}
			map.put(data2[i][0][0],a);
		}
		//2.解析整理，排序
		String res [] = new String[100];
		int q = 0;
		for(Map.Entry<Double,String> id:shiGong.entrySet()){
			  Double idkey=id.getKey();
			  String deValue = id.getValue();
				String result[] = new String[100];
				res[q] = idkey.toString()+",";
				  int k = 0;//相应参照表的哪一行
			for(Map.Entry<String,String> entry:map.entrySet()){
				  String key=entry.getKey();
				  if(deValue.equals(key)){
					  String value = entry.getValue(); 
					  if(key.startsWith("T")){
						  k = 2;
					  }
					  if(key.startsWith("A")){
						  k = 1;
					  }
					  if(key.startsWith("P")){
						  k = 4;
					  }
					  if(key.startsWith("B")){
						  k = 3;
					  }
					  String a[] = value.split("@");
					  for(int i=0;i<a.length;i++){
						  String b[] = a[i].split("#");
						  //b[0] 是名字 用来确定item,b[1]是值 直接装填
						  for(int j=1;j<data1[k].length;j++){
							  if(data1[k][j] == null) break;
							  //j 即为所判断的item
							  if(data1[k][j].startsWith(b[0])){
								  result[i] = b[1];
							  }
						  }
					  }
				  } 
				}
			for(int i=0;i<result.length;i++){
				if(result[i] == null){
					res[q]+=",";
				}else{
					res[q]+=result[i]+",";
				}
				if(data1[k][i+1] == null) break;
			}
			q++;
		}
//		for(int i=0;i<res.length;i++){
//			if(res[i] == null) break;
//			System.out.println(res[i]);
//			}
		//3.生成文件
		StringBuffer t = new StringBuffer();
		for(int i=0;i<res.length;i++){
			if(res[i]==null)break;
			t.append(res[i]);
			//System.out.println(result[i]);
			t.append("\r\n");
		}
		return t.toString();
	}
	//字符串切割重新拼接 返回为一个map集合 ，对应设备表中的一行
	public String toSplit(String str1,String str2,String str3,String str4,String str5){
		//1->含义  2->类型 3->地址 4->数量 5->倍率
		String temp1[] = str1.split("/");
		String temp2[] = str2.split("/");
		String temp3[] = str3.split("/");
		String temp4[] = str4.split("/");
		String temp5[] = str5.split("/");
		//System.out.println(temp1.length+" "+temp2.length+" "+temp3.length+" "+temp4.length+" "+temp5.length);
		
		String a = "";
		for(int i=0;i<temp1.length;i++){
			if(temp1[i] == null ||temp1[i].length()<=0) break;
			a += temp1[i]+"#"+temp3[i]+"/"+temp4[i]+"/"+temp5[i]+"/"+temp2[i]+"@";
		}
		  return a;
		  
	}
}
