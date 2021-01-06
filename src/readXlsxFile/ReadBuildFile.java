package readXlsxFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import readXlsxFile.ReadTables;

public class ReadBuildFile {

	static final int max_X = 10000;//������ÿ�ű����������ֵ
	static final int max_Y = 100;//������ÿ�ű����������ֵ

	public static int chuanganqi = 0;// ���� ʩ����ͼ��¼�� ���ڵ�һά��������chuanganqi
	public static int wenzhao = 0;// ���� ���´������Ͳ��¼��������ձ� ���ڵ�һά��������wenzhao
	public static int jizhongqi = 0;// ���� ���¼������ֳ�ʩ�����ü�¼ ���ڵ�һά��������jizhongqi
	public static int dianli = 0;// ���� �����Ǳ� ���ڵ�һά��������dianli
	public static int caiji = 0;// ���� �ɼ�PC�ֳ�ʩ�����ü�¼ ���ڵ�һά��������wenpei
	public static int tongguan = 0;  //���� ͨ�Ź������ ���ڵ�һά��������tongguan

	public static String[][][] BuildTable; //����ʩ����¼�ļ�
	//������Ϣ�洢
	public static String report="errors:\r\n"; //������Ϣ�洢

	// ��ȡʩ����¼����������
	public  static String [][][] readTables(String dir) {

		//��ȡ ����ʩ����¼�ļ�
		ReadTables readbuildtable = new ReadTables();
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		wb = readbuildtable.readExcel(dir);
		int sheetNum = wb.getNumberOfSheets(); // sheet����
		String[][][] array = new String[sheetNum][max_X][max_Y];
		if (wb != null) {
			// ��ȡ��һ��sheet
			for (int i = 0; i < sheetNum; i++) {
				sheet = wb.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();// ��ȡ�������
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
		//��������ʩ����¼�ļ�������λ���� BuildTable;

		// 1.sheet ��

		for (int i = 0; i < BuildTable.length; i++) {
			if (BuildTable[i][0][0].equals("������")) {
				chuanganqi = i;
			}
			if (BuildTable[i][0][0].equals("�������ͼ��������ռ����λ��")) {
				wenzhao = i;
			}
			if (BuildTable[i][0][0].equals("������")) {
				jizhongqi = i;
			}
			if (BuildTable[i][0][0].equals("ͨ�Ź����")) {
				tongguan= i;
			}
	/*		if (BuildTable[i][0][0].equals("�����Ǳ��ֳ�ʩ�����ü�¼��")) {
				dianli = i;
			}*/
			if(BuildTable[i][0][0].equals("�ɼ�������")){
				caiji = i;
			}
		}//����sheet������
	/*	table1();
		table2();
		table3();
		table4();
		table5();
		table6();*/
		return array;
	}

	public static void table1(){
		//����ʩ����ͼ
		String array[][] = BuildTable[chuanganqi];
		//1.���ұ�ͷ
		int title1 = 0;//�����  ������
		int title2 = 0;//���/����  ������
		int title3 = 0;//���ܵ�Ԫ  ������
		int title4 = 0;//�ɼ��豸����  ������
		int title5 = 0;//�豸�ͺ�  ������
		int title6 = 0;//����λ��  ������
		int title7 = 0;//�ɼ��豸ΨһID  ������
		//��ͷ �̶��ڵ����� ���Դӵ����б���
		for(int j = 0;j <array[6].length;j++){
			if (array[6][j] == null)
				break;
			if (slim(array[6][j],"�ɼ��豸����")) {
				title4= j;
			}
			if (slim(array[6][j],"�ɼ��豸ΨһID")) {
				title7= j;
			}
		}
		//2.������ݷ�Χ ��ʩ����ͼ��ֻ�вɼ��豸ΨһID���˷�Χ �����ݴӵڰ��п�ʼ
		for(int i=7;i<array.length;i++){
			if (array[i][title4] == null) break;
			if(slim(array[i][title4],"���´�����")){
				if(!((sTurnd(array[i][title7])>=200001)&&(sTurnd(array[i][title7])<=299999))){
					report +="ʩ����¼�ļ�-ʩ����ͼ��¼��:��  "+(i+1)+"��  �� "+(title7+1)+" �����ݴ���\r\n";
				}
			}else{
				if(!((sTurnd(array[i][title7])>=100001)&&(sTurnd(array[i][title7])<=199999))){
					report +="ʩ����¼�ļ�-ʩ����ͼ��¼��:��  "+(i+1)+"�е� "+title7+" �����ݴ���\r\n";
				}
			}
		}
		//return array;
	}
	public static void table2(){
		//����ͨ�Ź������
		String array[][] = BuildTable[tongguan];
		//1.���ұ�ͷ
		int title1 = 0;//ͨ�Ź�������  ������
		int title2 = 0;//��װλ��  ������
		int title3 = 0;//C��/S��  ������
		int title4 = 0;//PC���(����C��)  ������
		int title5 = 0;//����ͨ������ ������
		int title6 = 0;//����/IP/�˿�ӳ���ϵ  ������
		//��ͷ �̶��ڵڶ��� ���Դӵڶ��б���
		for(int j = 2;j <array[2].length;j++){
			if (array[2][j] == null)
				break;
			if (slim(array[2][j],"ͨ�Ź�������")) {
				title1= j;
			}
			if (slim(array[2][j],"��װλ��")) {
				title2= j;
			}
			if (slim(array[2][j],"C��/S��")) {
				title3= j;
			}
			if (slim(array[2][j],"PC���(����C��)")) {
				title4= j;
			}
			if (slim(array[2][j],"����ͨ������")) {
				title5= j;
			}
			if (slim(array[2][j],"����/IP/�˿�ӳ���ϵ")) {
				title6= j;
			}
		}
		//2.������ݷ�Χ �����ݴӵ����п�ʼ
		for(int i=3;i<array.length;i++){
			if (array[i][title1] == null) break;
			//System.out.println(array[i][title4] +"--------"+array[i][title7]);
			if(!((sTurnd(array[i][title1])>=1001)&&(sTurnd(array[i][title1])<=1999))){
				report +="ʩ����¼�ļ�-ͨ�Ź������:��  "+(i+1)+"��  �� "+(title1+1)+" �����ݴ���\r\n";
			}
			if(!((sTurnd(array[i][title4])>=001)&&(sTurnd(array[i][title4])<=999))){
				report +="ʩ����¼�ļ�-ͨ�Ź������:��  "+(i+1)+"��  �� "+(title4+1)+" �����ݴ���\r\n";
			}
			for(int j=title6;j<array[i].length;j++){
				if(array[i][j] == null||slim(array[i][j],"-")) break;
				String a[] = array[i][j].split("/");
				String b[] = a[1].split("\\.");
				double c = sTurnd(a[0].substring(a[0].length()-1,a[0].length()));
				if(!((c>=0)&&(c<=16))){
					report +="ʩ����¼�ļ�-ͨ�Ź������:��  "+(i+1)+"��  �� "+(j+1)+" �д��ڱ�����ݴ���\r\n";
				}
				if(!((sTurnd(b[0])>=0)&&(sTurnd(b[0])<=255))){
					report +="ʩ����¼�ļ�-ͨ�Ź������:��  "+(i+1)+"��  �� "+(j+1)+" ��IP���ݴ���\r\n";
				}
				if(!((sTurnd(b[1])>=0)&&(sTurnd(b[1])<=255))){
					report +="ʩ����¼�ļ�-ͨ�Ź������:��  "+(i+1)+"��  �� "+(j+1)+" ��IP���ݴ���\r\n";
				}
				if(!((sTurnd(b[2])>=0)&&(sTurnd(b[2])<=255))){
					report +="ʩ����¼�ļ�-ͨ�Ź������:��  "+(i+1)+"��  �� "+(j+1)+" ��IP���ݴ���\r\n";
				}
				if(!((sTurnd(b[3])>=0)&&(sTurnd(b[3])<=255))){
					report +="ʩ����¼�ļ�-ͨ�Ź������:��  "+(i+1)+"��  �� "+(j+1)+" ��IP���ݴ���\r\n";
				}
				if(!((sTurnd(a[2])>=0)&&(sTurnd(a[2])<=65535))){
					report +="ʩ����¼�ļ�-ͨ�Ź������:��  "+(i+1)+"��  �� "+(j+1)+" �ж˿����ݴ���\r\n";
				}
			}
		}
		//return array;
	}
	public static void table3(){
		//���ص����Ǳ�
		String array[][] = BuildTable[dianli];
		//1.���ұ�ͷ
		int title1 = 0;//�ɼ��豸ΨһID ������
		int title2 = 0;//���뷽ʽ(��̫��/����)  ������
		int title3 = 0;//ͨ��Э��  ������
		int title4 = 0;//������ͨ�Ź�������  ������
		int title5 = 0;//���ڱ�� ������
		int title6 = 0;//������  ������
		int title7 = 0;//����λ ������
		int title8 = 0;//У��λ ������
		int title9 = 0;//ֹͣλ ������
		int title10 = 0;//���� ������
		int title11 = 0;//��վ��ַ ������
		//��ͷ �̶��ڵ����� ���Դӵ����б���
		for(int j = 0;j <array[3].length;j++){
			if (array[3][j] == null)
				break;
			if (slim(array[3][j],"�ɼ��豸ΨһID")) {
				title1= j;
			}
			if (slim(array[3][j],"���뷽ʽ(��̫��/����)")) {
				title2= j;
			}
			if (slim(array[3][j],"ͨ��Э��(modbusTCP/modbusRTU)")) {
				title3= j;
			}
			if (slim(array[3][j],"������ͨ�Ź�������")) {
				title4= j;
			}
			if (slim(array[3][j],"���ڱ��")) {
				title5= j;
			}
			if (slim(array[3][j],"������")) {
				title6= j;
			}
			if (slim(array[3][j],"����λ")) {
				title7= j;
			}
			if (slim(array[3][j],"У��λ")) {
				title8= j;
			}
			if (slim(array[3][j],"ֹͣλ")) {
				title9= j;
			}
			if (slim(array[3][j],"����")) {
				title10= j;
			}
			if (slim(array[3][j],"��վ��ַ")) {
				title11= j;
			}

		}
		//2.������ݷ�Χ ���ݴӵ����п�ʼ
		for(int i=4;i<array.length;i++){
			if (array[i][title1] == null) break;
			if(!DLid(array[i][title1])){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title1+1)+" �����ݴ���\r\n";
			}
			if(!Jr(array[i][title2])){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title2+1)+" ����555�ݴ���\r\n";
			}
			if(!((array[i][title3].equals("modbusTCP"))||(array[i][title3].equals("modbusRTU")))){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title3+1)+" �����ݴ���\r\n";
			}
			if(!((sTurnd(array[i][title4])>=1001)&&(sTurnd(array[i][title4])<=1999))){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title4+1)+" �����ݴ���\r\n";
			}
			double c = sTurnd(array[i][title5].substring(array[i][title5].length()-1,array[i][title5].length()));
			if(!((c>=0)&&(c<=16))){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title5+1)+" �д��ڱ�����ݴ���\r\n";
			}
			if(!Bt(array[i][title6])){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title6+1)+" �����ݴ���\r\n";
			}
			if(!Sw(array[i][title7])){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title7+1)+" �����ݴ���\r\n";
			}
			if(!Jw(array[i][title8])){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title8+1)+" �����ݴ���\r\n";
			}
			if(!Tw(array[i][title9])){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title9+1)+" �����ݴ���\r\n";
			}
			if(!Lk(array[i][title10])){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title10+1)+" �����ݴ���\r\n";
			}
			if(!Cd(array[i][title11])){
				report +="ʩ����¼�ļ�-�����Ǳ�:��  "+(i+1)+"��  �� "+(title11+1)+" �����ݴ���\r\n";
			}
		}
		//return array;
	}
	public static void table4(){
		//���ز��¼�������
		String array[][] = BuildTable[wenzhao];
		//1.���ұ�ͷ
		int title1 = 0;//�ɼ��豸ΨһID ������
		int title2 = 0;//���뷽ʽ(��̫��/����)  ������
		int title3 = 0;//ͨ��Э��  ������
		int title4 = 0;//������ͨ�Ź�������  ������
		int title5 = 0;//���ڱ�� ������
		int title6 = 0;//������  ������
		int title7 = 0;//����λ ������
		int title8 = 0;//У��λ ������
		int title9 = 0;//ֹͣλ ������
		int title10 = 0;//���� ������
		int title11 = 0;//��վ��ַ ������
		//��ͷ �̶��ڵ����� ���Դӵ����б���
		for(int j = 0;j <array[1].length;j++){
			if (array[1][j] == null)
				break;
			if (slim(array[1][j],"�豸ΨһID���������ʹ�ã�")) {
				title1= j;
			}
			if (slim(array[1][j],"���뷽ʽ")) {
				title2= j;
			}
			if (slim(array[1][j],"ͨ��Э��(modbusTCP/modbusRTU)")) {
				title3= j;
			}
			if (slim(array[1][j],"������ͨ�Ź�������")) {
				title4= j;
			}
			if (slim(array[1][j],"���ڱ��")) {
				title5= j;
			}
			if (slim(array[1][j],"������")) {
				title6= j;
			}
			if (slim(array[1][j],"����λ")) {
				title7= j;
			}
			if (slim(array[1][j],"У��λ")) {
				title8= j;
			}
			if (slim(array[1][j],"ֹͣλ")) {
				title9= j;
			}
			if (slim(array[1][j],"����")) {
				title10= j;
			}
			if (slim(array[1][j],"��վ��ַ")) {
				title11= j;
			}

		}
		//2.������ݷ�Χ ���ݴӵ����п�ʼ
		for(int i=2;i<array.length;i++){
			if (array[i][title1] == null) break;
			if(!WJid(array[i][title1])){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title1+1)+" �����ݴ���\r\n";
			}
			if(!Jr(array[i][title2])){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title2+1)+" ����555�ݴ���\r\n";
			}
			if(!((array[i][title3].equals("modbusTCP"))||(array[i][title3].equals("modbusRTU")))){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title3+1)+" �����ݴ���\r\n";
			}
//			if(!((sTurnd(array[i][title4])>=1001)&&(sTurnd(array[i][title4])<=1999))){
//				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title4+1)+" �����ݴ���\r\n";
//			}
			double c = sTurnd(array[i][title5].substring(array[i][title5].length()-1,array[i][title5].length()));
			if(!((c>=0)&&(c<=16))){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title5+1)+" �д��ڱ�����ݴ���\r\n";
			}
			if(!Bt(array[i][title6])){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title6+1)+" �����ݴ���\r\n";
			}
			if(!Sw(array[i][title7])){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title7+1)+" �����ݴ���\r\n";
			}
			if(!Jw(array[i][title8])){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title8+1)+" �����ݴ���\r\n";
			}
			if(!Tw(array[i][title9])){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title9+1)+" �����ݴ���\r\n";
			}
			if(!Lk(array[i][title10])){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title10+1)+" �����ݴ���\r\n";
			}
			if(!Cd(array[i][title11])){
				report +="ʩ����¼�ļ�-���¼�������:��  "+(i+1)+"��  �� "+(title11+1)+" �����ݴ���\r\n";
			}
		}
		//return array;
	}
	public static void table5(){
		//���ش������Ͳ��¼��������ձ�
		String array[][] = BuildTable[wenzhao];
		//1.���ұ�ͷ
		int title1 = 0;//���´�����ID ������
		int title2 = 0;//���¼�����ID  ������
		int title3 = 0;//���´������ڲ��¼������е�ַ  ������

		//��ͷ �̶��ڵ����� ���Դӵ����б���
		for(int j = 0;j <array[3].length;j++){
			if (array[3][j] == null)
				break;
			if (slim(array[3][j],"���´�����ID")) {
				title1= j;
			}
			if (slim(array[3][j],"���¼�����ID")) {
				title2= j;
			}
			if (slim(array[3][j],"���´������ڲ��¼������е�ַ")) {
				title3= j;
			}

		}
		//2.������ݷ�Χ ���ݴӵ����п�ʼ
		for(int i=4;i<array.length;i++){
			if (array[i][title1] == null) break;
			if(!WCid(array[i][title1])){
				report +="ʩ����¼�ļ�-���´������Ͳ��¼��������ձ�:��  "+(i+1)+"��  �� "+(title1+1)+" �����ݴ���\r\n";
			}
			if(!WJid(array[i][title2])){
				report +="ʩ����¼�ļ�-���´������Ͳ��¼��������ձ�:��  "+(i+1)+"��  �� "+(title2+1)+" �����ݴ���\r\n";
			}
			if(!WD(array[i][title3])){
				report +="ʩ����¼�ļ�-���´������Ͳ��¼��������ձ�:��  "+(i+1)+"��  �� "+(title3+1)+" �����ݴ���\r\n";
			}
		}
		//return array;
	}
	public static void table6(){
		//���زɼ�PC��
		String array[][] = BuildTable[caiji];
		//1.���ұ�ͷ
		int title1 = 0;//�豸��ţ��Զ���Ψһ��ţ� ������
		int title2 = 0;//IP��ַ  ������
		int title3 = 0;//�˿ں� ������

		//��ͷ �̶��ڵ����� ���Դӵ����б���
		for(int j = 0;j <array[3].length;j++){
			if (array[3][j] == null)
				break;
			if (slim(array[3][j],"�豸��ţ��Զ���Ψһ��ţ�")) {
				title1= j;
			}
			if (slim(array[3][j],"IP��ַ")) {
				title2= j;
			}
			if (slim(array[3][j],"�˿ں�")) {
				title3= j;
			}

		}
		//2.������ݷ�Χ ���ݴӵ����п�ʼ
		for(int i=4;i<array.length;i++){
			if (array[i][title1] == null) break;
			if(!PCid(array[i][title1])){
				report +="ʩ����¼�ļ�-�ɼ�PC��:��  "+(i+1)+"��  �� "+(title1+1)+" �����ݴ���\r\n";
			}
			if(!IP(array[i][title2])){
				report +="ʩ����¼�ļ�-�ɼ�PC��:��  "+(i+1)+"��  �� "+(title2+1)+" �����ݴ���\r\n";
			}
			if(!DKnumber(array[i][title3])){
				report +="ʩ����¼�ļ�-�ɼ�PC��:��  "+(i+1)+"��  �� "+(title3+1)+" �����ݴ���\r\n";
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
		//�жϲ��¼������� ΨһID �Ƿ���ȷ
		double b = sTurnd(a);
		if(b<=399999&&b>=300001)
			return true;
		else
			return false;
	}
	public static boolean WCid(String a){
		//���´�����IDΨһID �Ƿ���ȷ
		double b = sTurnd(a);
		if(b<=299999&&b>=200001)
			return true;
		else
			return false;
	}
	public static boolean DLid(String a){
		//�жϵ����Ǳ�� ΨһID �Ƿ���ȷ
		double b = sTurnd(a);
		if(b<=199999&&b>=100001)
			return true;
		else
			return false;
	}
	public static boolean PCid(String a){
		//���´������ڲ��¼������е�ַ  �Ƿ���ȷ
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
		//���´������ڲ��¼������е�ַ  �Ƿ���ȷ
		double b = sTurnd(a);
		if(b<=65535&&b>=0)
			return true;
		else
			return false;
	}
	public static boolean Jr(String a){
		//�жϽ��뷽ʽ
		String b = a.replaceAll("\n", "").replaceAll(" ", "");
		if(b.equals("����")||b.equals("��̫��"))
			return true;
		else
			return false;
	}
	public static boolean Bt(String a){
		//�жϲ�����
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
		//�ж�����λ
		if(((sTurnd(a)>=5)&&(sTurnd(a)<=8))){
			return true;
		}else{
			return false;
		}
	}
	public static boolean Jw(String a){
		//�ж�У��λ
		if(a.equals("N")||a.equals("O")||a.equals("E")){
			return true;
		}else{
			return false;
		}
	}
	public static boolean Tw(String a){
		//�ж�ֹͣλ
		double b = sTurnd(a);
		if(b==1||b==1.5||b==2)
			return true;
		else
			return false;
	}
	public static boolean Lk(String a){
		//�ж�����
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
