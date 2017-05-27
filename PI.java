import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	
	static int [] cache = new int [10002];
	static int [] PI;
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		String t = sc.nextLine();
		int T = Integer.parseInt(t);
		
		while(T-- > 0) {
			Arrays.fill(cache, -1);
			String raw = sc.nextLine();
			PI = new int [raw.length()];
//			System.out.println(raw);
			
			for(int i=0; i<raw.length(); i++) 
				PI[i] = raw.charAt(i) - '0';
			
//			for(int i=0; i<PI.length; i++)
//				System.out.printf("%d ", PI[i]);
			
			
			System.out.println(memorize(0));
		}
	}
	
	public static int memorize(int begin) {
		
		if(begin == PI.length) return 0;
		
		if(cache[begin] != -1) return cache[begin];
		
		cache[begin] = 987654321;
		for(int L=3; L<=5; L++) {
			if(begin+L <= PI.length)
				cache[begin] = Math.min(cache[begin], memorize(begin+L) + classify(begin, begin + L - 1));
		}
		return cache[begin];
	}
	
	public static int classify(int s, int e) {
		
		// 난이도 1인지 확인 
		boolean isDiff1 = true;
		for(int i=s+1; i<=e; i++) {
			if(PI[s] - PI[i] != 0) {
				isDiff1 = false;
				break;
			}
		}
		if(isDiff1) return 1;
		
		// 등차수열인지 확인 
		boolean isDiff2 = true;
		for(int i=s+1; i<=e; i++) {
			if(PI[i] - PI[i-1] != PI[s+1] - PI[s]) {
				isDiff2 = false;
				break;
			}
		}
		
		// 난이도 2인지 확인 
		if(isDiff2 && Math.abs(PI[s+1] - PI[s]) == 1)
			return 2;
		
		
		// 두수가 번갈아 나오는지 확인
		boolean isDiff3 = true;
		for(int i=s+2; i<=e; i++) {
			if(PI[i] != PI[i-2]) {
				isDiff3 = false;
				break;
			}
		}
		
		if(isDiff3) return 4;
		if(isDiff2) return 5;
		return 10;
	}

}
