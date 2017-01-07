import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		int T;	// 케이스 
		int P;	// 거스름 돈  
		int K;	// 동전 개수 
		int [] D;	// 카운트 임시 저장 배열 
		Scanner sc = new Scanner(System.in);
		
		
		T = sc.nextInt();
		
		while(T-- != 0) {
			D = new int [5000];
			D[0] = 1;
			P = sc.nextInt();
			K = sc.nextInt();
			for(int i=0; i<K; i++) {
				int x = sc.nextInt();
				
				for(int j=x; j<=P; j+=1) {
					D[j] = (D[j] + D[j-x])%1000000007;
				}
			}
			System.out.println(D[P]);
		}
	}
}
