package VolunEx;


public class timeBlock {
	private int timeBlockNo;//timeBlock�� ������ ������ key
    public String title;//����
    //public String memo;//����
    public int sTime;//���۽ð�
    public int eTime;//����ð�
    public String day;//����
    public int color;//��
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