
public class Main {

	public static void main(String[] args) {
		
		System.out.println(payment(1000000, 12, 3.0));
		
	}
	
	
	
	private static double balance(double amount, int duration, double rates, double monthlyPayment) {
		double balance = amount;
		
		for(int i=0; i<duration; i++) {
			balance = balance * rates/12/100 - monthlyPayment;
		}
		
		return balance;
	}
	
	private static double payment(double amount, int duration, double rates) {
		double lo = 0, hi = amount * (1.0 + (rates / 12.0) /100.0);
		
		for(int i=0; i<100; i++) {
			double mid = (lo + hi) / 2.0;
			if(balance(amount, duration, rates, mid) <= 0)
				hi = mid;
			else 
				lo = mid;
		}
		
		return hi;
	}

}
