package com.gennlife.autoplatform.crfLogic;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.gennlife.autoplatform.bean.Excel;
import com.gennlife.autoplatform.utils.FileUtils;
import com.gennlife.autoplatform.utils.ListAndStringUtils;

/**
 * @Description: CrfLogic工具-1.0（crf逻辑测试）
 * @author: wangmiao
 * @Date: 2017年12月31日 下午11:25:38 
 */
public class SwingCrfLogicTools extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	JFrame mainframe;
    JPanel panel;
    //创建相关的Label标签
    JLabel infilepath_labelExcel = new JLabel("crf文件(Excel):");
    JLabel infilepath_labelJson = new JLabel("全量数据(Json):");
    JLabel outfilepath_label = new JLabel("导出文件路径:");
    //crf逻辑测试需要的地址
    JLabel infilepath_labelMongo = new JLabel("mongodb(IP):");
    JLabel infilepath_labelAuto = new JLabel("接口地址(http):");
    JLabel infilepath_labelDisease = new JLabel("病种名称:");
    
    //创建相关的文本域
    JTextField infilepath_textfieldExcel = new JTextField(20);
    JTextField infilepath_textfieldJson = new JTextField(20);
    JTextField outfilepath_textfield = new JTextField(20);
    //crf逻辑测试需要的地址
    JTextField infilepath_textfieldMongo = new JTextField(20);
    JTextField infilepath_textfieldAuto  = new JTextField(20);
    JTextField infilepath_textfieldDisease = new JTextField(20);
     
    //创建滚动条以及输出文本域
    JScrollPane jscrollPane;
    JTextArea outtext_textarea = new JTextArea();
    //创建按钮
    JButton infilepath_buttonExcel = new JButton("...");
    JButton infilepath_buttonJson = new JButton("...");
    JButton outfilepath_button = new JButton("...");
    JButton start_button = new JButton("开始");
    //JButton start_button2 = new JButton("配置联动路径");

    public void show(){
        mainframe = new JFrame("TestCrfLogicTools-1.0");
        // Setting the width and height of frame
        mainframe.setSize(575, 480);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);//固定窗体大小

        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包 
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸 
        int screenWidth = screenSize.width/2; // 获取屏幕的宽
        int screenHeight = screenSize.height/2; // 获取屏幕的高
        int height = mainframe.getHeight(); //获取窗口高度
        int width = mainframe.getWidth(); //获取窗口宽度
        mainframe.setLocation(screenWidth-width/2, screenHeight-height/2);//将窗口设置到屏幕的中部             
        //窗体居中，c是Component类的父窗口
        //mainframe.setLocationRelativeTo(c);   
        Image myimage=kit.getImage("resourse/hxlogo.gif"); //由tool获取图像
        mainframe.setIconImage(myimage);
        initPanel();//初始化面板
        mainframe.add(panel);
        mainframe.setVisible(true);
    }
     /* 创建面板，这个类似于 HTML 的 div 标签
     * 我们可以创建多个面板并在 JFrame 中指定位置
     * 面板中我们可以添加文本字段，按钮及其他组件。
     */
    public void initPanel(){
        this.panel = new JPanel();
        panel.setLayout(null);
        //this.panel = new JPanel(new GridLayout(3,2)); //创建3行3列的容器     
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        infilepath_labelExcel.setBounds(10,20,120,25);
        infilepath_textfieldExcel.setBounds(120,20,400,25);
        infilepath_buttonExcel.setBounds(520,20, 30, 25);
        
        infilepath_labelJson.setBounds(10,50,120,25);
        infilepath_textfieldJson.setBounds(120,50,400,25);
        infilepath_buttonJson.setBounds(520,50,30,25);
        
        this.panel.add(infilepath_labelExcel);
        this.panel.add(infilepath_textfieldExcel);
        this.panel.add(infilepath_buttonExcel);
        
        this.panel.add(infilepath_labelJson);
        this.panel.add(infilepath_textfieldJson);
        this.panel.add(infilepath_buttonJson);

        outfilepath_label.setBounds(10,80,120,25);
        outfilepath_textfield.setBounds(120,80,400,25);
        outfilepath_button.setBounds(520,80,30,25);
        this.panel.add(outfilepath_label);
        this.panel.add(outfilepath_textfield);
        this.panel.add(outfilepath_button);
        
        //逻辑测试需要的地址
        infilepath_labelMongo.setBounds(10,110,120,25);
        infilepath_textfieldMongo.setBounds(120,110,400,25);
        this.panel.add(infilepath_labelMongo);
        this.panel.add(infilepath_textfieldMongo);
        
        infilepath_labelAuto.setBounds(10,140,120,25);
        infilepath_textfieldAuto.setBounds(120,140,400,25);
        this.panel.add(infilepath_labelAuto);
        this.panel.add(infilepath_textfieldAuto);
        
        infilepath_labelDisease.setBounds(10,170,120,25);
        infilepath_textfieldDisease.setBounds(120,170,400,25);
        this.panel.add(infilepath_labelDisease);
        this.panel.add(infilepath_textfieldDisease);
        
        //crf逻辑测试，开始按钮
        start_button.setBounds(10,210,115,25);
        this.panel.add(start_button);
        //联动路径
        //start_button2.setBounds(160,110,115,25);
        //this.panel.add(start_button2);

        outtext_textarea.setEditable(false);
        outtext_textarea.setFont(new Font("标楷体", Font.BOLD, 16));
        jscrollPane = new JScrollPane(outtext_textarea);
        jscrollPane.setBounds(10,240,550,200);
        this.panel.add(jscrollPane);
        //增加动作监听
        infilepath_buttonExcel.addActionListener(this);
        infilepath_buttonJson.addActionListener(this);
        outfilepath_button.addActionListener(this);
        start_button.addActionListener(this);
        //start_button2.addActionListener(this);
    }
    /**
     * 单击动作触发方法
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
    	//配置英文名
    	if (event.getSource() == start_button) {
    		//页面显示
    		 outtext_textarea.setText("若长时间无反应，则检查excel配置是否正确（再重新执行）。请稍后...");
            //确认对话框弹出  //YES_NO_OPTION
            int result = JOptionPane.showConfirmDialog(null, "请确认文件处于关闭状态，是否开始执行?", "确认", 0);
            if (result == 1) {//是：0，否：1，取消：2
                return;
            }
            //判断输入框不为空
            if (infilepath_textfieldExcel.getText().equals("") ||
            	infilepath_textfieldJson.getText().equals("") || 
            	outfilepath_textfield.getText().equals("") ||
            	infilepath_textfieldMongo.getText().equals("") || 
            	infilepath_textfieldAuto.getText().equals("") || 
            	infilepath_textfieldDisease.getText().equals("")) {
            	JOptionPane.showMessageDialog(null, "输入框不能为空", "提示", 2);//弹出提示对话框，warning
                return;
            }else{
                String infilepath_Excel = infilepath_textfieldExcel.getText().trim();
                String infilepath_Json = infilepath_textfieldJson.getText().trim();
                String outfilepath = outfilepath_textfield.getText().trim();
                //输入文本
                String infilepath_Mongo = infilepath_textfieldMongo.getText().trim();
                String infilepath_Auto = infilepath_textfieldAuto.getText().trim();
                String infilepath_Disease = infilepath_textfieldDisease.getText().trim();
                
                //调用方法开始
                //先把文件copy到输出路径
                //然后执行方法
            	String outFilePath = ListAndStringUtils.stringReplaceReturnValue(outfilepath);
            	String fileName_Excel = ListAndStringUtils.stringToSubstringReturnFileName(infilepath_Excel);
            	String fileName_Json = ListAndStringUtils.stringToSubstringReturnFileName(infilepath_Json);
            	 //先把文件copy到输出路径
                try {
					FileUtils.copyFile(ListAndStringUtils.stringReplaceReturnValue(infilepath_Excel), outFilePath+"\\\\"+fileName_Excel);
                } catch (Exception e1) {
					e1.printStackTrace();
				}
                try {
                	FileUtils.copyFile(ListAndStringUtils.stringReplaceReturnValue(infilepath_Json),outFilePath+"\\\\"+fileName_Json);
                } catch (Exception e1) {
                	e1.printStackTrace();
                }
                
                Excel excel = new Excel(outFilePath,fileName_Excel, "Sheet1");
                //处理后的json文件path
                String path_Json = outFilePath+"\\\\"+fileName_Json;
                
                try {
                	//调用方法_插入patientDetail
                	//CrfLogic.insertDatasIntoPatientDetailAndPostAndWritePatIntoExcel(excel, path_Json,infilepath_Mongo, infilepath_Auto, infilepath_Disease);
                	//使用心得逻辑
                	CrfLogic.addFirstPat_insertDatasIntoPatientDetailAndPostAndWritePatIntoExcel(excel, path_Json,infilepath_Mongo, infilepath_Auto, infilepath_Disease);
                	
                	//调用方法_查询crfdata
                	CrfLogic.queryCrfdataByPatAndWriteResults(excel,infilepath_Mongo);
                	
                	//===========改变名称 start=========
                	String newFileName_Excel=null;
					File file=new File(outFilePath+"\\\\"+fileName_Excel); 
					newFileName_Excel = ListAndStringUtils.segmentFileAllNameToFileName(fileName_Excel);
					newFileName_Excel = newFileName_Excel+"_"+new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
					file.renameTo(new File(outFilePath+"\\\\"+newFileName_Excel+".xlsx"));
                	//===========改变名称 end=========
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                
                //调用方法结束
                //打开输出文件所在文件夹
                result = JOptionPane.showConfirmDialog(null, "已完成！是否打开输出文件所在文件夹?", "确认", 0);//YES_NO_OPTION
                if (result == 0) {//是：0，否：1，取消：2
                    try {
                        @SuppressWarnings("unused")
                        //调用cmd方法打开文件夹
                        Process process = Runtime.getRuntime().exec("cmd.exe  /c start "+outFilePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }   
                }
                //页面显示
                outtext_textarea.setText("ok...");
            }
		}else{
            //判断三个选择按钮并对应操作
            if(event.getSource() == infilepath_buttonExcel) {
                File file = openChoseWindow(JFileChooser.FILES_ONLY);
                if(file == null)
                    return;
                infilepath_textfieldExcel.setText(file.getAbsolutePath());
                outfilepath_textfield.setText(file.getParent()+"\\out");
            }else if(event.getSource() == infilepath_buttonJson) {
                File file = openChoseWindow(JFileChooser.FILES_ONLY);
                if(file == null)
                    return;
                infilepath_textfieldJson.setText(file.getAbsolutePath());
            }else if(event.getSource() == outfilepath_button){
                File file = openChoseWindow(JFileChooser.DIRECTORIES_ONLY);
                if(file == null)
                    return;
                outfilepath_textfield.setText(file.getAbsolutePath()+"\\out");
            }        
        }
    }
    /**
     * 打开选择文件窗口并返回文件
     * @param type
     * @return
     */
    public File openChoseWindow(int type){
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(type);//选择的文件类型(文件夹or文件)  
        jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile();
        return file;
    }
    public void windowClosed(WindowEvent arg0) {         
        System.exit(0);
    } 
    public void windowClosing(WindowEvent arg0) { 
        System.exit(0);
    }
    
   
    public static void main(String []args){
    	SwingCrfLogicTools f = new SwingCrfLogicTools();
        f.show();
    }
    
}
