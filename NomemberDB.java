package GUIDB;

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
class DBNomember{
	
	private Connection conn;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hwangyouna0415!";
    private static final String URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";
    
    Properties info = null;
    Statement stmt = null;
    String sql = null;
    ResultSet rs = null;
    
	public DBNomember() {// connection객체를 생성해서 디비에 연결해줌
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("연결성공");
 
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
    	NomemberDB n1 = new NomemberDB(sql);
    	String sql = "insert into sys.nomember(temporarycode, created_date) values(?, ?);";
        //String sql = "insert into sys.nomember(temporarycode) values(?);";
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            n1.temporarycode();
            pstmt.setString(1, n1.gettem());
            pstmt.setInt(2, 0);
            pstmt.executeUpdate(); 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) 
                    pstmt.close();
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
            sql = "select * from sys.nomember where temporarycode='" + temporaryID + "';";
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
}

//비회원을 나타내는 클래스
public class NomemberDB /*extends LoginDB*/{
	private static String temporaryID;
	Scanner scan = new Scanner(System.in);
	
	//생성자메서드
	
	public NomemberDB(String temporaryID) {
	this.temporaryID = temporaryID;
	}
	
	//getter and setter
	public String gettem() {
		return temporaryID;
	}
	public void settem(String temporaryID) {
		this.temporaryID = temporaryID;
	}
	
	//DBNomember에서 setTemcode()메서드가져오는 클래스
	public int setTemcode() {
		int num;
		String entertemporaryID;
		DBNomember db1 = new DBNomember();
		
		System.out.print("임시코드 : ");
		entertemporaryID = scan.next();
		num = db1.setTemcode(entertemporaryID);	//입력한 임시코드가 있는지 없는지 판별하기위해 DBNomember에서 setTemcode메서드 불러오기
		
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
	
	//비회원으로 로그인하기
	public int letLogin() {
		int num;  	
    	num = setTemcode();
    	
    		
    	 if(num == 1) { //입력한 임시코드가 DB에 없을때
    		System.out.println("찾으시는 임시코드가 없습니다. 다시 입력해주세요.");
    		letLogin();
    	}
    	return num;
	}
	
	
	//toString()
	public String toString() {
		return "임시코드는 " + temporaryID + "입니다.";
	}
	
	//Nomember의 기능을 담은 클래스
	public void callNomember() {
		
		DBNomember n1 = new DBNomember();
		
		settem(n1.insertTemporarycode());
		System.out.println(toString());
		
		
	}
	
	public static void main(String args[]) {
		
	}
}