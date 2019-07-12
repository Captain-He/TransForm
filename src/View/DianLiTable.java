package View;

import java.util.HashMap;
import java.util.Map;

public class DianLiTable {
	public String toDianTxt(String[][]data1,String[][][] data,String [][][] data2){
		//data1 Ϊ��������֯�����01��ʼ �� data Ϊʩ����¼�� data2 Ϊ�豸�ͺű� 
		//1.��ȡ���йؼ�����
		 //1.1��ȡʩ����¼���� �����Ǳ��  �ɼ��豸ΨһID��ID�� �� �豸�ͺţ�devNum��
		Map <Double,String> shiGong = new HashMap<Double,String>();
		int devNum = 0;
		int ID = 0;
		for(int i=0;i<data[0][6].length;i++){
			if(data[0][6][i] == null) break;
			if(data[0][6][i].equals("�豸�ͺ�")){
				devNum = i;
			}
			if(data[0][6][i].equals("�ɼ��豸ΨһID")){
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
		 //1.2�豸�ͺű� ��Ҫ�������� 
		int hanYi = 0;
		int leiXing = 0;
		int di = 0;
		int jiNum = 0;
		int beiLv = 0;
		for(int i=0;i<data2[2][1].length;i++){
			if(data2[2][1][i] == null) break;
			if(data2[2][1][i].equals("���ݺ���")){
				hanYi = i;
			}
			if(data2[2][1][i].equals("��������")){
				leiXing = i;
			}
			if(data2[2][1][i].equals("�Ĵ�����ַ")){
				di = i;
			}
			if(data2[2][1][i].replaceAll("\n", "").replaceAll(" ", "").equals("��������ռ�ݵļĴ�������")){
				jiNum = i;
			}
			if(data2[2][1][i].equals("����")){
				beiLv = i;
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<data2.length-4;i++){
			String a = "";
			for(int j=2;j<data2[i].length;j++){
				if(data2[i][j][0] == null) break;
				//data2[i][j][hanYi] to �ַ����ָ� ��������
				a+=toSplit(data2[i][j][hanYi].replaceAll("\n", "").replaceAll(" ", ""),data2[i][j][leiXing].replaceAll("\n", "").replaceAll(" ", ""),data2[i][j][di].replaceAll("\n", "").replaceAll(" ", ""),data2[i][j][jiNum].replaceAll("\n", "").replaceAll(" ", ""),data2[i][j][beiLv].replaceAll("\n", "").replaceAll(" ", ""));

			}
			map.put(data2[i][0][0],a);
		}
		//2.������������
		String res [] = new String[100];
		int q = 0;
		for(Map.Entry<Double,String> id:shiGong.entrySet()){
			  Double idkey=id.getKey();
			  String deValue = id.getValue();
				String result[] = new String[100];
				res[q] = idkey.toString()+",";
				  int k = 0;//��Ӧ���ձ����һ��
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
						  //b[0] ������ ����ȷ��item,b[1]��ֵ ֱ��װ��
						  for(int j=1;j<data1[k].length;j++){
							  if(data1[k][j] == null) break;
							  //j ��Ϊ���жϵ�item
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
		//3.�����ļ�
		StringBuffer t = new StringBuffer();
		for(int i=0;i<res.length;i++){
			if(res[i]==null)break;
			t.append(res[i]);
			//System.out.println(result[i]);
			t.append("\r\n");
		}
		return t.toString();
	}
	//�ַ����и�����ƴ�� ����Ϊһ��map���� ����Ӧ�豸���е�һ��
	public String toSplit(String str1,String str2,String str3,String str4,String str5){
		//1->����  2->���� 3->��ַ 4->���� 5->����
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
