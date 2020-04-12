package GUIDB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Seat extends JFrame {

	private static Login_nomember exl1;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seat frame = new Seat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Seat() {
		//this.setframe();
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 86, 891, 512);
		Mirimpc.add(panel);
		panel.setLayout(new GridLayout(3,10,10,50));
				
		JButton Button[] = new JButton[30];
		String btnValue[] = new String[30];
		for(int i =0; i<30; i++) {
			btnValue[i] = "" + (i+1);
			DBSeat s1 = new DBSeat();
			int disnum = s1.remaining_seat(i+1);  //DB에서 자리가 있는지 없는지 확인
			if(disnum == 0) {
				btnValue[i] = "X";
			}
			int num = i;
			//버튼에 값 대입
			Button[i] = new JButton(btnValue[i]);
			panel.add(Button[i]);
			Button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int actnum = actionmethod(Button, num);
					
					switch(actnum) {
					case 0 : Parttime_Payment pp1 = new Parttime_Payment();
							 pp1.main(null);
							 dispose(); 
							 break;
					case 1 : break;
					}
				}
			});
			Button[i].setBackground(new Color(175,175,175));
			Button[i].setFont(new Font("굴림",Font.BOLD,20));
			Button[i].setForeground(Color.WHITE);
		}
	}
	
	public int actionmethod(JButton[] Button, int num) {
		int renum = 0;
		
			if(Button[num].getText().equals("X")) {
				renum = 1; 
			}else {
				DBSeat s1 = new DBSeat();
				s1.insertDB(num+1);
				renum = 0;
			}
		
		return renum;
	}
}
