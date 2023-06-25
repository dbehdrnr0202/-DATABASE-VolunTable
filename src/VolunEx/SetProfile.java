package VolunEx;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;



//�α��� ������ ������ Ŭ����
public class SetProfile extends JFrame implements ActionListener{
	
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//�ػ��� ��ǥ�� �޴� �ż��带 ����ϱ� ���� ����
	
	JPanel p1;
	JLabel la1, la2, la3, la4;
	JTextField tf1, tf2, tf3, tf4;
	JButton b1;
	
	Student student;
	SetProfile(Student student) {
	    
		super("VOLUNTABLE - �� ������ ����");//������ ������ �̸�����
		System.out.println("SET PROFILE");

		this.student = student;
		p1 = new JPanel();//�⺻ �гλ���
		
		la1 = new JLabel("�̸� :", JLabel.RIGHT);
		la2 = new JLabel("���� :", JLabel.RIGHT);
		la3 = new JLabel("�б� �̸� :", JLabel.RIGHT);
		la4 = new JLabel("�� �ּ� :", JLabel.RIGHT);
		
		tf1 = new JTextField(student.getsInfo().name);
		tf2 = new JTextField(Integer.toString(student.getsInfo().age));
		tf3 = new JTextField(student.getsInfo().school);
		tf4 = new JTextField(student.getsInfo().home);
		
		b1 = new JButton("�����Ϸ�");
	////////////���� ��///////////////
		la1.setBounds(25,30,50,50);
		la2.setBounds(25,90,50,50);
		la3.setBounds(5,150,70,50);
		la4.setBounds(25,210,50,50);
		
		tf1.setBounds(90,40,140,25);
		tf2.setBounds(90,100,140,25);
		tf3.setBounds(90,160,140,25); 
		tf4.setBounds(90,220,140,25);
		
		b1.setBounds(180,280,95,30);
	//////////��ġ ��////////////////////////
		
		p1.setLayout(null);
		
		p1.add(la1);
		p1.add(la2);
		p1.add(la3);
		p1.add(la4);
		
		p1.add(tf1);
		p1.add(tf2);
		p1.add(tf3);
		p1.add(tf4);
		
		p1.add(b1);
	////////�г� �߰� ��///////////////////////////
		
		this.getContentPane().add(p1);
        
        
        b1.addActionListener(this); 
        
        this.setVisible(true);
		this.setBounds(2*(res.width)/3,(res.height)/4,320,380);
	}
////////////////////������ ��/////////////////////////////
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
		
			student.getsInfo().name = tf1.getText();
			student.getsInfo().age = Integer.parseInt(tf2.getText());
			student.getsInfo().school = tf3.getText();
			student.getsInfo().home = tf4.getText();
			student.updateStudentInfo(student.getsInfo());
			MainPage MP = new MainPage(student);
			this.dispose();
		}
	}	
///////////////////�׼�������///////////////////////////////
	
}
