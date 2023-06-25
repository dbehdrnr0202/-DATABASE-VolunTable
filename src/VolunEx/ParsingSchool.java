package VolunEx;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ParsingSchool {
	ParsingSchool(){
		try{
			Connection con = null;
			Statement stmt = null;
			
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/vtex";
			String user = "user";
			String pw = "pwd";
			
	    	File file = new File("전국초중등학교위치표준데이터.txt");
	    	//입력 스트림 생성
	    	FileReader filereader = new FileReader(file);
	    	//int singleCh = 0;
            BufferedReader bufReader = new BufferedReader(filereader);
            try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();

            String line = "";
            int i = 1;
	    	while((line = bufReader.readLine()) != null)	{
	    		String school[] = line.split("\t");
	    		String location[] = school[1].split(" ");
	    		String sql = "Insert into school (schoolkey, schoolnm, sidoNm, gugunNm) values ("+i+", '"
	    				+ school[0]+"', '"+location[0]+"', '"+location[1]+"')";
	    		stmt.execute(sql);
	    		System.out.println(i++);
	    	}
	          
			filereader.close();
	        
		}catch (FileNotFoundException e) {
	    
			// TODO: handle exception
	        
		}catch(IOException e){
	    
			System.out.println(e);
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParsingSchool p = new ParsingSchool();
	}

}
