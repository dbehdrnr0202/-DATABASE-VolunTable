package VolunEx;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;



//로그인 페이지 프레임 클래스
public class SetProfile extends JFrame implements ActionListener{
	
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//해상도의 좌표를 받는 매서드를 사용하기 위한 선언
	
	JPanel p1;
	JLabel la1, la2, la3, la4;
	JTextField tf1, tf2, tf3, tf4;
	JButton b1;
	
	Student student;
	SetProfile(Student student) {
	    
		super("VOLUNTABLE - 내 프로필 수정");//프레임 생성과 이름설정
		System.out.println("SET PROFILE");

		this.student = student;
		p1 = new JPanel();//기본 패널생성
		
		la1 = new JLabel("이름 :", JLabel.RIGHT);
		la2 = new JLabel("나이 :", JLabel.RIGHT);
		la3 = new JLabel("학교 이름 :", JLabel.RIGHT);
		la4 = new JLabel("집 주소 :", JLabel.RIGHT);
		
		tf1 = new JTextField(student.getsInfo().name);
		tf2 = new JTextField(Integer.toString(student.getsInfo().age));
		tf3 = new JTextField(student.getsInfo().school);
		tf4 = new JTextField(student.getsInfo().home);
		
		b1 = new JButton("수정완료");
	////////////생성 끝///////////////
		la1.setBounds(25,30,50,50);
		la2.setBounds(25,90,50,50);
		la3.setBounds(5,150,70,50);
		la4.setBounds(25,210,50,50);
		
		tf1.setBounds(90,40,140,25);
		tf2.setBounds(90,100,140,25);
		tf3.setBounds(90,160,140,25); 
		tf4.setBounds(90,220,140,25);
		
		b1.setBounds(180,280,95,30);
	//////////배치 끝////////////////////////
		
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
	////////패널 추가 끝///////////////////////////
		
		this.getContentPane().add(p1);
        
        
        b1.addActionListener(this); 
        
        this.setVisible(true);
		this.setBounds(2*(res.width)/3,(res.height)/4,320,380);
	}
////////////////////생성자 끝/////////////////////////////
	
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
///////////////////액션퍼폼끝///////////////////////////////
	
}
