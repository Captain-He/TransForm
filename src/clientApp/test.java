package clientApp;

import convertToTxt.Sql;
import readXlsxFile.ReadSensorTypeFile;
import sensorTypeModel.DataItem;
import sensorTypeModel.SensorType;
import sensorTypeModel.SensorTypeTables;

public class test {
    public static void main(String args[]){
        /*String buildFilePath = "D:\\workspace\\TranformApp\\document\\施工记录表.xlsx";
        String concentratorFilePath = "D:\\workspace\\TranformApp\\document\\集中器型号表.xlsx";
        String sensorTypeFilePath = "D:\\workspace\\TranformApp\\document\\传感器型号表.xlsx";
        String saveTxtPath = "D:\\workspace\\TranformApp\\document\\";*/
        String buildFilePath = "D:\\workspace\\TranformApp\\document\\施工记录表.xlsx";
        String concentratorFilePath = "D:\\workspace\\TranformApp\\document\\集中器型号表.xlsx";
        String sensorTypeFilePath = "D:\\workspace\\TranformApp\\document\\传感器型号表.xlsx";
        String saveTxtPath = "D:\\workspace\\TranformApp\\document\\";
        MainMethods testMain = new MainMethods();
        testMain.mainMethod(buildFilePath,concentratorFilePath,sensorTypeFilePath,saveTxtPath);

    }
}
