import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	static final int INF = 987654321;
	static final int MAXN = 12;
	static int [] prerequisite;
	static int [] classes = new int [10];
	static int [][] cache;
	static int n, k, m, l;
	
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		int T = sc.nextInt();
		
		while(T-- > 0) {
			n = sc.nextInt();
			k = sc.nextInt();
			m = sc.nextInt();
			l = sc.nextInt();
			
			prerequisite = new int [n];
			cache = new int [10][n];
			for(int i=0; i<n; i++) {
				int prerequisiteNum = sc.nextInt();
				for(int j=0; j<prerequisiteNum; j++) {
					prerequisite[i] |= (1 << sc.nextInt());
				}
			}
			
			for(int i=0; i<n; i++) {
				int classesNum = sc.nextInt();
				for(int j=0; j<classesNum; j++) {
					classes[i] |= (1 << sc.nextInt());
				}
			}
			
			int res = graduate(0, 0);
			if( res == INF)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(res);
		}

	}
	
	public static int bitCount(int n) {
		return Integer.bitCount(n);
	}
	
	public static int graduate(int semester, int taken) {
		if(bitCount(taken) >=k ) return 0;
		if(semester == m) return INF;
		
		if(cache[semester][taken] != -1) 
			return cache[semester][taken];
		
		cache[semester][taken] = INF;
		
		int canTake = (classes[semester] & ~taken);
		
		for(int i=0; i<n; i++)
			if( ((canTake & (1 << i)) != 0) && ((taken & prerequisite[i]) != prerequisite[i]))
				canTake &= ~(1 << i);
		
		for(int take = canTake; take>0; take = ((take-1) & canTake)) {
			if(bitCount(take) > l) continue;
			cache[semester][taken] = Math.min(cache[semester][taken], graduate(semester+1, taken | take) + 1);
		}
		
		cache[semester][taken] = Math.min(cache[semester][taken], graduate(semester+1, taken));
		return cache[semester][taken];
	}
	
	

}
