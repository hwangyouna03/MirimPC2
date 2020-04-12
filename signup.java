package GUIDB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import GUIDB.signupDB;

import java.awt.FlowLayout;
import javax.swing.JButton;

//DB
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern; //패스워드나 이메일 확인같은 문자열 제한 조건을 나타내기 위해 사용


public class signup extends JFrame {
	private JPanel contentPane;
	private Connection conn;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hwangyouna0415!";
    private static final String URL = "jdbc:mysql://localhost:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";

  
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	
	public signup() {
		
		setTitle("회원가입창");
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 12, 1294, 815);
		panel.setLayout(null);
		
		JLabel label = new JLabel("회원가입");
		label.setBounds(581, 41, 211, 75);
		label.setFont(new Font("굴림", Font.BOLD, 45));
		panel.add(label);
		
		JLabel name = new JLabel("이름");
		name.setBounds(376, 157, 76, 63);
		name.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		
		JLabel id = new JLabel("아이디");
		id.setBounds(376, 232, 98, 63);
		id.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		
		JLabel password = new JLabel("비밀번호");
		password.setBounds(346, 307, 106, 63);
		password.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		
		JLabel c_password = new JLabel("비밀번호 확인");
		c_password.setBounds(305, 382, 169, 63);
		c_password.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		
		
		JLabel number = new JLabel("전화번호");
		number.setBounds(346, 495, 128, 63);
		number.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		
		JLabel email = new JLabel("이메일");
		email.setBounds(356, 570, 106, 63);
		email.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		
		
		//텍스트박스
		JTextField txtname = new JTextField(3);
		txtname.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		JTextField txtid = new JTextField(10);
		txtid.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		JPasswordField txtpass = new JPasswordField(10);
		txtpass.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		JPasswordField txtc_pass = new JPasswordField(10);
		txtc_pass.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		JTextField txtnum = new JTextField(10);
		txtnum.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		JTextField txtemail = new JTextField(10);
		txtemail.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		
		//위치
		txtname.setBounds(505,166,344,56);
		txtid.setBounds(505,245, 344,49);
		txtpass.setBounds(505,320, 344,49);
		txtc_pass.setBounds(505, 395, 344,49);
		txtnum.setBounds(505,508, 344,49);
		txtemail.setBounds(505,579, 344,49);
		
		JButton signbtn = new JButton("완료");
		signbtn.setBounds(555, 692, 246, 75);
		signbtn.setFont(new Font("맑은 고딕", Font.PLAIN, 28));
		signbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(txtpass.getText().equals(txtc_pass.getText())) {
        			
        			if(txtnum.getText().equals("") && txtemail.getText().equals("")) {
        				JOptionPane.showMessageDialog(null, "전화번호 또는 이메일을 입력해주세요");
        			}else {
        				Signup1 s1 = new Signup1();
                		s1.insertProfile(txtname.getText(), txtid.getText(),txtpass.getText(),txtnum.getText(),txtemail.getText());
                		JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다^^");
                		new Memberwindow();
                		dispose();
        			}
//        			Signup1 s1 = new Signup1();
//            		s1.insertProfile(txtname.getText(), txtid.getText(),txtpass.getText(),txtnum.getText(),txtemail.getText());
            		
        		}else {
        			JOptionPane.showMessageDialog(null, "일치하지 않습니다. 다시 확인해주십시오.");
        		}
        		
        		
        		
        	}
				 
				
			
			
	});
        		
        	
	
		
		
		JLabel Label2 = new JLabel("※전화번호 또는 이메일을 입력해주십시오.※");
		Label2.setBounds(521, 478, 309, 18);
		Label2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		Label2.setForeground(Color.red);
		
		
		JButton btnback = new JButton("돌아가기");
		btnback.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnback.setBounds(335, 704, 185, 56);
		panel.add(btnback);
		btnback.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new main();
        		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		setBounds(280, 80, 1300, 850);
        	}
        });
		
		//판넬에 추가
		panel.add(name);
		panel.add(txtname);
		panel.add(id);
		panel.add(txtid);
		panel.add(password);
		panel.add(txtpass);
		panel.add(c_password);
		panel.add(txtc_pass);
		panel.add(number);
		panel.add(txtnum);
		panel.add(email);
		panel.add(txtemail);
		panel.add(signbtn);
		panel.add(Label2);
		
		//프레임 크기 및 위치
        setBounds(280, 80, 1300, 850);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 윈도우를 닫으면 프로그램 종료
		setContentPane(panel);
		
		

		
		
	}
	  
	
}
