package device.model;

import java.util.ArrayList;
import java.util.List;

public class DataCell {
	private int requestTimes; //读取数据需要几次
	private int segmentNum; //分段序号
	private int functionCode; //功能码
	private int startAddress; //起始地址
	private int registerCount; //寄存器数量
	private List<String> dataMeaning; //数据含义
	private List<String> dataType; //数据类型
	private List<String> registerAddress;//寄存器地址
	private List<String> registerNum; //单个数据占据的寄存器数量
	private List<String> dataTimes; //倍率
	private List<String> parameter; //参数
	
	public void setRequestTimes(String requestTimes){
		this.requestTimes = (int)Double.parseDouble(requestTimes);
	}
	public int getRequestTimes(){
		return requestTimes;
	}
	public void setSegmentNum(String segmentNum){
		this.segmentNum = (int)Double.parseDouble(segmentNum);
	}
	public int getSegmentNum(){
		return segmentNum;
	}
	public void setFunctionCode(String functionCode){
		this.functionCode = (int)Double.parseDouble(functionCode);
	}
	public int getFunctionCode(){
		return functionCode;
	}
	public void setStartAddress(String startAddress){
		this.startAddress = (int)Double.parseDouble(startAddress);
	}
	public int getStartAddress(){
		return startAddress;
	}
	public void setRegisterCount(String registerCount){
		this.registerCount = (int)Double.parseDouble(registerCount);
	}
	public int getRegisterCount(){
		return registerCount;
	}
	public void setDataMeaning(String dataMeaning){
		
		this.dataMeaning = strToList(dataMeaning);
	}
	public List<String> getDataMeaning(){
		return dataMeaning;
	}
	public void setDataType(String dataType){
		
		this.dataType = strToList(dataType);
	}
	public List<String> getDataType(){
		return dataType;
	}
	public void setRegisterAddress(String registerAddress){
		
		this.registerAddress = strToList(registerAddress);
	}
	public List<String> getRegisterAddress(){
		return registerAddress;
	}
	public void setRegisterNum(String registerNum){
		
		this.registerNum = strToList(registerNum);
	}
	public List<String> getRegisterNum(){
		return registerNum;
	}
	public void setDataTimes(String dataTimes){
		
		this.dataTimes = strToList(dataTimes);
	}
	public List<String> getDataTimes(){
		return dataTimes;
	}
	public void setParameter(String parameter){
		
		this.parameter = strToList(parameter);
	}
	public List<String> getParameter(){
		return parameter;
	}
	private List<String> strToList(String str){
		List<String> list = new ArrayList<String>();
		String strArray[] = str.split("/");
		for(int i=0;i<strArray.length;i++){
			if(strArray[i].length()!=0){
				list.add(strArray[i]);
			}
		}
		return list;
	}
}
