package 데베GUI연습;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


//로그인 페이지 프레임 클래스
public class SeeInfo extends JFrame implements ActionListener{
	
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//해상도의 좌표를 받는 매서드를 사용하기 위한 선언
	
	JPanel p1;
	JLabel la1, la2, la3, la4, la5, la6, la7, la8, la9, la10,la11;
	
	
	JButton b1;
	
	VolInfo vf;
	SeeInfo(VolInfo vf) {
	    
		super("VOLUNTABLE - 봉사 상세정보");//프레임 생성과 이름설정
		
		this.vf = vf;
		String[] Days = {"월", "화", "수", "목", "금", "토", "일"};
		
		
		p1 = new JPanel();//기본 패널생성
		
		la1 = new JLabel(" 봉  사  명 : " + vf.progrmSj, JLabel.LEFT);
		la2 = new JLabel(" 봉 사 장 소 : " + vf.actPlace, JLabel.LEFT);
		la3 = new JLabel(" 봉 사 요 일 : "+ Days[vf.actWkdy.indexOf("1")] , JLabel.LEFT);
		la4 = new JLabel("모집 시 작 일 : " + vf.noticeBgnde, JLabel.LEFT);
		la5 = new JLabel("모집 마 감 일 : " +vf.noticeEndde, JLabel.LEFT);
		la6 = new JLabel("활동 시작일 : " + vf.progrmBgnde , JLabel.LEFT);
		la7 = new JLabel("활동 마감일 : " + vf.progrmEndde , JLabel.LEFT);
		la8 = new JLabel("봉사 시작시간 : " + vf.actBeginTm , JLabel.LEFT);
		la9 = new JLabel("봉사 마감시간 : " + vf.actEndTm , JLabel.LEFT);
		la10 = new JLabel(" 전 화 번 호 : " + vf.telno , JLabel.LEFT);
		la11 = new JLabel(" 이 메 일  : " +vf.email, JLabel.LEFT);
	
	
		
		b1 = new JButton("확인");
	////////////생성 끝///////////////
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
	//////////배치 끝////////////////////////
		
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
	////////패널 추가 끝///////////////////////////
		
		this.getContentPane().add(p1);
        
        
        b1.addActionListener(this); 
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setBounds(5*(res.width)/16,1*(res.height)/4,600,500);
	}
////////////////////생성자 끝/////////////////////////////
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			dispose();

		}
	}	
///////////////////액션퍼폼끝///////////////////////////////

}
