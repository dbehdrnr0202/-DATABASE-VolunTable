package VolunEx;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


//로그인 페이지 프레임 클래스
public class MainPage extends JFrame implements ActionListener{
	
	Student student;

	
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//해상도의 좌표를 받는 매서드를 사용하기 위한 선언
	
	JPanel p1; //패널
	JButton[] btimetable = new JButton [30];
	JButton[] vtimetable = new JButton [30];
	JLabel imla0, imla1, imla2, imla3, la1; //라벨쓰
	JLabel info;
	JButton b1,b2, b3, b4; //버튼 넣기

	
	MainPage(Student student) {
		super("VOLUNTABLE - 메인");//프레임 생성과 이름설정
		System.out.println("MAIN");

		this.student = student;
		

		

		
		ImageIcon[] ColIcon = new ImageIcon[8];//컬러 아이콘이 들어간 블록
		
		ColIcon[0] = new ImageIcon("BColRed.png");
		ColIcon[1] = new ImageIcon("BColOrange.png");
		ColIcon[2] = new ImageIcon("BColYellow.png");
		ColIcon[3] = new ImageIcon("BColGreen.png");
		ColIcon[4] = new ImageIcon("BColBlue.png");
		ColIcon[5] = new ImageIcon("BColNavy.png");
		ColIcon[6] = new ImageIcon("BColPurple.png");
		ColIcon[7] = new ImageIcon("BColGrey.png");
		////////컬러 아이콘 생성////////////////
		
		p1 = new JPanel();//기본 패널생성
		
		imla0 = new JLabel();
        ImageIcon icon0 = new ImageIcon("MPborder.png");
        imla0.setIcon(icon0);//달력 이미지 라벨생성
		
		imla1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("CalImage.png");
        imla1.setIcon(icon1);//달력 이미지 라벨생성
        
        imla2 = new JLabel();
        ImageIcon icon2 = new ImageIcon("MenuLine.png");
        imla2.setIcon(icon2);//메뉴 밑에 언더바
        
        imla3 = new JLabel();
        ImageIcon icon3 = new ImageIcon("VTlabel.png");
        imla3.setIcon(icon3);//볼룬테이블 라벨
        
        la1 = new JLabel("MENU",JLabel.CENTER);
        la1.setFont(la1.getFont().deriveFont(60f));
        //String sinfo = this.student.getsInfo().name+" / "+this.student.getsInfo().home+" / "+this.student.getsInfo().school;
        //info = new JLabel(sinfo,JLabel.CENTER);
        /////버튼 생성/////////
        b1 = new JButton("내 시간표 수정");//버튼
        b1.setFont(b1.getFont().deriveFont(16f));
        b2 = new JButton("봉사 찾기/신청");
        b2.setFont(b2.getFont().deriveFont(16f));
        b3 = new JButton("프로필 수정");
        b3.setFont(b3.getFont().deriveFont(16f));
        b4 = new JButton("Logout");
        b4.setFont(b4.getFont().deriveFont(16f));
    
        
       
        
        
        
        //////////////////////////
        p1.setLayout(null);
        
        for(int i=0; i<student.getSchedule().size() ; i++){
        	
        	timeBlock temptb = student.getSchedule().get(i);
        	btimetable[i] = new JButton();
        	
        	btimetable[i].setText(temptb.title);
        	btimetable[i].setBounds(200+(temptb.day.indexOf("1"))*50,((temptb.sTime-8)*25)+75, 50, (temptb.eTime-temptb.sTime)*25);//끝시간 -시작시간//);
        	
        	for(int j=0; j<7; j++){
        		if(temptb.color == j){
        		btimetable[i].setIcon(ColIcon[j]);
        		}
        	}
        	
        	btimetable[i].setHorizontalTextPosition(SwingConstants.CENTER);
        	btimetable[i].setFont(btimetable[i].getFont().deriveFont(6f));
        	
        	

        	if (student.getSchedule().get(i).valid==1)	{
        		p1.add(btimetable[i]);
        	}
        }//시간표버튼 배치 완료
        
        for(int i=0; i<student.getvolapplication().size() ; i++){
        	Volunteer temptb = student.getvolapplication().get(i);
        	vtimetable[i] = new JButton();
        	
        	vtimetable[i].setText("봉사"+i);
        	vtimetable[i].setBounds(200+(temptb.actWkdy.indexOf("1"))*50,((temptb.actBeginTm-8)*25)+75, 50, (temptb.actEndTm-temptb.actBeginTm)*25);//끝시간 -시작시간//);
        	
        	
        	vtimetable[i].setIcon(ColIcon[7]);
        	
        	
        	vtimetable[i].setHorizontalTextPosition(SwingConstants.CENTER);
        	vtimetable[i].setFont(btimetable[i].getFont().deriveFont(6f));
        	
        	
        	p1.add(vtimetable[i]);
        }//봉사버튼 배치 완료
        
        
       
        ////라벨/////
        imla0.setBounds(30,20,925,470);
        imla1.setBounds(150,50,400,400);
        la1.setBounds(625,70,225,50);
        imla2.setBounds(595,140,280,5);
        imla3.setBounds(50,30,80,450);
        /////////////
        
        //버튼//
        b1.setBounds(625,175,225,50);
        b2.setBounds(625,275,225,50);
        b3.setBounds(625,375,225,50);
        b4.setBounds(825,508,100,30);
        /////////
        
        
        
        /////////
        p1.add(imla0);
        p1.add(imla1);
        p1.add(imla2);
        p1.add(imla3);
        p1.add(la1);
        
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);//프레임에 기본 버튼 추가 create exit
        
       ////시간표 버튼 만들기///
        
        
        

        
        
        this.getContentPane().add( p1);
        
        
        b1.addActionListener(this); 
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);  
        for (int i=0; i<student.getvolapplication().size(); i++) {
			 vtimetable[i].addActionListener(this);
		}
        setVisible(true);
        setBounds((res.width)/4,(res.height)/4,1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}//생성자 끝
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			//스케줄 관리 열고 메인 닫기//
			EventQueue.invokeLater(new Runnable() {

				   public void run() {

				    try {

				     ManageSchedule MS= new ManageSchedule(student);

				     MS.setVisible(true);
				     
				     dispose();
				    } catch (Exception e) {

				     e.printStackTrace();

				    }

				   }

				  });
			
		}
		
		if(e.getSource()==b2) {
			///봉사찾기 열고 메인 닫기//
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {

				    try {

				     SearchVol SV = new SearchVol(student);

				     SV.setVisible(true);
				     
				     dispose();
				    } catch (Exception e) {

				     e.printStackTrace();

				    }

				} 

			});
			
		}
		
		if(e.getSource()==b3) {
			SetProfile SP = new SetProfile(student);
			dispose();
		}
		
		if(e.getSource()==b4) {
			System.exit(0);
		}
		
		for (int i=0; i<student.getvolapplication().size(); i++) {
			if(e.getSource() == vtimetable[i]) {
				VolInfo vi = student.showVolInfo((this.student.getvolapplication().get(i)).progrmRegistNo);
				SeeInfo SI = new SeeInfo(vi);
			}
		}
		
	}
	
}
