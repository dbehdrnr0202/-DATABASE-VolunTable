package VolunEx;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NewJoin extends JFrame implements ActionListener{
	   JLabel la_name, la_id, la_pwd, la_age, la_schoolname, la_location, la_title;
	   JTextField tf_name, tf_id, tf_pwd, tf_age, tf_schoolname, tf_location;
	   JButton bu_join, bu_cancel, bu_idCheck;
	   Student student;
	  
	   StudentInfo sInfo;
	   //생성자
		boolean duplicated;
		boolean checked;
	   public NewJoin() {
		   super("Voluntable - 회원가입");
			System.out.println("NEW JOIN");

		   la_title = new JLabel("회원 가입");
		   la_title.setFont(new Font("Dialog",Font.BOLD, 20));
	       la_title.setForeground(Color.blue);
	       la_name = new JLabel("이름", JLabel.RIGHT);
	       la_id = new JLabel("아이디",JLabel.RIGHT);
	       la_pwd = new JLabel("PWD",JLabel.RIGHT);
	       la_age = new JLabel("나이",JLabel.RIGHT);
	       la_schoolname = new JLabel("학교이름",JLabel.RIGHT);
	       la_location = new JLabel("집 주소", JLabel.RIGHT);
	       this.duplicated = true;
	       this.checked = false;
	       tf_name = new JTextField("");
	   	
	       tf_id = new JTextField("");
	       tf_pwd = new JTextField("");
	       tf_age = new JTextField("");
	       tf_schoolname = new JTextField("");
	       tf_location = new JTextField("");
	  
	       bu_join = new JButton("회원 등록");
	       bu_cancel = new JButton("취소");
	       bu_idCheck = new JButton("중복 확인");
	  
	       this.student = new Student();
	       this.sInfo = new StudentInfo();
	       /*
	      String data1[][] = new String[0][1];
	      String cols1[] = {"대화명"};
	      DefaultTableModel model = new DefaultTableModel(data1,cols1);
	      JTable table = new JTable(model);
	      JScrollPane roomInfoPane = new JScrollPane(table);
	      */
	  
	       getContentPane().setLayout(null); //배치를 프로그래머가 하려고
	  
	       //타이틀
	       getContentPane().add(la_title).setBounds(100,4,130,20);
	       //라벨
	       getContentPane().add(la_name).setBounds(18,62,50,50);
	       getContentPane().add(la_id).setBounds(25,122,50,50);
	       getContentPane().add(la_pwd).setBounds(18,182,50,50);
	       getContentPane().add(la_age).setBounds(25,242,50,50);
	       getContentPane().add(la_schoolname).setBounds(18,302,50,50);
	       getContentPane().add(la_location).setBounds(18,362,50,50);
	  
	       //텍스트 필드.
	       getContentPane().add(tf_name).setBounds(82,72,140,25);
	       getContentPane().add(tf_id).setBounds(82,132,140,25);
	       getContentPane().add(tf_pwd).setBounds(82,192,140,25); 
	       getContentPane().add(tf_age).setBounds(82,252,140,25);
	       getContentPane().add(tf_schoolname).setBounds(82,312,140,25);
	       getContentPane().add(tf_location).setBounds(82, 372, 140, 25);

	  
	       //버튼 위치.
	       getContentPane().add(bu_join).setBounds(65,400,100,30); 
	       getContentPane().add(bu_cancel).setBounds(180,400,95,30);
	       getContentPane().add(bu_idCheck).setBounds(226,130,90,25);
	  
	       setBounds(500,100,340,500);
	       setVisible(true);
	  
	  
	       //이벤트 등록
	       bu_join.addActionListener(this);
	       bu_cancel.addActionListener(this);
	       bu_idCheck.addActionListener(this);
	   }
	  
	   public void actionPerformed(ActionEvent e){
		   if(e.getSource()==bu_join) {//가입버튼을 누를 경우
				if (!this.checked)	{
					 JOptionPane.showMessageDialog(null, "아이디 중복체크를 하십시오.");
				}
				else if (this.duplicated==true) {
					 JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
				}
				else {
					this.sInfo.name = tf_name.getText();
					this.sInfo.ID = tf_id.getText();
					this.sInfo.PW = tf_pwd.getText();
					this.sInfo.age = Integer.parseInt(tf_age.getText());
					this.sInfo.school = tf_schoolname.getText();
					this.sInfo.home = tf_location.getText();
				 
					if ((sInfo.name=="")||(sInfo.ID=="")||(sInfo.PW == "")||(sInfo.school=="")||(sInfo.home==""))
						 JOptionPane.showMessageDialog(null, "빈칸을 채워주세요");
					else {
						student.setsInfo(sInfo);
						student.joinNewAccount(sInfo);
						dispose();//로그인창 종료
						Login login = new Login();
						
					}
					
				}
				
		   }
			 if (e.getSource()==bu_cancel) {
				 dispose();
				 Login login = new Login();
			 }
			 if (e.getSource() == bu_idCheck)	{
				 this.sInfo.ID = tf_id.getText();
				 this.student.setsInfo(sInfo);
				 this.checked = true;
				 if (student.isIdDuplicated(this.sInfo.ID))	{
					 JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
					 this.duplicated = true;
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "중복되지않는 아이디입니다.");
					 this.duplicated = false;
				 }
			 } 
			 
	   }
}
