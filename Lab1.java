import java.awt.DefaultFocusTraversalPolicy;
import java.util.Scanner;

class Bank{
	private int balace;

	public int getBalace() {
		return balace;
	}

	public void setBalace(int balace) {
		this.balace = balace;
	}
	
}
class Deposit extends Thread{
	private Bank bank;
	public Deposit(Bank bank) {
		this.bank = bank;
		
	}
	@Override
	public void run() {
		for (int  i=0; i<10;i++){
			System.out.println("in Deposit for " + i + " current balance is " + bank.getBalace());
			synchronized (bank) {
				bank.setBalace(bank.getBalace()+1);
				if (bank.getBalace() > 0 ){
					System.out.println("Notifying bank");
					bank.notify();
				}
			}
			try{Thread.sleep((int)(Math.random()*1000));}catch(Exception e){}
		}
		System.out.println("in Deposit Final   current balance is " + bank.getBalace());
	}
	
}
class Widraw extends Thread{
	private Bank bank;
	public Widraw(Bank bank) {
		this.bank = bank;
	}
	@Override
	public void run() {
		for (int  i=0; i<10;i++){

			System.out.println("in Widraw for " + i + " current balance is " + bank.getBalace());
			synchronized (bank) {

					if (bank.getBalace() <= 0){
					System.out.println("-------------cant continue widraw, balance zero");
					try {
							bank.wait(6000);
						}
					 catch (InterruptedException e) {
						System.out.println("Exception in wait " + e);
					}
					}
					bank.setBalace(bank.getBalace()-1);
			}
			try{Thread.sleep((int)(Math.random()*1000));}catch(Exception e){}
		}
		System.out.println("in Widraw Final   current balance is " + bank.getBalace());
	}
}
	public class Lab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a number to continue..");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();

		
		
		Bank bank = new Bank();
		Deposit deposit = new Deposit(bank);
		Widraw widraw = new Widraw(bank);
		deposit.start();
		widraw.start();
		
	}

}
