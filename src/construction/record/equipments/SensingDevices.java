package construction.record.equipments;

public class SensingDevices {
    /*
     * �������豸 �࣬�ǵ����Ǳ� �� ���¼����� �� ����
     * created in 2019 09 15
     * 
	 */
	
	private int id ;//�ɼ��豸ΨһID
	private String accessType; //���뷽ʽ(��̫��/����)
	private String modbusType; //ͨ��Э��(modbusTCP/modbusRTU)
	private int underNum; //������ͨ�Ź�������
	private String comNum; //���ڱ��
	private int bpsNum; //������
	private int dataBit; //����λ
	private char checkBit; //У��λ
	private float stopBit; //ֹͣλ
	private char fluidControl;//����
	private int slaveAddress; //��վ��ַ
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setAccessType(String accessType){
		this.accessType = accessType;
	}
	public String getAccessType(){
		return accessType;
	}
	public void setModbusType(String modbusType){
		this.modbusType = modbusType;
	}
	public String getModbusType(){
		return modbusType;
	}

	public void setUnderNum(int underNum){
		this.underNum = underNum;
	}
	public int getUnderNum(){
		return underNum;
	}
	public void setComNum(String comNum){
		this.comNum = comNum;
	}
	public String getComNum(){
		return comNum;
	}
	public void setBpsNum(int bpsNum){
		this.bpsNum = bpsNum;
	}
	public int getBpsNum(){
		return bpsNum;
	}
	public void setDataBit(int dataBit){
		this.dataBit = dataBit;
	}
	public int getDataBit(){
		return dataBit;
	}
	public void setCheckBit(char checkBit){
		this.checkBit = checkBit;
	}
	public char getCheckBit(){
		return checkBit;
	}
	public void setStopBit(float stopBit){
		this.stopBit = stopBit;
	}
	public float getStopBit(){
		return stopBit;
	}
	public void setFluidControl(char fluidControl){
		this.fluidControl = fluidControl;
	}
	public char getFluidControl(){
		return fluidControl;
	}
	public void setSlaveAddress(int slaveAddress){
		this.slaveAddress= slaveAddress;
	}
	public int getSlaveAddress(){
		return slaveAddress;
	}
}
