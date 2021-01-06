package convertToTxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import construction.record.equipments.CommunicationManager;
import construction.record.functional.CommunicationManagerTable;
import writeFile.WriteFile;

public class DpuList {
	
	/*ͨ�Ź������(dpu_list)
	  ͨ�Ź�������
	+ C��/S��
	+ ͨ������
	+ {���ڱ��/IP��ַ/�˿�}*/

	public void toTxt(String array[][][],String savePath){
		CommunicationManagerTable cm = new CommunicationManagerTable();
		List <CommunicationManager> cmlist = cm.getList(array);
		StringBuffer content = new StringBuffer();
		for(CommunicationManager s:cmlist){
			String str = s.getId()+" "+s.getTerminal();
			String arr[][] = s.getMapRelation();
			for(int i=0;i<arr.length;i++){
				if(arr[i][0] == null) continue;
				str+=" "+arr[i].length;
				for(int j=0;j<arr[i].length;j++){
					if(arr[i][j] == null) continue;
					str+=" "+arr[i][j];
				}
				str+="\n";
				content.append(str);
			}
			WriteFile writefile = new WriteFile();
			try {
				writefile.WriteToFile(content.toString().trim(), savePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
