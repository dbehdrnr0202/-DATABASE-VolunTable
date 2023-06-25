package VolunEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import VolunEx.Student.Time;

public class MakeSQL {
	//학생 총 인원수를 select count 해주는 sql문을 반환
	public String selectStudentTotalNumSQL() {
		String sql = "select COUNT(skey) from student";
		return sql;
	}
	//
	public String selectScheduleMaxNumSQL() {
		String sql = "select MAX(stimekey) from studentschedule";
		return sql;
	}
	public String selectScheduleTotalNumSQL() {
		String sql = "select COUNT(stimekey) from studentschedule";
		return sql;
	}
	public int getScheduleMaxlNum()	{
		int totalNum = 0;
		String driver = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://localhost:3306/vtex";
    	String user = "user";
    	String pw = "pwd";
        Connection con;
    	Statement stmt;
    	ResultSet rs = null;
    	try {
    		Class.forName(driver);
    		con = DriverManager.getConnection(url, user, pw);
    		stmt = con.createStatement();
    		try {
    			rs = stmt.executeQuery(this.selectScheduleMaxNumSQL());
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}	
    		while(rs.next()) {
    			totalNum = rs.getInt(1);
    		}
    	}
    	catch(ClassNotFoundException e)	{
    		System.out.println("load error"+e.getStackTrace());
    	}
    	catch(SQLException e) {
    		System.out.println("connection error"+e.getStackTrace());
    	}
		return totalNum;
	
	}
	public int getScheduleTotalNum()	{
		int totalNum = 0;
		String driver = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://localhost:3306/vtex";
    	String user = "root";
    	String pw = "dongdky11";
        Connection con;
    	Statement stmt;
    	ResultSet rs = null;
    	try {
    		Class.forName(driver);
    		con = DriverManager.getConnection(url, user, pw);
    		stmt = con.createStatement();
    		try {
    			rs = stmt.executeQuery(this.selectScheduleTotalNumSQL());
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}	
    		while(rs.next()) {
    			totalNum = rs.getInt(1);
    		}
    	}
    	catch(ClassNotFoundException e)	{
    		System.out.println("load error"+e.getStackTrace());
    	}
    	catch(SQLException e) {
    		System.out.println("connection error"+e.getStackTrace());
    	}
		return totalNum;
	
	}
	//학생의 총 인원수를 출력한는 메소드이다.
	public int getStudentTotalNum()	{
		int totalNum = 0;
		String driver = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://localhost:3306/vtex";
    	String user = "root";
    	String pw = "dongdky11";
        Connection con;
    	Statement stmt;
    	ResultSet rs = null;
    	try {
    		Class.forName(driver);
    		con = DriverManager.getConnection(url, user, pw);
    		stmt = con.createStatement();
    		try {
    			rs = stmt.executeQuery(this.selectStudentTotalNumSQL());
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}	
    		while(rs.next()) {
    			totalNum = rs.getInt(1);
    		}
    	}
    	catch(ClassNotFoundException e)	{
    		System.out.println("load error"+e.getStackTrace());
    	}
    	catch(SQLException e) {
    		System.out.println("connection error"+e.getStackTrace());
    	}
		return totalNum;
	}
	//새로운 계정 생성하기 schoolCd, homeCd를 편집해야한다.
	public String insertStudentSQL(Student student) {
		String sql = "insert into student (sKey, age, sname, ID, PW, school, home, schoolsidoCode, schoolgugunCode, homesidoCode, homegugunCode) values ("
				+student.getsInfo().sKey+ ", "
				+student.getsInfo().age+ ", '"
				+student.getsInfo().name+ "', '"
				+student.getsInfo().ID+ "', '"
				+student.getsInfo().PW+ "', '"
				+student.getsInfo().school+ "', '"
				+student.getsInfo().home+ "', "
				+student.getsInfo().schoolCd[0]+ ", "
				+student.getsInfo().schoolCd[1]+ ", "
				+student.getsInfo().homeCd[0]+", "
				+student.getsInfo().homeCd[1]+ ")";
		return sql;
	}
	//새로운 계정 생성하기 schoolCd, homeCd는 Student 부분에서 관련해서 사용한다.
	public String insertStudentSQL(StudentInfo sInfo) {
		sInfo.sKey = this.getStudentTotalNum()+1;
		String sql = "insert into student values ("
				+sInfo.sKey+ ", "
				+sInfo.age+ ", '"
				+sInfo.name+ "', '"
				+sInfo.ID+ "', '"
				+sInfo.PW+ "', '"
				+sInfo.school+ "', '"
				+sInfo.home+ "', "
				+sInfo.schoolCd[0]+ ", "
				+sInfo.schoolCd[1]+ ", "
				+sInfo.homeCd[0]+ ", "
				+sInfo.homeCd[1]+ ")";
		return sql;
	}
	//로그인할때 계정이 존재하는지 확인(해당 id와 pw가 동일한 계정정보가 존재하는지 검색한다.)
	public String selectCntStudentSQL(String loid, String lopw) {
		String sql = "select COUNT(*) as cnt from STUDENT where id = '"
				+ loid+"' AND pw = '"
				+ lopw+"'";
		return sql;
	}
	//가입할때 id 존재하는지 확인한다.
	public String selectCntId(String loid) {
		String sql = "select COUNT(*) as cnt from student where ID = '"+loid+"'";
		return sql;
	}
	//지역명을 쳤을때 그 지역의 코드를 가져온다. 약간 수정해야한다.
	public String selectLocalCd(String sidoName, String gugunName)	{
		String sql = "select sidocd, guguncd from location where sidoNm = '"
				+sidoName+"' and gugunNm = '"+gugunName+"'";
		return sql;
	}
	//학교명을 쳤을때 그 학교의 지역코드를 가져온다.
	public String selectSchoolLocalCd(String schoolNm) {
		String sql = "select l.sidoCd, l.gugunCd from location l, school s where "
				+ "l.sidoNm = s.sidoNm "
				+ "AND l.gugunNm = s.gugunNm "
				+ "AND s.schoolnm = '"+schoolNm+"'";
		return sql;
	}
	//옳은 계정일 경우 user의 계정정보를 가져온다(sKey, id+pw로 확인)
	public String selectStudentSQL(int sKey)	{
		String sql = "select * from STUDENT where skey = "
				+ sKey+"";
		return sql;
	}
	public String selectStudentSQL(String loid, String lopw)	{
		String sql = "select * from STUDENT where id = '"
				+ loid+"' AND pw = '"
				+ lopw+"'";
		return sql;
	}
	//옳은 계정일 경우 user가 신청한 봉사 활동 리스트를 가져온다.
	public String selectStudentVolApp(Student student) {
		int skey = student.getsInfo().sKey;
		String sql = "select * from volapplication where skey = "
				+skey;
		return sql;
	}
	public String selectStudentVolApp(int sKey)	{
		String sql = "select * from volapplication where skey = "
				+sKey;
		return sql;
	}
	public String selectStudentVolunteer(int sKey)	{
		String sql = "select * from volapplication va, volunteer v, volinfo vi where va.skey = "
				+ sKey+" and "
				+ "va.volkey = v.progrmRegistNo "
				+ "and vi.progrmRegistNo = v.progrmRegistNo";
		return sql;
	}
	//옳은 계정일 경우 user가 만든 시간표 정보 리스트를 가져온다.
	public String selectStudentSchedule(int sKey)	{
		String sql = "select * from studentSchedule where skey = "
				+ sKey;
		return sql;
	}
	public String selectStudentSchedule(Student student) {
		String sql = "select * from studentSchedule where skey = "
				+student.getsInfo().sKey;
		return sql;
	}
	//선택된 progrmRegistNo의 봉사 상세정보를 보낸다
	public String selectVolInfo(int progrmRegistNo)	{
		String sql = "select * from VolInfo where progrmRegistNo = "
				+ progrmRegistNo;
		return sql;
	}
	//user의 개인 정보를 수정한다.
	public String updateStudentSQL(StudentInfo sInfo)	{
		String sql = "UPDATE Student SET "
				+ "age = "+sInfo.age
				+ ", sname = '"+sInfo.name
				+ "', ID = '"+sInfo.ID
				+ "', PW = '"+sInfo.PW
				+ "', school = '"+sInfo.school
				+ "', home = '"+sInfo.home
				+ "', schoolsidoCode ="+sInfo.schoolCd[0]
				+ ", schoolgugunCode ="+sInfo.schoolCd[1]
				+ ", homesidoCode = "+sInfo.homeCd[0]
				+ ", homegugunCode = "+sInfo.homeCd[1]
				+ " WHERE sKey = "+sInfo.sKey;
		return sql;
	}
	public String updateScheduleValid(timeBlock time, int sKey) {
		String sql = "UPDATE studentschedule set valid = "
				+ time.valid+" WHERE stimeKey = "
				+ time.getTimeBlockNo()+" AND sKey = "+sKey;
		return sql;
	}
	public String updateScheduleValid(timeBlock time) {
		String sql = "UPDATE studentschedule set valid = "
				+ time.valid+" WHERE stimeKey = "
				+ time.getTimeBlockNo();
		return sql;
	}
	public String deleteSchedule(timeBlock time)	{
		String sql = "DELETE from studentschedule where stimeKey = "+time.getTimeBlockNo();
		return sql;
	}
	public String deleteSchedule(int blockNo)	{
		String sql = "DELETE from studentschedule where stimeKey = "+blockNo;
		return sql;
	}
	public String deleteSchedule(int blockNo, int sKey)	{
		String sql = "DELETE from studentschedule where stimeKey = "+blockNo+" AND sKey = "+sKey;
		return sql;
	}
	public String insertSchedule(timeBlock time, int blockNo, int sKey) {
		
		String sql = "INSERT into studentschedule (skey, stime, etime, title, "
				+ "weekDate, valid, color) values"
				+ " ("+sKey+", "+time.sTime+", "+time.eTime+", '"+time.title
				+"', '"+time.day+"', "+time.valid+", "+time.color+")";
		int No = this.getScheduleMaxlNum();
		time.settimeBlockNo(No);
		
		return sql;
	}
	//봉사 검색하기
	//학교 주변
	public String selectSchoolVolunteer(String bgnDate, String endDate, StudentInfo sInfo) {
		//일단 날짜, 장소, 나이 정보에 알맞은 모집중 봉사정보 뽑기
		String sql = "select v.progrmRegistNo, v.progrmBgnde, v.progrmEndde,"
				+ " v.progrmSj, v.actPlace, vi.actWkdy, vi.actBeginTm, vi.actEndTm "
				+ "from volunteer v, volinfo vi where "
				+ "v.progrmRegistNo = vi.progrmRegistNo "
				+ "AND v.progrmBgnde > '"+bgnDate
				+ "' AND v.progrmEndde < '"+ endDate
				+ "' AND v.sidoCd = "+sInfo.schoolCd[0]
				+ " AND v.gugunCd = "+sInfo.schoolCd[1]
				+ " AND v.noticeBgnde < date(now()) "
				+ "AND v.noticeEndde > date(now()) "
				+ "AND v.progrmSttusSe = 2";
		if(sInfo.age < 20)
			sql+=" AND v.yngbgsPosblAt = 1";
		return sql;
	}
	//집 주변
	public String selectHomeVolunteer(String bgnDate, String endDate, StudentInfo sInfo) {
		String sql = "select v.progrmRegistNo, v.progrmBgnde, v.progrmEndde, "
				+ "v.progrmSj, v.actPlace, vi.actWkdy, vi.actBeginTm, vi.actEndTm "
				+ "from volunteer v, volinfo vi where "
				+ "v.progrmRegistNo = vi.progrmRegistNo "
				+ "AND v.progrmBgnde > '"+bgnDate
				+ "' AND v.progrmEndde < '"+ endDate
				+ "' AND v.sidoCd = "+sInfo.homeCd[0]
				+ " AND v.gugunCd = "+sInfo.homeCd[1]
				+ " AND v.noticeBgnde < date(now()) "
				+ "AND v.noticeEndde > date(now()) "
				+ "AND v.progrmSttusSe = 2";
		if(sInfo.age < 20)
			sql+=" AND v.yngbgsPosblAt = 1";
		return sql;
	}
	//특정 봉사를 insert volapplication에 insert하기
	public String insertVolApplication(Volunteer vt, StudentInfo sInfo) {
		String sql = "insert into volapplication (skey, volkey, volsdate, voledate, progrmSj)"
				+ " values"
				+ "("+sInfo.sKey+", "+vt.progrmRegistNo+", '"+vt.progrmBgnde+"', '"
				+vt.progrmEndde+"', '"+vt.progrmSj+"')";
		return sql;
	}
	//특정학생의 특정 봉사활동을 delete
	public String deleteVolApplication(Volunteer vt, StudentInfo sInfo) {
		String sql = "delete from volapplication where sKey = "+sInfo.sKey+" AND volKey = "+vt.progrmRegistNo;
		return sql;
	}
	public String deleteVolApplication(int progrmRegistNo, StudentInfo sInfo) {
		String sql = "delete from volapplication where sKey = "
				+sInfo.sKey+" AND volKey = "+progrmRegistNo;
		return sql;	
	}
	//봉사정보를 insert 혹은 delete 했을 경우에 volunteer과 volapplication의 모집 숫자를 update해준다.
	public String updateVolunteer(int progrmRegistNo, int status)	{
		String sql = "update volunteer set progrmSttusSe = "+status
				+" where progrmRegistNo = "+progrmRegistNo;
		return sql;
	}
	public String updateVolInfo(int progrmRegistNo, int app, int status) {
		String sql = "update volinfo set appTotal = "+app+",progrmSttusSe = "
				+status+" where progrmRegistNo = "+progrmRegistNo;
		return sql;
	}

}
