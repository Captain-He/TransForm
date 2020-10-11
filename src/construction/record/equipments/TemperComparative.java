package construction.record.equipments;

public class TemperComparative {

	private int wenId;
	private int jiId;
	private int address;
	
	public void setWenId(String wenId){
		this.wenId = (int)Double.parseDouble(wenId);
	}
	
	public int getWenId(){
		return wenId; 
	}
	
	public void setJiId(String jiId){
		this.jiId = (int)Double.parseDouble(jiId);
	}
	public int getJiId(){
		return jiId;
	}
	public void setAddress(String address){
		this.address = (int)Double.parseDouble(address);
	}
	public int getAddress(){
		return address;
	}
	

}
