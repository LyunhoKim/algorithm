import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;


public class Main {
	
	static int [] table;
	
	static Hashtable<String, Integer> friendName;

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new FileInputStream("src/testcase.txt"));
//		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		while(T-- > 0) {
			int N = sc.nextInt();  // Number of Friend
			int M = sc.nextInt();  // Number of Food
			
			table = new int [M]; // binary table
			
			// name : binary value
			friendName = new Hashtable<String, Integer>();
			for(int i=0, j=1; i<N; i++, j=j<<1) {
				friendName.put(sc.next(), j);
			}
			
			// table setting
			for(int i=0; i<M; i++) {
				int eatable = sc.nextInt();
				for(int j=0; j<eatable; j++) {
					table[i] |= friendName.get(sc.next());
				}
			}
			// print table
			for(int i=0; i<table.length; i++)
				System.out.println(Integer.toBinaryString(table[i]));
			
			
			for(int i=0; i<M; i++) {
				int min = 0;
				if( (min=findCombination(i)) != -1 ) {
					System.out.println(min);
				}
					
			}
			
		}
	}
	
	
	// n = 조합 
	static private int findCombination(int n) {
		
		
		return -1;
	}
}
