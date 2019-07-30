package View;

import java.util.HashMap;
import java.util.Map;

public class DataConfigTable {
	// data 为施工记录表； data2 为设备型号表 
	public  String toDataConfigTableTxt(String data[][][],String data2[][][]){
		// 1.sheet 级
				int shigong = 0;// 查找 施工总图记录表 存于第一维索引存于shigong
				int dianli = 0;// 查找 电力仪表 存于第一维索引存于dianli
				int wenji = 0;// 查找 测温集中器现场施工配置记录 存于第一维索引存于wenpei
				int tongguan = 0;//查找 通信管理机表 存于第一维索引存于tongguan
				for (int i = 0; i < data.length; i++) {
					if (data[i][0][0].equals("施工总图记录表")) {
						shigong = i;
					}
					if (data[i][0][0].equals("通信管理机现场施工配置记录表")) {
						tongguan= i;
					}
					if (data[i][0][0].equals("电力仪表现场施工配置记录表")) {
						dianli = i;
					}
					if(data[i][0][0].equals("测温集中器现场施工配置记录")){
						wenji = i;
					}
				}
				// 2.表头 级
				  //2.1施工总图中的设备唯一ID 和 设备型号
				int ID = 0;//采集设备唯一ID所在列
				int dev = 0;//设备型号所在列
				int type = 0;//采集设备类型所在列
				for(int j = 0;j < 30;j++){
					if (data[shigong][6][j] == null) 
						break;
						if (data[shigong][6][j].equals("采集设备唯一ID")) {
							ID= j;
						}
						if (data[shigong][6][j].equals("设备型号")) {
							dev= j;
						}
						if (data[shigong][6][j].equals("采集设备类型")) {
							type= j;
						}
				}
//				  //2.2测温集中器现场施工配置记录 表头
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
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("设备唯一ID（后期软件使用）")) {
//							wenid = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("设备型号")) {
//							wendev = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("接入方式")) {
//							wenjie = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("隶属的通信管理机编号")) {
//							wenli = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("通信协议(modbusTCP/modbusRTU)")) {
//							wentong = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("串口编号")) {
//							wenchuan = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("波特率")) {
//							wenbo = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("数据位")) {
//							wenshu = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("校验位")) {
//							wenjiao = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("停止位")) {
//							wenting = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("流控")) {
//							wenliu = j;
//						}
//						if (data[wenji][1][j].replaceAll("\n", "").replaceAll(" ", "").equals("从站地址")) {
//							wencong = j;
//						}
//				}
				//2.3 电力仪表 表头
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
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("采集设备唯一ID")) {
							dianid = j;
						}
//						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("设备型号")) {
//							diandev = j;
//						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("接入方式(以太网/串口)")) {
							dianjie = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("隶属的通信管理机编号")) {
							dianl = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("通信协议(modbusTCP/modbusRTU)")) {
							diantong = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("串口编号")) {
							dianchuan = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("波特率")) {
							dianbo = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("数据位")) {
							dianshu = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("校验位")) {
							dianjiao = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("停止位")) {
							dianting = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("流控")) {
							dianliu = j;
						}
						if (data[dianli][3][j].replaceAll("\n", "").replaceAll(" ", "").equals("从站地址")) {
							diancong = j;
						}
				}
				//2.4 设备型号表 中电力仪表  表头 用第三个表
				int devcan = 0;
				int devshu= 0;
				int devgong = 0; //
				int devjidi = 0; //
				int devjishu = 0;
				for(int j = 0;j < 30;j++){
					if (data2[2][1][j] == null) 
						break;
						if (data2[2][1][j].equals("参数分段序号")) {
							devcan = j;
						}
						if (data2[2][1][j].equals("读取全部数据需要几次")) {
							devshu = j;
						}
						if (data2[2][1][j].equals("功能码")) {
							devgong = j;
						}
						if (data2[2][1][j].equals("寄存器起始地址")) {
							devjidi = j;
						}
						if (data2[2][1][j].equals("寄存器数量")) {
							devjishu = j;
						}
				}
//				//2.5 设备型号表 中温度表  表头 用第三个表 倒数第一个表为例子
//				int wendevcan = 0;
//				int wendevshu= 0;
//				int wendevgong = 0; //
//				int wendevjidi = 0; //
//				int wendevjishu = 0;
//				for(int j = 0;j < 30;j++){
//					if (data2[data2.length-1][1][j] == null) 
//						break;
//						if (data2[data2.length-1][1][j].equals("参数分段序号")) {
//							wendevcan = j;
//						}
//						if (data2[data2.length-1][1][j].equals("读取全部数据需要几次")) {
//							wendevshu = j;
//						}
//						if (data2[data2.length-1][1][j].equals("功能码")) {
//							wendevgong = j;
//						}
//						if (data2[data2.length-1][1][j].equals("寄存器起始地址")) {
//							wendevjidi = j;
//						}
//						if (data2[data2.length-1][1][j].equals("寄存器数量")) {
//							wendevjishu = j;
//						}
//				}
				//2.6 获取 通信管理机 表头
				int tongid = 0;
				int pcid = 0;
				int chuanip = 0;
				for(int j = 0;j < 30;j++){
					if (data[tongguan][2][j] == null) 
						break;
						if (data[tongguan][2][j].replaceAll("\n", "").replaceAll(" ", "").equals("通信管理机编号")) {
							tongid = j;
						}
						if (data[tongguan][2][j].replaceAll("\n", "").replaceAll(" ", "").equals("PC编号(如是C端)")) {
							pcid = j;
						}
						if (data[tongguan][2][j].replaceAll("\n", "").replaceAll(" ", "").equals("总线/IP/端口映射关系")) {
							chuanip = j;
						}
				}
				//3.1获取 施工总图的设备唯一采集ID 和 设备型号
				Map<String,String> map_ID_dev = new HashMap<String,String>();
				for(int i =7;i<data[shigong].length;i++){
					if(data[shigong][i][dev] == null) break;
					map_ID_dev.put(data[shigong][i][ID], data[shigong][i][dev]+"@"+data[shigong][i][type]);
				}
				//3.2 获取  施工记录 中的 电力仪表 和测温集中器 中的 关键信息
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
				//3.3 获取 通信管理机表的 PC 、通信管理机IP 和 通信管理机端口号
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
				//3.4 获取 设备型号表 中  电力仪表 和测温表的 重要数据
				Map<String,String> map_dev = new HashMap<String,String>();
				for(int i=0;i<data2.length;i++){
					String a = "";
					for(int j=2;j<data2[i].length;j++){//$用来判断有几行
						if(data2[i][j][devcan] == null) break;
						a +=data2[i][j][devcan]+"@"+data2[i][j][devshu]+"@"+data2[i][j][devgong]+"@"+data2[i][j][devjidi]+"@"+data2[i][j][devjishu]+"%";
					}
					map_dev.put(data2[i][0][0], a);
				}
				//4 重新组织 	
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
					String id = entry1.getKey();//采集设备唯一ID
					String value1[] = entry1.getValue().split("@");
					String xinghao = value1[0];//设备型号
					String tpname = value1[1];//采集设备类型
					for(Map.Entry<String, String> entry2 : map_dev.entrySet()){
						String value2[] =  entry2.getValue().split("%");
						//System.out.println(xinghao+"----------"+entry2.getKey());
						if(xinghao.equals(entry2.getKey())){
							//System.out.println(xinghao+"#############"+entry2.getKey());
							for(int i=0;i<value2.length;i++){
								//电力仪表 value2.length标识行数
								String v2[] = value2[i].split("@");//v2 为五个设备型号 数据
								if(tpname.equals("测温传感器")){
									//System.out.println(map_wen);
									for(Map.Entry<String, String> entry4: map_wen.entrySet()){
										if(id.equals(entry4.getKey())){
//											System.out.println(entry4.getValue());
											String v4 [] = entry4.getValue().split("@");//电力仪表重要信息
											//System.out.println(v4[1]+v4[3]); v4[1]为隶属的通信管理机编号，v4[3]为串口编号
											for(Map.Entry<String, String> entry5: map_tong.entrySet()){
												if(v4[1].equals(entry5.getKey())){
													String v5 [] = entry5.getValue().split("@");//通信管理机重要信息
													//v5[1] 为PC编号
													for(int k=1;k<v5.length;k++){
														String a[] = v5[k].split("/");
														//System.out.println(v4[1]+"======"+a[0]);
														if(v4[3].equals(a[0])){
															//System.out.println(a[1]+a[2]);
															//a[1]  通信管理机IP 
															//a[2] 通信管理机端口号
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
											String v4 [] = entry4.getValue().split("@");//电力仪表重要信息
											//System.out.println(v4[1]+v4[3]); v4[1]为隶属的通信管理机编号，v4[3]为串口编号
											for(Map.Entry<String, String> entry5: map_tong.entrySet()){
												if(v4[1].equals(entry5.getKey())){
													String v5 [] = entry5.getValue().split("@");//通信管理机重要信息
													//v5[1] 为PC编号
													for(int k=1;k<v5.length;k++){
														String a[] = v5[k].split("/");
														//System.out.println(v4[1]+"======"+a[0]);
														if(v4[3].equals(a[0])){
															//System.out.println(a[1]+a[2]);
															//a[1]  通信管理机IP 
															//a[2] 通信管理机端口号
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
