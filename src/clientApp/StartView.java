package clientApp;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import convertToTxt.Sql;
import readBuildTables.ReadBuildTables;
import readDevTables.ReadDevTables;

public class StartView {

	public static  String path1 = "a";
	public static String path2 = "a";

    public static int sql = 0;
    static String buildTable[][][];
    static String devTable[][][];
	public static void main(String[] args) throws IOException {
		
		 final JFrame jf = new JFrame("表格转换");
	        jf.setSize(350, 300);
	        jf.setLocationRelativeTo(null);
	        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();

	        // 创建文本区域, 用于显示相关信息
	        final JTextArea msgTextArea = new JTextArea(10, 30);
	        JScrollPane js=new JScrollPane(msgTextArea);
	        //分别设置水平和垂直滚动条总是隐藏
	        js.setHorizontalScrollBarPolicy( 
	        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
	        js.setVerticalScrollBarPolicy( 
	        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        msgTextArea.setLineWrap(true);
	        panel.add(js);

	        JButton openBtna = new JButton("导入设备型号表");
	        openBtna.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                showFileOpenDialog(jf, msgTextArea,'a');
	            }
	        });
	        panel.add(openBtna);
	        
	        JButton openBtnb = new JButton("导入施工记录表");
	        openBtnb.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                showFileOpenDialog(jf, msgTextArea,'b');
	            }
	        });
	        panel.add(openBtnb);

			JRadioButton b1 = new JRadioButton("导出SQL文件");
			panel.add(b1);

			b1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(b1.isSelected()){
						msgTextArea.append("确定导出sql文件\n");
					}else{
						msgTextArea.append("取消导出sql文件\n");
					}
				}
			});

	        JButton saveBtn = new JButton("转换");
	        saveBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	MainMethods callprogram = new MainMethods();
					File directory = new File("");
					String savepath = null;
					try {
						savepath = directory.getCanonicalPath();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					if(path1.equals("a")||path2.equals("a")){
	            		JOptionPane.showMessageDialog(null, "请输入完整的文档", "标题【注意】", JOptionPane.ERROR_MESSAGE);
	            	}else{
	            		boolean work = callprogram.mainMethod(path2,path1,savepath+"/document");
	            		 if(work) {
							 msgTextArea.append("转换完成,文件保存路径为"+savepath+"/document\n");
							 if(b1.isSelected()){
								 Sql a = new Sql();
								 a.toTxt(path2,savepath+"/document");
							 }
						 }
	            		 else msgTextArea.append("出现错误");
	            	}
	            }
	        });
	        panel.add(saveBtn);
	        jf.setContentPane(panel);
	        jf.setVisible(true);	
		
	}
	/*
     * 打开文件
     */
    private static void showFileOpenDialog(Component parent, JTextArea msgTextArea,char p) {
        // 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();

        // 设置默认显示的文件夹为当前文件夹
        fileChooser.setCurrentDirectory(new File("./document"));

        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // 设置是否允许多选
        fileChooser.setMultiSelectionEnabled(true);

        // 添加可用的文件过滤器（FileNameExtensionFilter 的第一个参数是描述, 后面是需要过滤的文件扩展名 可变参数）
       // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
        // 设置默认使用的文件过滤器
        fileChooser.setFileFilter(new FileNameExtensionFilter("xlsx(*.xlsx,)", "xlsx"));

        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"确定", 则获取选择的文件路径
            File file = fileChooser.getSelectedFile();

            // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
            // File[] files = fileChooser.getSelectedFiles();
            if(p=='a'){
            	path1 = file.getAbsolutePath();
            	ReadDevTables read = new ReadDevTables();
            	devTable = read.readTables(path1);
                msgTextArea.append("打开文件: " + file.getAbsolutePath() + "\n"+read.errors());
            }
            if(p=='b'){
            	path2 = file.getAbsolutePath();
            	ReadBuildTables read = new ReadBuildTables();
            	buildTable = read.readTables(path2);
                msgTextArea.append("打开文件: " + file.getAbsolutePath() + "\n"+read.errors());
            }	
            //msgTextArea.append("打开文件: " + file.getAbsolutePath() + "\n");
        }
    }
}

