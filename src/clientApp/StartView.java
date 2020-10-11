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
		
		 final JFrame jf = new JFrame("���ת��");
	        jf.setSize(350, 300);
	        jf.setLocationRelativeTo(null);
	        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();

	        // �����ı�����, ������ʾ�����Ϣ
	        final JTextArea msgTextArea = new JTextArea(10, 30);
	        JScrollPane js=new JScrollPane(msgTextArea);
	        //�ֱ�����ˮƽ�ʹ�ֱ��������������
	        js.setHorizontalScrollBarPolicy( 
	        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
	        js.setVerticalScrollBarPolicy( 
	        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        msgTextArea.setLineWrap(true);
	        panel.add(js);

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

			JRadioButton b1 = new JRadioButton("����SQL�ļ�");
			panel.add(b1);

			b1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(b1.isSelected()){
						msgTextArea.append("ȷ������sql�ļ�\n");
					}else{
						msgTextArea.append("ȡ������sql�ļ�\n");
					}
				}
			});

	        JButton saveBtn = new JButton("ת��");
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
	            		JOptionPane.showMessageDialog(null, "�������������ĵ�", "���⡾ע�⡿", JOptionPane.ERROR_MESSAGE);
	            	}else{
	            		boolean work = callprogram.mainMethod(path2,path1,savepath+"/document");
	            		 if(work) {
							 msgTextArea.append("ת�����,�ļ�����·��Ϊ"+savepath+"/document\n");
							 if(b1.isSelected()){
								 Sql a = new Sql();
								 a.toTxt(path2,savepath+"/document");
							 }
						 }
	            		 else msgTextArea.append("���ִ���");
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
        fileChooser.setCurrentDirectory(new File("./document"));

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
            if(p=='a'){
            	path1 = file.getAbsolutePath();
            	ReadDevTables read = new ReadDevTables();
            	devTable = read.readTables(path1);
                msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n"+read.errors());
            }
            if(p=='b'){
            	path2 = file.getAbsolutePath();
            	ReadBuildTables read = new ReadBuildTables();
            	buildTable = read.readTables(path2);
                msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n"+read.errors());
            }	
            //msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n");
        }
    }
}

