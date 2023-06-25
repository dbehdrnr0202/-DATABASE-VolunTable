package VolunEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Comparator;

public class Student {
    private StudentInfo sInfo;
    private List<timeBlock> schedule;
    private List<Volunteer> volapplication;
    private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
    public class Time{
    	int sTime, eTime;
    	public Time(){
    		sTime = eTime = 0;
    	}
    	public Time(int sTime, int eTime){
    		this.sTime = sTime;
    		this.eTime = eTime;
    	}
    }
    
    Student()  {
    	this.stmt = null;
        this.sInfo = new StudentInfo();
        this.schedule = new ArrayList<timeBlock>();
        this.volapplication = new ArrayList<Volunteer>();
        connectDB();
    }

    Student(Student student) 	{
    	this.sInfo = student.sInfo;
    	this.schedule = student.schedule;
    	this.volapplication = student.volapplication;
    	connectDB();
    }
    public void finalize()	{
    	disconnectDB();
    	this.sInfo = null;
    	this.volapplication = null;
    	this.schedule = null;
    }
    //DB와 연결을 해제한다.
    private void disconnectDB()	{
    	try {
    		if (con!=null)
    			con.close();
    		if (stmt!=null)
    			stmt.close();
    		if(rs!=null)
    			rs.close();
    	}
    	catch(SQLException e)	{
    		System.out.println("disconnection error"+e.getStackTrace());
    	}
    }
    //DB와 연결한다.
    private void connectDB()	{
    	String driver = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://localhost:3306/vtex";
    	String user = "user";
    	String pw = "pwd";
    	try {
    		Class.forName(driver);
    		con = DriverManager.getConnection(url, user, pw);
    		stmt = con.createStatement();
    	}
    	catch(ClassNotFoundException e)	{
    		System.out.println("load error"+e.getStackTrace());
    	}
    	catch(SQLException e) {
    		System.out.println("connection error"+e.getStackTrace());
    	}
    }
    //해당하는 sql문을 실행한 후에 resultSet에 넣는다.
    public void executeSQL(String sql)	{
    	ResultSet resultSet = null;
    	try {
    		//String[] msql = sql.split(" ");
    		if (sql.contains("select"))
    			resultSet = stmt.executeQuery(sql);
    		else
    			stmt.executeUpdate(sql);
    		System.out.println("EXECUTE SUCCESS : "+sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("EXECUTE FAILED : "+sql);
			e.printStackTrace();
		}
    	rs = resultSet;
    }
    //progrmRegistNo에 해당하는 상세 봉사 정보를 리턴해준다.
    public VolInfo showVolInfo(int progrmRegistNo)	{
    	VolInfo vInfo = new VolInfo();
    	MakeSQL mSQL = new MakeSQL();
    	String sql = mSQL.selectVolInfo(progrmRegistNo);
    	this.executeSQL(sql);
    	try {
			while(this.rs.next()) {
				vInfo.actBeginTm = rs.getInt("actBeginTm");
				vInfo.actEndTm = rs.getInt("actEndTm");
				vInfo.actPlace  = rs.getString("actPlace");
				vInfo.actWkdy = rs.getString("actWkdy");
				vInfo.adultPosblAt = rs.getInt("adultPosblAt") == 1? true:false;
				vInfo.appTotal = rs.getInt("appTotal");
				vInfo.email = rs.getString("email");
				vInfo.mnnstNm = rs.getString("mnnstNm");
				vInfo.nanmmbyNm = rs.getString("nanmmbyNm");
				vInfo.progrmBgnde = rs.getString("progrmBgnde");
				vInfo.progrmCn = rs.getString("progrmCn");
				vInfo.progrmEndde = rs.getString("progrmEndde");
				vInfo.progrmSj = rs.getString("progrmSj");
				vInfo.noticeBgnde = rs.getString("noticeBgnde");
				vInfo.noticeEndde = rs.getString("noticeEndde");
				vInfo.telno = rs.getString("telno");
				vInfo.yngbgsPosblAt = rs.getInt("yngbgsPosblAt")==1?true:false;
				vInfo.rcritNmpr = rs.getInt("rcritNmpr");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return vInfo;
    }
    //로그인할때 해당하는 loid, lopw를 가지는 학생 계정의 정보를 가져온다.
    public void loginStudent(String loid, String lopw) {
    	MakeSQL mSQL = new MakeSQL();
    	String sql = mSQL.selectStudentSQL(loid, lopw);
    	this.executeSQL(sql);
    	try {
			while(this.rs.next())	{
				this.sInfo.age = rs.getInt("age");
				this.sInfo.home = rs.getString("home");
				this.sInfo.homeCd[0] = rs.getInt("homesidoCode");
				this.sInfo.homeCd[1] = rs.getInt("homegugunCode");
				this.sInfo.ID = rs.getString("ID");
				this.sInfo.PW = rs.getString("PW");
				this.sInfo.sKey = rs.getInt("sKey");
				this.sInfo.school = rs.getString("school");
				this.sInfo.schoolCd[0] = rs.getInt("schoolsidoCode");
				this.sInfo.schoolCd[1] = rs.getInt("schoolgugunCode");
				this.sInfo.name = rs.getString("sname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//학생의 스케쥴을 받아온다.
    	sql = mSQL.selectStudentSchedule(this.sInfo.sKey);
    	List<timeBlock> s = new ArrayList<timeBlock>();
    	this.executeSQL(sql);
    	
    	try {
			while(this.rs.next())	{
				timeBlock tb =new timeBlock();
				tb.color = rs.getInt("color");
				tb.day = rs.getString("weekDate");
				tb.eTime = rs.getInt("Etime");
				tb.sTime = rs.getInt("Stime");
				tb.title = rs.getString("title");
				tb.valid = rs.getInt("valid");
				tb.settimeBlockNo(rs.getInt("stimekey"));
				s.add(tb);
			}
			this.schedule = s;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//학생이 신청한 봉사관련 정보에 대한 리스트를 받아온다.
    	sql = mSQL.selectStudentVolunteer(this.sInfo.sKey);
    	this.executeSQL(sql);
    	try {
    		List<Volunteer> v = new ArrayList<Volunteer>();
			while(this.rs.next())	{
				Volunteer vt = new Volunteer();
				vt.actBeginTm = rs.getInt("actBeginTm");
				vt.actEndTm = rs.getInt("actEndTm");
				vt.actPlace = rs.getString("actPlace");
				vt.adultPosblAt = rs.getInt("adultPosblAt")==1? true: false;
				vt.progrmSj = rs.getString("progrmSj");
				vt.progrmRegistNo = rs.getInt("progrmRegistNo");
				vt.actWkdy = rs.getString("actWkdy");
				vt.progrmBgnde = rs.getString("volSdate");
				vt.progrmEndde = rs.getString("volEdate");
				v.add(vt);
			}
			this.volapplication = v;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //학생이 자신의 계정정보를 update할 경우
    public void updateStudentInfo(StudentInfo sInfo)	{
    	MakeSQL mSQL = new MakeSQL();
    	StudentInfo sInfoTmp = sInfo;
    	String home[] = sInfo.home.split(" ");
    	//String school = sInfo.school.split(" ");
    	//homecd를 처리해주기
    	String sql = mSQL.selectLocalCd(home[0], home[1]);
    	this.executeSQL(sql);
    	try {
			while(this.rs.next()) {
				sInfoTmp.homeCd[0] = rs.getInt("sidocd");
				sInfoTmp.homeCd[1] = rs.getInt("gugunCd");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//schoolcd를 처리해주기
    	String school = sInfo.school;
    	int schoolCd[] = new int[2];
    	sql = mSQL.selectSchoolLocalCd(school);
    	executeSQL(sql);
    	try {
			while(this.rs.next())	{
				schoolCd[0] = rs.getInt("l.sidocd");
				schoolCd[1] = rs.getInt("l.guguncd");
				System.out.println("Student's school address local code is SIDO:"+schoolCd[0]+", GUGUN:"+schoolCd[1]);
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sInfo.schoolCd = schoolCd;
    	
    	
    	sql = mSQL.updateStudentSQL(sInfoTmp);
    	this.executeSQL(sql);
    }
    //학생이 DB에 존재하는지 확인하기
    public boolean isStudentExist(String loid, String lopw) {
    	MakeSQL mSQL = new MakeSQL();
    	String sql = mSQL.selectCntStudentSQL(loid, lopw);
    	this.executeSQL(sql);
    	try {
			while(this.rs.next()) {
				if (rs.getInt("cnt")==1)	
					return true;
				else return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    //아이디가 존재하는지 중복확인한다.
    public boolean isIdDuplicated(String loid) {
    	MakeSQL mSQL = new MakeSQL();
    	String sql = mSQL.selectCntId(loid);
    	
    	this.executeSQL(sql);
    	try {
			while(this.rs.next())	{
				if (rs.getInt("cnt")==1)
					return true;
				else return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    //새로운 학생정보 가입하기
    public void joinNewAccount(StudentInfo sInfo)	{
    	MakeSQL mSQL = new MakeSQL();
    	//sInfo에 입력한 집 정보를 바탕으로 지역코드 설정해주기
    	String home[] = sInfo.home.split(" ");
    	int homeCd[] = new int[2];
    	String sql = mSQL.selectLocalCd(home[0], home[1]);
    	executeSQL(sql);
    	try {
			while(this.rs.next())	{
				homeCd[0] = rs.getInt("sidocd");
				homeCd[1] = rs.getInt("guguncd");
				System.out.println("Student's home address local code is SIDO:"+homeCd[0]+", GUGUN:"+homeCd[1]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sInfo.homeCd = homeCd;
    	//sInfo에 입력한 학교 정보를 바탕으로 지역코드 설정해주기
    	String school = sInfo.school;
    	int schoolCd[] = new int[2];
    	sql = mSQL.selectSchoolLocalCd(school);
    	executeSQL(sql);
    	try {
			while(this.rs.next())	{
				schoolCd[0] = rs.getInt("l.sidocd");
				schoolCd[1] = rs.getInt("l.guguncd");
				System.out.println("Student's school address local code is SIDO:"+schoolCd[0]+", GUGUN:"+schoolCd[1]);
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sInfo.schoolCd = schoolCd;
    	
    	sql = mSQL.insertStudentSQL(sInfo);
    	executeSQL(sql);
    	
    }
    
    //////////////////////////////////////////////
    ///////////자신의 스케쥴 관리에 관련한 메소드들////////////
    //////////////////////////////////////////////
    
    //확인 버튼을 눌렀을 경우에 갱신
    public void updateSchedule(List<timeBlock> schedule)	{
    	MakeSQL mSQL = new MakeSQL();
    	//원래 있던 스케쥴 DB에서 정말로 삭제할 리스트
    	List<Integer> beDeleted = new ArrayList<Integer>();
    	//생성해줄때 timeblockno를 맞추기 위해 필요하다.
    	//원래 존재했던 것들
    	for (int i = 0;i<this.schedule.size();i++) {
    		//Integer integer = new Integer(this.schedule.get(i).getTimeBlockNo());
    		beDeleted.add(this.schedule.get(i).getTimeBlockNo());
    	}
    	//삭제해야하는 것들 pq에 저장, valid 변경
    	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    	for (int i = 0;i < schedule.size();i++) {
    		String sql = mSQL.updateScheduleValid(schedule.get(i));
    		int blockNo = schedule.get(i).getTimeBlockNo();
    		//만약 해당하는 blockNo를 가지고 있다면 index를 저장하기
    		if(beDeleted.contains(blockNo)) {
    			int index = beDeleted.indexOf(blockNo);
    			pq.add(index);
    		}
    		this.executeSQL(sql);
    		/*
    		if (blockNo==-1) {
    			String sql2 = mSQL.insertSchedule(schedule.get(i), curTimeKey++, this.sInfo.sKey);
    			this.executeSQL(sql2);
    		}
    		*/
    	}
    	//삭제해주기
    	while(!pq.isEmpty())	{
    		int index = pq.poll();
    		beDeleted.remove(index);
    	}
    	for (int i = 0;i<beDeleted.size();i++) {
    		int blockNo = beDeleted.get(i);
    		String sql = mSQL.deleteSchedule(blockNo);
    		this.executeSQL(sql);
    	}    	
    	//int curTimeKey = mSQL.getScheduleTotalNum() == 0 ? 1 : mSQL.getScheduleMaxlNum()+1;    	
    	//추가해주기
    	for(int i = 0;i<schedule.size();i++)	{
    		int blockNo = schedule.get(i).getTimeBlockNo();
    		if (blockNo==-1)	{
    			String sql = mSQL.insertSchedule(schedule.get(i), blockNo, this.sInfo.sKey);
    			this.executeSQL(sql);
    		}
    	}
    	this.schedule = schedule;
    }
    
    //////////////////////////////////////////////
    ///////////자신의 봉사 관리에 관련한 메소드들////////////
    //////////////////////////////////////////////
    
    //검색 버튼을 눌렀을 경우
    public List<Volunteer> searchVolunteer(String bgnDate, String endDate, boolean isHome){
    	List<Volunteer> volunteerList = new ArrayList<Volunteer>();
    	MakeSQL mSQL = new MakeSQL();
    	//int Wkdy = Wkday.indexOf("1");
    	String sqlString = mSQL.selectHomeVolunteer(bgnDate, endDate, this.sInfo);
    	if (!isHome)	{
    		sqlString = mSQL.selectSchoolVolunteer(bgnDate, endDate, this.sInfo);
    	}
    	this.executeSQL(sqlString);
    	try {
			while(this.rs.next())	{
				Volunteer vtemp = new Volunteer();
				//String actWkdy = this.rs.getString("vi.actWkdy");
				//이 부분을 날짜 받는거 약간 고쳐야한다.
				//if (actWkdy.charAt(Wkdy)=='1') {
					//프로그램번호
					vtemp.progrmRegistNo = this.rs.getInt("v.progrmRegistNo");
					//시작, 종료시간
					vtemp.actBeginTm = this.rs.getInt("vi.actBeginTm");
					vtemp.actEndTm = this.rs.getInt("vi.actEndTm");
					//프로그램명
					vtemp.progrmSj = this.rs.getString("v.progrmSj");
					///프로그램 시작, 종료일
					vtemp.progrmBgnde = this.rs.getString("v.progrmBgnde");
					vtemp.progrmEndde = this.rs.getString("v.progrmEndde");
					//프로그램 장소
					vtemp.actPlace = this.rs.getString("v.actPlace");
					vtemp.actWkdy = this.rs.getString("vi.actWkdy");
					volunteerList.add(vtemp);
				//}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//이제 여기서부터 volunteerList를 편집해줘야한다.
    	//시간표에 걸쳐지면 안된다.
    	/////////////////////////////////////////////////////////////////////////////////////////////////////
    	/////////////////////////////////////////////////////////////////////////////////////////////////////
    	/////////////////////////////////////////////////////////////////////////////////////////////////////
    	/////////////////////////////////////////////////////////////////////////////////////////////////////
    	/////////////////////////////////////////////////////////////////////////////////////////////////////
    	/////////////////////////////////////////////////////////////////////////////////////////////////////
    	List<List> weekScheduletmp = new ArrayList<List>();
    	List<List> weekSchedule = new ArrayList<List>();
    	List<List> weekVolunteer = new ArrayList<List>();
    	//요일별로 정리
    	for (int i = 0;i<7;i++) {
    		List<timeBlock> day = new ArrayList<timeBlock>();
    		weekScheduletmp.add(day);
    		//weekSchedule.add(day);
    		List<Volunteer> vt = new ArrayList<Volunteer>();
    		weekVolunteer.add(vt);
    	}
    	for (int i = 0;i<this.schedule.size();i++) {
    		int day = this.schedule.get(i).getDay();
    		timeBlock tb = this.schedule.get(i);
    		if (tb.valid==1)
    			weekScheduletmp.get(day).add(tb);
    	}
    	for (int i = 0;i<volunteerList.size();i++) {
    		int day = volunteerList.get(i).actWkdy.indexOf("1");
    		Volunteer vt = volunteerList.get(i);
    		weekVolunteer.get(day).add((Volunteer)vt);
    	}
    	for (int i = 0;i<7;i++) {
    		List<timeBlock> tmp = new ArrayList<timeBlock>();
    		weekSchedule.add(tmp);
    	}
    	Comparator<timeBlock> c1 = new timeComparator();
    	Comparator<Volunteer> c2 = new volComparator();
    	for (int i = 0;i<7;i++)	{
    		Collections.sort(weekVolunteer.get(i), c2);
    		Collections.sort(weekScheduletmp.get(i), c1);
    		List<timeBlock> tmpList = weekScheduletmp.get(i);
    		if (tmpList.size()==0) {
        		timeBlock tmp = new timeBlock();

    			tmp.sTime = 8;
				tmp.eTime = 23;
				weekSchedule.get(i).add(tmp);
			}
    		for (int j = 0;j<tmpList.size();j++)	{
    			//timeBlock t = new timeBlock();
    			
    			if (j==0) {
    	    		timeBlock t = new timeBlock();

    				t.sTime = 8;
    				t.eTime = tmpList.get(j).sTime;
    				weekSchedule.get(i).add(t);
    			}
    			else {
    	    		timeBlock t = new timeBlock();

    				t.sTime = tmpList.get(j - 1).eTime;
        			t.eTime = tmpList.get(j).sTime;
        			weekSchedule.get(i).add(t);
        			
    			}
    			if (j==weekScheduletmp.get(i).size() - 1) {
    	    		timeBlock t = new timeBlock();

    				t.sTime = tmpList.get(j).eTime;
    				t.eTime = 23;
    				weekSchedule.get(i).add(t);
    			}	
    			
    			
    			
    		}
    		Collections.sort(weekSchedule.get(i), c1);
    	}
    	
    	
    	volunteerList = new ArrayList<Volunteer>();
    	for (int i = 0;i<7;i++) {
    		for (int j =0;j<weekVolunteer.get(i).size();j++) {
    			boolean flag = false;
    			Volunteer v = (Volunteer)weekVolunteer.get(i).get(j);
    			for (int k = 0;k<weekSchedule.get(i).size();k++) {
    				timeBlock t = (timeBlock)weekSchedule.get(i).get(k);
    				if (v.actBeginTm>=t.sTime&&v.actEndTm<=t.eTime)	{
    					flag = true;
    					break;
    				}
    			}
    			if (flag)
    				volunteerList.add(v);
    		}
    	}
    	return volunteerList;
    }
    
    public void updateVolApplication(List<Volunteer> volApplication)	{
    	MakeSQL mSQL = new MakeSQL();
    	//원래 있던 봉사신청 테이블에서 정말로 삭제할 리스트
    	List<Integer> old = new ArrayList<Integer>();
    	for (int i = 0;i<this.volapplication.size();i++)	{
    		int registNo = this.volapplication.get(i).progrmRegistNo;
    		old.add(registNo);
    	}
    	List<Volunteer>	volunapplication = volApplication;
    	//old.removeAll(volApplication);
    	for (int i = 0;i<volApplication.size();i++) {
    		old.remove((Integer)volApplication.get(i).progrmRegistNo);
    	}
    	
    	volApplication.removeAll(this.volapplication);
    	//삭제하기
    	for (int i = 0;i<old.size();i++) {
    		int registNo = old.get(i);
    		String sql = mSQL.selectVolInfo(registNo);
    		int status, appTotal;
    		status = appTotal = 0;
    		this.executeSQL(sql);
    		try {
				while(this.rs.next()) {
					status = rs.getInt("progrmSttusSe");
					//rcritNmpr = rs.getInt("rcitNmpr");
					appTotal = rs.getInt("appTotal");
					//모집완료
					if (status==3) {
						String update = mSQL.updateVolInfo(registNo, appTotal - 1, 2);
						this.executeSQL(update);
						update = mSQL.updateVolunteer(registNo, status);
						this.executeSQL(update);
						break;
					}
					//모집중
					else if (status==2)	{
						String update = mSQL.updateVolInfo(registNo, appTotal - 1, status);
						this.executeSQL(update);
						update = mSQL.updateVolunteer(registNo, status);
						this.executeSQL(update);
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		sql = mSQL.deleteVolApplication(registNo, this.sInfo);
    		this.executeSQL(sql);
    	}
    	//추가하기
    	for (int i = 0;i<volApplication.size();i++) {
    		int registNo = volApplication.get(i).progrmRegistNo;
    		String sql = mSQL.selectVolInfo(registNo);
    		int status, appTotal, rcritNmpr;
    		status = appTotal = 0;
    		this.executeSQL(sql);
    		try {
				
				while(this.rs.next()) {

					status = rs.getInt("progrmSttusSe");
					rcritNmpr = rs.getInt("rcritNmpr");
					appTotal = rs.getInt("appTotal");
					
					if (rcritNmpr==appTotal+1) {
						String update = mSQL.updateVolInfo(registNo, appTotal+1, 3);
						this.executeSQL(update);
						update = mSQL.updateVolunteer(registNo, 3);
						this.executeSQL(update);
						break;
					}
					else {
						String update = mSQL.updateVolInfo(registNo, appTotal+1, status);
						this.executeSQL(update);
						update = mSQL.updateVolunteer(registNo, status);
						this.executeSQL(update);
						break;
					}	
				}	
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		sql = mSQL.insertVolApplication(volApplication.get(i), this.sInfo);
    		this.executeSQL(sql);
    	}
    	//
    	this.volapplication = volunapplication;
    }
    
    public List<timeBlock> getSchedule() {
        return schedule;
    }
    public StudentInfo getsInfo() {
        return sInfo;
    }
    public List<Volunteer> getvolapplication() {
        return volapplication;
    }
    public void setschedule(List<timeBlock> schedule) {
        this.schedule = schedule;
    }
    public void setsInfo(StudentInfo sInfo) {
        this.sInfo = sInfo;
    }
    public void setvolapplication(List<Volunteer> volapplication) {
        this.volapplication = volapplication;
    }

    
}