package View;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteFile {
	/**
	 * 生成文件
	 * 
	 * @param str
	 * @param filePath
	 * @throws IOException
	 */
	public  void WriteToFile(String str, String filePath)
			throws IOException {
		BufferedWriter bw = null;
		try {
			FileOutputStream out = new FileOutputStream(filePath, false);// true,表示:文件追加内容，不重新生成,默认为false
			bw = new BufferedWriter(new OutputStreamWriter(out, "GBK"));
			bw.write(str += "\r\n");// 换行
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}
	}
}
