package construction.record.functional;

import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.CommunicationManager;

public class CommunicationManagerTable {
	private static int sheetNum = 1;
	private static int beginNum = 3;
	public List <CommunicationManager> getList(String arr[][][]){
		String array[][] = arr[sheetNum];
		List <CommunicationManager> list = new ArrayList<CommunicationManager>();
		int id = searchTitle(array,"ͨ�Ź�������");//ͨ�Ź�������
		int terminal = searchTitle(array,"C��/S��");//C��/S��
		int pcId = searchTitle(array,"PC���(����C��)"); //���´������ڲ��¼������е�ַ
		int scNum = searchTitle(array,"����ͨ������"); //���´������ڲ��¼������е�ַ
		int mapRelation = searchTitle(array,"����/IP/�˿�ӳ���ϵ"); //���´������ڲ��¼������е�ַ
		
		for(int i= beginNum+1;i<array.length;i++){
			if(array[i][id] == null) continue;
			CommunicationManager object = new CommunicationManager();
			object.setId(array[i][id]);
			object.setTerminal(array[i][terminal]);
			object.setPcId(array[i][pcId]);
			object.setScNum(array[i][scNum]);
			object.setMapRelation(communicationManagerComIp(array,mapRelation,i));
			list.add(object);
		}
		return list;
	}
	public static int searchTitle(String array[][],String titleName){
		//���� ��ͷ����ֵ
		int num = 0;
		for(int i=0;i<array[beginNum].length;i++){
			if(array[beginNum][i] == null) break;
			if(array[beginNum][i].contains(titleName)){
				num = i;
			}
		}
		return num;
	}
	
	//2.1 ͨ�Ź���� �� ����/IP/�˿�ӳ���ϵ ����Ҫ���⴦��,���ص���ά������ĳ��ĳ�� ��1 2 3 com ip  �˿�
		public String[][] communicationManagerComIp(String searchedArray[][],int itemNum,int rowNum){
			String [][] array = new String[100][100];
				String recept = "";
				for(int j=itemNum;j<searchedArray[rowNum].length;j++){
					if(searchedArray[rowNum][j] == null||searchedArray[rowNum][j].length()==0) break;
					recept+=searchedArray[rowNum][j]+",";
				}
				 array[rowNum-beginNum] = recept.split(",");
			return array;
		}
		
		//���ڱ��/IP��ַ/�˿� �ָ�
		public static String[] ComIpSplit(String str){
			String splitArray[] = {};
			if((str != null)&&(!isEquals(str,"-"))){
					splitArray = str.split("/");
			}	
			return splitArray;
		}
		
		//�ж��ַ���a �Ƿ��� �ַ���b ���
		public static boolean isEquals(String a,String b){
			return a.replaceAll("\n", "").replaceAll(" ", "").equals(b);
		}
}
