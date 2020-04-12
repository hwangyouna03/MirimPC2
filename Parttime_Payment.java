package GUIDB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class Parttime_Payment extends JFrame {
	
	private JFrame frame;
	JTextField card_payment_field, cash_payment_field;
	JButton Button[] = new JButton[5];
	private JTextField cash_input_field;
	private JTextField cash_change_field;
	private int timemoney;
	private int inputmoney;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parttime_Payment frame = new Parttime_Payment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Parttime_Payment() {
		JPanel Mirimpc= new JPanel();
        setContentPane(Mirimpc);
        
		//프레임 크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(280, 80, 1000, 700);
		
		Mirimpc.setBorder(new EmptyBorder(5, 5, 5, 5));
		Mirimpc.setLayout(null);
		
		JLabel mirimPC = new JLabel("미림PC");
		mirimPC.setFont(new Font("굴림", Font.PLAIN, 30));
		mirimPC.setBounds(14, 12, 109, 49);
		Mirimpc.add(mirimPC);
		
		JPanel panel_parttime = new JPanel();
		panel_parttime.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_parttime.setBackground(UIManager.getColor("Button.background"));
		panel_parttime.setBounds(14, 73, 416, 568);
		Mirimpc.add(panel_parttime);
		panel_parttime.setLayout(null);
		
		JLabel label_parttime = new JLabel("시간제");
		label_parttime.setFont(new Font("굴림", Font.PLAIN, 20));
		label_parttime.setBounds(1, 3, 414, 52);
		panel_parttime.add(label_parttime);
		
		JPanel panel_parttime_ = new JPanel();
		panel_parttime_.setBounds(11, 67, 391, 489);
		panel_parttime_.setLayout(new GridLayout(6,1,5,20));
		panel_parttime.add(panel_parttime_);
		
		String btn_parttime[] = {
				"01:00                                1000\uC6D0",
				"02:10                                2000\uC6D0",
				"03:20                                3000\uC6D0",
				"06:00                                5000\uC6D0",
				"12:00                               10000\uC6D0"
		};
		for(int i=0; i<=4; i++) {
			Button[i] = new JButton(btn_parttime[i]);
			panel_parttime_.add(Button[i]);
			Button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String input = e.getActionCommand();
					if(input.equals("01:00                                1000\uC6D0")) {
						card_payment_field.setText("1000");
						cash_payment_field.setText("1000");
						timemoney = 1000;
					}else if(input.equals("02:10                                2000\uC6D0")) {
						card_payment_field.setText("2000");
						cash_payment_field.setText("2000");
						timemoney = 2000;
					}else if(input.equals("03:20                                3000\uC6D0")) {
						card_payment_field.setText("3000");
						cash_payment_field.setText("3000");
						timemoney = 3000;
					}else if(input.equals("06:00                                5000\uC6D0")) {
						card_payment_field.setText("5000");
						cash_payment_field.setText("5000");
						timemoney = 5000;
					}else if(input.equals("12:00                               10000\uC6D0")) {
						card_payment_field.setText("10000");
						cash_payment_field.setText("10000");
						timemoney = 10000;
					}
				}
			});
			Button[i].setFont(new Font("굴림",Font.PLAIN,20));	
		}
		
		JPanel panel_payment = new JPanel();
		panel_payment.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_payment.setBounds(466, 73, 502, 568);
		Mirimpc.add(panel_payment);
		panel_payment.setLayout(null);
		
		JLabel label_payment = new JLabel("\uACB0\uC81C");
		label_payment.setFont(new Font("굴림", Font.PLAIN, 20));
		label_payment.setBounds(14, 12, 62, 18);
		panel_payment.add(label_payment);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(38, 84, 416, 435);
		panel_payment.add(tabbedPane);
		
		JPanel card = new JPanel();
		tabbedPane.addTab("카드", null, card, null);
		card.setLayout(null);
		
		JLabel card_label1 = new JLabel("\uACB0\uC81C \uAE08\uC561\uC740");
		card_label1.setFont(new Font("굴림", Font.PLAIN, 18));
		card_label1.setBounds(161, 74, 102, 31);
		card.add(card_label1);
		
		JLabel card_label2 = new JLabel("\uC6D0 \uC785\uB2C8\uB2E4.");
		card_label2.setFont(new Font("굴림", Font.PLAIN, 18));
		card_label2.setBounds(232, 146, 94, 31);
		card.add(card_label2);
		
		JButton card_confirm_btn = new JButton("\uD655\uC778");
		card_confirm_btn.setFont(new Font("굴림", Font.PLAIN, 20));
		card_confirm_btn.setBounds(81, 320, 105, 44);
		card_confirm_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new finalWindow();
				dispose(); //parttime_payment 화면 없애기
			}
			
		});
		card.add(card_confirm_btn);
		
		JButton card_cancel_btn = new JButton("\uCDE8\uC18C");
		card_cancel_btn.setFont(new Font("굴림", Font.PLAIN, 20));
		card_cancel_btn.setBounds(244, 320, 105, 44);
		card_cancel_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//처음 화면으로 넘어가기
			}
		});
		card.add(card_cancel_btn);
		
		card_payment_field = new JTextField("",15);
		card_payment_field.setBackground(Color.WHITE);
		card_payment_field.setHorizontalAlignment(SwingConstants.CENTER);
		card_payment_field.setEditable(false);
		card_payment_field.setFont(new Font("굴림", Font.PLAIN, 20));
		card_payment_field.setBounds(103, 133, 118, 44);
		card.add(card_payment_field);
		
		JPanel cash = new JPanel();
		tabbedPane.addTab("현금", null, cash, null);
		cash.setLayout(null);
		
		JLabel cash_label1 = new JLabel("\uACB0\uC81C\uAE08\uC561");
		cash_label1.setFont(new Font("굴림", Font.PLAIN, 20));
		cash_label1.setBounds(43, 51, 89, 24);
		cash.add(cash_label1);
		
		cash_payment_field = new JTextField("",15);
		cash_payment_field.setBackground(Color.WHITE);
		cash_payment_field.setHorizontalAlignment(SwingConstants.CENTER);
		cash_payment_field.setEditable(false);
		cash_payment_field.setFont(new Font("굴림", Font.PLAIN, 20));
		cash_payment_field.setBounds(133, 39, 118, 44);
		cash.add(cash_payment_field);
		
		JLabel cash_label2 = new JLabel("\uD22C\uC785\uAE08\uC561");
		cash_label2.setFont(new Font("굴림", Font.PLAIN, 20));
		cash_label2.setBounds(43, 123, 89, 24);
		cash.add(cash_label2);
		
		cash_input_field = new JTextField("", 15);
		cash_input_field.setHorizontalAlignment(SwingConstants.CENTER);
		cash_input_field.setFont(new Font("굴림", Font.PLAIN, 20));
		cash_input_field.setBackground(Color.WHITE);
		cash_input_field.setBounds(133, 108, 118, 44);
		cash_input_field.addKeyListener(new KeyAdapter() {
			String s= "0";
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField)ke.getSource();
				if(src.getText().length() >= 6) ke.consume();
			}
			public void keyReleased(KeyEvent ke) {
				try {
					JTextField src = (JTextField)ke.getSource();
					if(src.getText().length() >= 6) {
						ke.consume(); 
					}else {
						String s0 = ke.getKeyText(ke.getKeyCode());
						int s1 = ke.getKeyCode();
						
						if(s1>47 && s1<58) {
							s += s0;
							inputmoney = Integer.parseInt(s);
						}else {
							s = "0";
							cash_input_field.setText("");
						}
					}
				}catch (Exception e){
					System.out.println(e);
					//새창 띄우기
					//dispose();
				}
			}			
		});
		cash.add(cash_input_field);
		
		JLabel cash_label3 = new JLabel("\uAC70\uC2A4\uB984\uB3C8");
		cash_label3.setFont(new Font("굴림", Font.PLAIN, 20));
		cash_label3.setBounds(43, 183, 89, 24);
		cash.add(cash_label3);
		
		cash_change_field = new JTextField("", 15);
		cash_change_field.setHorizontalAlignment(SwingConstants.CENTER);
		cash_change_field.setFont(new Font("굴림", Font.PLAIN, 13));
		cash_change_field.setEditable(false);
		cash_change_field.setBackground(Color.WHITE);
		cash_change_field.setBounds(133, 173, 118, 44);
		cash.add(cash_change_field);
		
		JButton cash_confirm_btn = new JButton("\uD655\uC778");
		cash_confirm_btn.setFont(new Font("굴림", Font.PLAIN, 20));
		cash_confirm_btn.setBounds(43, 335, 105, 44);
		cash.add(cash_confirm_btn);
		cash_confirm_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PaymentExcode px1 = new PaymentExcode();
				int resultmoney = px1.Change(timemoney, inputmoney);
				if(resultmoney >= 0) {
					cash_change_field.setText(resultmoney + "");
					cash_change_field.setFont(new Font("굴림", Font.PLAIN, 20));
					
					timer2();
				}else {
					cash_change_field.setFont(new Font("굴림", Font.PLAIN, 13));
					cash_change_field.setText("잔액이 부족합니다.");							
				}			
			}
		});
		
		JButton cash_cancel_btn = new JButton("\uCDE8\uC18C");
		cash_cancel_btn.setFont(new Font("굴림", Font.PLAIN, 20));
		cash_cancel_btn.setBounds(263, 335, 105, 44);
		cash_cancel_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//처음화면으로 돌아가기
			}
		});
		cash.add(cash_cancel_btn);
	}
	public void timer2() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				new finalWindow();
				dispose(); //parttime_payment 화면 없애기
			}
		};
		timer.schedule(timerTask, 2000);
	}
}

class finalWindow extends JFrame {
	finalWindow() {
        setTitle("결제 완료");
        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("결제 완료");
        NewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
        
        NewWindowContainer.add(NewLabel);
        
        setBounds(280, 80, 300,100);
        setResizable(false);
        setVisible(true);
        
        timer();
    }
	public void timer() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				dispose(); //결제완료창 닫기
				//처음 화면 열기
			}
		};
		timer.schedule(timerTask, 5000);
	}
}