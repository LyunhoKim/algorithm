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
			N = sc.nextInt();
			D = new int [N*2-1][N+1];
			
			int i=0, j=0;
			
			for(i=0; i<N*2-1; i++) {
				for(j=0; j<N; j++) {
					D[i][j] = 0;
				}
			}
			
			D[0][1] = sc.nextInt();
			
			for(i=1; i<N; i++) {
				for(j=0+1; j<=i+1; j++){
					D[i][j] = sc.nextInt();
					D[i][j] = D[i][j] + Math.max(D[i-1][j-1], D[i-1][j]);
				}
			}
			
			for(i=N; i<2*N-1; i++) {
				for(j=1; 2*N-i>j; j++) {
					D[i][j] = sc.nextInt();
					D[i][j] = D[i][j] + Math.max(D[i-1][j], D[i-1][j+1]);
				}
			}
			
			
			System.out.println(D[i-1][j-1]);
					
		}

	}
	

}
