package View;


public class CeWenTable {
	public  String toTempTxt(String[][]data1,String[][][] data,String [][][] data2) {
		StringBuffer t = new StringBuffer();
		// 1.sheet ��
		int shigong = 0;// ���� ʩ����ͼ��¼�� ���ڵ�һά��������shigong
		int wenzhao = 0;// ���� ���´������Ͳ��¼��������ձ� ���ڵ�һά��������wenzhao
		int wenpei = 0;// ���� ���¼������ֳ�ʩ�����ü�¼ ���ڵ�һά��������wenpei
		for (int i = 0; i < data.length; i++) {
			if (data[i][0][0].equals("ʩ����ͼ��¼��")) {
				shigong = i;
			}
			if (data[i][0][0].equals("���´������Ͳ��¼��������ձ�")) {
				wenzhao = i;
			}
			if (data[i][0][0].equals("���¼������ֳ�ʩ�����ü�¼")) {
				wenpei = i;
			}
		}
		// 2.��ͷ ��
		  // 2.1 ���´������Ͳ��¼��������ձ�
		int wenchuanID = 0;// ���´�����ID����ֵ������
		int wenjiID = 0;// ���¼�����ID����ֵ
		int wendiID = 0;// ���´������ڲ��¼������е�ַ����ֵ
		for (int j = 0; j < 30; j++) {
			if (data[wenzhao][3][j] == null)
				break;
				if (data[wenzhao][3][j].equals("���´�����ID")) {
					wenchuanID = j;
				}
				if (data[wenzhao][3][j].equals("���¼�����ID")) {
					wenjiID = j;
				}
				if (data[wenzhao][3][j].equals("���´������ڲ��¼������е�ַ")) {
					wendiID = j;
				}
			
		}
		  //2.2���¼������ֳ�ʩ�����ü�¼
		int wenjiID_ = 0; //�豸ΨһID���������ʹ�ã�������
		int sheBEI = 0; //�豸�ͺ�������
		for(int j = 0;j < 30;j++){
			if (data[wenpei][1][j] == null) 
				break;
				if (data[wenpei][1][j].equals("�豸ΨһID���������ʹ�ã�")) {
					wenjiID_ = j;
				}
				if (data[wenpei][1][j].equals("�豸�ͺ�")) {
					sheBEI = j;
				}
		}
		//2.3 ʩ����ͼ�е��豸ΨһID
		int ID = 0;//�ɼ��豸ΨһID������
		for(int j = 0;j < 30;j++){
			if (data[shigong][6][j] == null) 
				break;
				if (data[shigong][6][j].equals("�ɼ��豸ΨһID")) {
					ID= j;
				}
		}
		//3.��ȡʩ����¼�еĹؼ�����
		  //3.1��ȡ���´�����ΨһID 
		String F_ID [] = new String[30]; //���´������Ĳɼ��豸ΨһID
		int k = 0;
		for(int i=0;i<data[shigong].length;i++){
			if(data[shigong][i][ID] != null&&data[shigong][i][ID].getBytes().length == 8){
				double a = Double.parseDouble(data[shigong][i][ID]);
				if(200000<=a&&a<300000){
					F_ID[k] = data[shigong][i][ID];
					k++;
				}
			}
		}
		  //3.2��ȡ���¼������ֳ�ʩ�����ü�¼ �ؼ�����
		String CEWEN_JI[][] = new String[30][2];
		for(int i=2;i<data[wenpei].length;i++){
				if(data[wenpei][i][wenjiID_] != null &&data[wenpei][i][sheBEI] != null){
					CEWEN_JI[i-2][0] = data[wenpei][i][wenjiID_];
					CEWEN_JI[i-2][1] = data[wenpei][i][sheBEI];
				}
			}
		//3.3��ȡ ���´������Ͳ��¼��������ձ� �ؼ�����
		String CEWEN_ZHAO[][] = new String[30][3];
		for(int i=4;i<data[wenzhao].length;i++){
			if(data[wenzhao][i][wenchuanID] != null &&data[wenzhao][i][wenjiID] != null&&data[wenzhao][i][wendiID] != null){
				CEWEN_ZHAO[i-4][0] = data[wenzhao][i][wenchuanID];
				CEWEN_ZHAO[i-4][1] = data[wenzhao][i][wenjiID];
				CEWEN_ZHAO[i-4][2] = data[wenzhao][i][wendiID];
			}
		}
		//4.���ݹ���������֯����
		String result[] = new String[100];
		int f = 0;
		for(int i=0;i<F_ID.length;i++){
			if(F_ID[i] == null) break;
			for(int j=0;j<CEWEN_ZHAO.length;j++){
				if(CEWEN_ZHAO[j][0] == null) break;
				double q = Double.valueOf(F_ID[i]);
				double p = Double.valueOf(CEWEN_ZHAO[j][0]);
				if(p==q){
					// F_ID[i] CEWEN_ZHAO[j][0] Ψһid
					//CEWEN_ZHAO[j][1]������ id
					//CEWEN_ZHAO[j][2] ��ַ
					result[f] = F_ID[i]+","+CEWEN_ZHAO[j][1]+",";
					for(int a=0;a<CEWEN_JI.length;a++){
						if(CEWEN_JI[a][0] == null) break;
						if(CEWEN_ZHAO[j][1].equals(CEWEN_JI[a][0])){
							//CEWEN_JI[a][1] ���������
							result[f] = F_ID[i]+","+CEWEN_ZHAO[j][1]+",";
							for(int b =0;b<data2.length;b++){
								if(data2[b][0][0] == null) break;
								String []res = new String[100];
								String dd = "";
								int max = -1;
								if(data2[b][0][0].equals(CEWEN_JI[a][1])){
									for(int c=2;c<data2[b].length;c++){
										if(data2[b][c][0] == null) break;
										for(int d=1;d<data1[0].length;d++){	
											if(data1[0][d] == null) break;
											//System.out.print(data1[0][d]+"******"+data2[b][c][5]);System.out.println();
											if(data1[0][d].startsWith(data2[b][c][5])){
												//System.out.println(data1[0][d]+"$$$"+data2[b][c][5]);
												//System.out.println(d);
												double di =  Double.valueOf(CEWEN_ZHAO[j][2])+ Double.valueOf(data2[b][c][3])-1;
												 dd= String.valueOf(di)+"/"+data2[b][c][7]+"/"+data2[b][c][7]+"/"+data2[b][c][6]+",";
												//System.out.println(di+"/"+data2[b][c][7]+"/"+data2[b][c][7]+"/"+data2[b][c][6]+",");
												res[d] =dd; 
												if(d>max) max = d;
												break;
											}
										}
									}
								}
								for(int y=0;y<=max;y++){
									if(res[y]==null){
										result[f]+=",";
									}else{
										result[f]+=res[y];
									}
								}
							}
						}
					}
					f++;
				}
			}
		}
		for(int i=0;i<result.length;i++){
			if(result[i]==null)break;
			t.append(result[i]);
			t.append("\r\n");
		}
		return t.toString();
	}

}
