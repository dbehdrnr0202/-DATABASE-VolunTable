package VolunEx;
//¿Ï·á
public class StudentInfo   {
    public int sKey;
    public int age;
    public String name;
    public String ID;
    public String PW;
    public String school;
    public String home;
    public int[] homeCd;
    public int[] schoolCd;
    
    StudentInfo()	{
    	this.sKey = 0;
    	this.age = 0;
    	this.name = null;
    	this.ID = null;
    	this.PW = null;
    	this.school = null;
    	this.home = null;
    	this.schoolCd = new int[2];
    	this.homeCd = new int[2];
    }
}