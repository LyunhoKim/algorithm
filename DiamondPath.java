import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

	static int [][] D;
	static int N;
	public static void main(String[] args) throws FileNotFoundException {
		
		
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
		
		int T = sc.nextInt();
		
		while(T-- > 0) {
			int i=0, j=0;
			N = sc.nextInt();
			D = new int [N*2-1][N+1]; // 0번째 열 비워두기 위해 
			
			// 꼭대기 값 
			D[0][1] = sc.nextInt();
			
			// 상단 
			for(i=1; i<N; i++) 
				for(j=0+1; j<=i+1; j++)
					D[i][j] = sc.nextInt() + Math.max(D[i-1][j-1], D[i-1][j]);
			
			// 하단 
			for(i=N; i<2*N-1; i++) 
				for(j=1; 2*N-i>j; j++) 
					D[i][j] = sc.nextInt() + Math.max(D[i-1][j], D[i-1][j+1]);
			
			System.out.println(D[i-1][j-1]);
					
		}

	}
	

}
