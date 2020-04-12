package GUIDB;

import java.util.Scanner;

public class PaymentExcode {
	private int card; 
	private int money;
	
	Scanner scan = new Scanner(System.in);
		
	//돈의종류
	private int inputamount;		//투입금액	 -> 넣은돈 
	private int returnamount;		//반환금액	 -> 거스름돈
	
	//getter and setter
	public void setCard() {
		this.card = card;
	}
	public int getCard() {
		return card;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoney() {
		return money;
	}

	//종료
	boolean payment = true;
	
	//결제수단
//	public void setPayment(int result) {
//		
//		System.out.println("---------------------------------------------");
//		System.out.println("<결제>");
//		System.out.println("1.카드    2.현금   3.취소");
//		System.out.print("번호입력 : ");
//		int num = scan.nextInt();		//결제 수단 입력
//		switch(num) {		
//			case 1:
//				setCardpayment(result);		//카드
//				break;
//			case 2:
//				setMoneypayment(result);	//현금
//				break;
//			case 3:
//				Pcroom p1 = new Pcroom();   //처음으로
//				p1.main(null);   
//				break;
//			default:
//				System.out.println("다시입력하세요"); //다시입력
//				setPayment(result);
//				break;
//		}
//		PaymentExcode.Exit();			//다음구문으로 이동
//			payment = false;
//	}
	
//	//카드결제 메서드	
//	public void setCardpayment(int result) {
//		System.out.println("---------------------------------------------");
//		System.out.println("<카드결제>");	
//		System.out.println("1.결제  2.취소");
//		System.out.println("결제 금액 " + result + "원 입니다");		//결제금액		
//		System.out.print("번호를 입력하세요 : ");		
//		int cardnum = scan.nextInt();			//카드결제 입력
//		switch(cardnum) {
//			case 1:
//				setcountdown(); break;
//			case 2:	
//				Pcroom p1 = new Pcroom();
//				p1.main(null);   break;
//			default:
//				System.out.println("다시입력하세요");	//다음구문으로이동
//				break;
//		}
//	}
	
	//현금결제 메서드
	public void setMoneypayment(int resultmoney) {
		
//		System.out.println("---------------------------------------------");
//		System.out.println("<현금결제>");
//		System.out.println("1.결제  2.취소");
//		System.out.print("번호를 입력하세요 : ");
//		int cashpayment = scan.nextInt();		//현금결제 입력
//		
//		switch(cashpayment) {
//			case 1:
//				System.out.println("");
//				System.out.print("투입금액을 입력하세요 : ");		
//				inputamount = scan.nextInt();		//넣은돈입력
//				System.out.println("");
//				
//				if(inputamount >= result) {	
//					System.out.println("결제 금액은" + result + "원 입니다");	//내야할돈
//					setChange(result);
//					System.out.println(toString());	//반환금액->거스름돈
//					setcountdown();
//				}else
//					System.out.println("잔액이 부족합니다");
//				
//				setMoneypayment(result);
//				break;
////			case 2:
////				Pcroom p1 = new Pcroom();
////				p1.main(null);   break;
//			default:
//				System.out.println("다시입력하세요");	//다음구문으로 이동
//				break;
//		}
//		PaymentExcode.Exit();
//		payment = false;
	}
	
	//거스름돈 메서드
	public int Change(int timemoney, int inputmoney) {
		int resultmoney = inputmoney-timemoney; //거스름돈
		
		return resultmoney;
	}
//	public void setChange(int result) {
//		returnamount = inputamount - result;	//거스름돈 = 입력한값 - 결제금액
//	}
	//반환금액 오버라이딩
	@Override
	public String toString() {
		return "거스름돈은 " + returnamount + "원 입니다";	
	}
	
	public void setcountdown() {
		
	//카운트다운
		System.out.println("\n<결제>");
		System.out.println("*5초뒤 자동으로 초기화면으로 돌아갑니다");
			for(int i=5; i>=0; i--) {
				
				for(long j=0; j<=2099999999; j++) {
					
				}//for
				
				for(long j=0; j<=2099999999; j++) {
					
				}//for
				
				System.out.println(i);
			}//for i
			
			System.out.println("결제가 완료되었습니다. 감사합니다   ");
			System.out.println("");
//			Pcroom a = new Pcroom();	//메인 객체 불러옴
//			a.main(null);	//메인화면으로
	}
			
	//종료
	static void Exit() {
		System.exit(0);
	}

}