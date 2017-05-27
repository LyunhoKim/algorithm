import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	
	static double [][] cache = new double [51][101];
	static int [] deg = new int [51];
	static int [][] map;
	static int N, D, P;
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		int C = sc.nextInt();
		
		while(C-- > 0) {
			
			N = sc.nextInt();
			D = sc.nextInt();
			P = sc.nextInt();
			
			map = new int [N][N];
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					map[i][j] = sc.nextInt();
			
			Arrays.fill(deg, 0);
			
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					if(map[i][j] == 1)
						deg[i]++;
			
			for(int i=0; i<cache.length; i++)
				Arrays.fill(cache[i], -1);
			
			
			int T = sc.nextInt();
			for(int i=0; i<T; i++)
				System.out.printf("%.8f ", search3(sc.nextInt(), D));
			
			System.out.println();
		}
	}
	
	
	public static double search3(int here, int days) {
		if(days == 0) return (here == P ? 1.0 : 0.0);
		
		if(cache[here][days] > -0.5) return cache[here][days];
		cache[here][days] = 0.0;
		for(int there=0; there < N; there++)
			if(map[here][there] != 0)
				cache[here][days] += search3(there, days-1) / deg[there];
		
		return cache[here][days];
	}
	
}
