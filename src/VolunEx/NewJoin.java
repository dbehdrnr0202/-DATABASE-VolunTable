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
	   //������
		boolean duplicated;
		boolean checked;
	   public NewJoin() {
		   super("Voluntable - ȸ������");
			System.out.println("NEW JOIN");

		   la_title = new JLabel("ȸ�� ����");
		   la_title.setFont(new Font("Dialog",Font.BOLD, 20));
	       la_title.setForeground(Color.blue);
	       la_name = new JLabel("�̸�", JLabel.RIGHT);
	       la_id = new JLabel("���̵�",JLabel.RIGHT);
	       la_pwd = new JLabel("PWD",JLabel.RIGHT);
	       la_age = new JLabel("����",JLabel.RIGHT);
	       la_schoolname = new JLabel("�б��̸�",JLabel.RIGHT);
	       la_location = new JLabel("�� �ּ�", JLabel.RIGHT);
	       this.duplicated = true;
	       this.checked = false;
	       tf_name = new JTextField("");
	   	
	       tf_id = new JTextField("");
	       tf_pwd = new JTextField("");
	       tf_age = new JTextField("");
	       tf_schoolname = new JTextField("");
	       tf_location = new JTextField("");
	  
	       bu_join = new JButton("ȸ�� ���");
	       bu_cancel = new JButton("���");
	       bu_idCheck = new JButton("�ߺ� Ȯ��");
	  
	       this.student = new Student();
	       this.sInfo = new StudentInfo();
	       /*
	      String data1[][] = new String[0][1];
	      String cols1[] = {"��ȭ��"};
	      DefaultTableModel model = new DefaultTableModel(data1,cols1);
	      JTable table = new JTable(model);
	      JScrollPane roomInfoPane = new JScrollPane(table);
	      */
	  
	       getContentPane().setLayout(null); //��ġ�� ���α׷��Ӱ� �Ϸ���
	  
	       //Ÿ��Ʋ
	       getContentPane().add(la_title).setBounds(100,4,130,20);
	       //��
	       getContentPane().add(la_name).setBounds(18,62,50,50);
	       getContentPane().add(la_id).setBounds(25,122,50,50);
	       getContentPane().add(la_pwd).setBounds(18,182,50,50);
	       getContentPane().add(la_age).setBounds(25,242,50,50);
	       getContentPane().add(la_schoolname).setBounds(18,302,50,50);
	       getContentPane().add(la_location).setBounds(18,362,50,50);
	  
	       //�ؽ�Ʈ �ʵ�.
	       getContentPane().add(tf_name).setBounds(82,72,140,25);
	       getContentPane().add(tf_id).setBounds(82,132,140,25);
	       getContentPane().add(tf_pwd).setBounds(82,192,140,25); 
	       getContentPane().add(tf_age).setBounds(82,252,140,25);
	       getContentPane().add(tf_schoolname).setBounds(82,312,140,25);
	       getContentPane().add(tf_location).setBounds(82, 372, 140, 25);

	  
	       //��ư ��ġ.
	       getContentPane().add(bu_join).setBounds(65,400,100,30); 
	       getContentPane().add(bu_cancel).setBounds(180,400,95,30);
	       getContentPane().add(bu_idCheck).setBounds(226,130,90,25);
	  
	       setBounds(500,100,340,500);
	       setVisible(true);
	  
	  
	       //�̺�Ʈ ���
	       bu_join.addActionListener(this);
	       bu_cancel.addActionListener(this);
	       bu_idCheck.addActionListener(this);
	   }
	  
	   public void actionPerformed(ActionEvent e){
		   if(e.getSource()==bu_join) {//���Թ�ư�� ���� ���
				if (!this.checked)	{
					 JOptionPane.showMessageDialog(null, "���̵� �ߺ�üũ�� �Ͻʽÿ�.");
				}
				else if (this.duplicated==true) {
					 JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.");
				}
				else {
					this.sInfo.name = tf_name.getText();
					this.sInfo.ID = tf_id.getText();
					this.sInfo.PW = tf_pwd.getText();
					this.sInfo.age = Integer.parseInt(tf_age.getText());
					this.sInfo.school = tf_schoolname.getText();
					this.sInfo.home = tf_location.getText();
				 
					if ((sInfo.name=="")||(sInfo.ID=="")||(sInfo.PW == "")||(sInfo.school=="")||(sInfo.home==""))
						 JOptionPane.showMessageDialog(null, "��ĭ�� ä���ּ���");
					else {
						student.setsInfo(sInfo);
						student.joinNewAccount(sInfo);
						dispose();//�α���â ����
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
					 JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.");
					 this.duplicated = true;
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "�ߺ������ʴ� ���̵��Դϴ�.");
					 this.duplicated = false;
				 }
			 } 
			 
	   }
}
