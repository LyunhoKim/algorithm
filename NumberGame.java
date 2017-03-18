import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	
	static int [] board;
	static int [][] cache;

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		int T = sc.nextInt();
		
		while(T-- > 0) {
			int N = sc.nextInt();
			board = new int [50];
			cache = new int [50][50];
			
			for(int i=0; i<N; i++)
				board[i] = sc.nextInt();
			
			for(int i=0; i<50; i++)
				Arrays.fill(cache[i], -987654321);
			
			System.out.println(play(0, N-1));
		}
		
	}
	
	public static int play(int l, int r) {
		if(l > r) return 0;
		if(cache[l][r] != -987654321) return cache[l][r];
		
		cache[l][r] = Math.max(board[l] - play(l + 1, r), 
							   board[r] - play(l, r - 1));
		
		if(r - l + 1 >= 2) {
			cache[l][r] = Math.max(cache[l][r], -play(l + 2, r));
			cache[l][r] = Math.max(cache[l][r], -play(l, r - 2));
		}
		
		return cache[l][r];
	}

}
