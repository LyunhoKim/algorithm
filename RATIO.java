import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	
	public static long L = 2000000000;

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		int T = sc.nextInt();
		while(T-- > 0) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			System.out.println(neededGames(N, M));
		}
		
		
		
	}
	
	private static int ratio(long b, long a) {
		return (int) ((a * 100) / b);
	}
	private static long neededGames(long games, long won) {
		
		if(ratio(games, won) == ratio(games + L, won + L))
			return -1;
		
		long lo = 0, hi = L;
		
		while(lo + 1 < hi) {
			long mid = (lo + hi) / 2;
			if(ratio(games, won) == ratio(games + mid, won + mid))
				lo = mid;
			else
				hi = mid;
		}
		return hi;
	}

}
