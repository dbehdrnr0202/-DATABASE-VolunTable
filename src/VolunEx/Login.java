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
	//해상도 좌표 받는 메서드 이용하기 위해 객체 선언
	 

	//프레임 디자인을 위한 각종 컴포넌트 선언
		JPasswordField tf2;  //이 컴포넌트는 TF 와는 다르게 입력되는 값을 * 로 표현해준다. 비밀번호 입력용이다.
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


	 //db 관련 vari
		String DRIVER ="com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/vtex";
		String USER="user";
		String PWD= "pwd";
	 
		Connection con = null;
		Statement stmt= null;
		ResultSet rs= null;

	   
	 //cons //생성자 시작
	 public Login(){
		 
		 super("Login"); //프레임 이름 설정
		 student = new Student();
		System.out.println("LOGIN");

	
	  //생성자가 호출 되면서 프레임에 배치할 콤포넌트를 각각 선언한다. //디자인 시작

	  tab = new JTabbedPane();
	  p1= new JPanel();
	 
	  la1 = new JLabel("ID",JLabel.RIGHT);
	  la2 = new JLabel("PWD",JLabel.RIGHT);
	  
	  table = new JTable(model);
	  pane = new JScrollPane(table);
	  
	  //테이블의 재정렬, 사이즈조절, 배경색에 대한 값 조정이다.
	  table.getTableHeader().setReorderingAllowed(false);
	  table.getTableHeader().setResizingAllowed(false);
	  table.getTableHeader().setBackground(Color.white);
	  
	   
	  tf1= new JTextField();
	  tf2= new JPasswordField();
	  
	  b1= new JButton("로그인");
	  b2= new JButton("회원가입");
	  b3= new JButton("종료");
	  
	 //판넬 p1의 레이아웃 타입을 null 로 선언하고, 각 컴포넌트의 위치를 직접 세팅해준다. 

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
	  

	  //다른 탭 목록을 추가하지는 않았지만, p1 판넬을 탭에 넣어주고, 탭을 화면 가운데 배치해준다

	  tab.addTab("로그인", null,p1,"로그인");
	  this.getContentPane().add("Center", tab);
	  
	   
	  //이벤트 등록. 사용해야할 버튼은 3개이다. 이벤트 등록도 3개를 해준다.
	  b1.addActionListener(this); 
	  b2.addActionListener(this);
	  b3.addActionListener(this);
	    
	  setVisible(true);
	  setBounds((res.width)/3,(res.height)/3,430, 260);
	  
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }
//////////////////생성자 끝//////////////////////////////
	 
	 public void actionPerformed(ActionEvent e){
		 if(e.getSource()==b1) {//로그인
			 
			 String loid = tf1.getText();//아이디 받아오기
			 String lopw = tf2.getText();//패스워드 받아오기
			 this.student = new Student();
			 if (loid ==""||lopw=="")	{//아이디나 패스워드 입력을 덜 했을 경우
				 JOptionPane.showMessageDialog(null, "아이디나 패스워드를 입력해주십시오.");
				}
			 else {
				 if (student.isStudentExist(loid, lopw)) {//로그인이 제대로 되었을 경우
					 student.loginStudent(loid, lopw);
					 
					 dispose();//로그인창 종료
					 MainPage MP = new MainPage(student);
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "존재하지않는 계정입니다.");
				 }
			 }

		 }
		 
		 if(e.getSource()==b2) {//회원가입
			 dispose();
			 NewJoin NJ = new NewJoin();//회원가입생성하기
		 
		 }

		if(e.getSource()==b3) {//종료
			System.exit(0);//나가버리기
		}
		 
	 }
}
