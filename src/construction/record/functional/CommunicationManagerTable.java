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
		int id = searchTitle(array,"通信管理机编号");//通信管理机编号
		int terminal = searchTitle(array,"C端/S端");//C端/S端
		int pcId = searchTitle(array,"PC编号(如是C端)"); //测温传感器在测温集中器中地址
		int scNum = searchTitle(array,"串行通道数量"); //测温传感器在测温集中器中地址
		int mapRelation = searchTitle(array,"总线/IP/端口映射关系"); //测温传感器在测温集中器中地址
		
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
		//查找 表头的列值
		int num = 0;
		for(int i=0;i<array[beginNum].length;i++){
			if(array[beginNum][i] == null) break;
			if(array[beginNum][i].contains(titleName)){
				num = i;
			}
		}
		return num;
	}
	
	//2.1 通信管理机 的 总线/IP/端口映射关系 列需要特殊处理,返回的三维数组是某行某列 的1 2 3 com ip  端口
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
		
		//串口编号/IP地址/端口 分割
		public static String[] ComIpSplit(String str){
			String splitArray[] = {};
			if((str != null)&&(!isEquals(str,"-"))){
					splitArray = str.split("/");
			}	
			return splitArray;
		}
		
		//判断字符串a 是否与 字符串b 相等
		public static boolean isEquals(String a,String b){
			return a.replaceAll("\n", "").replaceAll(" ", "").equals(b);
		}
}
