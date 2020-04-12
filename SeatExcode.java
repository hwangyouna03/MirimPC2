package GUIDB;

import java.util.Properties;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

class DBSeat {
	private Connection conn;
	static final String USERNAME = "root";
	private static final String PASSWORD = "hwangyouna0415!";
    private static final String URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";
	Scanner scan = new Scanner(System.in);
	
	Properties info = null;
    Statement stmt = null;
    String sql = null;
    ResultSet rs = null;	
    
    //DB에 연결하기
    public DBSeat() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//         System.out.println("연결성공");
 
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
    //DB에 id와  좌석번호 삽입하기
    public void insertDB(int seat) {
    	PreparedStatement pstmt = null;
    	String id = null;
    	Scanner sc;
    
		String sql = "insert into sys.seat(ID, seat_number) values(?,?);";
        
        try {     	
        	//id 읽어오기
        	try {
                File aFile = new File("id.txt");
                FileReader fileReader = new FileReader(aFile);
                BufferedReader reader = new BufferedReader(fileReader);
                 
                String read1 = null;
                while((read1 = reader.readLine()) != null) {
                	id = read1;
                }
                reader.close();
            } catch(Exception ex) {
            	System.out.println("문제있음");
            }
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, id);
        	pstmt.setInt(2, seat);
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
    //남은 좌석 확인하는 메서드
    public int remaining_seat(int num) {
    	int dbseat = -1;
    	int returnnum = 1;
    	
    	try {
          stmt = conn.createStatement();
          sql = "select seat_number from sys.seat;";
          rs = stmt.executeQuery(sql); 
          
          while(rs.next()==true) {
        	  dbseat = rs.getInt("seat_number");
        	  if(dbseat == num) {
        		  returnnum = 0;
        	  }
          }
      } catch (Exception ee) {
          System.out.println("문제있음");
          ee.printStackTrace();
      }    	
    	return returnnum;
    }
    //선택한 자리가 있는지 없는지
    public int seat_availability() {
    	int seat = 0;
    	int dbseat = -1;
    	int num = 0;
    	Scanner sc;
    	
    	try {
            stmt = conn.createStatement();
            sql = "select seat_number from sys.seat;";
            rs = stmt.executeQuery(sql); 
            
            try {
        		int dt;
        		sc = new Scanner(new File("seat.txt"));
        		while(sc.hasNextInt() == true) {
        			dt = sc.nextInt();
        			seat = dt;
        		}
                sc.close();
        	}catch(Exception ex) {
        		System.out.println("문제있음");
            }
            
            while(rs.next()) {
            	dbseat = rs.getInt("seat_number");
            	if(dbseat == seat) { //선택한 자리가 누군가 사용중일때
            		SeatExcode s1 = new SeatExcode();
            		num = 1;
//                	System.out.println("자리있음");
//                	s1.seat();
                }
            }
            if(num == 0) {
   //         	this.insertDB();
            }
        } catch (Exception ee) {
            System.out.println("문제있음");
            ee.printStackTrace();
        }
 
    	return num;
//    	
//        this.remaining_seat();
    }
}

public class SeatExcode {
	private int seat;
	private String ID;
	
	//생성자메소드
	public SeatExcode(int seat, String iD) {
		super();
		this.seat = seat;
		this.ID = iD;
	}
	public SeatExcode() {}
	//getter and setter
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		this.ID = iD;
	}
	//좌석출력
	public void seat() {
		Scanner scan = new Scanner(System.in);
		PrintWriter writer;
		
		System.out.println("---------------------------------------------");
		System.out.println("<좌석 선택>");
		System.out.println("[01]~[30]까지 입력하세요! \n");
		System.out.println(" [01]   [02]   [03]   [04]   [05]   [06]   [07]   [08]   [09]   [10]");
		System.out.println(" [11]   [12]   [13]   [14]   [15]   [16]   [17]   [18]   [19]   [20]");
		System.out.println(" [21]   [22]   [23]   [24]   [25]   [26]   [27]   [28]   [29]   [30]");
		System.out.println("");
		
		System.out.print("좌석번호 입력 : ");
		int mySeat = scan.nextInt();
		try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter("seat.txt")));
            writer.printf("%d\n",mySeat);
            writer.close();
        } catch(Exception ex) {
        	System.out.println("문제있음");
        }
	}
	//seat의 기능을 담은 메소드
	public void callSeat() {
		DBSeat ds1 = new DBSeat();
		
		this.seat();
		DBSeat s1 = new DBSeat();
		s1.seat_availability();
//		Part_Time pt1 = new Part_Time();
//		Payment p1 = new Payment();
//		p1.setPayment(pt1.ChooseParttime());
	}
}
