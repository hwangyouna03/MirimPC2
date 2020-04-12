package GUIDB;

import java.util.Calendar;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Nomember클래스에서 db에관해 필요한 메서드들을 모아놓은 클래스
class DBNomember1{
	
	private Connection conn;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hwangyouna0415!";
    private static final String URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";
    
    Properties info = null;
    Statement stmt = null;
    String sql = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
	public DBNomember1() {// connection객체를 생성해서 디비에 연결해줌
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//          System.out.println("연결성공");
 
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("클래스 적재 실패!!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("연결 실패!!");
        }
    }
	
	//sys.nomember DB에 임시코드 삽입클래스
    public String insertTemporarycode() {
    	NomemberExcode n1 = new NomemberExcode();
        String sql = "insert into java.nomember(temporarycode, created_date) values(?, ?);";
        
        try {
            ps = conn.prepareStatement(sql);
            n1.temporarycode();
            ps.setString(1, n1.gettem());
            ps.setInt(2, n1.created_date());
            ps.executeUpdate(); 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (ps != null && !ps.isClosed()) 
                    ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return n1.gettem();
    }
    
    //아이디가 있는지 없는지 구별하는 클래스
    public int setTemcode(String temporaryID) {
    	int result = 1;
    	
    	try {
            stmt = conn.createStatement();
            sql = "select * from java.nomember where temporarycode='" + temporaryID + "';";
            rs = stmt.executeQuery(sql); // 읽어오는거라 다르다 비교해    //리턴타입이 ResultSet
 
            if (rs.next() == false)// id가 존재x
               result = 1;
            else 				   // id존재
               result = 0;         
        } catch (Exception ee) {
            System.out.println("문제있음");
            ee.printStackTrace();
        }
    	return result;
    }
    
    //하루가 지나면 임시코드가 사라지는 클래스
    public void autodelete() {
    	NomemberExcode n1 = new NomemberExcode();
    	
    	try {
    		sql = "SELECT created_date FROM java.nomember;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(); 
            while(rs.next()) {
            	if(rs.getInt("created_date") < n1.created_date()) {
            		sql="DELETE FROM java.nomember WHERE created_date = ?;";
            		ps = conn.prepareStatement(sql);
                    ps.setInt(1, rs.getInt("created_date"));
                    ps.executeUpdate(); 
            	}
            }
           
        } catch (Exception ee) {
            System.out.println("문제있음");
            ee.printStackTrace();
        }
    }
}

//비회원을 나타내는 클래스
public class NomemberExcode{
	private String temporaryID;
	Scanner scan = new Scanner(System.in);
	
	//생성자메서드
	public NomemberExcode(String temporaryID) {
		this.temporaryID = temporaryID;
	}
	public NomemberExcode() {}
	
	//getter and setter
	public String gettem() {
		return temporaryID;
	}
	public void settem(String temporaryID) {
		this.temporaryID = temporaryID;
	}
	
	//DBNomember에서 setTemcode()메서드가져오는 클래스
	public int setTemcode(String entertemporaryID) {
		int num;
		DBNomember1 db1 = new DBNomember1();
		num = db1.setTemcode(entertemporaryID);
		
//		int num;
//		String entertemporaryID;
//		DBNomember db1 = new DBNomember();
//		
//		System.out.print("임시코드 : ");
//		entertemporaryID = scan.next();
//		num = db1.setTemcode(entertemporaryID);	//입력한 임시코드가 있는지 없는지 판별하기위해 DBNomember에서 setTemcode메서드 불러오기
//		
		return num;
	}
	
	//임시코드 발급 메서드
	public void temporarycode() {
		String temcode;
		
		for (int i = 0; i < 1; i++) {
			temcode = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다.
		    temcode = temcode.substring(0, 8); //temcode를 앞에서부터 8자리 잘라줌
		    this.temporaryID = temcode;
		}	
	}		
	
	//임시코드 발급 날짜
	public int created_date() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		
		return (year+month+date);
	}
	
	//비회원으로 로그인하기
	public int letLogin() {
		int num, choosenum;  	
		System.out.println("---------------------------------------------");
		System.out.println("<비회원-로그인>");
    	System.out.println("*1.확인   2.취소 ");
    	
    	num = setTemcode(null);
    	
    	if(num == 0) { //입력한 임시코드가 DB에 있을때
    		choosenum = choosenum();
    		
    		switch(choosenum) {
    		case 1 : //좌석선택으로 넘어가기
//    			Seat s1 = new Seat();
//    	    	s1.callSeat();
//    			break;
    		case 2 : //전(처음)으로 돌아가기
//    			Pcroom pc1 = new Pcroom(); 
//			 	pc1.main(null); 
//			 	break;
    		default : //1,2를 제외한 번호가 입력되었을 때 실행
//    			System.out.println("다시 입력해주세요");
//    			letLogin(); 
//    			break;  	 	
    		}
    	}else if(num == 1) { //입력한 임시코드가 DB에 업을때
    		System.out.println("찾으시는 임시코드가 없습니다. 다시 입력해주세요.");
    		letLogin();
    	}
    	return num;
	}
	
	//번호 선택
	public int choosenum() {
		int choosenum;

		System.out.print("번호 : ");
		choosenum = scan.nextInt();
		return choosenum;
	}
	
	//toString()
	public String toString() {
		return "임시코드는 " + temporaryID + "입니다.";
	}
	
	//Nomember의 기능을 담은 클래스
	public void callNomember() {
		int choosenum;
		Scanner scan = new Scanner(System.in);
		
		DBNomember1 n1 = new DBNomember1();
		
		System.out.println("---------------------------------------------");
		System.out.println("<임시코드 발급>");
		System.out.println("*1.확인	2.취소");
		settem(n1.insertTemporarycode());
		n1.autodelete();
		System.out.println(toString());
		choosenum = choosenum();
		
		switch(choosenum) {
		case 1 : //비회원로그인 불러오기
			letLogin();  
			break;
		case 2 : //전(처음)으로 돌아가기
//			Pcroom pc1 = new Pcroom(); 
//			System.out.println("");
//			pc1.main(null);  
//			break;
		default : //1,2를 제외한 번호가 선택되었을 때 실행
			System.out.println("다시입력해주세요."); 
			callNomember(); 
			break;
		}
	}
}