import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	
	static int MOD = 1000000007;
	static int []  cache = new int [101];

	public static void main(String[] args) throws FileNotFoundException {
		
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		int T = sc.nextInt();
		while(T-- > 0 ) {
			
			System.out.println(asymmetric(sc.nextInt()));
			
		}


	}
	
	public static int asymmetric(int width) {
		if(width % 2 == 1)
			return (tiling(width) - tiling(width/2) + MOD) % MOD;
		int ret = tiling(width);
		ret = (ret - tiling(width/2) + MOD) % MOD;
		ret = (ret - tiling(width/2-1) + MOD) % MOD;
		return ret;
		
	}
	
	
	
	public static int tiling(int width) {
		if(width <= 1) return 1;
		
		int ret = cache[width];
		if(ret != -1) return ret;
		return ret = (tiling(width-2) + tiling(width-1)) % MOD;
	}

}
