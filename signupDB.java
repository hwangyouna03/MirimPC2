package GUIDB;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern; //패스워드나 이메일 확인같은 문자열 제한 조건을 나타내기 위해 사용

//Signup클래스에서 db에관해 필요한 메서드들을 모아놓은 클래스
class Signup1 {
	 
    private Connection conn;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hwangyouna0415!";
    private static final String URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";
  
    public Signup1() {// connection객체를 생성해서 디비에 연결해줌
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
    
    //입력된 회원정보를 DB에 저장
    public void insertProfile(String setName, String setID, String setPW, String setPnum, String setEmail ) {
//    	signupDB inforprofile = new signupDB();
//    	inforprofile.setName();
//    	inforprofile.setID();
//    	inforprofile.setPW();
//    	inforprofile.setPnum();
//    	inforprofile.setEmail();
    	
    	
        String sql = "insert into sys.member(name, ID, password, email, number) values(?,?,?,?,?);";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, setName);
            pstmt.setString(2, setID);
            pstmt.setString(3, setPW);
            pstmt.setString(4, setEmail);
            pstmt.setString(5, setPnum);           
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
    }
}

//signup 클래스
public class signupDB {
	private String name;
	private String ID;
	private String PW;
	private String Pnum;
	private String Email;
	
	Scanner scan = new Scanner(System.in);

	//생성자메서드
	public signupDB(String name, String iD, String PW, String pnum, String email) {
		super();
		this.name = name;
		this.ID = iD;
		this.PW = PW;
		this.Pnum = pnum;
		this.Email = email;
	}
	public signupDB() {}
	
	//getter and setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setName() {
		System.out.print("이름 : ");
		name = scan.next();
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public void setID() {
		System.out.print("ID : ");
		ID = scan.next();
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	//비밀번호 이중확인
	public void setPW() {
		String confirmPW1, confirmPW2;
		
		System.out.print("비밀번호 입력 : ");
		confirmPW1 = scan.next();
		System.out.print("비밀번호 확인 : ");
		confirmPW2 = scan.next();
		
		if(confirmPW1.equals(confirmPW2)) {
			this.PW = confirmPW1;
	        System.out.println("<일치>");
		}else {
	        System.out.println("<불일치>");
			setPW();
		}
	}
	public String getPnum() {
		return Pnum;
	}
	public void setPnum(String pnum) {
		Pnum = pnum;
	}
	public void setPnum() {
		System.out.print("전화번호(***-****-****) : ");
		Pnum = scan.next();
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public void setEmail() {
		System.out.print("이메일 : ");
		Email = scan.next();
	}
	
	
	
	//Signup의 기능을 담은 클래스
	public void callSignup() {
		int choosenum;
	    Signup1 s1 = new Signup1();
	    Scanner scan = new Scanner(System.in);
	    
	    System.out.println("---------------------------------------------");
	    System.out.println("<회원가입>");
	    System.out.println("* 1.확인		2.취소");
	   
	   // s1.insertProfile();
	    
	}
	
	
}