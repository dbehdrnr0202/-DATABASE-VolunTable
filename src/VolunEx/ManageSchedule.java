package VolunEx;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


//로그인 페이지 프레임 클래스
public class ManageSchedule extends JFrame implements ActionListener{

///////////클래스 변수 선언//////////////////////////
	 int index1;//생성 리스트인덱스
	 int index2;//추가 리스트인덱스
	 
	 Vector<String> make_list = new Vector<String>();
	 Vector<String> add_list = new Vector<String>();///생성 스트링리스트, 추가 스트링 리스트
	 ArrayList<timeBlock> real_make_list = new ArrayList();
	 ArrayList<timeBlock> real_add_list = new ArrayList();
	 ArrayList<timeBlock> temp_schedule = new ArrayList();

	 
	 int Day = 0;
	 String[] Days = {"월", "화", "수", "목", "금", "토", "일"};
	 String[] CombCol = {"Red", "Orange", "Yellow", "Green", "Blue", "Navy", "Purple"};
	

	
//////////// 컴포넌트 정의///////////////////////////
	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();//해상도의 좌표를 받는 매서드를 사용하기 위한 선언
	
	JPanel p1; //패널
	JLabel imla0, imla1, la1, la2, la3, la4, la5,la6,la7; //라벨
	JTextField tf1,tf2,tf3; //검색에 들어갈 텍스트필드
	JCheckBox cb0, cb1, cb2, cb3, cb4, cb5, cb6;//체크박스
	JComboBox cmb1;
	JList ls1, ls2; //리스트 띄워줌
	JButton b1,b2, b3, b4, b5; //버튼 넣기
	JScrollPane p2, p3;
/////////컴포넌트 정의 끝////////////////////////////////////
	
	Student student;
////////생성자 시작///////////////////////////////////
	ManageSchedule(Student student) {
		super("VOLUNTABLE - 내 시간표 수정");//프레임 생성과 이름설정
		this.student = student;
		 
		System.out.println("MANAGE SCHEDULE");

		for(int i=0; i<student.getSchedule().size(); i++) {
		
			timeBlock tempblock = student.getSchedule().get(i);
			
			tempblock.day.indexOf("1");
			String tempstr = "과목명:"+tempblock.title+"/  시작시간:  "+tempblock.sTime + "/  종료시간:"+tempblock.eTime + " "+"/"
			+ "  요일:"+Days[tempblock.day.indexOf("1")]+" /  색: "+ CombCol[tempblock.color];
			
			System.out.println(tempstr);
			if(student.getSchedule().get(i).valid == 0) {
				real_make_list.add(student.getSchedule().get(i));
				make_list.add(tempstr);
			}
			else{
				real_add_list.add(student.getSchedule().get(i));
				add_list.add(tempstr);
			}
		
		}
		
		
		p1 = new JPanel();//기본 패널생성
		
		//라벨생성//
		imla0 = new JLabel();
        ImageIcon icon0 = new ImageIcon("MPborder.png");
        imla0.setIcon(icon0);//보더 라벨생성
        
        imla1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("CBborder.png");
        imla1.setIcon(icon1);//보더 라벨생성
        
        la1 = new JLabel("과목명( 2~3자 ) :",JLabel.CENTER);
        la2 = new JLabel("시작시간 :   ",JLabel.RIGHT);
        la3 = new JLabel("종료시간 :   ",JLabel.RIGHT);
        la4 = new JLabel("요일          :",JLabel.CENTER);
        la5 = new JLabel("색 지정 :     ",JLabel.RIGHT);
        la6 = new JLabel("생성 목록",JLabel.CENTER);
        la7 = new JLabel("추가 목록",JLabel.CENTER);
        ///텍스트 필드 생성//
        tf1 = new JTextField();//과목명
        tf2 = new JTextField();//시작시간
        tf3 = new JTextField();//종료시간
        
        ///체크박스 생성//
    
        cb0 = new JCheckBox("월");
        cb1 = new JCheckBox("화");
        cb2 = new JCheckBox("수");
        cb3 = new JCheckBox("목");
        cb4 = new JCheckBox("금");
        cb5 = new JCheckBox("토");
        cb6 = new JCheckBox("일");
        
        //콤보박스 생성//
        cmb1 = new JComboBox(CombCol);
        
        //리스트 생성//
        ls1 = new JList(make_list);
        ls2 = new JList(add_list);
        

        //버튼생성///
        b1 = new JButton("생성");//버튼
        b1.setFont(b1.getFont().deriveFont(16f));
        b2 = new JButton("->");
        b2.setFont(b2.getFont().deriveFont(16f));
        b3 = new JButton("<-");
        b3.setFont(b3.getFont().deriveFont(16f));
        b4 = new JButton("삭제");
        b4.setFont(b4.getFont().deriveFont(16f));
        b5 = new JButton("확인");
        b5.setFont(b5.getFont().deriveFont(16f));      
///////////컴포넌트 생성 끝 /////////////////////////////////////////////////
        
        
        p1.setLayout(null);

        //패널의 레이아웃
       

        ////라벨/////
        imla0.setBounds(30,20,925,470);
        imla1.setBounds(55,115,515,40);
        la1.setBounds(57,60,125,50);//과목명
        la1.setFont(la6.getFont().deriveFont(16f));
        la2.setBounds(310,60,125,50);//시작시간
        la2.setFont(la6.getFont().deriveFont(16f));
        la3.setBounds(560,60,125,50);//종료시간
        la3.setFont(la6.getFont().deriveFont(16f));
        la4.setBounds(80,97,125,75);//요일
        la4.setFont(la6.getFont().deriveFont(16f));
        la5.setBounds(567,97,125,75);//색지정
        la5.setFont(la6.getFont().deriveFont(16f));
        la6.setBounds(40,145,350,75);
        la6.setFont(la6.getFont().deriveFont(20f));
        la7.setBounds(590,145,350,75);
        la7.setFont(la7.getFont().deriveFont(20f));
        
        ///텍스트////
        tf1.setBounds(185,70,125,30);
        tf2.setBounds(435,70,125,30);
        tf3.setBounds(685,70,125,30);
        
        //체크박스//
        cb0.setBounds(195,120,55,30);
        cb1.setBounds(250,120,55,30);
        cb2.setBounds(305,120,55,30);
        cb3.setBounds(360,120,55,30);
        cb4.setBounds(415,120,55,30);
        cb5.setBounds(470,120,55,30);
        cb6.setBounds(525,120,40,30);
        
        //콤보박스//
        cmb1.setBounds(685,120,125,30);
        
        //리스트//
        
        
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
        p1.add(la4);
        p1.add(la5);
        p1.add(la6);
        p1.add(la7);
        p1.add(tf1);
        p1.add(tf2);
        p1.add(tf3);
        
        p1.add(cb0);
        p1.add(cb1);
        p1.add(cb2);
        p1.add(cb3);
        p1.add(cb4);
        p1.add(cb5);
        p1.add(cb6);
        
        
        
        p1.add(cmb1);
        
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
     /////////////////   
        
////////컴포넌트 추가 끝///////////////////////////////////////////
        
        ls1.addListSelectionListener(new selectListListener1());
        ls2.addListSelectionListener(new selectListListener2());
        
        p2 = new JScrollPane(ls1);
        p3 = new JScrollPane(ls2);
        
        
        p2.setBounds(40,200,350,275);
        p3.setBounds(590,200,350,275);
        
        p1.add(p2);
        p1.add(p3);
        
        
///////////////////스크롤 추가 부분//////////////////////////////
        
        this.getContentPane().add(p1);
  
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
		
		//여기서 템프 
		if(e.getSource()==b1) {
		/////여기서 스캐줄 객체를 입력받은 값으로 객체를 생성한 다음에 리스트에다가 넣어주고, 그 리스트마다 스트링으로 만들어 주면?될 것 같다///
	    ////마지막에 확인 버튼을 누르면 한번에 데이터베이스에 입력하게 끔?//////
		
		
		
		String Color = (String) cmb1.getItemAt(cmb1.getSelectedIndex());
		
		timeBlock tb = new timeBlock();
		tb.setTitle(tf1.getText().trim());
		tb.setsTime(Integer.parseInt(tf2.getText().trim()));
		tb.seteTime(Integer.parseInt(tf3.getText().trim()));
		///////////스트링 입력 받기 끝////////////
			String tempstr = "과목명:"+tf1.getText()+"/  시작시간:  "+tf2.getText() + "/  종료시간:"+tf3.getText() + " "+"/  요일:";
			
			if(cb0.isSelected()) {
				tb.setDay("1000000");
				tempstr += cb0.getText();
			}	//월
			else if(cb1.isSelected()) {
				tb.setDay("0100000");
				tempstr += cb1.getText();
			}

			else if(cb2.isSelected()) {
				tb.setDay("0010000");
				tempstr += cb2.getText();
			}
			else if(cb3.isSelected()) {
				tb.setDay("0001000");
				tempstr += cb3.getText();
			}
			else if(cb4.isSelected()) {
				tb.setDay("0000100");
				tempstr += cb4.getText();
			}
			else if(cb5.isSelected()) {
				tb.setDay("0000010");
				tempstr += cb5.getText();
			}
			else if(cb6.isSelected()) {
				tb.setDay("0000001");
				tempstr += cb6.getText();
			}
		////////체크 박스 이벤트 끝/////////	
			tb.setColor(cmb1.getSelectedIndex());
			tempstr += " /  색: " + (String) cmb1.getItemAt(cmb1.getSelectedIndex());
		///////값 받아오기 끝/////////////
			tb.valid = 0;//밸리드 초기화
			tb.inittimeBlockNo();//생성한거 표시
			
			real_make_list.add(tb);//리얼 테이블 리스트에 타임블록 추가해준다.
			make_list.add(tempstr);
			
			ls1.setListData(make_list);
			
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");

		}
		
		if(e.getSource()==b2) {//->버튼
			
			
			add_list.add(make_list.remove(index1)); 
			
			real_make_list.get(index1).valid = 1; ///0이었던 밸리드를 1로 바꿔준다.
			real_add_list.add(real_make_list.remove(index1));//인덱스 바꾼거 리얼로 바꿔준다. 
			
			ls1.setListData(make_list);
			ls2.setListData(add_list);
		}
		
		if(e.getSource()==b3) {//<-버튼
			make_list.add(add_list.remove(index2));
			
			real_add_list.get(index2).valid = 0; ///0이었던 밸리드를 1로 바꿔준다.
			real_make_list.add(real_add_list.remove(index2));//인덱스 바꾼거 리얼로 바꿔준다. 
			
			ls1.setListData(make_list);
			ls2.setListData(add_list);
		}
		
        if(e.getSource()==b4) {//삭제버튼
            make_list.remove(index1);
            real_make_list.remove(index1);
            ls1.setListData(make_list); 
		}
        
        if(e.getSource()==b5) {//메인페이지 열고 창닫기///여기서 객체들이랑 다 지워줘야 한다?//
        	temp_schedule = real_make_list;
        	temp_schedule.addAll(real_add_list);

        	student.updateSchedule(temp_schedule);
        	student.setschedule(temp_schedule);
        	//여기서 템프 스케줄을 전체 스케줄로 바꿔줘야한다.
			MainPage MP = new MainPage(student);
			this.dispose();
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



