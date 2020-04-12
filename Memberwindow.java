package GUIDB;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern; //패스워드나 이메일 확인같은 문자열 제한 조건을 나타내기 위해 사용

public class Memberwindow extends JFrame {
	
	
	private PrintWriter writer;

	
	private JPanel contentPane;
	private Connection conn;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hwangyouna0415!";
    private static final String URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";


	protected static final AbstractButton ID = null;


	protected static final AbstractButton PW = null;
    
   
   
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Memberwindow frame = new Memberwindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Memberwindow() {
			Login l1 = new Login();

			setTitle("회원창");
			getContentPane().setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBounds(29, 12, 1294, 815);

			JTextField txtID = new JTextField(10);
			JPasswordField txtpass = new JPasswordField(10);
			
			JLabel label = new JLabel("ID: ");
			label.setBounds(372, 239, 72, 63);
			JLabel password = new JLabel("Password: ");
			password.setBounds(236, 383, 225, 63);
			JButton logbtn = new JButton("Log In");
			logbtn.setBounds(846, 247, 159, 199);
			
			//폰트, 위치& 크기설정 (다시해야함)
			label.setFont(new Font("맑은 고딕", Font.PLAIN, 45));
			password.setFont(new Font("맑은고딕", Font.PLAIN, 45));
			logbtn.setFont(new Font("맑은고딕", Font.PLAIN, 35));
			txtID.setBounds(502,239,292,56);
			txtID.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
			txtpass.setBounds(502,393, 292,49);
			txtpass.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
			panel.setLayout(null);
			
			
			
			//라벨, 텍스트 박스 추가
			panel.add(label);
			panel.add(txtID);
			panel.add(password);
			panel.add(txtpass);
			panel.add(logbtn);
			getContentPane().add(panel);
			
			JLabel label_1 = new JLabel("로그인");
			label_1.setBounds(576, 52, 185, 75);
			panel.add(label_1);
			label_1.setFont(new Font("굴림", Font.BOLD, 45));
			
			JButton btnback = new JButton("돌아가기");
			btnback.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			btnback.setBounds(680, 569, 185, 56);
			panel.add(btnback);
			btnback.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new main();
					dispose();
				}
			});
			
			JButton btnfound = new JButton("아이디 찾기");
			btnfound.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			
				
			btnfound.setBounds(407, 569, 176, 56);
			panel.add(btnfound);
			btnfound.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new FoundID();
	        		dispose();
	        		setBounds(280, 80, 1300, 850);
	        	}
	        });
			
			//로그인 기능
			logbtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					LoginDB li = new LoginDB();
					int num = li.letLogin(txtID.getText(), txtpass.getText());
					
					if(num == 0) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다!");
						Seat s1 = new Seat();
						s1.main(null);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "잘못 입력하셨습니다.");
					}
					
					
					
				}
				
		});
			
			
			
			//프레임 크기 및 위치
	        setBounds(280, 80, 1300, 850);
	        setVisible(true);
	        setResizable(false);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 윈도우를 닫으면 프로그램 종료
			
	       
	}
	
}

