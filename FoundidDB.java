package GUIDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FoundidDB {
	static final String USERNAME = "root";
	private static final String PASSWORD = "hwangyouna0415!";
	private static final String URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";
	private static final int FoundID = 0;
	
	public FoundidDB() {
		
		
	}
	
	
	Scanner scan = new Scanner(System.in);
	
	//아이디를 찾은 후 번호선택하는 메서드
	public void chooseNum() {  
		int chooseNum;
		System.out.print("번호 : ");
		chooseNum = scan.nextInt();
		
		switch(chooseNum) {  
		case 1 : //확인을 선택하면 Login클래스를 불러옴
			Login l1 = new Login();
			l1.callLogin();	
			break;
		case 2 : //취소를 선택하면 member클래스를 불러옴
			//member m1 = new member();
			//m1.callMember();  
			break;
		default : //1,2를 제외한 다른 번호를 선택한 경우 실행
			System.out.println("다시입력해주세요.");
			chooseNum();  
			break;
		}
	}
	//아이디를 찾는 메서드
			public String foundID(String number) {
				String id = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				    
				         
				    Statement stmt = conn.createStatement();
				    ResultSet rs = null;
				    
				   
				       		 
				       	String sql = "SELECT * FROM sys.member where number ='" + number + "';";
				       	rs = stmt.executeQuery(sql);
				       	if(rs.next()) { //아이디가 있는지(있으면 if문 실행)
				       		id = rs.getString("ID");
				     
				       	}else {
				       		id = "찾으시는 ID는 없습니다.";
				       	}
				}catch(Exception e) { 
					System.err.println("Got an exception!");
				    System.err.println(e.getMessage());
				}
				return id;
			}
				    
			public String foundID2(String email) {
				String id = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				    
				         
				    Statement stmt = conn.createStatement();
				    ResultSet rs = null;
				    
				 	String sql = "SELECT * FROM sys.member where email ='" + email + "';";
			       	rs = stmt.executeQuery(sql);
			       	
			       	if(rs.next()) { //아이디가 있는지(있으면 if문 실행)
			       		id = rs.getString("ID");
			     
			       
				
			       	}else {
			       		id = "찾으시는 ID는 없습니다.";
			       	}
				
				}catch(Exception e) { 
					System.err.println("Got an exception!");
				    System.err.println(e.getMessage());
				}
				return id;
			}
	
			
		}

	
