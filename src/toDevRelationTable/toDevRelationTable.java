package toDevRelationTable;

public class toDevRelationTable {
	public  String toDevRelationTableTxt(String[][][]data,String str){
		// 1.��ͷ ��
		int id= 0;// �ɼ��豸ΨһID
		int type = 0;// �ɼ��豸����
		int location = 0;// ����λ��
		int dev = 0;//�豸�ͺ�
		int cell = 0;//���ܵ�Ԫ
		for (int j = 0; j < data[0].length; j++) {
			if (data[0][6][j] == null)
				break;
				if (data[0][6][j].equals("�ɼ��豸ΨһID")) {
					id = j;
				}
				if (data[0][6][j].equals("�ɼ��豸����")) {
					type = j;
				}
				if (data[0][6][j].equals("����λ��")) {
					location = j;
				}
				if (data[0][6][j].equals("�豸�ͺ�")) {
					dev = j;
				}
				if (data[0][6][j].equals("���ܵ�Ԫ")) {
					cell = j;
				}
			
		}
		String strArray[] = str.split("\t\n");
		//2.��ȡ��Ҫ����  �ɼ��豸ID ��ʱ�����ܵ�Ԫ����
		String result [] = new String[data[0].length];
		for(int i=7;i<data[0].length;i++){
			if(data[0][i][0] == null||data[0][i][0].length()<=0) break;
			String a = "";
			for(int j=0;j<strArray.length;j++){
				String strArrayCell[] = strArray[j].split(",");
				if(data[0][i][cell].equals(strArrayCell[1])){
					a+=data[0][i][id]+","+data[0][i][type]+","+strArrayCell[0]+","+data[0][i][location]+","+data[0][i][dev]+",";
					result[i-7] = a;
				}
			}
		}
		StringBuffer t = new StringBuffer();
		for(int i=0;i<result.length;i++){
			if(result[i] == null||result[i].length()<=0) break;
			t.append(result[i]);
			t.append("\t\n");
		}
		
		return t.toString();
	} 
}
