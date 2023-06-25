package VolunEx;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


//�α��� ������ ������ Ŭ����
public class ManageSchedule extends JFrame implements ActionListener{

///////////Ŭ���� ���� ����//////////////////////////
	 int index1;//���� ����Ʈ�ε���
	 int index2;//�߰� ����Ʈ�ε���
	 
	 Vector<String> make_list = new Vector<String>();
	 Vector<String> add_list = new Vector<String>();///���� ��Ʈ������Ʈ, �߰� ��Ʈ�� ����Ʈ
	 ArrayList<timeBlock> real_make_list = new ArrayList();
	 ArrayList<timeBlock> real_add_list = new ArrayList();
	 ArrayList<timeBlock> temp_schedule = new ArrayList();

	 
	 int Day = 0;
	 String[] Days = {"��", "ȭ", "��", "��", "��", "��", "��"};
	 String[] CombCol = {"Red", "Orange", "Yellow", "Green", "Blue", "Navy", "Purple"};
	

	
//////////// ������Ʈ ����///////////////////////////
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//�ػ��� ��ǥ�� �޴� �ż��带 ����ϱ� ���� ����
	
	JPanel p1; //�г�
	JLabel imla0, imla1, la1, la2, la3, la4, la5,la6,la7; //��
	JTextField tf1,tf2,tf3; //�˻��� �� �ؽ�Ʈ�ʵ�
	JCheckBox cb0, cb1, cb2, cb3, cb4, cb5, cb6;//üũ�ڽ�
	JComboBox cmb1;
	JList ls1, ls2; //����Ʈ �����
	JButton b1,b2, b3, b4, b5; //��ư �ֱ�
	JScrollPane p2, p3;
/////////������Ʈ ���� ��////////////////////////////////////
	
	Student student;
////////������ ����///////////////////////////////////
	ManageSchedule(Student student) {
		super("VOLUNTABLE - �� �ð�ǥ ����");//������ ������ �̸�����
		this.student = student;
		 
		System.out.println("MANAGE SCHEDULE");

		for(int i=0; i<student.getSchedule().size(); i++) {
		
			timeBlock tempblock = student.getSchedule().get(i);
			
			tempblock.day.indexOf("1");
			String tempstr = "�����:"+tempblock.title+"/  ���۽ð�:  "+tempblock.sTime + "/  ����ð�:"+tempblock.eTime + " "+"/"
			+ "  ����:"+Days[tempblock.day.indexOf("1")]+" /  ��: "+ CombCol[tempblock.color];
			
			System.out.println(tempstr);
			if(student.getSchedule().get(i).valid == 0) {
				real_make_list.add(student.getSchedule().get(i));
				make_list.add(tempstr);
			}
			else{
				real_add_list.add(student.getSchedule().get(i));
				add_list.add(tempstr);
			}
		
		}
		
		
		p1 = new JPanel();//�⺻ �гλ���
		
		//�󺧻���//
		imla0 = new JLabel();
        ImageIcon icon0 = new ImageIcon("MPborder.png");
        imla0.setIcon(icon0);//���� �󺧻���
        
        imla1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("CBborder.png");
        imla1.setIcon(icon1);//���� �󺧻���
        
        la1 = new JLabel("�����( 2~3�� ) :",JLabel.CENTER);
        la2 = new JLabel("���۽ð� :   ",JLabel.RIGHT);
        la3 = new JLabel("����ð� :   ",JLabel.RIGHT);
        la4 = new JLabel("����          :",JLabel.CENTER);
        la5 = new JLabel("�� ���� :     ",JLabel.RIGHT);
        la6 = new JLabel("���� ���",JLabel.CENTER);
        la7 = new JLabel("�߰� ���",JLabel.CENTER);
        ///�ؽ�Ʈ �ʵ� ����//
        tf1 = new JTextField();//�����
        tf2 = new JTextField();//���۽ð�
        tf3 = new JTextField();//����ð�
        
        ///üũ�ڽ� ����//
    
        cb0 = new JCheckBox("��");
        cb1 = new JCheckBox("ȭ");
        cb2 = new JCheckBox("��");
        cb3 = new JCheckBox("��");
        cb4 = new JCheckBox("��");
        cb5 = new JCheckBox("��");
        cb6 = new JCheckBox("��");
        
        //�޺��ڽ� ����//
        cmb1 = new JComboBox(CombCol);
        
        //����Ʈ ����//
        ls1 = new JList(make_list);
        ls2 = new JList(add_list);
        

        //��ư����///
        b1 = new JButton("����");//��ư
        b1.setFont(b1.getFont().deriveFont(16f));
        b2 = new JButton("->");
        b2.setFont(b2.getFont().deriveFont(16f));
        b3 = new JButton("<-");
        b3.setFont(b3.getFont().deriveFont(16f));
        b4 = new JButton("����");
        b4.setFont(b4.getFont().deriveFont(16f));
        b5 = new JButton("Ȯ��");
        b5.setFont(b5.getFont().deriveFont(16f));      
///////////������Ʈ ���� �� /////////////////////////////////////////////////
        
        
        p1.setLayout(null);

        //�г��� ���̾ƿ�
       

        ////��/////
        imla0.setBounds(30,20,925,470);
        imla1.setBounds(55,115,515,40);
        la1.setBounds(57,60,125,50);//�����
        la1.setFont(la6.getFont().deriveFont(16f));
        la2.setBounds(310,60,125,50);//���۽ð�
        la2.setFont(la6.getFont().deriveFont(16f));
        la3.setBounds(560,60,125,50);//����ð�
        la3.setFont(la6.getFont().deriveFont(16f));
        la4.setBounds(80,97,125,75);//����
        la4.setFont(la6.getFont().deriveFont(16f));
        la5.setBounds(567,97,125,75);//������
        la5.setFont(la6.getFont().deriveFont(16f));
        la6.setBounds(40,145,350,75);
        la6.setFont(la6.getFont().deriveFont(20f));
        la7.setBounds(590,145,350,75);
        la7.setFont(la7.getFont().deriveFont(20f));
        
        ///�ؽ�Ʈ////
        tf1.setBounds(185,70,125,30);
        tf2.setBounds(435,70,125,30);
        tf3.setBounds(685,70,125,30);
        
        //üũ�ڽ�//
        cb0.setBounds(195,120,55,30);
        cb1.setBounds(250,120,55,30);
        cb2.setBounds(305,120,55,30);
        cb3.setBounds(360,120,55,30);
        cb4.setBounds(415,120,55,30);
        cb5.setBounds(470,120,55,30);
        cb6.setBounds(525,120,40,30);
        
        //�޺��ڽ�//
        cmb1.setBounds(685,120,125,30);
        
        //����Ʈ//
        
        
        //��ư//
        b1.setBounds(835,60,100,100);
        b2.setBounds(415,235,150,50);
        b3.setBounds(415,310,150,50);
        b4.setBounds(415,385,150,50);
        b5.setBounds(825,508,100,30);
////////�ٿ�� ���� �� /////////////////////////////////////////////////
        
        
        p1.add(imla0);
        p1.add(imla1);
        p1.add(la1);
        p1.add(la2);
        p1.add(la3);
        p1.add(la4);
        p1.add(la5);
        p1.add(la6);
        p1.add(la7);
        p1.add(tf1);
        p1.add(tf2);
        p1.add(tf3);
        
        p1.add(cb0);
        p1.add(cb1);
        p1.add(cb2);
        p1.add(cb3);
        p1.add(cb4);
        p1.add(cb5);
        p1.add(cb6);
        
        
        
        p1.add(cmb1);
        
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
     /////////////////   
        
////////������Ʈ �߰� ��///////////////////////////////////////////
        
        ls1.addListSelectionListener(new selectListListener1());
        ls2.addListSelectionListener(new selectListListener2());
        
        p2 = new JScrollPane(ls1);
        p3 = new JScrollPane(ls2);
        
        
        p2.setBounds(40,200,350,275);
        p3.setBounds(590,200,350,275);
        
        p1.add(p2);
        p1.add(p3);
        
        
///////////////////��ũ�� �߰� �κ�//////////////////////////////
        
        this.getContentPane().add(p1);
  
        b1.addActionListener(this); 
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        
        setVisible(true);
        setBounds((res.width)/4,(res.height)/4,1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
///////////////////////������ ��//////////////////////////////////////////////
	
	public void actionPerformed(ActionEvent e) {
		
		//���⼭ ���� 
		if(e.getSource()==b1) {
		/////���⼭ ��ĳ�� ��ü�� �Է¹��� ������ ��ü�� ������ ������ ����Ʈ���ٰ� �־��ְ�, �� ����Ʈ���� ��Ʈ������ ����� �ָ�?�� �� ����///
	    ////�������� Ȯ�� ��ư�� ������ �ѹ��� �����ͺ��̽��� �Է��ϰ� ��?//////
		
		
		
		String Color = (String) cmb1.getItemAt(cmb1.getSelectedIndex());
		
		timeBlock tb = new timeBlock();
		tb.setTitle(tf1.getText().trim());
		tb.setsTime(Integer.parseInt(tf2.getText().trim()));
		tb.seteTime(Integer.parseInt(tf3.getText().trim()));
		///////////��Ʈ�� �Է� �ޱ� ��////////////
			String tempstr = "�����:"+tf1.getText()+"/  ���۽ð�:  "+tf2.getText() + "/  ����ð�:"+tf3.getText() + " "+"/  ����:";
			
			if(cb0.isSelected()) {
				tb.setDay("1000000");
				tempstr += cb0.getText();
			}	//��
			else if(cb1.isSelected()) {
				tb.setDay("0100000");
				tempstr += cb1.getText();
			}

			else if(cb2.isSelected()) {
				tb.setDay("0010000");
				tempstr += cb2.getText();
			}
			else if(cb3.isSelected()) {
				tb.setDay("0001000");
				tempstr += cb3.getText();
			}
			else if(cb4.isSelected()) {
				tb.setDay("0000100");
				tempstr += cb4.getText();
			}
			else if(cb5.isSelected()) {
				tb.setDay("0000010");
				tempstr += cb5.getText();
			}
			else if(cb6.isSelected()) {
				tb.setDay("0000001");
				tempstr += cb6.getText();
			}
		////////üũ �ڽ� �̺�Ʈ ��/////////	
			tb.setColor(cmb1.getSelectedIndex());
			tempstr += " /  ��: " + (String) cmb1.getItemAt(cmb1.getSelectedIndex());
		///////�� �޾ƿ��� ��/////////////
			tb.valid = 0;//�븮�� �ʱ�ȭ
			tb.inittimeBlockNo();//�����Ѱ� ǥ��
			
			real_make_list.add(tb);//���� ���̺� ����Ʈ�� Ÿ�Ӻ�� �߰����ش�.
			make_list.add(tempstr);
			
			ls1.setListData(make_list);
			
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");

		}
		
		if(e.getSource()==b2) {//->��ư
			
			
			add_list.add(make_list.remove(index1)); 
			
			real_make_list.get(index1).valid = 1; ///0�̾��� �븮�带 1�� �ٲ��ش�.
			real_add_list.add(real_make_list.remove(index1));//�ε��� �ٲ۰� ����� �ٲ��ش�. 
			
			ls1.setListData(make_list);
			ls2.setListData(add_list);
		}
		
		if(e.getSource()==b3) {//<-��ư
			make_list.add(add_list.remove(index2));
			
			real_add_list.get(index2).valid = 0; ///0�̾��� �븮�带 1�� �ٲ��ش�.
			real_make_list.add(real_add_list.remove(index2));//�ε��� �ٲ۰� ����� �ٲ��ش�. 
			
			ls1.setListData(make_list);
			ls2.setListData(add_list);
		}
		
        if(e.getSource()==b4) {//������ư
            make_list.remove(index1);
            real_make_list.remove(index1);
            ls1.setListData(make_list); 
		}
        
        if(e.getSource()==b5) {//���������� ���� â�ݱ�///���⼭ ��ü���̶� �� ������� �Ѵ�?//
        	temp_schedule = real_make_list;
        	temp_schedule.addAll(real_add_list);

        	student.updateSchedule(temp_schedule);
        	student.setschedule(temp_schedule);
        	//���⼭ ���� �������� ��ü �����ٷ� �ٲ�����Ѵ�.
			MainPage MP = new MainPage(student);
			this.dispose();
		}
	}
	
	class selectListListener1 implements ListSelectionListener {

		   public void valueChanged(ListSelectionEvent event) {
		    String selection1 = (String) ls1.getSelectedValue();
		    index1 = ls1.getSelectedIndex(); // ����Ʈ���� �ε�����ȣ ��������
		    
		   }
	}
	
	class selectListListener2 implements ListSelectionListener {

		   public void valueChanged(ListSelectionEvent event) {
			   
		
		    String selection2 = (String) ls2.getSelectedValue();

		    index2 = ls2.getSelectedIndex(); // ����Ʈ���� �ε�����ȣ ��������
		   
		   }
	}
	
}



