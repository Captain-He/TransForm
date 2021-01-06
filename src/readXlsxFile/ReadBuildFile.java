package readXlsxFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import readXlsxFile.ReadTables;

public class ReadBuildFile {

	static final int max_X = 10000;//数组中每张表行数的最大值
	static final int max_Y = 100;//数组中每张表列数的最大值

	public static int chuanganqi = 0;// 查找 施工总图记录表 存于第一维索引存于chuanganqi
	public static int wenzhao = 0;// 查找 测温传感器和测温集中器对照表 存于第一维索引存于wenzhao
	public static int jizhongqi = 0;// 查找 测温集中器现场施工配置记录 存于第一维索引存于jizhongqi
	public static int dianli = 0;// 查找 电力仪表 存于第一维索引存于dianli
	public static int caiji = 0;// 查找 采集PC现场施工配置记录 存于第一维索引存于wenpei
	public static int tongguan = 0;  //查找 通信管理机表 存于第一维索引存于tongguan

	public static String[][][] BuildTable; //整个施工记录文件
	//错误信息存储
	public static String report="errors:\r\n"; //错误信息存储

	// 读取施工记录表，返回数组
	public  static String [][][] readTables(String dir) {

		//读取 整个施工记录文件
		ReadTables readbuildtable = new ReadTables();
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readbuildtable.readExcel(dir);
		int sheetNum = wb.getNumberOfSheets(); // sheet数量
		String[][][] array = new String[sheetNum][max_X][max_Y];
		if (wb != null) {
			// 获取第一个sheet
			for (int i = 0; i < sheetNum; i++) {
				sheet = wb.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();// 获取最大行数
				for (int j = 0; j <readbuildtable.getSheetRows(sheet); j++) {
					row = sheet.getRow(j);
					if (row != null) {
						int colnum = row.getPhysicalNumberOfCells();
						for (int k = 0; k < colnum; k++) {
							String cellData = (String) readbuildtable.getCellFormatValue(row
									.getCell(k));
							if (cellData != null && cellData.length() > 0) {
								array[i][j][k] = cellData;
								System.out.println(cellData);
							}
						}
					}
				}
			}
		}
		BuildTable = array;
		//到此整个施工记录文件存在三位数组 BuildTable;

		// 1.sheet 级

		for (int i = 0; i < BuildTable.length; i++) {
			if (BuildTable[i][0][0].equals("传感器")) {
				chuanganqi = i;
			}
			if (BuildTable[i][0][0].equals("传感器和集中器对照及相对位置")) {
				wenzhao = i;
			}
			if (BuildTable[i][0][0].equals("集中器")) {
				jizhongqi = i;
			}
			if (BuildTable[i][0][0].equals("通信管理机")) {
				tongguan= i;
			}
	/*		if (BuildTable[i][0][0].equals("电力仪表现场施工配置记录表")) {
				dianli = i;
			}*/
			if(BuildTable[i][0][0].equals("采集服务器")){
				caiji = i;
			}
		}//到此sheet级结束
	/*	table1();
		table2();
		table3();
		table4();
		table5();
		table6();*/
		return array;
	}

	public static void table1(){
		//返回施工总图
		String array[][] = BuildTable[chuanganqi];
		//1.查找表头
		int title1 = 0;//配电室  所在列
		int title2 = 0;//柜号/柜名  所在列
		int title3 = 0;//功能单元  所在列
		int title4 = 0;//采集设备类型  所在列
		int title5 = 0;//设备型号  所在列
		int title6 = 0;//测量位置  所在列
		int title7 = 0;//采集设备唯一ID  所在列
		//表头 固定在第七行 所以从第七行遍历
		for(int j = 0;j <array[6].length;j++){
			if (array[6][j] == null)
				break;
			if (slim(array[6][j],"采集设备类型")) {
				title4= j;
			}
			if (slim(array[6][j],"采集设备唯一ID")) {
				title7= j;
			}
		}
		//2.检查数据范围 （施工总图中只有采集设备唯一ID给了范围 ）数据从第八行开始
		for(int i=7;i<array.length;i++){
			if (array[i][title4] == null) break;
			if(slim(array[i][title4],"测温传感器")){
				if(!((sTurnd(array[i][title7])>=200001)&&(sTurnd(array[i][title7])<=299999))){
					report +="施工记录文件-施工总图记录表:第  "+(i+1)+"行  第 "+(title7+1)+" 列数据错误\r\n";
				}
			}else{
				if(!((sTurnd(array[i][title7])>=100001)&&(sTurnd(array[i][title7])<=199999))){
					report +="施工记录文件-施工总图记录表:第  "+(i+1)+"行第 "+title7+" 列数据错误\r\n";
				}
			}
		}
		//return array;
	}
	public static void table2(){
		//返回通信管理机表
		String array[][] = BuildTable[tongguan];
		//1.查找表头
		int title1 = 0;//通信管理机编号  所在列
		int title2 = 0;//安装位置  所在列
		int title3 = 0;//C端/S端  所在列
		int title4 = 0;//PC编号(如是C端)  所在列
		int title5 = 0;//串行通道数量 所在列
		int title6 = 0;//总线/IP/端口映射关系  所在列
		//表头 固定在第二行 所以从第二行遍历
		for(int j = 2;j <array[2].length;j++){
			if (array[2][j] == null)
				break;
			if (slim(array[2][j],"通信管理机编号")) {
				title1= j;
			}
			if (slim(array[2][j],"安装位置")) {
				title2= j;
			}
			if (slim(array[2][j],"C端/S端")) {
				title3= j;
			}
			if (slim(array[2][j],"PC编号(如是C端)")) {
				title4= j;
			}
			if (slim(array[2][j],"串行通道数量")) {
				title5= j;
			}
			if (slim(array[2][j],"总线/IP/端口映射关系")) {
				title6= j;
			}
		}
		//2.检查数据范围 ）数据从第四行开始
		for(int i=3;i<array.length;i++){
			if (array[i][title1] == null) break;
			//System.out.println(array[i][title4] +"--------"+array[i][title7]);
			if(!((sTurnd(array[i][title1])>=1001)&&(sTurnd(array[i][title1])<=1999))){
				report +="施工记录文件-通信管理机表:第  "+(i+1)+"行  第 "+(title1+1)+" 列数据错误\r\n";
			}
			if(!((sTurnd(array[i][title4])>=001)&&(sTurnd(array[i][title4])<=999))){
				report +="施工记录文件-通信管理机表:第  "+(i+1)+"行  第 "+(title4+1)+" 列数据错误\r\n";
			}
			for(int j=title6;j<array[i].length;j++){
				if(array[i][j] == null||slim(array[i][j],"-")) break;
				String a[] = array[i][j].split("/");
				String b[] = a[1].split("\\.");
				double c = sTurnd(a[0].substring(a[0].length()-1,a[0].length()));
				if(!((c>=0)&&(c<=16))){
					report +="施工记录文件-通信管理机表:第  "+(i+1)+"行  第 "+(j+1)+" 列串口编号数据错误\r\n";
				}
				if(!((sTurnd(b[0])>=0)&&(sTurnd(b[0])<=255))){
					report +="施工记录文件-通信管理机表:第  "+(i+1)+"行  第 "+(j+1)+" 列IP数据错误\r\n";
				}
				if(!((sTurnd(b[1])>=0)&&(sTurnd(b[1])<=255))){
					report +="施工记录文件-通信管理机表:第  "+(i+1)+"行  第 "+(j+1)+" 列IP数据错误\r\n";
				}
				if(!((sTurnd(b[2])>=0)&&(sTurnd(b[2])<=255))){
					report +="施工记录文件-通信管理机表:第  "+(i+1)+"行  第 "+(j+1)+" 列IP数据错误\r\n";
				}
				if(!((sTurnd(b[3])>=0)&&(sTurnd(b[3])<=255))){
					report +="施工记录文件-通信管理机表:第  "+(i+1)+"行  第 "+(j+1)+" 列IP数据错误\r\n";
				}
				if(!((sTurnd(a[2])>=0)&&(sTurnd(a[2])<=65535))){
					report +="施工记录文件-通信管理机表:第  "+(i+1)+"行  第 "+(j+1)+" 列端口数据错误\r\n";
				}
			}
		}
		//return array;
	}
	public static void table3(){
		//返回电力仪表
		String array[][] = BuildTable[dianli];
		//1.查找表头
		int title1 = 0;//采集设备唯一ID 所在列
		int title2 = 0;//接入方式(以太网/串口)  所在列
		int title3 = 0;//通信协议  所在列
		int title4 = 0;//隶属的通信管理机编号  所在列
		int title5 = 0;//串口编号 所在列
		int title6 = 0;//波特率  所在列
		int title7 = 0;//数据位 所在列
		int title8 = 0;//校验位 所在列
		int title9 = 0;//停止位 所在列
		int title10 = 0;//流控 所在列
		int title11 = 0;//从站地址 所在列
		//表头 固定在第三行 所以从第三行遍历
		for(int j = 0;j <array[3].length;j++){
			if (array[3][j] == null)
				break;
			if (slim(array[3][j],"采集设备唯一ID")) {
				title1= j;
			}
			if (slim(array[3][j],"接入方式(以太网/串口)")) {
				title2= j;
			}
			if (slim(array[3][j],"通信协议(modbusTCP/modbusRTU)")) {
				title3= j;
			}
			if (slim(array[3][j],"隶属的通信管理机编号")) {
				title4= j;
			}
			if (slim(array[3][j],"串口编号")) {
				title5= j;
			}
			if (slim(array[3][j],"波特率")) {
				title6= j;
			}
			if (slim(array[3][j],"数据位")) {
				title7= j;
			}
			if (slim(array[3][j],"校验位")) {
				title8= j;
			}
			if (slim(array[3][j],"停止位")) {
				title9= j;
			}
			if (slim(array[3][j],"流控")) {
				title10= j;
			}
			if (slim(array[3][j],"从站地址")) {
				title11= j;
			}

		}
		//2.检查数据范围 数据从第五行开始
		for(int i=4;i<array.length;i++){
			if (array[i][title1] == null) break;
			if(!DLid(array[i][title1])){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title1+1)+" 列数据错误\r\n";
			}
			if(!Jr(array[i][title2])){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title2+1)+" 列数555据错误\r\n";
			}
			if(!((array[i][title3].equals("modbusTCP"))||(array[i][title3].equals("modbusRTU")))){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title3+1)+" 列数据错误\r\n";
			}
			if(!((sTurnd(array[i][title4])>=1001)&&(sTurnd(array[i][title4])<=1999))){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title4+1)+" 列数据错误\r\n";
			}
			double c = sTurnd(array[i][title5].substring(array[i][title5].length()-1,array[i][title5].length()));
			if(!((c>=0)&&(c<=16))){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title5+1)+" 列串口编号数据错误\r\n";
			}
			if(!Bt(array[i][title6])){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title6+1)+" 列数据错误\r\n";
			}
			if(!Sw(array[i][title7])){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title7+1)+" 列数据错误\r\n";
			}
			if(!Jw(array[i][title8])){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title8+1)+" 列数据错误\r\n";
			}
			if(!Tw(array[i][title9])){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title9+1)+" 列数据错误\r\n";
			}
			if(!Lk(array[i][title10])){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title10+1)+" 列数据错误\r\n";
			}
			if(!Cd(array[i][title11])){
				report +="施工记录文件-电力仪表:第  "+(i+1)+"行  第 "+(title11+1)+" 列数据错误\r\n";
			}
		}
		//return array;
	}
	public static void table4(){
		//返回测温集中器表
		String array[][] = BuildTable[wenzhao];
		//1.查找表头
		int title1 = 0;//采集设备唯一ID 所在列
		int title2 = 0;//接入方式(以太网/串口)  所在列
		int title3 = 0;//通信协议  所在列
		int title4 = 0;//隶属的通信管理机编号  所在列
		int title5 = 0;//串口编号 所在列
		int title6 = 0;//波特率  所在列
		int title7 = 0;//数据位 所在列
		int title8 = 0;//校验位 所在列
		int title9 = 0;//停止位 所在列
		int title10 = 0;//流控 所在列
		int title11 = 0;//从站地址 所在列
		//表头 固定在第三行 所以从第三行遍历
		for(int j = 0;j <array[1].length;j++){
			if (array[1][j] == null)
				break;
			if (slim(array[1][j],"设备唯一ID（后期软件使用）")) {
				title1= j;
			}
			if (slim(array[1][j],"接入方式")) {
				title2= j;
			}
			if (slim(array[1][j],"通信协议(modbusTCP/modbusRTU)")) {
				title3= j;
			}
			if (slim(array[1][j],"隶属的通信管理机编号")) {
				title4= j;
			}
			if (slim(array[1][j],"串口编号")) {
				title5= j;
			}
			if (slim(array[1][j],"波特率")) {
				title6= j;
			}
			if (slim(array[1][j],"数据位")) {
				title7= j;
			}
			if (slim(array[1][j],"校验位")) {
				title8= j;
			}
			if (slim(array[1][j],"停止位")) {
				title9= j;
			}
			if (slim(array[1][j],"流控")) {
				title10= j;
			}
			if (slim(array[1][j],"从站地址")) {
				title11= j;
			}

		}
		//2.检查数据范围 数据从第五行开始
		for(int i=2;i<array.length;i++){
			if (array[i][title1] == null) break;
			if(!WJid(array[i][title1])){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title1+1)+" 列数据错误\r\n";
			}
			if(!Jr(array[i][title2])){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title2+1)+" 列数555据错误\r\n";
			}
			if(!((array[i][title3].equals("modbusTCP"))||(array[i][title3].equals("modbusRTU")))){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title3+1)+" 列数据错误\r\n";
			}
//			if(!((sTurnd(array[i][title4])>=1001)&&(sTurnd(array[i][title4])<=1999))){
//				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title4+1)+" 列数据错误\r\n";
//			}
			double c = sTurnd(array[i][title5].substring(array[i][title5].length()-1,array[i][title5].length()));
			if(!((c>=0)&&(c<=16))){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title5+1)+" 列串口编号数据错误\r\n";
			}
			if(!Bt(array[i][title6])){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title6+1)+" 列数据错误\r\n";
			}
			if(!Sw(array[i][title7])){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title7+1)+" 列数据错误\r\n";
			}
			if(!Jw(array[i][title8])){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title8+1)+" 列数据错误\r\n";
			}
			if(!Tw(array[i][title9])){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title9+1)+" 列数据错误\r\n";
			}
			if(!Lk(array[i][title10])){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title10+1)+" 列数据错误\r\n";
			}
			if(!Cd(array[i][title11])){
				report +="施工记录文件-测温集中器表:第  "+(i+1)+"行  第 "+(title11+1)+" 列数据错误\r\n";
			}
		}
		//return array;
	}
	public static void table5(){
		//返回传感器和测温集中器对照表
		String array[][] = BuildTable[wenzhao];
		//1.查找表头
		int title1 = 0;//测温传感器ID 所在列
		int title2 = 0;//测温集中器ID  所在列
		int title3 = 0;//测温传感器在测温集中器中地址  所在列

		//表头 固定在第四行 所以从第四行遍历
		for(int j = 0;j <array[3].length;j++){
			if (array[3][j] == null)
				break;
			if (slim(array[3][j],"测温传感器ID")) {
				title1= j;
			}
			if (slim(array[3][j],"测温集中器ID")) {
				title2= j;
			}
			if (slim(array[3][j],"测温传感器在测温集中器中地址")) {
				title3= j;
			}

		}
		//2.检查数据范围 数据从第五行开始
		for(int i=4;i<array.length;i++){
			if (array[i][title1] == null) break;
			if(!WCid(array[i][title1])){
				report +="施工记录文件-测温传感器和测温集中器对照表:第  "+(i+1)+"行  第 "+(title1+1)+" 列数据错误\r\n";
			}
			if(!WJid(array[i][title2])){
				report +="施工记录文件-测温传感器和测温集中器对照表:第  "+(i+1)+"行  第 "+(title2+1)+" 列数据错误\r\n";
			}
			if(!WD(array[i][title3])){
				report +="施工记录文件-测温传感器和测温集中器对照表:第  "+(i+1)+"行  第 "+(title3+1)+" 列数据错误\r\n";
			}
		}
		//return array;
	}
	public static void table6(){
		//返回采集PC表
		String array[][] = BuildTable[caiji];
		//1.查找表头
		int title1 = 0;//设备编号（自定义唯一编号） 所在列
		int title2 = 0;//IP地址  所在列
		int title3 = 0;//端口号 所在列

		//表头 固定在第四行 所以从第四行遍历
		for(int j = 0;j <array[3].length;j++){
			if (array[3][j] == null)
				break;
			if (slim(array[3][j],"设备编号（自定义唯一编号）")) {
				title1= j;
			}
			if (slim(array[3][j],"IP地址")) {
				title2= j;
			}
			if (slim(array[3][j],"端口号")) {
				title3= j;
			}

		}
		//2.检查数据范围 数据从第五行开始
		for(int i=4;i<array.length;i++){
			if (array[i][title1] == null) break;
			if(!PCid(array[i][title1])){
				report +="施工记录文件-采集PC表:第  "+(i+1)+"行  第 "+(title1+1)+" 列数据错误\r\n";
			}
			if(!IP(array[i][title2])){
				report +="施工记录文件-采集PC表:第  "+(i+1)+"行  第 "+(title2+1)+" 列数据错误\r\n";
			}
			if(!DKnumber(array[i][title3])){
				report +="施工记录文件-采集PC表:第  "+(i+1)+"行  第 "+(title3+1)+" 列数据错误\r\n";
			}
		}
		//return array;
	}
	public static boolean slim(String a,String b){
		return a.replaceAll("\n", "").replaceAll(" ", "").equals(b);
	}
	public static double sTurnd(String a){
		return Double.valueOf(a);
	}
	public static boolean WJid(String a){
		//判断测温集中器的 唯一ID 是否正确
		double b = sTurnd(a);
		if(b<=399999&&b>=300001)
			return true;
		else
			return false;
	}
	public static boolean WCid(String a){
		//测温传感器ID唯一ID 是否正确
		double b = sTurnd(a);
		if(b<=299999&&b>=200001)
			return true;
		else
			return false;
	}
	public static boolean DLid(String a){
		//判断电力仪表的 唯一ID 是否正确
		double b = sTurnd(a);
		if(b<=199999&&b>=100001)
			return true;
		else
			return false;
	}
	public static boolean PCid(String a){
		//测温传感器在测温集中器中地址  是否正确
		double b = sTurnd(a);
		if(b<=999&&b>=1)
			return true;
		else
			return false;
	}

	public static boolean DKnumber(String a){
		double b = sTurnd(a);
		if(!(b<=65535&&b>=0))
			return false;
		else
			return true;
	}
	public static boolean IP(String a){

		boolean T = true;
		String b[] = a.split("\\.");
		if(!(b.length == 4)) return false;
		for(int i=0;i<4;i++){
			double c = sTurnd(b[i]);
			if(!((c<=255)&&(c>=0))) {
				T = false;
				break;
			}
		}
		return T;
	}
	public static boolean WD(String a){
		//测温传感器在测温集中器中地址  是否正确
		double b = sTurnd(a);
		if(b<=65535&&b>=0)
			return true;
		else
			return false;
	}
	public static boolean Jr(String a){
		//判断接入方式
		String b = a.replaceAll("\n", "").replaceAll(" ", "");
		if(b.equals("串口")||b.equals("以太网"))
			return true;
		else
			return false;
	}
	public static boolean Bt(String a){
		//判断波特率
		boolean T = false;
		int b = (int)sTurnd(a);
		switch(b){
			case 1200:T = true;break;
			case 2400:T = true;break;
			case 4800:T = true;break;
			case 9600:T = true;break;
			case 14400:T = true;break;
			case 19200:T = true;break;
			case 38400:T = true;break;
			case 43000:T = true;break;
			case 57600:T = true;break;
			case 76800:T = true;break;
			case 115200:T = true;break;
			case 128000:T = true;break;
			case 230400:T = true;break;
			case 256000:T = true;break;
			case 460800:T = true;break;
			case 921600:T = true;break;
			case 1382400:T = true;break;
			default:T = false;
		}
		return T;
	}
	public static boolean Sw(String a){
		//判断数据位
		if(((sTurnd(a)>=5)&&(sTurnd(a)<=8))){
			return true;
		}else{
			return false;
		}
	}
	public static boolean Jw(String a){
		//判断校验位
		if(a.equals("N")||a.equals("O")||a.equals("E")){
			return true;
		}else{
			return false;
		}
	}
	public static boolean Tw(String a){
		//判断停止位
		double b = sTurnd(a);
		if(b==1||b==1.5||b==2)
			return true;
		else
			return false;
	}
	public static boolean Lk(String a){
		//判断流控
		boolean T;
		switch(a){
			case "N": T = true; break;
			case "R": T = true; break;
			case "D": T = true; break;
			case "RD": T = true; break;
			default :T = false;
		}
		return T;
	}
	public static boolean Cd(String a){
		double b = sTurnd(a);
		if(b<=255&&b>=1)
			return true;
		else
			return false;
	}
	public String errors(){
		return report;
	}
}
