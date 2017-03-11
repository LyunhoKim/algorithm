import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	
	static long NEGINF = 0;
	static int n, m;
	static int [] A = new int [100];
	static int [] B = new int [100];
	static int [][] cache = new int [101][101];
	
	

	public static void main(String[] args) throws FileNotFoundException {
		
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		int T = sc.nextInt();
		while(T-- > 0 ) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			for(int i=0; i<n; i++)
				A[i] = sc.nextInt();
			for(int i=0; i<m; i++)
				B[i] = sc.nextInt();
			
			System.out.println(jlis(0,0));
			
			
		}
		

	}
	
	public static int jlis(int indexA , int indexB) {
		
		int ret = cache[indexA+1][indexB+1];
		if(ret != -1) return ret;
		
		ret = 2;
		long a = (indexA == -1 ? NEGINF : A[indexA]);
		long b = (indexB == -1 ? NEGINF : B[indexB]);
		long maxElement = Math.max(a, b);
		
		for(int nextA = indexA + 1; nextA < n; nextA++)
			if(maxElement < A[nextA])
				ret = Math.max(ret, jlis(nextA, indexB) + 1);
		
		for(int nextB = indexB + 1; nextB < m; nextB++)
			if(maxElement < B[nextB])
				ret = Math.max(ret, jlis(indexA, nextB) + 1);
				
		return ret;
		
	}

}
