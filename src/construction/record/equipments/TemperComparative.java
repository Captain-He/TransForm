package construction.record.equipments;

public class TemperComparative {

	private int wenId;
	private int jiId;
	private int firstRelativeOffsetAddress;
	private int S;
    private int I;
	public void setJiId(String jiId){
		this.jiId = (int)Double.parseDouble(jiId);
	}
	public int getJiId(){
		return jiId;
	}

	public int getFirstRelativeOffsetAddress() {
		return firstRelativeOffsetAddress;
	}

	public void setFirstRelativeOffsetAddress(String firstRelativeOffsetAddress) {
		this.firstRelativeOffsetAddress = (int)Double.parseDouble(firstRelativeOffsetAddress);
	}

	public int getS() {
		return S;
	}

	public void setS(String s) {
		S = (int)Double.parseDouble(s);
	}

	public int getWenId() {
		return wenId;
	}

	public void setWenId(int wenId) {
		this.wenId = wenId;
	}

	public int getI() {
		return I;
	}

	public void setI(int i) {
		I = i;
	}
}
