package VolunEx;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import VolunEx.ManageSchedule.selectListListener1;
import VolunEx.ManageSchedule.selectListListener2;


//�α��� ������ ������ Ŭ����
public class SearchVol extends JFrame implements ActionListener{
	
	VolInfo vi = new VolInfo();
	
	 int index1;//���� ����Ʈ�ε���
	 int index2;//�߰� ����Ʈ�ε���
	 
	 ArrayList<Volunteer> real_search_list = new ArrayList<Volunteer>();
	 ArrayList<Volunteer> real_admit_list = new ArrayList<Volunteer>();//��¥ ��ġ����Ʈ
	 Vector<String> search_list = new Vector<String>();
	 Vector<String> admit_list = new Vector<String>();///���� ��Ʈ������Ʈ, �߰� ��Ʈ�� ����Ʈ
	
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//�ػ��� ��ǥ�� �޴� �ż��带 ����ϱ� ���� ����
	
	JPanel p1; //�г�
	JLabel imla0, imla1, la1, la2, la3, la6,la7; //��
	JTextField tf2,tf3; //�˻��� �� �ؽ�Ʈ�ʵ�
	JCheckBox cb0, cb1;//üũ�ڽ�
	JList ls1, ls2; //����Ʈ �����
	JButton b1,b2, b3, b4, b5; //��ư �ֱ�
	JScrollPane p2, p3;
/////////������Ʈ ���� ��////////////////////////////////////
	Student student;
	String[] Days = {"��", "ȭ", "��", "��", "��", "��", "��"};
	
SearchVol(Student student) {
	    
		super("VOLUNTABLE - ���� ã�� / ��û");//������ ������ �̸�����
		this.student = student;
		System.out.println("SEARCH VOL");

		p1 = new JPanel();//�⺻ �гλ���
		
		
		///�л��� �̹� ����� ���� ����Ʈ ������ִ� ��
		for(int i=0; i<student.getvolapplication().size(); i++) {
			
			Volunteer tempblock = student.getvolapplication().get(i);
			
	
			String tempstr = "���� ����:"+ tempblock.progrmSj+"/  ���� ���:  "+tempblock.actPlace + "  ����:"+Days[tempblock.actWkdy.indexOf("1")]
			+"/  ���� ������:  "+tempblock.progrmBgnde+"/  ���� ������:  "+tempblock.progrmEndde +"/  ���۽ð�:  "+tempblock.actBeginTm + "/  ����ð�:"+tempblock.actEndTm + " "+"/"
			;
			
			real_admit_list.add(student.getvolapplication().get(i));
			admit_list.add(tempstr);
		}
		
		
		//�󺧻���//
		imla0 = new JLabel();
        ImageIcon icon0 = new ImageIcon("MPborder.png");
        imla0.setIcon(icon0);//���� �󺧻���
        
        imla1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("CBborder2.png");
        imla1.setIcon(icon1);//���� �󺧻���
        
        la1 = new JLabel("��ȣ ��� :",JLabel.CENTER);
        la2 = new JLabel("���� ��¥ : ",JLabel.RIGHT);
        la3 = new JLabel("���� ��¥ : ",JLabel.RIGHT);
        la6 = new JLabel("ã�� ���",JLabel.CENTER);
        la7 = new JLabel("��û ����",JLabel.CENTER);
       
        ///�ؽ�Ʈ �ʵ� ����//
        tf2 = new JTextField();//���۽ð�
        tf3 = new JTextField();//����ð�
        
        ///üũ�ڽ� ����//
        cb0 = new JCheckBox("�� ��ó");
        cb1 = new JCheckBox("�б� ��ó");
  
        //����Ʈ ����//
        ls1 = new JList(search_list);
        ls2 = new JList(admit_list);
        

        //��ư����///
        b1 = new JButton("ã��");//��ư
        b1.setFont(b1.getFont().deriveFont(16f));
        b2 = new JButton("��û �ϱ�");
        b2.setFont(b2.getFont().deriveFont(16f));
        b3 = new JButton("��û ���");
        b3.setFont(b3.getFont().deriveFont(16f));
        b4 = new JButton("�� ����");
        b4.setFont(b4.getFont().deriveFont(16f));
        b5 = new JButton("Ȯ��");
        b5.setFont(b4.getFont().deriveFont(16f));      
///////////������Ʈ ���� �� /////////////////////////////////////////////////
        
        
        p1.setLayout(null);
        //�г��� ���̾ƿ�
       
        ////��/////
        imla0.setBounds(30,20,925,470);
        imla1.setBounds(55,70,260,80);
        la1.setBounds(57,85,125,50);//��ȣ ���
        la1.setFont(la6.getFont().deriveFont(18f));
        la2.setBounds(310,85,125,50);//���۽ð�
        la2.setFont(la6.getFont().deriveFont(18f));
        la3.setBounds(560,85,125,50);//����ð�
        la3.setFont(la6.getFont().deriveFont(18f));
        la6.setBounds(40,145,350,75);
        la6.setFont(la6.getFont().deriveFont(20f));
        la7.setBounds(590,145,350,75);
        la7.setFont(la7.getFont().deriveFont(20f));
        
        ///�ؽ�Ʈ////
        tf2.setBounds(435,95,125,30);
        tf3.setBounds(685,95,125,30);
        
        //üũ�ڽ�//
        cb0.setBounds(190,75,120,30);
        cb0.setFont(cb0.getFont().deriveFont(16f));
        cb1.setBounds(190,115,120,30);
        cb1.setFont(cb1.getFont().deriveFont(16f));
  
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
        p1.add(la6);
        p1.add(la7);
        
        p1.add(tf2);
        p1.add(tf3);
        
        p1.add(cb0);
        p1.add(cb1);

        p1.add(ls1);
        p1.add(ls2);
        
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
////////������Ʈ �߰� ��///////////////////////////////////////////
        
        ls1.addListSelectionListener(new selectListListener1());
        ls2.addListSelectionListener(new selectListListener2());
        
        p2 = new JScrollPane(ls1);
        p3 = new JScrollPane(ls2);
        
        p2.setBounds(40,200,350,275);
        p3.setBounds(590,200,350,275);
        
        p1.add(p2);
        p1.add(p3);
        
        
///////��ũ�� �߰� �κ�/////////////////////////////////
        this.getContentPane().add( p1);
        
        
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
		if(e.getSource()==b1) {
			if(cb0.isSelected()) {
				String bgn = tf2.getText();
				String end = tf3.getText();
				real_search_list = (ArrayList<Volunteer>) this.student.searchVolunteer(bgn, end, true);
			}	//��
			else if(cb1.isSelected()) {
				String bgn = tf2.getText();
				String end = tf3.getText();
				real_search_list = (ArrayList<Volunteer>) this.student.searchVolunteer(bgn, end, false);
			}
			
			search_list = new Vector<String>();
			for(int i=0; i<real_search_list.size(); i++) {
				
				Volunteer tempblock = real_search_list.get(i);
				
		
				String tempstr = "���� ����:"+ tempblock.progrmSj+"/  ���� ���:  "+tempblock.actPlace + "  ����:"+Days[tempblock.actWkdy.indexOf("1")]
				+"/  ���� ������:  "+tempblock.progrmBgnde+"/  ���� ������:  "+tempblock.progrmEndde +"/  ���۽ð�:  "+tempblock.actBeginTm + "/  ����ð�:"+tempblock.actEndTm + " "+"/"
				;
				
				search_list.add(tempstr);
			}
			
			
			
			ls1.setListData(search_list);
			
			tf2.setText("");
			tf3.setText("");
		}
		
		
		
		if(e.getSource()==b2) {
			real_admit_list.add(real_search_list.remove(index1));
			admit_list.add(search_list.remove(index1));
			
			ls1.setListData(search_list);
			ls2.setListData(admit_list);
		}
		
		if(e.getSource()==b3) {
			admit_list.remove(index2);
            real_admit_list.remove(index2);
            ls2.setListData(admit_list);
		}
		
		if(e.getSource()==b4) {
			int registNo = this.real_search_list.get(index1).progrmRegistNo;
			SeeInfo SI = new SeeInfo(student.showVolInfo(registNo));
		}
		
		if(e.getSource()==b5) {
			ArrayList<Volunteer> l = new ArrayList<Volunteer>();
			l.addAll(real_admit_list);
			student.updateVolApplication(real_admit_list);
			
			student.setvolapplication(l);
			
			MainPage MP = new MainPage(student);
			dispose();
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