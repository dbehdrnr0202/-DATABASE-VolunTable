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


//로그인 페이지 프레임 클래스
public class SearchVol extends JFrame implements ActionListener{
	
	VolInfo vi = new VolInfo();
	
	 int index1;//생성 리스트인덱스
	 int index2;//추가 리스트인덱스
	 
	 ArrayList<Volunteer> real_search_list = new ArrayList<Volunteer>();
	 ArrayList<Volunteer> real_admit_list = new ArrayList<Volunteer>();//진짜 서치리스트
	 Vector<String> search_list = new Vector<String>();
	 Vector<String> admit_list = new Vector<String>();///생성 스트링리스트, 추가 스트링 리스트
	
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//해상도의 좌표를 받는 매서드를 사용하기 위한 선언
	
	JPanel p1; //패널
	JLabel imla0, imla1, la1, la2, la3, la6,la7; //라벨
	JTextField tf2,tf3; //검색에 들어갈 텍스트필드
	JCheckBox cb0, cb1;//체크박스
	JList ls1, ls2; //리스트 띄워줌
	JButton b1,b2, b3, b4, b5; //버튼 넣기
	JScrollPane p2, p3;
/////////컴포넌트 정의 끝////////////////////////////////////
	Student student;
	String[] Days = {"월", "화", "수", "목", "금", "토", "일"};
	
SearchVol(Student student) {
	    
		super("VOLUNTABLE - 봉사 찾기 / 신청");//프레임 생성과 이름설정
		this.student = student;
		System.out.println("SEARCH VOL");

		p1 = new JPanel();//기본 패널생성
		
		
		///학생이 이미 등록한 봉사 리스트 출력해주는 것
		for(int i=0; i<student.getvolapplication().size(); i++) {
			
			Volunteer tempblock = student.getvolapplication().get(i);
			
	
			String tempstr = "봉사 제목:"+ tempblock.progrmSj+"/  봉사 장소:  "+tempblock.actPlace + "  요일:"+Days[tempblock.actWkdy.indexOf("1")]
			+"/  봉사 시작일:  "+tempblock.progrmBgnde+"/  봉사 종료일:  "+tempblock.progrmEndde +"/  시작시간:  "+tempblock.actBeginTm + "/  종료시간:"+tempblock.actEndTm + " "+"/"
			;
			
			real_admit_list.add(student.getvolapplication().get(i));
			admit_list.add(tempstr);
		}
		
		
		//라벨생성//
		imla0 = new JLabel();
        ImageIcon icon0 = new ImageIcon("MPborder.png");
        imla0.setIcon(icon0);//보더 라벨생성
        
        imla1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("CBborder2.png");
        imla1.setIcon(icon1);//보더 라벨생성
        
        la1 = new JLabel("선호 장소 :",JLabel.CENTER);
        la2 = new JLabel("시작 날짜 : ",JLabel.RIGHT);
        la3 = new JLabel("종료 날짜 : ",JLabel.RIGHT);
        la6 = new JLabel("찾기 목록",JLabel.CENTER);
        la7 = new JLabel("신청 내역",JLabel.CENTER);
       
        ///텍스트 필드 생성//
        tf2 = new JTextField();//시작시간
        tf3 = new JTextField();//종료시간
        
        ///체크박스 생성//
        cb0 = new JCheckBox("집 근처");
        cb1 = new JCheckBox("학교 근처");
  
        //리스트 생성//
        ls1 = new JList(search_list);
        ls2 = new JList(admit_list);
        

        //버튼생성///
        b1 = new JButton("찾기");//버튼
        b1.setFont(b1.getFont().deriveFont(16f));
        b2 = new JButton("신청 하기");
        b2.setFont(b2.getFont().deriveFont(16f));
        b3 = new JButton("신청 취소");
        b3.setFont(b3.getFont().deriveFont(16f));
        b4 = new JButton("상세 정보");
        b4.setFont(b4.getFont().deriveFont(16f));
        b5 = new JButton("확인");
        b5.setFont(b4.getFont().deriveFont(16f));      
///////////컴포넌트 생성 끝 /////////////////////////////////////////////////
        
        
        p1.setLayout(null);
        //패널의 레이아웃
       
        ////라벨/////
        imla0.setBounds(30,20,925,470);
        imla1.setBounds(55,70,260,80);
        la1.setBounds(57,85,125,50);//선호 장소
        la1.setFont(la6.getFont().deriveFont(18f));
        la2.setBounds(310,85,125,50);//시작시간
        la2.setFont(la6.getFont().deriveFont(18f));
        la3.setBounds(560,85,125,50);//종료시간
        la3.setFont(la6.getFont().deriveFont(18f));
        la6.setBounds(40,145,350,75);
        la6.setFont(la6.getFont().deriveFont(20f));
        la7.setBounds(590,145,350,75);
        la7.setFont(la7.getFont().deriveFont(20f));
        
        ///텍스트////
        tf2.setBounds(435,95,125,30);
        tf3.setBounds(685,95,125,30);
        
        //체크박스//
        cb0.setBounds(190,75,120,30);
        cb0.setFont(cb0.getFont().deriveFont(16f));
        cb1.setBounds(190,115,120,30);
        cb1.setFont(cb1.getFont().deriveFont(16f));
  
        //버튼//
        b1.setBounds(835,60,100,100);
        b2.setBounds(415,235,150,50);
        b3.setBounds(415,310,150,50);
        b4.setBounds(415,385,150,50);
        b5.setBounds(825,508,100,30);
////////바운드 설정 끝 /////////////////////////////////////////////////
        
        
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
////////컴포넌트 추가 끝///////////////////////////////////////////
        
        ls1.addListSelectionListener(new selectListListener1());
        ls2.addListSelectionListener(new selectListListener2());
        
        p2 = new JScrollPane(ls1);
        p3 = new JScrollPane(ls2);
        
        p2.setBounds(40,200,350,275);
        p3.setBounds(590,200,350,275);
        
        p1.add(p2);
        p1.add(p3);
        
        
///////스크롤 추가 부분/////////////////////////////////
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
///////////////////////생성자 끝//////////////////////////////////////////////
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			if(cb0.isSelected()) {
				String bgn = tf2.getText();
				String end = tf3.getText();
				real_search_list = (ArrayList<Volunteer>) this.student.searchVolunteer(bgn, end, true);
			}	//월
			else if(cb1.isSelected()) {
				String bgn = tf2.getText();
				String end = tf3.getText();
				real_search_list = (ArrayList<Volunteer>) this.student.searchVolunteer(bgn, end, false);
			}
			
			search_list = new Vector<String>();
			for(int i=0; i<real_search_list.size(); i++) {
				
				Volunteer tempblock = real_search_list.get(i);
				
		
				String tempstr = "봉사 제목:"+ tempblock.progrmSj+"/  봉사 장소:  "+tempblock.actPlace + "  요일:"+Days[tempblock.actWkdy.indexOf("1")]
				+"/  봉사 시작일:  "+tempblock.progrmBgnde+"/  봉사 종료일:  "+tempblock.progrmEndde +"/  시작시간:  "+tempblock.actBeginTm + "/  종료시간:"+tempblock.actEndTm + " "+"/"
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
		    index1 = ls1.getSelectedIndex(); // 리스트에서 인덱스번호 가져오기
		    
		   }
	}
	
	class selectListListener2 implements ListSelectionListener {

		   public void valueChanged(ListSelectionEvent event) {
		    String selection2 = (String) ls2.getSelectedValue();
		    index2 = ls2.getSelectedIndex(); // 리스트에서 인덱스번호 가져오기
		   
		   }
	}
	
	
}