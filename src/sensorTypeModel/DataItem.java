package sensorTypeModel;

public class DataItem {
	private int ItemNum;//数据序号
	private String dataType;
	private String dataMeans;

	public int getItemNum() {
		return ItemNum;
	}

	public void setItemNum(int itemNum) {
		ItemNum = itemNum;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataMeans() {
		return dataMeans;
	}

	public void setDataMeans(String dataMeans) {
		this.dataMeans = dataMeans;
	}
}
