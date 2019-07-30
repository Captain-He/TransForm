package View;

import java.util.HashMap;
import java.util.Map;

public class StrucTable {
	public static StringBuffer t = new StringBuffer();
	public  String toStructureTxt(String data[][][]){
		//1.读取重要数据
		String result [] = new String[data[0].length];
		for(int i=7;i<data[0].length;i++){
			if(data[0][i][0] == null)break;
			String a = "";
			for(int j=0;j<4;j++){
				a+=data[0][i][j]+"@";
			}
			result[i-7] = a;
		}
		for(int i=0;i<result.length-1;i++){
			if(result[i] != null) {
				for(int j=i+1;j<result.length;j++){
						if(result[j] != null) {
							if(result[i].equals(result[j])){
								result[j] = "";
							}
						}
					}
			}
		}
		//2.写数据到文本
		String res = "";
		for(int i=0;i<result.length;i++){
			if(result[i]==null||result[i].length()<=0) continue;
			String a[] = result[i].split("@");
				for(int j=0;j<a.length;j++){
					String type = "";
					switch(j){
					case 0:
					 type = "客户";break;
					case 1:
						 type = "配电室";break;
					case 2:
						 type = "柜号/柜名";break;
					case 3:
						 type = "功能单元";break;
					 
					}
					if(!res.contains(a[j])){
						String ce = "";
						if(j == 0) {
							 ce = "0";
						}else{
							ce = a[j-1];
						}
						res +=a[j]+","+type+","+ce+"@";
					}
				}
				res+=i+"%";
		}
		//自增ID 与 名称 与类别
		Map<Integer,String> map_id_name = new HashMap<Integer,String>();
		String b[] = res.split("%");
		int index = 1;
		for(int i=0;i<b.length;i++){
			String d[] = b[i].split("@");
			for(int j = 0;j<d.length-1;j++){
				String c = index+","+d[j];
				map_id_name.put(index, d[j]);
				index++;
				t.append(c);
				t.append("\t\n");
			}
		}
		StringBuffer y = new StringBuffer();
		String text[] = t.toString().split("\t\n"); //2,澄清配电室,配电室,永城龙宇煤化工
		for(int i=0;i<text.length;i++){
			String a[] = text[i].split(",");
			if(a[3].equals("0")){
				y.append(a[0]+","+a[1]+","+a[2]+","+"0");
				y.append("\t\n");
			}
			for(int j=0;j<text.length;j++){
				String c[] = text[j].split(",");
				if(c[1].equals(a[3])){
					y.append(a[0]+","+a[1]+","+a[2]+","+c[0]);
					y.append("\t\n");
				}
			}

		}
		
//		for(int i=0;i<result.length;i++){
//			if(result[i]==null||result[i].length()<=0) continue;
//			String []a = result[i].split("@");
//			String type = "";
//			for(int j=0;j<a.length;j++){
//				if(a[j] == null) break;
//				switch(j){
//				case 0:
//				 type = "客户";break;
//				case 1:
//					 type = "配电室";break;
//				case 2:
//					 type = "柜号/柜名";break;
//				case 3:
//					 type = "功能单元";break;
//				 
//				}
//				String b =j+1+ ","+a[j]+","+type+","+j+"\r\n";
//				t.append(b);
//			}
//			t.append("-----------------------\r\n");
//		}
//		return t.toString();
		return y.toString();
 	}
	public  String backdata(){
		return t.toString();
	}
}
