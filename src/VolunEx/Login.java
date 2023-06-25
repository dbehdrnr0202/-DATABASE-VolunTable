package VolunEx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Login extends JFrame implements ActionListener{ 
	
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
	
	Student student;
	//�ػ� ��ǥ �޴� �޼��� �̿��ϱ� ���� ��ü ����
	 

	//������ �������� ���� ���� ������Ʈ ����
		JPasswordField tf2;  //�� ������Ʈ�� TF �ʹ� �ٸ��� �ԷµǴ� ���� * �� ǥ�����ش�. ��й�ȣ �Է¿��̴�.
		JButton b1,b2, b3;
		JTabbedPane tab;
		JLabel la1,la2,la3;
		JTextField tf1;
		JRadioButton ra1, ra2;
		ButtonGroup group;
		JPanel p1,p2;
		DefaultTableModel model;
		JTable table;
		JScrollPane pane; 


	 //db ���� vari
		String DRIVER ="com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/vtex";
		String USER="user";
		String PWD= "pwd";
	 
		Connection con = null;
		Statement stmt= null;
		ResultSet rs= null;

	   
	 //cons //������ ����
	 public Login(){
		 
		 super("Login"); //������ �̸� ����
		 student = new Student();
		System.out.println("LOGIN");

	
	  //�����ڰ� ȣ�� �Ǹ鼭 �����ӿ� ��ġ�� ������Ʈ�� ���� �����Ѵ�. //������ ����

	  tab = new JTabbedPane();
	  p1= new JPanel();
	 
	  la1 = new JLabel("ID",JLabel.RIGHT);
	  la2 = new JLabel("PWD",JLabel.RIGHT);
	  
	  table = new JTable(model);
	  pane = new JScrollPane(table);
	  
	  //���̺��� ������, ����������, ������ ���� �� �����̴�.
	  table.getTableHeader().setReorderingAllowed(false);
	  table.getTableHeader().setResizingAllowed(false);
	  table.getTableHeader().setBackground(Color.white);
	  
	   
	  tf1= new JTextField();
	  tf2= new JPasswordField();
	  
	  b1= new JButton("�α���");
	  b2= new JButton("ȸ������");
	  b3= new JButton("����");
	  
	 //�ǳ� p1�� ���̾ƿ� Ÿ���� null �� �����ϰ�, �� ������Ʈ�� ��ġ�� ���� �������ش�. 

	  p1.setLayout(null);
	    
	  la1.setBounds(0,40,100,30);
	  tf1.setBounds(120,40,150,30);
	  
	  la2.setBounds(0,80,100,30);
	  tf2.setBounds(120,80,150,30);
	  
	  b1.setBounds(285,40,75,70);
	  b2.setBounds(115,130,100,30);
	  b3.setBounds(225,130,100,30);
	  
	  p1.add(la1);
	  p1.add(tf1);
	  
	  p1.add(la2);
	  p1.add(tf2);
	  
	  p1.add(b1);
	  p1.add(b2);
	  p1.add(b3);
	  

	  //�ٸ� �� ����� �߰������� �ʾ�����, p1 �ǳ��� �ǿ� �־��ְ�, ���� ȭ�� ��� ��ġ���ش�

	  tab.addTab("�α���", null,p1,"�α���");
	  this.getContentPane().add("Center", tab);
	  
	   
	  //�̺�Ʈ ���. ����ؾ��� ��ư�� 3���̴�. �̺�Ʈ ��ϵ� 3���� ���ش�.
	  b1.addActionListener(this); 
	  b2.addActionListener(this);
	  b3.addActionListener(this);
	    
	  setVisible(true);
	  setBounds((res.width)/3,(res.height)/3,430, 260);
	  
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }
//////////////////������ ��//////////////////////////////
	 
	 public void actionPerformed(ActionEvent e){
		 if(e.getSource()==b1) {//�α���
			 
			 String loid = tf1.getText();//���̵� �޾ƿ���
			 String lopw = tf2.getText();//�н����� �޾ƿ���
			 this.student = new Student();
			 if (loid ==""||lopw=="")	{//���̵� �н����� �Է��� �� ���� ���
				 JOptionPane.showMessageDialog(null, "���̵� �н����带 �Է����ֽʽÿ�.");
				}
			 else {
				 if (student.isStudentExist(loid, lopw)) {//�α����� ����� �Ǿ��� ���
					 student.loginStudent(loid, lopw);
					 
					 dispose();//�α���â ����
					 MainPage MP = new MainPage(student);
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "���������ʴ� �����Դϴ�.");
				 }
			 }

		 }
		 
		 if(e.getSource()==b2) {//ȸ������
			 dispose();
			 NewJoin NJ = new NewJoin();//ȸ�����Ի����ϱ�
		 
		 }

		if(e.getSource()==b3) {//����
			System.exit(0);//����������
		}
		 
	 }
}
