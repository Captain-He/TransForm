package View;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteFile {
	/**
	 * �����ļ�
	 * 
	 * @param str
	 * @param filePath
	 * @throws IOException
	 */
	public  void WriteToFile(String str, String filePath)
			throws IOException {
		BufferedWriter bw = null;
		try {
			FileOutputStream out = new FileOutputStream(filePath, false);// true,��ʾ:�ļ�׷�����ݣ�����������,Ĭ��Ϊfalse
			bw = new BufferedWriter(new OutputStreamWriter(out, "GBK"));
			bw.write(str += "\r\n");// ����
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}
	}
}
