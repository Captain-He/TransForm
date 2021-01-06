package sensorTypeModel;


import java.util.ArrayList;

public class SensorType {
	private String sensorTypeName;
	private ArrayList<DataItem>sensorTypeList ;

	public String getSensorTypeName() {
		return sensorTypeName;
	}

	public void setSensorTypeName(String sensorTypeName) {
		this.sensorTypeName = sensorTypeName;
	}

	public ArrayList<DataItem> getSensorTypeList() {
		return sensorTypeList;
	}

	public void setSensorTypeList(ArrayList<DataItem> sensorTypeList) {
		this.sensorTypeList = sensorTypeList;
	}
}
