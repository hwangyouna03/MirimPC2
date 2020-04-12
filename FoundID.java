package GUIDB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern; //패스워드나 이메일 확인같은 문자열 제한 조건을 나타내기 위해 사용


public class FoundID extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private PrintWriter writer;
	
	 String email ; //이메일
	 String number; //전화번호
	
	
	static final String USERNAME = "root";
	private static final String PASSWORD = "hwangyouna0415!";
	private static final String URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";

	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoundID frame = new FoundID();
					frame.setVisible(true);
						
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Got an exception!");
				    System.err.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FoundID() {
		
		setTitle("아이디찾기창");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel label = new JLabel("아이디 찾기");
		label.setBounds(515, 107, 248, 63);
		label.setFont(new Font("맑은고딕", Font.BOLD, 45));
		panel.add(label);
		getContentPane().add(panel);
		
		JLabel Label = new JLabel("전화번호");
		Label.setBounds(293, 303, 156, 48);
		Label.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		panel.add(Label);
		
		JLabel Label2 = new JLabel("이메일");
		Label2.setBounds(293, 457, 118, 33);
		Label2.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		panel.add(Label2);
		
		textField = new JTextField(); //전화번호
		textField.setBounds(463, 303, 397, 54);
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		panel.add(textField);
		textField.setColumns(10);
		
		JTextField textField1 = new JTextField(); //이메일
		textField1.setBounds(463, 449, 397, 54);
		textField1.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		panel.add(textField1);
		textField1.setColumns(10);
		
		
		
		
		
		JButton btn1 = new JButton("확인"); //전화번호 확인 버튼
		btn1.setBounds(897, 282, 128, 97);
		btn1.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		panel.add(btn1);
		btn1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		FoundidDB f1 = new FoundidDB();
        		String id = f1.foundID(textField.getText());
        		
        		
        		Fdwindow fd = new Fdwindow(id);
        		fd.main(id);
        		
        		new Memberwindow();
        		dispose();
        		
        	
        		
        	}
        });
		
					
		JButton btn2 = new JButton("확인"); //이메일 확인 버튼
		btn2.setBounds(897, 440, 128, 97);
		btn2.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		panel.add(btn2);
		btn2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	FoundidDB f1 = new FoundidDB();
        		String id = f1.foundID2(textField1.getText());
        		
        		Fdwindow fd = new Fdwindow(id);
        		fd.main(id);
        		
        		new Memberwindow();
        		dispose();
        		
	        	}
	        });
		
		
		JLabel Label3 = new JLabel("※ 전화번호 또는 이메일을 입력하시면 아이디 찾기 기능을 이용하실 수 있습니다.※");
		Label3.setBounds(349, 182, 615, 26);
		Label3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		Label3.setForeground(Color.red);
		panel.add(Label3);
		
		JButton btnback = new JButton("돌아가기");
		btnback.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnback.setBounds(542, 637, 185, 56);
		panel.add(btnback);
		btnback.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new main();
        		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		setBounds(280, 80, 1300, 850);
        	}
        });
		
		
	
		 setBounds(280, 80, 1300, 850);
	     setVisible(true);
	     setResizable(false);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 윈도우를 닫으면 프로그램 종료
		
			
	}
	
}
