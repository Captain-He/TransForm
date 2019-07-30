package View;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View {

	public static  String path1 = "a";
	public static String path2 = "a";
    public static String savepath = "a";
	public static void main(String[] args) throws IOException {
		
		 final JFrame jf = new JFrame("表格转化");
	        jf.setSize(350, 300);
	        jf.setLocationRelativeTo(null);
	        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();

	        // 创建文本区域, 用于显示相关信息
	        final JTextArea msgTextArea = new JTextArea(10, 30);
	        msgTextArea.setLineWrap(true);
	        panel.add(msgTextArea);

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
	        
	        JButton openBtnc = new JButton("选择存储路径");
	        openBtnc.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	JFileChooser chooser = new JFileChooser();
	            	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            	chooser.showOpenDialog(null);
	            	savepath = chooser.getSelectedFile().getPath();
	            	msgTextArea.append("打开文件: " + savepath + "\n\n");
	            }
	        });
	        panel.add(openBtnc);

	        JButton saveBtn = new JButton("转化");
	        saveBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	ReadFile readfile = new ReadFile();
	            	if(path1.equals("a")||path2.equals("a")){
	            		JOptionPane.showMessageDialog(null, "请输入完整的文档", "标题【注意】", JOptionPane.ERROR_MESSAGE);
	            		if(savepath.equals("a")){
	            			JOptionPane.showMessageDialog(null, "请选择文件保存路径", "标题【注意】", JOptionPane.ERROR_MESSAGE);
	            		}
	            	}else{
	            		readfile.readfile(path1,path2,savepath);
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
        fileChooser.setCurrentDirectory(new File("."));

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
            if(p=='a')
            	path1 = file.getAbsolutePath();
            if(p=='b')
            	path2 = file.getAbsolutePath();
            msgTextArea.append("打开文件: " + file.getAbsolutePath() + "\n\n");
        }
    }
}
