package View;

public class StrucTable {
	public static String toStructureTxt(String data[][][]){
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
		StringBuffer t = new StringBuffer();
		for(int i=0;i<result.length;i++){
			if(result[i]==null||result[i].length()<=0) continue;
			String []a = result[i].split("@");
			String type = "";
			for(int j=0;j<a.length;j++){
				if(a[j] == null) break;
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
				String b =j+1+ ","+a[j]+","+type+","+j+"\r\n";
				t.append(b);
			}
			t.append("-----------------------\r\n");
		}
		return t.toString();
	}
}
