package VolunEx;


public class timeBlock {
	private int timeBlockNo;//timeBlock이 가지는 고유한 key
    public String title;//제목
    //public String memo;//내용
    public int sTime;//시작시간
    public int eTime;//종료시간
    public String day;//요일
    public int color;//색
    public int valid;
    
    timeBlock()	{
    	this.timeBlockNo = -1;
    }
    
    public int getDay() {
    	int i = 0;
    	for (i = 0; i < 7 ; i++) {
    		if(this.day.charAt(i)=='1')	{
    			return i;
    		}
    	}
    	return i;
    }
    public int getTimeBlockNo()	{
    	return this.timeBlockNo;
    }
    public void settimeBlockNo(int blockNo)	{
    	this.timeBlockNo = blockNo;
    }
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setsTime(int sTime) {
		this.sTime = sTime;
	}
	public void seteTime(int eTime) {
		this.eTime = eTime;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public void inittimeBlockNo() {
		//this.valid = 0;	
		this.settimeBlockNo(-1);
	}
	public int getETime() {
		return this.eTime;
	}
	
}