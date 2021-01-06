package clientApp;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import readXlsxFile.ReadBuildFile;
import readXlsxFile.ReadDevFile;

public class StartView {

	public static  String concentratorFilePath = "e";
	public static String buildFilePath = "e";
	public static String sensorTypeFilePath = "e";
    public static int sql = 0;
    static String buildTable[][][];
    static String devTable[][][];
	public static void main(String[] args) throws IOException {
		
		 final JFrame jf = new JFrame("�����ļ�������");
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

	        JButton openBtna = new JButton("���뼯�����ͺű�");
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

			JButton openBtnc = new JButton("���봫�����ͺű�");
			openBtnc.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showFileOpenDialog(jf, msgTextArea,'c');
				}
			});
			panel.add(openBtnc);

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

					if(concentratorFilePath.equals("e")||buildFilePath.equals("e")||sensorTypeFilePath.equals("e")){
	            		JOptionPane.showMessageDialog(null, "�������������ĵ�", "���⡾ע�⡿", JOptionPane.ERROR_MESSAGE);
	            	}else{
	            		boolean work = callprogram.mainMethod(buildFilePath,concentratorFilePath,sensorTypeFilePath,savepath+"/document/");
	            		 if(work) {
							 msgTextArea.append("ת�����,�ļ�����·��Ϊ"+savepath+"/document\n");
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
	            concentratorFilePath = file.getAbsolutePath();
            	ReadDevFile read = new ReadDevFile();
            	devTable = read.readTables(concentratorFilePath);
                msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n"+read.errors());
            }
            if(p=='b'){
	            buildFilePath = file.getAbsolutePath();
            	ReadBuildFile read = new ReadBuildFile();
            	buildTable = read.readTables(buildFilePath);
                msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n"+read.errors());
            }
	        if(p=='c'){
		        sensorTypeFilePath = file.getAbsolutePath();
		        msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n");
	        }
            //msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n");
        }
    }
}

