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
		
		 final JFrame jf = new JFrame("���ת��");
	        jf.setSize(350, 300);
	        jf.setLocationRelativeTo(null);
	        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();

	        // �����ı�����, ������ʾ�����Ϣ
	        final JTextArea msgTextArea = new JTextArea(10, 30);
	        msgTextArea.setLineWrap(true);
	        panel.add(msgTextArea);

	        JButton openBtna = new JButton("�����豸�ͺű�");
	        openBtna.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                showFileOpenDialog(jf, msgTextArea,'a');
	            }
	        });
	        panel.add(openBtna);
	        
	        JButton openBtnb = new JButton("����ʩ����¼��");
	        openBtnb.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                showFileOpenDialog(jf, msgTextArea,'b');
	            }
	        });
	        panel.add(openBtnb);
	        
	        JButton openBtnc = new JButton("ѡ��洢·��");
	        openBtnc.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	JFileChooser chooser = new JFileChooser();
	            	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            	chooser.showOpenDialog(null);
	            	savepath = chooser.getSelectedFile().getPath();
	            	msgTextArea.append("���ļ�: " + savepath + "\n\n");
	            }
	        });
	        panel.add(openBtnc);

	        JButton saveBtn = new JButton("ת��");
	        saveBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	ReadFile readfile = new ReadFile();
	            	if(path1.equals("a")||path2.equals("a")){
	            		JOptionPane.showMessageDialog(null, "�������������ĵ�", "���⡾ע�⡿", JOptionPane.ERROR_MESSAGE);
	            		if(savepath.equals("a")){
	            			JOptionPane.showMessageDialog(null, "��ѡ���ļ�����·��", "���⡾ע�⡿", JOptionPane.ERROR_MESSAGE);
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
     * ���ļ�
     */
    private static void showFileOpenDialog(Component parent, JTextArea msgTextArea,char p) {
        // ����һ��Ĭ�ϵ��ļ�ѡȡ��
        JFileChooser fileChooser = new JFileChooser();

        // ����Ĭ����ʾ���ļ���Ϊ��ǰ�ļ���
        fileChooser.setCurrentDirectory(new File("."));

        // �����ļ�ѡ���ģʽ��ֻѡ�ļ���ֻѡ�ļ��С��ļ����ļ�����ѡ��
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // �����Ƿ������ѡ
        fileChooser.setMultiSelectionEnabled(true);

        // ��ӿ��õ��ļ���������FileNameExtensionFilter �ĵ�һ������������, ��������Ҫ���˵��ļ���չ�� �ɱ������
       // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
        // ����Ĭ��ʹ�õ��ļ�������
        fileChooser.setFileFilter(new FileNameExtensionFilter("xlsx(*.xlsx,)", "xlsx"));

        // ���ļ�ѡ����߳̽�������, ֱ��ѡ��򱻹رգ�
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // ��������"ȷ��", ���ȡѡ����ļ�·��
            File file = fileChooser.getSelectedFile();

            // �������ѡ�����ļ�, ��ͨ�����淽����ȡѡ��������ļ�
            // File[] files = fileChooser.getSelectedFiles();
            if(p=='a')
            	path1 = file.getAbsolutePath();
            if(p=='b')
            	path2 = file.getAbsolutePath();
            msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n\n");
        }
    }
}
