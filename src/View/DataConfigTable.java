package View;

import java.util.HashMap;
import java.util.Map;

public class DataConfigTable {
	// data Ϊʩ����¼�� data2 Ϊ�豸�ͺű� 
	public  String toDataConfigTableTxt(String data[][][],String data2[][][]){
		// 1.sheet ��
				int shigong = 0;// ���� ʩ����ͼ��¼�� ���ڵ�һά��������shigong
				int dianli = 0;// ���� �����Ǳ� ���ڵ�һά��������dianli
				int wenji = 0;// ���� ���¼������ֳ�ʩ�����ü�¼ ���ڵ�һά��������wenpei
				int tongguan = 0;//���� ͨ�Ź������ ���ڵ�һά��������tongguan
				for (int i = 0; i < data.length; i++) {
					if (data[i][0][0].equals("ʩ����ͼ��¼��")) {
						shigong = i;
					}
					if (data[i][0][0].equals("ͨ�Ź�����ֳ�ʩ�����ü�¼��")) {
						tongguan= i;
					}
					if (data[i][0][0].equals("�����Ǳ��ֳ�ʩ�����ü�¼��")) {
						dianli = i;
					}
					if(data[i][0][0].equals("���¼������ֳ�ʩ�����ü�¼")){
						wenji = i;
					}
				}
				// 2.��ͷ ��
				  //2.1ʩ����ͼ�е��豸ΨһID �� �豸�ͺ�
				int ID = 0;//�ɼ��豸ΨһID������
				int dev = 0;//�豸�ͺ�������
				int type = 0;//�ɼ��豸����������
				for(int j = 0;j < 30;j++){
					if (data[shigong][6][j] == null) 
						break;
						if (data[shigong][6][j].equals("�ɼ��豸ΨһID")) {
							ID= j;
						}
						if (data[shigong][6][j].equals("�豸�ͺ�")) {
							dev= j;
						}
						if (data[shigong][6][j].equals("�ɼ��豸����")) {
							type= j;
						}
				}
//				  //2.2���¼������ֳ�ʩ�����ü�¼ ��ͷ
//				int wenid = 0;
//				int wendev = 0;
//				int wenjie = 0; //
//				int wenli = 0; //
//				int wentong = 0;
//				int wenchuan = 0;
//				int wenbo = 0;
//				int wenshu = 0;
//				int wenjiao = 0;
//				int wenting = 0;
//				int wenliu = 0;
//				int wencong = 0;
//				for(int j = 0;j < 30;j++){
//					if (data[wenji][1][j] == null) break;
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("�豸ΨһID���������ʹ�ã�")) {
//							wenid = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("�豸�ͺ�")) {
//							wendev = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("���뷽ʽ")) {
//							wenjie = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("������ͨ�Ź�������")) {
//							wenli = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("ͨ��Э��(modbusTCP/modbusRTU)")) {
//							wentong = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("���ڱ��")) {
//							wenchuan = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("������")) {
//							wenbo = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("����λ")) {
//							wenshu = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("У��λ")) {
//							wenjiao = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("ֹͣλ")) {
//							wenting = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("����")) {
//							wenliu = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("��վ��ַ")) {
//							wencong = j;
//						}
//				}
				//2.3 �����Ǳ� ��ͷ
				int dianid = 0;
//				int diandev = 0;
				int dianjie = 0; //
				int dianl = 0; //
				int diantong = 0;
				int dianchuan = 0;
				int dianbo = 0;
				int dianshu = 0;
				int dianjiao = 0;
				int dianting = 0;
				int dianliu = 0;
				int diancong = 0;
				for(int j = 0;j < 30;j++){
					if (data[dianli][3][j] == null) break;
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("�ɼ��豸ΨһID")) {
							dianid = j;
						}
//						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("�豸�ͺ�")) {
//							diandev = j;
//						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("���뷽ʽ(��̫��/����)")) {
							dianjie = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("������ͨ�Ź�������")) {
							dianl = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("ͨ��Э��(modbusTCP/modbusRTU)")) {
							diantong = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("���ڱ��")) {
							dianchuan = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("������")) {
							dianbo = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("����λ")) {
							dianshu = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("У��λ")) {
							dianjiao = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("ֹͣλ")) {
							dianting = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("����")) {
							dianliu = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("��վ��ַ")) {
							diancong = j;
						}
				}
				//2.4 �豸�ͺű� �е����Ǳ�  ��ͷ �õ�������
				int devcan = 0;
				int devshu= 0;
				int devgong = 0; //
				int devjidi = 0; //
				int devjishu = 0;
				for(int j = 0;j < 30;j++){
					if (data2[2][1][j] == null) 
						break;
						if (data2[2][1][j].equals("�����ֶ����")) {
							devcan = j;
						}
						if (data2[2][1][j].equals("��ȡȫ��������Ҫ����")) {
							devshu = j;
						}
						if (data2[2][1][j].equals("������")) {
							devgong = j;
						}
						if (data2[2][1][j].equals("�Ĵ�����ʼ��ַ")) {
							devjidi = j;
						}
						if (data2[2][1][j].equals("�Ĵ�������")) {
							devjishu = j;
						}
				}
//				//2.5 �豸�ͺű� ���¶ȱ�  ��ͷ �õ������� ������һ����Ϊ����
//				int wendevcan = 0;
//				int wendevshu= 0;
//				int wendevgong = 0; //
//				int wendevjidi = 0; //
//				int wendevjishu = 0;
//				for(int j = 0;j < 30;j++){
//					if (data2[data2.length-1][1][j] == null) 
//						break;
//						if (data2[data2.length-1][1][j].equals("�����ֶ����")) {
//							wendevcan = j;
//						}
//						if (data2[data2.length-1][1][j].equals("��ȡȫ��������Ҫ����")) {
//							wendevshu = j;
//						}
//						if (data2[data2.length-1][1][j].equals("������")) {
//							wendevgong = j;
//						}
//						if (data2[data2.length-1][1][j].equals("�Ĵ�����ʼ��ַ")) {
//							wendevjidi = j;
//						}
//						if (data2[data2.length-1][1][j].equals("�Ĵ�������")) {
//							wendevjishu = j;
//						}
//				}
				//2.6 ��ȡ ͨ�Ź���� ��ͷ
				int tongid = 0;
				int pcid = 0;
				int chuanip = 0;
				for(int j = 0;j < 30;j++){
					if (data[tongguan][2][j] == null) 
						break;
						if (data[tongguan][2][j].replaceAll("\n", "").replaceAll(" ", "").equals("ͨ�Ź�������")) {
							tongid = j;
						}
						if (data[tongguan][2][j].replaceAll("\n", "").replaceAll(" ", "").equals("PC���(����C��)")) {
							pcid = j;
						}
						if (data[tongguan][2][j].replaceAll("\n", "").replaceAll(" ", "").equals("����/IP/�˿�ӳ���ϵ")) {
							chuanip = j;
						}
				}
				//3.1��ȡ ʩ����ͼ���豸Ψһ�ɼ�ID �� �豸�ͺ�
				Map<String,String> map_ID_dev = new HashMap<String,String>();
				for(int i =7;i<data[shigong].length;i++){
					if(data[shigong][i][dev] == null) break;
					map_ID_dev.put(data[shigong][i][ID], data[shigong][i][dev]+"@"+data[shigong][i][type]);
				}
				//3.2 ��ȡ  ʩ����¼ �е� �����Ǳ� �Ͳ��¼����� �е� �ؼ���Ϣ
				Map<String,String> map_dian = new HashMap<String,String>();
				for(int i =4;i<data[dianli].length;i++ ){
					if(data[dianli][i][0] == null) break;
					String a = data[dianli][i][dianjie]+"@"+data[dianli][i][dianl]+"@"+data[dianli][i][diantong]+"@"+data[dianli][i][dianchuan]+"@"+data[dianli][i][dianbo]+"@"+data[dianli][i][dianshu]+"@"+data[dianli][i][dianjiao]+"@"+data[dianli][i][dianting]+"@"+data[dianli][i][dianliu]+"@"+data[dianli][i][diancong];
					map_dian.put(data[dianli][i][dianid], a);
				}
				Map<String,String> map_wen = new HashMap<String,String>();
				for(int i=2;i<data[wenji].length;i++){
					if(data[wenji][i][0] == null)break;
					String a = data[wenji][i][dianjie]+"@"+data[wenji][i][dianl]+"@"+data[wenji][i][diantong]+"@"+data[wenji][i][dianchuan]+"@"+data[wenji][i][dianbo]+"@"+data[wenji][i][dianshu]+"@"+data[wenji][i][dianjiao]+"@"+data[wenji][i][dianting]+"@"+data[wenji][i][dianliu]+"@"+data[wenji][i][diancong];
					map_wen.put(data[wenji][i][dianid], a);
				}
				//3.3 ��ȡ ͨ�Ź������� PC ��ͨ�Ź����IP �� ͨ�Ź�����˿ں�
				Map<String,String> map_tong = new HashMap<String,String>();
				for(int i=4;i<data[tongguan].length;i++){
					if(data[tongguan][i][0] == null) break;
					String a = data[tongguan][i][pcid]+"@";
					for(int j=chuanip;j<data[tongguan][i].length;j++){
						if(data[tongguan][i][j] == null ) break;
							a += data[tongguan][i][j]+"@";
					}
					map_tong.put(data[tongguan][i][tongid],a);
				}
				//3.4 ��ȡ �豸�ͺű� ��  �����Ǳ� �Ͳ��±�� ��Ҫ����
				Map<String,String> map_dev = new HashMap<String,String>();
				for(int i=0;i<data2.length;i++){
					String a = "";
					for(int j=2;j<data2[i].length;j++){//$�����ж��м���
						if(data2[i][j][devcan] == null) break;
						a +=data2[i][j][devcan]+"@"+data2[i][j][devshu]+"@"+data2[i][j][devgong]+"@"+data2[i][j][devjidi]+"@"+data2[i][j][devjishu]+"%";
					}
					map_dev.put(data2[i][0][0], a);
				}
				//4 ������֯ 	
//				for(Map.Entry<String, String> entry1 : map_ID_dev.entrySet()){
////					System.out.println("key=" + entry.getKey());
////					System.out.println("value" + entry.getValue());
//				}
//				for(Map.Entry<String, String> entry2 : map_dev.entrySet()){
////					System.out.println("key=" + entry2.getKey());
////					System.out.println("value" + entry2.getValue());
//				}
//				for(Map.Entry<String, String> entry3 : map_dian.entrySet()){
////					System.out.println("key=" + entry3.getKey());
////					System.out.println("value" + entry3.getValue());
//				}
//				for(Map.Entry<String, String> entry4: map_wen.entrySet()){
////					System.out.println("key=" + entry4.getKey());
////					System.out.println("value" + entry4.getValue());
//				}
//				for(Map.Entry<String, String> entry5: map_tong.entrySet()){
//					System.out.println("key=" + entry5.getKey());
//					System.out.println("value" + entry5.getValue());
//				}
				StringBuffer t = new StringBuffer();
				for(Map.Entry<String, String> entry1 : map_ID_dev.entrySet()){
					String id = entry1.getKey();//�ɼ��豸ΨһID
					String value1[] = entry1.getValue().split("@");
					String xinghao = value1[0];//�豸�ͺ�
					String tpname = value1[1];//�ɼ��豸����
					for(Map.Entry<String, String> entry2 : map_dev.entrySet()){
						String value2[] =  entry2.getValue().split("%");
						//System.out.println(xinghao+"----------"+entry2.getKey());
						if(xinghao.equals(entry2.getKey())){
							//System.out.println(xinghao+"#############"+entry2.getKey());
							for(int i=0;i<value2.length;i++){
								//�����Ǳ� value2.length��ʶ����
								String v2[] = value2[i].split("@");//v2 Ϊ����豸�ͺ� ����
								if(tpname.equals("���´�����")){
									//System.out.println(map_wen);
									for(Map.Entry<String, String> entry4: map_wen.entrySet()){
										if(id.equals(entry4.getKey())){
//											System.out.println(entry4.getValue());
											String v4 [] = entry4.getValue().split("@");//�����Ǳ���Ҫ��Ϣ
											//System.out.println(v4[1]+v4[3]); v4[1]Ϊ������ͨ�Ź������ţ�v4[3]Ϊ���ڱ��
											for(Map.Entry<String, String> entry5: map_tong.entrySet()){
												if(v4[1].equals(entry5.getKey())){
													String v5 [] = entry5.getValue().split("@");//ͨ�Ź������Ҫ��Ϣ
													//v5[1] ΪPC���
													for(int k=1;k<v5.length;k++){
														String a[] = v5[k].split("/");
														//System.out.println(v4[1]+"======"+a[0]);
														if(v4[3].equals(a[0])){
															//System.out.println(a[1]+a[2]);
															//a[1]  ͨ�Ź����IP 
															//a[2] ͨ�Ź�����˿ں�
															String wen = id+","+v2[0]+","+v2[1]+","+v4[0]+","+v4[1]+","+v5[1]+","+v4[2]+","+v4[3]+","+a[1]+","+a[2]+","+v4[4]+","+v4[5]+","+v4[6]+","+v4[7]+","+v4[8]+","+v4[9]+","+v2[2]+","+v2[3]+","+v2[4];
															t.append(wen);
															t.append("\r\n");
														}
													}
												}
											}
										}
									}
								}else{
									//System.out.println(map_dian);
									for(Map.Entry<String, String> entry4: map_dian.entrySet()){
										if(id.equals(entry4.getKey())){
//											System.out.println(entry4.getValue());
											String v4 [] = entry4.getValue().split("@");//�����Ǳ���Ҫ��Ϣ
											//System.out.println(v4[1]+v4[3]); v4[1]Ϊ������ͨ�Ź������ţ�v4[3]Ϊ���ڱ��
											for(Map.Entry<String, String> entry5: map_tong.entrySet()){
												if(v4[1].equals(entry5.getKey())){
													String v5 [] = entry5.getValue().split("@");//ͨ�Ź������Ҫ��Ϣ
													//v5[1] ΪPC���
													for(int k=1;k<v5.length;k++){
														String a[] = v5[k].split("/");
														//System.out.println(v4[1]+"======"+a[0]);
														if(v4[3].equals(a[0])){
															//System.out.println(a[1]+a[2]);
															//a[1]  ͨ�Ź����IP 
															//a[2] ͨ�Ź�����˿ں�
															String dian = id+","+v2[0]+","+v2[1]+","+v4[0]+","+v4[1]+","+v5[1]+","+v4[2]+","+v4[3]+","+a[1]+","+a[2]+","+v4[4]+","+v4[5]+","+v4[6]+","+v4[7]+","+v4[8]+","+v4[9]+","+v2[2]+","+v2[3]+","+v2[4];
															t.append(dian);
															t.append("\r\n");
														}
													}
												}
											}
										}
									}
								}
							}
							
						}
					}
					
				}
				
			return t.toString();	
	}
}
