package VolunEx;
//수정해야하는거

public class  Volunteer	{
	int progrmRegistNo;	//봉사 정보 기본키
	String progrmSj;		//봉사 제목
	String progrmBgnde;		//봉사 시작일
	String progrmEndde;		//봉사 종료일
	int actBeginTm;			//봉사 시작 시간
	int actEndTm;			//봉사 종료 시간
	int progrmSttusSe;		//봉사 현황
	String sidoCd;			//시도 코드
	String gugunCd;			//시군구 코드
	String noticeBgnde;		//봉사 모집 시작일
	String noticeEndde;		//봉사 모집 종료일
	boolean adultPosblAt;	//성인 가능 여부
	boolean yngbgsPosblAt;	//청소년 가능 여부
	String nanmmbyNm;		//등록 기관명
	String actPlace;		//봉사 장소
	String url;				//1365 url
	String actWkdy;			//활동 요일 0000000 으로 월화수목금토일 (1)이 활동일

	public void setProgrmRegistNo(int progrmRegistNo) {
		this.progrmRegistNo = progrmRegistNo;
	}
	public void setProgrmSj(String progrmSj) {
		this.progrmSj = progrmSj;
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
	public void setProgrmSttusSe(int progrmSttusSe) {
		this.progrmSttusSe = progrmSttusSe;
	}
	public void setSidoCd(String sidoCd) {
		this.sidoCd = sidoCd;
	}
	public void setGugunCd(String gugunCd) {
		this.gugunCd = gugunCd;
	}
	public void setNoticeBgnde(String noticeBgnde) {
		this.noticeBgnde = noticeBgnde;
	}
	public void setNoticeEndde(String noticeEndde) {
		this.noticeEndde = noticeEndde;
	}
	public void setAdultPosblAt(boolean adultPosblAt) {
		this.adultPosblAt = adultPosblAt;
	}
	public void setYngbgsPosblAt(boolean yngbgsPosblAt) {
		this.yngbgsPosblAt = yngbgsPosblAt;
	}
	public void setNanmmbyNm(String nanmmbyNm) {
		this.nanmmbyNm = nanmmbyNm;
	}
	public void setActPlace(String actPlace) {
		this.actPlace = actPlace;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}