package construction.record.equipments;

public class CommunicationManager {
	private int id; //ͨ�Ź�������
	private String terminal; //C��/S��
	private int pcId; //PC���(����C��)
	private int scNum;//����ͨ������
	private String mapRelation[][]; //����/IP/�˿�ӳ���ϵ
	
	public void setId(String id){
		this.id = (int)Double.parseDouble(id);
	}
	public int getId(){
		return id;
	}
	public void setTerminal(String terminal){
		this.terminal = terminal;
	}
	public String getTerminal(){
		return terminal;
	}
	public void setPcId(String pcId){
		this.pcId = (int)Double.parseDouble(pcId);
	}
	public int getPcId(){
		return pcId;
	}
	public void setScNum(String scNum){
		this.scNum = (int)Double.parseDouble(scNum);
	}
	public int getScNum(){
		return scNum;
	}
	public void setMapRelation(String mapRelation [][]){
		this.mapRelation = mapRelation;
	}
	public String [][]getMapRelation(){
		return mapRelation;
	}
}
