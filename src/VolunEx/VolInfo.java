package VolunEx;
//완료
public class VolInfo {
	String progrmRegistNo;	//봉사 정보 기본키
	String progrmSj;		//봉사 제목
	String progrmSttusSe;	//모집 상태 0-
	String progrmBgnde;		//봉사 시작일
	String progrmEndde;		//봉사 종료일
	int actBeginTm;			//봉사 시작 시간
	int actEndTm;			//봉사 종료 시간
	String noticeBgnde;		//봉사 모집 시작일
	String noticeEndde;		//봉사 모집 종료일
	int rcritNmpr;			//총 모집 인원
	int appTotal;			//신청 인원 수
	String actWkdy;			//활동 요일 0000000 으로 월화수목금토일 (1)이 활동일
	String srvcClCode;		//봉사 분야
	boolean adultPosblAt;	//성인 가능 여부
	boolean yngbgsPosblAt;	//청소년 가능 여부
	String mnnstNm;			//모집 기관명
	String nanmmbyNm;		//등록 기관명
	String actPlace;		//봉사 장소
	String telno;			//전화번호
	String email;			//이메일 주소
	String progrmCn;		//내용
	public void setProgrmRegistNo(String progrmRegistNo) {
		this.progrmRegistNo = progrmRegistNo;
	}
	public void setProgrmSj(String progrmSj) {
		this.progrmSj = progrmSj;
	}
	public void setProgrmSttusSe(String progrmSttusSe) {
		this.progrmSttusSe = progrmSttusSe;
	}
	public void setProgrmBgnde(String progrmBgnde) {
		this.progrmBgnde = progrmBgnde;
	}
	public void setProgrmEndde(String progrmEndde) {
		this.progrmEndde = progrmEndde;
	}
	public void setActBeginTm(int actBeginTm) {
		this.actBeginTm = actBeginTm;
	}
	public void setActEndTm(int actEndTm) {
		this.actEndTm = actEndTm;
	}
	public void setNoticeBgnde(String noticeBgnde) {
		this.noticeBgnde = noticeBgnde;
	}
	public void setNoticeEndde(String noticeEndde) {
		this.noticeEndde = noticeEndde;
	}
	public void setRcritNmpr(int rcritNmpr) {
		this.rcritNmpr = rcritNmpr;
	}
	public void setAppTotal(int appTotal) {
		this.appTotal = appTotal;
	}
	public void setActWkdy(String actWkdy) {
		this.actWkdy = actWkdy;
	}
	public void setSrvcClCode(String srvcClCode) {
		this.srvcClCode = srvcClCode;
	}
	public void setAdultPosblAt(boolean adultPosblAt) {
		this.adultPosblAt = adultPosblAt;
	}
	public void setYngbgsPosblAt(boolean yngbgsPosblAt) {
		this.yngbgsPosblAt = yngbgsPosblAt;
	}
	public void setMnnstNm(String mnnstNm) {
		this.mnnstNm = mnnstNm;
	}
	public void setNanmmbyNm(String nanmmbyNm) {
		this.nanmmbyNm = nanmmbyNm;
	}
	public void setActPlace(String actPlace) {
		this.actPlace = actPlace;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setProgrmCn(String progrmCn) {
		this.progrmCn = progrmCn;
	}

}
