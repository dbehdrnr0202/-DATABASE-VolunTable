package ����GUI����;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


//�α��� ������ ������ Ŭ����
public class SeeInfo extends JFrame implements ActionListener{
	
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//�ػ��� ��ǥ�� �޴� �ż��带 ����ϱ� ���� ����
	
	JPanel p1;
	JLabel la1, la2, la3, la4, la5, la6, la7, la8, la9, la10,la11;
	
	
	JButton b1;
	
	VolInfo vf;
	SeeInfo(VolInfo vf) {
	    
		super("VOLUNTABLE - ���� ������");//������ ������ �̸�����
		
		this.vf = vf;
		String[] Days = {"��", "ȭ", "��", "��", "��", "��", "��"};
		
		
		p1 = new JPanel();//�⺻ �гλ���
		
		la1 = new JLabel(" ��  ��  �� : " + vf.progrmSj, JLabel.LEFT);
		la2 = new JLabel(" �� �� �� �� : " + vf.actPlace, JLabel.LEFT);
		la3 = new JLabel(" �� �� �� �� : "+ Days[vf.actWkdy.indexOf("1")] , JLabel.LEFT);
		la4 = new JLabel("���� �� �� �� : " + vf.noticeBgnde, JLabel.LEFT);
		la5 = new JLabel("���� �� �� �� : " +vf.noticeEndde, JLabel.LEFT);
		la6 = new JLabel("Ȱ�� ������ : " + vf.progrmBgnde , JLabel.LEFT);
		la7 = new JLabel("Ȱ�� ������ : " + vf.progrmEndde , JLabel.LEFT);
		la8 = new JLabel("���� ���۽ð� : " + vf.actBeginTm , JLabel.LEFT);
		la9 = new JLabel("���� �����ð� : " + vf.actEndTm , JLabel.LEFT);
		la10 = new JLabel(" �� ȭ �� ȣ : " + vf.telno , JLabel.LEFT);
		la11 = new JLabel(" �� �� ��  : " +vf.email, JLabel.LEFT);
	
	
		
		b1 = new JButton("Ȯ��");
	////////////���� ��///////////////
		la1.setBounds(25,30,400,50);
		la2.setBounds(25,90,400,50);
		la3.setBounds(280,90,400,50);
		la4.setBounds(25,150,400,50);
		la5.setBounds(280,150,400,50);
		la6.setBounds(25,210,400,50);
		la7.setBounds(280,210,400,50);
		la8.setBounds(25,270,400,50);
		la9.setBounds(280,270,400,50);
		la10.setBounds(25,330,400,50);
		la11.setBounds(280,330,400,50);
	
		
		
		
	
		b1.setBounds(450,400,95,30);
	//////////��ġ ��////////////////////////
		
		p1.setLayout(null);
		
		p1.add(la1);
		p1.add(la2);
		p1.add(la3);
		p1.add(la4);
		p1.add(la5);
		p1.add(la6);
		p1.add(la7);
		p1.add(la8);
		p1.add(la9);
		p1.add(la10);
		p1.add(la11);
		

		
		p1.add(b1);
	////////�г� �߰� ��///////////////////////////
		
		this.getContentPane().add(p1);
        
        
        b1.addActionListener(this); 
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setBounds(5*(res.width)/16,1*(res.height)/4,600,500);
	}
////////////////////������ ��/////////////////////////////
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			dispose();

		}
	}	
///////////////////�׼�������///////////////////////////////

}
